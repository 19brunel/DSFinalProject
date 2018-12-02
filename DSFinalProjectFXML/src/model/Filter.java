package model;

public class Filter {
	protected String search;
	protected String dinning;
	protected String cuisine;
	protected int rating;
	protected double minPrice;
	protected boolean useFilter;
	public Filter(String se, String c, String s, int r, double m, boolean u) {
		search = se;
		cuisine = c;
		dinning = s;
		rating = r;
		minPrice = m;
		useFilter = u;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String s) {
		search = s;
	}
	public String getDinning() {
		return dinning;
	}
	public void setDinning(String filterString) {
		this.dinning = filterString;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public double getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}
	public boolean getUseFilter() {
		return useFilter;
	}
	public void setUseFilter(boolean useFilter) {
		this.useFilter = useFilter;
	}
	public String getCuisine() {
		return cuisine;
	}
	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}
	
}
