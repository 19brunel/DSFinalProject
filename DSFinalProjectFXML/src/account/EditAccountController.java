package account;

import database.DatabaseReader;
import detail.DetailWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import list.ListWrapper;
import model.Email;
import model.Filter;
import model.PhoneNumber;
import model.Rating;
import model.Restaurant;
import model.User;
import model.arraySortedList.ArraySortedList;

public class EditAccountController {
	private Stage thisStage;
	private ListWrapper wrap;
	private User user;
	@FXML TextField name;
	@FXML TextField username;
	@FXML TextField password;
	@FXML TextField email;
	@FXML TextField phoneNumber;
	
	public EditAccountController(ListWrapper r) {
		thisStage = new Stage();
		wrap = r;
		user = wrap.getUser();
		Parent root = null;
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/account/editAccount.fxml"));
    		loader.setController(this);
    		root = loader.load();
    		root.getStylesheets().add("/application/application.css");
    		thisStage.setScene(new Scene(root));
    	} catch (Exception e) {
    		System.out.println(e);
    	}
		name.setText(user.getName());
		username.setText(user.getUsername());
		password.setText(user.getPassword());
		email.setText(user.getEmail().getEmail());
		phoneNumber.setText(user.getPhoneNumber().print());
	}
	public void showStage() {
		thisStage.show();
	}
	@FXML
	public void exit(ActionEvent e) {
		thisStage.close();
	}
	@FXML
	public void submit(ActionEvent e) {
		User newUser = wrap.getUser();
		ArraySortedList<User> newDB = wrap.getUserDB();
		newDB.remove(newUser);
		String numS = phoneNumber.getText();
		if(numS.contains("(")||numS.contains("")) {
			System.out.println(numS);
			numS = numS.substring(1);
			System.out.println(numS);
			numS = numS.substring(0, 3)+numS.substring(4);
			if(numS.contains("-")) {
				numS = numS.replaceAll("-","");
			}
		}
		System.out.println(numS);
		PhoneNumber num = new PhoneNumber(numS);
		newUser= new User(username.getText(),password.getText(),name.getText(),new Email(email.getText()),num, user.isAdmin());
		newDB.add(newUser);
		DatabaseReader writer = new DatabaseReader();
		writer.writeUserDatabase(getClass().getResource("/database/userDB.txt").getPath().replaceAll("/", "\\\\").substring(1).replaceAll("%20", " ").replaceAll("bin", "src"), newDB);
		//Call add review in the database writer class passing the restaurant, rating, description
		
		AccountController refreshedDetail = new AccountController(new ListWrapper(wrap.getEvent(), newUser, newDB, wrap.getRestaurantDB(), new Filter("None","None", 0, 0, false)));
		refreshedDetail.showStage();
		thisStage.close();
	}
}
