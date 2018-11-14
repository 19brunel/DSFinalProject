package detail;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AddReviewController {
	private Stage thisStage;
	//@FXML private Button doneButton;
	//@FXML private Button cancelButton;
	
	public AddReviewController() {
		thisStage = new Stage();
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
		/*cancelButton = new Button("CANCEL");
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent event) {
    			System.out.println("Cancel Pressed.");
    			thisStage.close();
    		}
    	});*/
	}
	public void showStage() {
		thisStage.show();
	}
	@FXML
	public void exit(ActionEvent e) {
		thisStage.close();
	}
}
