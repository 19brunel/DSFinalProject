package login;

import model.Restaurant;
import model.User;
import model.arraySortedList.ArraySortedList;
import model.arraySortedList.ArrayUnsortedList;

public class LoginWrapper {
	protected ArraySortedList<User> userDB;
	protected ArraySortedList<Restaurant> restaurantDB;
	public LoginWrapper(ArraySortedList<User> users, ArraySortedList<Restaurant> restaurants) {
		userDB = users;
		restaurantDB = restaurants;
	}
	public ArraySortedList<User> getUserDB() {
		return userDB;
	}
	public ArraySortedList<Restaurant> getRestaurantDB(){
		return restaurantDB;
	}
}
