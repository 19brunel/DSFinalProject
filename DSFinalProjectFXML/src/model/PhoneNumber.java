package model;

public class PhoneNumber {
	protected double number;
	public PhoneNumber(String n) {
		number = Double.parseDouble(n);
	}
	public String getPhoneNumber() {
		return String.format("%.12f", number).substring(0,10);
	}
	public String print() {
		String output = Math.round(number)+"";
		output = "("+output.substring(0, 3)+")-"+output.substring(3,6)+"-"+output.substring(6,10);
		return output;
	}
}
