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
import model.Rating;
import model.Restaurant;
import model.User;
import model.arraySortedList.ArraySortedList;

public class AddReviewController {
	private Stage thisStage;
	private DetailWrapper wrap;
	@FXML ChoiceBox ratingDrop;
	@FXML TextArea description;
	
	public AddReviewController(DetailWrapper r) {
		thisStage = new Stage();
		wrap = r;
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
		Restaurant newRes = wrap.getRestaurant();
		ArraySortedList<Rating> newRat = wrap.getRestaurant().getRatings();
		newRat.add(new Rating(Integer.parseInt((String)ratingDrop.getValue()), description.getText()));
		newRes.setRatings(newRat);
		ArraySortedList<Restaurant> newDB = wrap.getRestaurantDB();
		newDB.add(newRes);
		//Call add review in the database writer class passing the restaurant, rating, description
		DetailController refreshedDetail = new DetailController(new DetailWrapper(wrap.getEvent(), wrap.getUser(), wrap.getUserDB(), newDB, newRes, wrap.getOrder()));
		refreshedDetail.showStage();
		thisStage.close();
	}
}
