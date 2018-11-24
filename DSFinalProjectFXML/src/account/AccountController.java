package account;

import detail.DetailWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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
	private ListWrapper wrap;
	
	@FXML private AnchorPane bottomPane;
	@FXML private VBox userVbox;
	
	public AccountController(ListWrapper w) {
		wrap = w;
		thisStage = (Stage)((Node)wrap.getEvent().getSource()).getScene().getWindow();
		user = wrap.getUser();
		name = new Text(user.getName().toUpperCase());
		
		
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
