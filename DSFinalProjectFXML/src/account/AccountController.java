package account;

import detail.DetailWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import list.ListController;
import list.ListWrapper;
import login.LoginController;
import login.LoginWrapper;
import model.Filter;
import model.User;

public class AccountController {
	private Stage thisStage;
	private User user;
	private Text name;
	private Text username;
	private Text password;
	private Text email;
	private Text phoneNumber;
	private boolean isAdmin;
	private VBox container;
	private ListWrapper wrap;
	
	@FXML private AnchorPane bottomPane;
	@FXML private ScrollPane scroll;
	
	public AccountController(ListWrapper w) {
		wrap = w;
		thisStage = (Stage)((Node)wrap.getEvent().getSource()).getScene().getWindow();
		user = wrap.getUser();
		name = new Text(user.getName().toUpperCase());
		username = new Text(user.getUsername());
		password = new Text(user.getPassword());
		email = new Text(user.getEmail().getEmail());
		phoneNumber = new Text(user.getPhoneNumber().print());
		HBox userName = new HBox(new Text("USERNAME: "), username);
		userName.setPadding(new Insets(10));
		userName.setAlignment(Pos.CENTER);
		HBox passWord = new HBox(new Text("PASSWORD: "), password);
		passWord.setPadding(new Insets(10));
		passWord.setAlignment(Pos.CENTER);
		HBox login = new HBox(userName, passWord);
		login.setAlignment(Pos.CENTER);
		login.setPadding(new Insets(10));
		HBox eMail = new HBox(new Text("EMAIL: "), email);
		eMail.setPadding(new Insets(10));
		eMail.setAlignment(Pos.CENTER);
		HBox phNumber = new HBox(new Text("PHONE NUMBER: "), phoneNumber);
		phNumber.setPadding(new Insets(10));
		phNumber.setAlignment(Pos.CENTER);
		container = new VBox(name, login, eMail, phNumber);
		container.setAlignment(Pos.CENTER);
		container.setPadding(new Insets(10));
		container.setSpacing(10);
		
		
		Parent root = null;
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/account/account.fxml"));
    		loader.setController(this);
    		root = loader.load();
    		root.getStylesheets().add("/application/application.css");
    		thisStage.setScene(new Scene(root));
    	} catch (Exception e) {
    		System.out.println(e);
    	}
    	scroll.setContent(container);
    	container.prefWidthProperty().bind(scroll.widthProperty());
	}
	public void showStage() {
		thisStage.show();
	}
	@FXML
	public void find(ActionEvent event) {
		ListController list = new ListController(new ListWrapper(event, wrap.getUser(), wrap.getUserDB(), wrap.getRestaurantDB(), new Filter("None","None", 0, 0, false)));
		list.showStage();
	}
	@FXML
	public void logout(ActionEvent event) {
		LoginController loginController = new LoginController(new LoginWrapper(wrap.getUserDB(),wrap.getRestaurantDB(), thisStage));
    	loginController.showStage();
	}
	
}
