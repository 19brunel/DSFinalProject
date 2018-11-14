package model;

public class User implements Comparable{
	protected static int id = 0;
	protected String username;
	protected String name;
	protected String password;
	protected Email email;
	protected boolean isAdmin;
	protected PhoneNumber phoneNumber;
	public User(String u, String p, String n, Email e, PhoneNumber pn, Boolean ad) {
		id++;
		username = u;
		password = p;
		name = n;
		email = e;
		phoneNumber = pn;
		isAdmin = ad;
	}
	public int compareTo(User user) {
		return this.id-user.getId();
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String u) {
		username = u;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String p) {
		password = p;
	}
	public String getName() {
		return name;
	}
	public void setName(String n) {
		name = n;
	}
	public static int getId() {
		return id;
	}
	public Email getEmail() {
		return email;
	}
	public void setEmail(Email email) {
		this.email = email;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public PhoneNumber getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(PhoneNumber phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Override
	public int compareTo(Object arg0) {
		return 0;
	}
}
