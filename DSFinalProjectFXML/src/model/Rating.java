package model;

public class Rating implements Comparable{
	protected int rating; //0-5
	//protected String title;
	protected String description;
	//protected User user;
	public Rating(int r, String d) {
		rating = r;
		description = d;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public int compareTo(Object o) {
		Rating rat = (Rating)o;
		return rat.getRating()-this.rating;
	}
}
