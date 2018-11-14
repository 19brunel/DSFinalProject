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
	public int compareTo(FoodItem other) {
		if(other.getPrice()>price) {
			return (int)(price-other.getPrice());
		}else {
			if(other.getPrice()==price) {
				return 0;
			}else {
				return (int)(other.getPrice()-price);
			}
		}
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
		// TODO Auto-generated method stub
		return 0;
	}
}
