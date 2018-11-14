package list;

import javafx.event.ActionEvent;
import model.Restaurant;
import model.User;
import model.arraySortedList.ArraySortedList;

public class ListWrapper {
	protected ArraySortedList<User> userDB;
	protected ArraySortedList<Restaurant> restaurantDB;
	protected User user;
	protected ActionEvent event;
	public ListWrapper(ActionEvent e, User u, ArraySortedList<User> users, ArraySortedList<Restaurant> restaurants) {
		userDB = users;
		user = u;
		event = e;
		restaurantDB = restaurants;
	}
	public ArraySortedList<User> getUserDB() {
		return userDB;
	}
	public ArraySortedList<Restaurant> getRestaurantDB(){
		return restaurantDB;
	}
	public ActionEvent getEvent() {
		return event;
	}
	public User getUser() {
		return user;
	}
}
