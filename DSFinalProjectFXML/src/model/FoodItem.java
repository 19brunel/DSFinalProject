package model;

public class FoodItem implements Comparable{
	protected String name;
	protected String description;
	protected double price;
	public FoodItem(String n, String d,double p) {
		name = n;
		price = p;
		description = d;
	}
	public String toString() {
		return (name + " $"+price);
	}
	public double getPrice() {
		return price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public int compareTo(Object o) {
		FoodItem item = (FoodItem)o;
		return (int)(this.price*100)-(int)(item.getPrice()*100);
	}
}
