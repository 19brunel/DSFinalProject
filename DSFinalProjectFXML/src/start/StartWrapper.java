package start;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import model.Filter;
import model.Restaurant;
import model.User;
import model.arraySortedList.ArraySortedList;

public class StartWrapper {
	protected ArraySortedList<User> userDB;
	protected ArraySortedList<Restaurant> restaurantDB;
	protected User user;
	protected Stage stage;
	protected Filter filter;
	public StartWrapper(Stage s, User u, ArraySortedList<User> users, ArraySortedList<Restaurant> restaurants, Filter f) {
		userDB = users;
		user = u;
		stage = s;
		restaurantDB = restaurants;
		filter = f;
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
	public User getUser() {
		return user;
	}
	public Filter getFilter() {
		return filter;
	}
	public void setFilter(Filter filter) {
		this.filter = filter;
	}
}
