package detail;


import javafx.event.ActionEvent;
import model.Restaurant;
import model.User;
import model.arraySortedList.ArraySortedList;

public class DetailWrapper {
	protected ArraySortedList<User> userDB;
	protected ArraySortedList<Restaurant> restaurantDB;
	protected User user;
	protected ActionEvent event;
	protected Restaurant restaurant;
	protected OrderController order;
	public DetailWrapper(ActionEvent e, User u, ArraySortedList<User> users, ArraySortedList<Restaurant> restaurants, Restaurant r, OrderController o) {
		userDB = users;
		user = u;
		event = e;
		restaurantDB = restaurants;
		restaurant = r;
		order = o;
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
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public OrderController getOrder() {
		return order;
	}
	public void setOrder(OrderController order) {
		this.order = order;
	}
}
