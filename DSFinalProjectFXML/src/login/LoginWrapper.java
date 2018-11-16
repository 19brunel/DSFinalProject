package login;

import javafx.stage.Stage;
import model.Restaurant;
import model.User;
import model.arraySortedList.ArraySortedList;
import model.arraySortedList.ArrayUnsortedList;

public class LoginWrapper {
	protected ArraySortedList<User> userDB;
	protected ArraySortedList<Restaurant> restaurantDB;
	protected Stage stage;
	public LoginWrapper(ArraySortedList<User> users, ArraySortedList<Restaurant> restaurants, Stage s) {
		userDB = users;
		restaurantDB = restaurants;
		stage = s;
	}
	public ArraySortedList<User> getUserDB() {
		return userDB;
	}
	public ArraySortedList<Restaurant> getRestaurantDB(){
		return restaurantDB;
	}
	public Stage getStage() {
		return stage;
	}
}
