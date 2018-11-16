package detail;

import java.net.URL;
import java.util.ResourceBundle;

import database.DatabaseReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import list.ListController;
import list.ListWrapper;
import list.RestaurantListCell;
import login.LoginController;
import login.LoginWrapper;
import model.Email;
import model.Filter;
import model.FoodItem;
import model.PhoneNumber;
import model.Rating;
import model.Restaurant;
import model.User;
import model.arraySortedList.ArraySortedList;

public class DetailController implements Initializable{
	private final Stage thisStage;
	protected ArraySortedList<User> userDB;
	protected ArraySortedList<Restaurant> restaurantDB;
	protected User user;
	protected Restaurant restaurant;
	private VBox container;
	private VBox restaurantInfo;
	private Text restaurantName;
	private HBox ratDin;
	private ImageView rating; private Text dinning;
	private StackPane imageContainer;
	private ImageView banner;
	private Text address;
	private ListView<FoodItem> menuList;
	private ListView<Rating> reviewList;
	private DetailWrapper wrap;
	private OrderController order;
	private Text menuTitle;
	private Text reviewTitle;
	private Button addReview;
	private HBox reviewHeader;
	@FXML private ScrollPane restaurantScroll;
	@FXML private ScrollPane sideBar;
	@FXML private Button find;
	
	public DetailController(DetailWrapper w) {
		this.wrap = w;
		userDB = wrap.getUserDB();
		restaurantDB = wrap.getRestaurantDB();
		user = wrap.getUser();
		restaurant = wrap.getRestaurant();
		order = wrap.getOrder();
		thisStage = (Stage)((Node)wrap.getEvent().getSource()).getScene().getWindow();
    	dinning = new Text(restaurant.getCuisineType()+" "+restaurant.getDinningType());
    	banner = new ImageView(new Image(restaurant.getBannerURL()));
    	banner.setFitWidth(900);
    	banner.setFitHeight(200);
    	BoxBlur boxBlur = new BoxBlur();
    	boxBlur.setWidth(10);
    	boxBlur.setHeight(3);
    	boxBlur.setIterations(3);
    	banner.setEffect(boxBlur);
    	rating = new ImageView();
    	rating.setFitWidth(50);
    	rating.setPreserveRatio(true);
    	menuTitle = new Text("MENU");
    	sideBar = new ScrollPane();
    	sideBar.setStyle("-fx-background-color: #efefef;");
    	address = new Text(restaurant.getAddress());
    	reviewTitle = new Text("REVIEWS");
    	addReview = new Button("WRITE A REVIEW");
    	addReview.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent event) {
    			AddReviewController adder = new AddReviewController(new DetailWrapper(event, wrap.getUser(), wrap.getUserDB(), wrap.getRestaurantDB(), wrap.getRestaurant(),wrap.getOrder()));
    			adder.showStage();
    		}
    	});
    	//addReview.setOnAction();
    	reviewHeader = new HBox(reviewTitle, addReview);
    	menuList = new ListView<FoodItem>();
    	reviewList = new ListView<Rating>();
    	switch((int)restaurant.getAvgRating()) {
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
    	restaurantName = new Text(restaurant.getName());
    	ratDin = new HBox(rating, dinning);
    	ratDin.setAlignment(Pos.CENTER_LEFT);
    	restaurantInfo = new VBox(restaurantName, ratDin, address);
    	restaurantInfo.setStyle("-fx-fill: WHITE;");
    	imageContainer = new StackPane(banner, restaurantInfo);
    	imageContainer.setAlignment(Pos.BOTTOM_LEFT);
    	restaurantScroll = new ScrollPane();
    	container = new VBox(imageContainer, menuTitle, menuList, reviewHeader, reviewList);
    	Parent root = null;
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/detail/detail.fxml"));
    		loader.setController(this);
    		root = loader.load();
    		root.getStylesheets().add("/application/application.css");
    		thisStage.setScene(new Scene(root));
    	} catch (Exception e) {
    		System.out.println(e);
    	}
    	ObservableList<FoodItem> menu = FXCollections.observableArrayList();
    	restaurant.getMenu().reset();
    	FoodItem currentItem = null;
    	for(int x =0; x<restaurant.getMenu().size();x++) {
    		currentItem = restaurant.getMenu().getNext();
    		//System.out.println(currentItem);
    		menu.add(currentItem);
    	}
    	menuList.setItems(menu);
		menuList.setCellFactory(new Callback<ListView<FoodItem>, ListCell<FoodItem>>() {
            @Override
            public ListCell<FoodItem> call(ListView<FoodItem> listView) {
                return new MenuListCell(wrap);
            }
        });
		ObservableList<Rating> reviews = FXCollections.observableArrayList();
		ArraySortedList<Rating> resRev = restaurant.getRatings();
    	resRev.reset();
    	Rating currentRev = null;
    	for(int x =0; x<resRev.size();x++) {
    		currentRev = resRev.getNext();
    		//System.out.println(currentItem);
    		reviews.add(currentRev);
    	}
    	reviewList.setItems(reviews);
		reviewList.setCellFactory(new Callback<ListView<Rating>, ListCell<Rating>>() {
            @Override
            public ListCell<Rating> call(ListView<Rating> listView) {
                return new ReviewListCell(wrap);
            }
        });
		restaurantScroll.setContent(container);
		sideBar.setContent(order.getContainer());
	}
	public void showStage() {
		thisStage.show();
	}
	@FXML
	public void find(ActionEvent event) {
		ListController list = new ListController(new ListWrapper(event, wrap.getUser(), wrap.getUserDB(), wrap.getRestaurantDB(), new Filter("None", 0, 0, false)));
		list.showStage();
	}
	@FXML
	public void logout(ActionEvent event) {
		LoginController loginController = new LoginController(new LoginWrapper(wrap.getUserDB(),wrap.getRestaurantDB()));
    	loginController.showStage();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//restaurantName.setText(restaurant.getName());
		
	}
	public void addMenuItem() {
		
	}
	public ArraySortedList<User> getUserDB() {
		return userDB;
	}
	public void setUserDB(ArraySortedList<User> userDB) {
		this.userDB = userDB;
	}
	public ArraySortedList<Restaurant> getRestaurantDB() {
		return restaurantDB;
	}
	public void setRestaurantDB(ArraySortedList<Restaurant> restaurantDB) {
		this.restaurantDB = restaurantDB;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
}
