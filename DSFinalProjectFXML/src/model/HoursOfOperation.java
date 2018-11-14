package model;

public class HoursOfOperation {
	protected String monday;
	protected String tuesday;
	protected String wednesday;
	protected String thursday;
	protected String friday;
	protected String saturday;
	protected String sunday;
	public HoursOfOperation(String m, String t, String w, String th, String f, String s, String su) {
		monday = m;
		tuesday = t;
		wednesday = w;
		thursday = th;
		friday = f;
		saturday = s;
		sunday = su;
	}
	public String toString() {
		return ("Monday: "+monday+"\nTuesday: "+tuesday+"\nWednesday: "+wednesday+"\nThursday: "+thursday+"\nFriday: "+friday+"\nSaturday: "+saturday+"\nSunday: "+sunday);
	}
	public String getMonday() {
		return monday;
	}
	public void setMonday(String monday) {
		this.monday = monday;
	}
	public String getTuesday() {
		return tuesday;
	}
	public void setTuesday(String tuesday) {
		this.tuesday = tuesday;
	}
	public String getWednesday() {
		return wednesday;
	}
	public void setWednesday(String wednesday) {
		this.wednesday = wednesday;
	}
	public String getThursday() {
		return thursday;
	}
	public void setThursday(String thursday) {
		this.thursday = thursday;
	}
	public String getFriday() {
		return friday;
	}
	public void setFriday(String friday) {
		this.friday = friday;
	}
	public String getSaturday() {
		return saturday;
	}
	public void setSaturday(String saturday) {
		this.saturday = saturday;
	}
	public String getSunday() {
		return sunday;
	}
	public void setSunday(String sunday) {
		this.sunday = sunday;
	}
}
