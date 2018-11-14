package list;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Restaurant;
import model.User;
import model.arraySortedList.ArraySortedList;

public class ListController implements Initializable{
	private final Stage thisStage;
	private User user;
	private ActionEvent event;
	protected ArraySortedList<User> userDB;
	protected ArraySortedList<Restaurant> restaurantDB;
	@FXML private ListView restaurantList;
	
	public ListController(ListWrapper wrap) {
		user = wrap.getUser();
		event = wrap.getEvent();
		userDB = wrap.getUserDB();
		restaurantDB = wrap.getRestaurantDB();
		restaurantList = new ListView<Restaurant>();
		thisStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Parent root = null;
		System.out.println("List: Trying to load scene.");
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
    	for(int x =0; x<restaurantDB.size();x++) {
    		restList.add(restaurantDB.getNext());
    	}
    	restaurantList.setItems(restList);
    	restaurantList.setCellFactory(new Callback<ListView<Restaurant>, ListCell<Restaurant>>() {
            @Override
            public ListCell<Restaurant> call(ListView<Restaurant> listView) {
                return new RestaurantListCell(wrap);
            }
        });
	}
	public void showStage() {
		thisStage.show();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
    public void viewRestaurant(ActionEvent event) {
    	
    }
}
