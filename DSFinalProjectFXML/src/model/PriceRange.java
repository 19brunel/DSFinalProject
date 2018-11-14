package model;

public class PriceRange {
	protected double min;
	protected double max;
	public PriceRange(double min, double max) {
		this.min = min;
		this.max = max;
	}
	public String toString() {
		return "$"+min+"-$"+max;
	}
	public double getMin() {
		return min;
	}
	public void setMin(double min) {
		this.min = min;
	}
	public double getMax() {
		return max;
	}
	public void setMax(double max) {
		this.max = max;
	}
}
