package list;

import javafx.event.ActionEvent;
import model.Filter;
import model.Restaurant;
import model.User;
import model.arraySortedList.ArraySortedList;

public class ListWrapper {
	protected ArraySortedList<User> userDB;
	protected ArraySortedList<Restaurant> restaurantDB;
	protected User user;
	protected ActionEvent event;
	protected Filter filter;
	public ListWrapper(ActionEvent e, User u, ArraySortedList<User> users, ArraySortedList<Restaurant> restaurants, Filter f) {
		userDB = users;
		user = u;
		event = e;
		restaurantDB = restaurants;
		filter = f;
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
	public Filter getFilter() {
		return filter;
	}
	public void setFilter(Filter filter) {
		this.filter = filter;
	}
}
