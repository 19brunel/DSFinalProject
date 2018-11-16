package model;

public class Filter {
	protected String dinningString;
	protected int rating;
	protected double minPrice;
	protected boolean useFilter;
	public Filter(String s, int r, double m, boolean u) {
		dinningString = s;
		rating = r;
		minPrice = m;
		useFilter = u;
	}
	public String getDinningString() {
		return dinningString;
	}
	public void setDinningString(String filterString) {
		this.dinningString = filterString;
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
	
}
