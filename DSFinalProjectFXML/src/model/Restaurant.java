package model;

import javafx.scene.image.Image;
import model.arraySortedList.*;
import model.stack.*;

public class Restaurant implements Comparable{
	/*
	 * restaurant name, Address, Phone Number, String, Hours of operation, Types of
	 * cuisine, Type of dining, Price Range, Food Items and Price of the food item
	 */
	protected static int id = 0;
	protected String name;
	protected String description;
	protected String iconURL;
	protected String bannerURL;
	protected String address; //toString is printale address | sort all Resturants by distance from the location provided | or sort by CuisineType and DinningType
	protected PhoneNumber phoneNumber; //toString is (###)-###-####
	protected String website; //*@*.*
	protected String cuisineType;
	protected String dinningType;
	protected PriceRange priceRange; //toString $# - $#
	protected HoursOfOperation hoursOfOp; //display the entire week
	protected ArraySortedList<Rating> ratings; //Sort by most recent->oldest
	protected ArraySortedList<FoodItem> menu; //Sort by price
	
	public Restaurant(String n, String des, String iURL, String bURL, String l, PhoneNumber pn, String e, String c, String d, PriceRange p, HoursOfOperation h, ArraySortedList<Rating> r, ArraySortedList<FoodItem> f) {
		id++;
		name = n;
		description = des;
		iconURL = iURL;
		bannerURL = bURL;
		address = l;
		phoneNumber = pn;
		website = e;
		cuisineType = c;
		dinningType = d;
		priceRange = p;
		hoursOfOp = h;
		ratings = r;
		menu = f;
	}
	public double getAvgRating() {
		double avgRating = 0;
		ratings.reset();
		for(int i =0; i<ratings.size();i++) {
			avgRating+=ratings.getNext().getRating();
		}
		return ((double)(int)((avgRating/((double)ratings.size()))*10)/10);
	}
	public static int getId() {
		return id;
	}

	public static void setId(int id) {
		Restaurant.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public PhoneNumber getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(PhoneNumber phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getCuisineType() {
		return cuisineType;
	}

	public void setCuisineType(String cuisineType) {
		this.cuisineType = cuisineType;
	}

	public String getDinningType() {
		return dinningType;
	}

	public void setDinningType(String dinningType) {
		this.dinningType = dinningType;
	}

	public PriceRange getPriceRange() {
		return priceRange;
	}

	public void setPriceRange(PriceRange priceRange) {
		this.priceRange = priceRange;
	}

	public HoursOfOperation getHoursOfOp() {
		return hoursOfOp;
	}

	public void setHoursOfOp(HoursOfOperation hoursOfOp) {
		this.hoursOfOp = hoursOfOp;
	}

	public ArraySortedList<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(ArraySortedList<Rating> ratings) {
		this.ratings = ratings;
	}

	public ArraySortedList<FoodItem> getMenu() {
		return menu;
	}

	public void setMenu(ArraySortedList<FoodItem> menu) {
		this.menu = menu;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIconURL() {
		return iconURL;
	}

	public void setIconURL(String iconURL) {
		this.iconURL = iconURL;
	}

	public String getBannerURL() {
		return bannerURL;
	}

	public void setBannerURL(String bannerURL) {
		this.bannerURL = bannerURL;
	}

	@Override
	public int compareTo(Object arg0) {
		Restaurant rest = (Restaurant)arg0;
		return (int)(rest.getAvgRating()*10)-(int)(this.getAvgRating()*10);
	}
}
