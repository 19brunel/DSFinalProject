package model;

public class PhoneNumber {
	protected double number;
	public PhoneNumber(String n) {
		number = Double.parseDouble(n);
	}
	public String getPhoneNumber() {
		return ""+number;
	}
	public String print() {
		String output = "";
		output+= "("+(int)(number/10000000)+")-";
		output+= (int)((number/10000)-((int)(number/10000000))*1000)+"-";
		output+= (int)((number)-((number/10000)-((int)(number/10000000))*1000)*10000)+"";
		return output;
	}
}
