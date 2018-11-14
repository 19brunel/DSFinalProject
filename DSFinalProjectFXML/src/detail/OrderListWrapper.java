package detail;


import javafx.event.ActionEvent;
import model.FoodItem;
import model.Restaurant;
import model.User;
import model.arraySortedList.ArraySortedList;

public class OrderListWrapper {
	protected ArraySortedList<User> userDB;
	protected ArraySortedList<Restaurant> restaurantDB;
	protected User user;
	protected ActionEvent event;
	protected Restaurant restaurant;
	protected ArraySortedList<FoodItem> order;
	public OrderListWrapper(ActionEvent e, User u, ArraySortedList<User> users, ArraySortedList<Restaurant> restaurants, Restaurant r, ArraySortedList<FoodItem> o) {
		userDB = users;
		user = u;
		event = e;
		restaurantDB = restaurants;
		restaurant = r;
		order = o;
	}
	public void setOrder(ArraySortedList<FoodItem> o) {
		order = o;
	}
	public ArraySortedList<FoodItem> getOrder(){
		return order;
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
}
