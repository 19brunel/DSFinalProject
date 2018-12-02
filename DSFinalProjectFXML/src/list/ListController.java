package list;

import java.net.URL;
import java.util.ResourceBundle;

import account.AccountController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import login.LoginController;
import login.LoginWrapper;
import model.Filter;
import model.Restaurant;
import model.User;
import model.arraySortedList.ArraySortedList;
import start.StartController;
import start.StartWrapper;

public class ListController implements Initializable {
	private final Stage thisStage;
	private User user;
	private ActionEvent event;
	protected ArraySortedList<User> userDB;
	protected ArraySortedList<Restaurant> restaurantDB;
	protected Filter filter;
	protected ListWrapper wrap;
	protected int filterValue;
	@FXML
	private ListView restaurantList;
	@FXML
	private ChoiceBox cuisine;
	@FXML
	private ChoiceBox dinning;
	@FXML
	private Button subtract;
	@FXML
	private ImageView rating;
	@FXML
	private Button add;
	@FXML
	private TextField minPrice;
	@FXML
	private HBox ratingButtons;
	@FXML
	private TextField search;

	public ListController(ListWrapper wrap) {
		this.wrap = wrap;
		user = wrap.getUser();
		event = wrap.getEvent();
		userDB = wrap.getUserDB();
		filter = wrap.getFilter();
		restaurantDB = wrap.getRestaurantDB();
		restaurantList = new ListView<Restaurant>();
		restaurantList.setStyle("-fx-background-insets: 0;");
		thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Parent root = null;
		// System.out.println("List: Trying to load scene.");
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/list/list.fxml"));
			loader.setController(this);
			root = loader.load();
			root.getStylesheets().add("/application/application.css");
			thisStage.setScene(new Scene(root));
		} catch (Exception e) {
			System.out.println(e);
		}
		ObservableList<Restaurant> restList = FXCollections.observableArrayList();
		restaurantDB.reset();
		Restaurant curRest = null;
		boolean meetsFilter = true;
		for (int x = 0; x < restaurantDB.size(); x++) {
			meetsFilter = true;
			curRest = restaurantDB.getNext();
			if (filter.getUseFilter()) {
				if(!curRest.getName().toLowerCase().contains(filter.getSearch().toLowerCase())) {
					System.out.println("False because search.");
					meetsFilter=false;
				}
				if(filter.getDinning().equals("") || filter.getDinning().equals("None") || curRest.getDinningType().equals(filter.getDinning())) {
				}else {
					System.out.println(curRest.getName()+" False because dinning.");
					meetsFilter = false;
				}
				if(filter.getCuisine().equals("") || filter.getCuisine().equals("None")|| curRest.getCuisineType().equals(filter.getCuisine())) {
					System.out.println(curRest.getCuisineType()+"-"+filter.getCuisine());
				}else {
					System.out.println(curRest.getName()+" False because cuisine.");
					System.out.println(curRest.getCuisineType()+"-"+filter.getCuisine());
					meetsFilter = false;
				}
				if (filter.getRating() > 0 && filter.getRating() < 6) {
					if (filter.getRating() >= curRest.getAvgRating()) {
						meetsFilter = false;
					} else {
						System.out.println("False because rating.");
					}
				}
				if (filter.getMinPrice() > 0) {
					if (filter.getMinPrice() >= curRest.getPriceRange().getMin()) {
						System.out.println("False because min price.");
						meetsFilter = false;
					} else {

					}
				}
			} else {
				meetsFilter = true;
			}
			if (meetsFilter) {
				System.out.println("Adding: "+curRest.getName());
				restList.add(curRest);
			}
		}
		restaurantList.setItems(restList);
		restaurantList.setCellFactory(new Callback<ListView<Restaurant>, ListCell<Restaurant>>() {
			@Override
			public ListCell<Restaurant> call(ListView<Restaurant> listView) {
				return new RestaurantListCell(wrap);
			}
		});
		filterValue = 0;
		rating.setImage(new Image("/starIcons/0star.png"));
		rating.setPreserveRatio(true);
		rating.setFitWidth(200);
		ratingButtons.setAlignment(Pos.CENTER);
		ratingButtons.setSpacing(10);
		if(!filter.getCuisine().equals(null)) {
			cuisine.setValue(filter.getCuisine());
		}else {
			cuisine.setValue("None");
		}
		if(!filter.getDinning().equals(null)) {
			dinning.setValue(filter.getDinning());
		}else {
			dinning.setValue("None");
		}
		search.setText(filter.getSearch());
		filterValue = filter.getRating();
		switch (filterValue) {
		case 0:
			rating.setImage(new Image("/starIcons/0star.png"));
			break;
		case 1:
			rating.setImage(new Image("/starIcons/1star.png"));
			break;
		case 2:
			rating.setImage(new Image("/starIcons/2star.png"));
			break;
		case 3:
			rating.setImage(new Image("/starIcons/3star.png"));
			break;
		case 4:
			rating.setImage(new Image("/starIcons/4star.png"));
			break;
		case 5:
			rating.setImage(new Image("/starIcons/5star.png"));
			break;
		}
		minPrice.setText("" + filter.getMinPrice());
	}

	public void showStage() {
		thisStage.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
	@FXML
	public void start(MouseEvent mevent) {
		StartController starter = new StartController(new StartWrapper(thisStage, wrap.getUser(), wrap.getUserDB(), wrap.getRestaurantDB(), new Filter("","None","None", 0, 0, false)));
		starter.showStage();
	}
	@FXML
	public void account(ActionEvent event) {
		AccountController accountCont = new AccountController(new ListWrapper(event, wrap.getUser(), wrap.getUserDB(), wrap.getRestaurantDB(), new Filter("","None","None", 0, 0, false)));
		accountCont.showStage();
	}
	@FXML
	public void apply(ActionEvent event) {
		System.out.println("Applying filters.");
		boolean useFil = false;
		String cuisineString = "";
		String dinningString = "";
		System.out.println(cuisine.getValue() + " " + dinning.getValue());
		if (cuisine.getValue() != null && dinning.getValue() != null) {
			//if (!cuisine.getValue().equals("None") || !dinning.getValue().equals("None")) {
				cuisineString = (String)cuisine.getValue();
				dinningString = (String)dinning.getValue();
				useFil = true;
			//}
		}
		if (filterValue > 0) {
			useFil = true;
		}
		double price = 0;
		if (!minPrice.getText().equals("")) {
			if (Double.parseDouble(minPrice.getText()) > 0) {
				useFil = true;
				price = Double.parseDouble(minPrice.getText());
			}
		}
		//System.out.println(filterString + "-" + filterValue + "-" + price + "-" + useFil);
		ListController listController = new ListController(new ListWrapper(event, user, userDB, restaurantDB,
				new Filter(search.getText(),cuisineString, dinningString, filterValue, price, useFil)));
		listController.showStage();
	}
	@FXML
	public void search(ActionEvent event) {
		System.out.println("Searching.");
		boolean useFil = false;
		String cuisineString = "";
		String dinningString = "";
		System.out.println(cuisine.getValue() + " " + dinning.getValue());
		if (cuisine.getValue() != null && dinning.getValue() != null) {
			//if (!cuisine.getValue().equals("None") || !dinning.getValue().equals("None")) {
				cuisineString = (String)cuisine.getValue();
				dinningString = (String)dinning.getValue();
				useFil = true;
			//}
		}
		if (filterValue > 0) {
			useFil = true;
		}
		double price = 0;
		if (!minPrice.getText().equals("")) {
			if (Double.parseDouble(minPrice.getText()) > 0) {
				useFil = true;
				price = Double.parseDouble(minPrice.getText());
			}
		}
		//System.out.println(filterString + "-" + filterValue + "-" + price + "-" + useFil);
		ListController listController = new ListController(new ListWrapper(event, user, userDB, restaurantDB,
				new Filter(search.getText(),cuisineString, dinningString, filterValue, price, useFil)));
		listController.showStage();
	}
	@FXML
	public void logout(ActionEvent event) {
		LoginController loginController = new LoginController(new LoginWrapper(userDB, restaurantDB, thisStage));
		loginController.showStage();
	}

	@FXML
	public void subtract(ActionEvent event) {
		System.out.println("Minus.");
		if (filterValue > 0) {
			filterValue--;
		}
		switch (filterValue) {
		case 0:
			rating.setImage(new Image("/starIcons/0star.png"));
			break;
		case 1:
			rating.setImage(new Image("/starIcons/1star.png"));
			break;
		case 2:
			rating.setImage(new Image("/starIcons/2star.png"));
			break;
		case 3:
			rating.setImage(new Image("/starIcons/3star.png"));
			break;
		case 4:
			rating.setImage(new Image("/starIcons/4star.png"));
			break;
		case 5:
			rating.setImage(new Image("/starIcons/5star.png"));
			break;
		}
	}

	@FXML
	public void add(ActionEvent event) {
		System.out.println("Plus.");
		if (filterValue < 5) {
			filterValue++;
		}
		switch (filterValue) {
		case 0:
			rating.setImage(new Image("/starIcons/0star.png"));
			break;
		case 1:
			rating.setImage(new Image("/starIcons/1star.png"));
			break;
		case 2:
			rating.setImage(new Image("/starIcons/2star.png"));
			break;
		case 3:
			rating.setImage(new Image("/starIcons/3star.png"));
			break;
		case 4:
			rating.setImage(new Image("/starIcons/4star.png"));
			break;
		case 5:
			rating.setImage(new Image("/starIcons/5star.png"));
			break;
		}
	}
}
