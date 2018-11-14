package detail;

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
import javafx.stage.Stage;
import model.Restaurant;

public class AddReviewController {
	private Stage thisStage;
	private Restaurant restaurant;
	@FXML ChoiceBox ratingDrop;
	@FXML TextArea description;
	
	public AddReviewController(Restaurant r) {
		thisStage = new Stage();
		restaurant = r;
		Parent root = null;
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/detail/addReview.fxml"));
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
	public void exit(ActionEvent e) {
		thisStage.close();
	}
	@FXML
	public void submit(ActionEvent e) {
		System.out.println(ratingDrop.getValue());
		System.out.println(description.getText());
		//Call add review in the database writer class passing the restaurant, rating, description
		thisStage.close();
	}
}
