package model;

public class PhoneNumber {
	protected double number;
	public PhoneNumber(String n) {
		number = Double.parseDouble(n);
	}
	public String getPhoneNumber() {
		return ""+number;
	}
}
