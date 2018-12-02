package start;

import java.net.URL;
import java.util.ResourceBundle;

import account.AccountController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import list.ListController;
import list.ListWrapper;
import login.LoginController;
import login.LoginWrapper;
import model.Email;
import model.Filter;
import model.PhoneNumber;
import model.Restaurant;
import model.User;
import model.arraySortedList.ArraySortedList;

public class StartController implements Initializable {
	private Stage thisStage;
	private StartWrapper wrap;
	private User user;
	protected ArraySortedList<User> userDB;
	protected ArraySortedList<Restaurant> restaurantDB;
	@FXML
	private Button find;
	@FXML
	private Button account;
	@FXML
	private Button logger;
	@FXML
	private TextField search;

	public StartController(StartWrapper wrap) {
		userDB = wrap.getUserDB();
		restaurantDB = wrap.getRestaurantDB();
		thisStage = wrap.getStage();
		user = wrap.getUser();
		this.wrap = wrap;
		Parent root = null;
		// System.out.println("List: Trying to load scene.");
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/start/start.fxml"));
			loader.setController(this);
			root = loader.load();
			root.getStylesheets().add("/application/application.css");
			thisStage.setScene(new Scene(root));
		} catch (Exception e) {
			System.out.println(e);
		}
		
		if(user!=null) {
			find.setText("FIND");
			account.setText("MY ACCOUNT");
			logger.setText("LOGOUT");
		}else {
			find.setText("");
			account.setText("");
			logger.setText("LOGIN");
		}
	}

	public void showStage() {
		thisStage.show();
	}
	@FXML
	public void logger(ActionEvent event) {
		LoginController login = new LoginController(new LoginWrapper(wrap.getUserDB(),wrap.getRestaurantDB(),thisStage));
		login.showStage();
	}
	@FXML
	public void search(ActionEvent event) {
		if(user!=null) {
			ListController accountCont = new ListController(new ListWrapper(event, wrap.getUser(),
					wrap.getUserDB(), wrap.getRestaurantDB(), new Filter(search.getText(), "None", "None", 0, 0, false)));
			accountCont.showStage();
		}else {
			LoginController login = new LoginController(new LoginWrapper(wrap.getUserDB(),wrap.getRestaurantDB(),thisStage));
			login.showStage();
		}
	}
	@FXML
	public void account(ActionEvent event) {
		if (wrap.getUser() != null) {
			AccountController accountCont = new AccountController(new ListWrapper(event, wrap.getUser(),
					wrap.getUserDB(), wrap.getRestaurantDB(), new Filter("", "None", "None", 0, 0, false)));
			accountCont.showStage();
		}else {
			LoginController login = new LoginController(new LoginWrapper(wrap.getUserDB(),wrap.getRestaurantDB(),thisStage));
			login.showStage();
		}
	}
	@FXML
	public void list(ActionEvent event) {
		if (wrap.getUser() != null) {
			ListController accountCont = new ListController(new ListWrapper(event, wrap.getUser(),
					wrap.getUserDB(), wrap.getRestaurantDB(), new Filter("", "None", "None", 0, 0, false)));
			accountCont.showStage();
		}else {
			LoginController login = new LoginController(new LoginWrapper(wrap.getUserDB(),wrap.getRestaurantDB(),thisStage));
			login.showStage();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}
