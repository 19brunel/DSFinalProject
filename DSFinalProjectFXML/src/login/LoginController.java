package login;

import java.net.URL;
import java.util.ResourceBundle;

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
import model.Email;
import model.Filter;
import model.PhoneNumber;
import model.Restaurant;
import model.User;
import model.arraySortedList.ArraySortedList;

public class LoginController implements Initializable {
	private final Stage thisStage;
	private LoginWrapper wrap;
	protected ArraySortedList<User> userDB;
	protected ArraySortedList<Restaurant> restaurantDB;
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	@FXML
	private Text fail;

	public LoginController(LoginWrapper wrap) {
		userDB = wrap.getUserDB();
		restaurantDB = wrap.getRestaurantDB();
		thisStage = wrap.getStage();
		this.wrap = wrap;
		Parent root = null;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/login/login.fxml"));
			loader.setController(this);
			root = loader.load();
			root.getStylesheets().add("/application/application.css");
			thisStage.setScene(new Scene(root));
		} catch (Exception e) {
			System.out.println(e);
		}
		fail.setVisible(false);
	}

	public void showStage() {
		thisStage.show();
	}

	@FXML
	private void attemptLogin(ActionEvent event) {
		// System.out.println("Login: Username - "+username.getText());
		// System.out.println("Login: Password - "+password.getText());
		userDB.reset();
		User tempUser = null;
		User attemptedUser = null;
		for (int i = 0; i < userDB.size(); i++) {
			tempUser = userDB.getNext();
			if (tempUser.getUsername().equals(username.getText())) {
				attemptedUser = tempUser;
			} else {
				fail.setVisible(true);
			}
		}
		if (attemptedUser != null) {
			if (attemptedUser.getPassword().equals(password.getText()) && attemptedUser != null) {
				ListController listController = new ListController(new ListWrapper(event, attemptedUser, userDB,
						restaurantDB, new Filter("None", "None", 0, 0, false)));
				listController.showStage();
			}
		}
	}

	@FXML
	private void create() {
		CreateAccountController createCont = new CreateAccountController(wrap);
		createCont.showStage();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}
