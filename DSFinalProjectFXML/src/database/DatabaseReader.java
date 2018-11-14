package database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import model.Email;
import model.FoodItem;
import model.HoursOfOperation;
import model.PhoneNumber;
import model.PriceRange;
import model.Rating;
import model.Restaurant;
import model.User;
import model.arraySortedList.ArraySortedList;

public class DatabaseReader {
	public DatabaseReader() {

	}

	public ArraySortedList<User> readUserDatabase(String url) {
		ArraySortedList<User> users = new ArraySortedList<User>();
		String[] parameters = new String[6];
		Charset charset = Charset.forName("US-ASCII");
		//Charset charset = Charset.forName("UTF-8");
		Path path = FileSystems.getDefault().getPath(url);
		System.out.println("Found path.");
		try (BufferedReader reader = Files.newBufferedReader(path, charset)) {
			System.out.println("Created buffer reader.");
			String line = null;
			while ((line = reader.readLine()) != null) {
				parameters = line.split(",");
				users.add(new User(parameters[0], parameters[1], parameters[2], new Email(parameters[3]),
						new PhoneNumber(parameters[4]), Boolean.parseBoolean(parameters[5])));
			}
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}
		return users;
	}
	

	public ArraySortedList<Restaurant> readRestaurantDatabase(String url) {
		ArraySortedList<Restaurant> restaurants = new ArraySortedList<Restaurant>();
		Charset charset = Charset.forName("US-ASCII");
		//Charset charset = Charset.forName("UTF-8");
		Path path = FileSystems.getDefault().getPath(url);
		try (BufferedReader reader = Files.newBufferedReader(path, charset)) {
			String line = null;
			String name = null;
			String des = null;
			String icon = null;
			String banner = null;
			String address = null;
			String pn = null;
			String web = null;
			String cuisine = null;
			String dinning = null;
			PriceRange priceRng = null;
			HoursOfOperation hoursOfOp = null;
			ArraySortedList<Rating> ratings = new ArraySortedList<Rating>();
			ArraySortedList<FoodItem> menu = new ArraySortedList<FoodItem>();
			while ((line = reader.readLine()) != null) {
				menu = new ArraySortedList<FoodItem>();
				ratings = new ArraySortedList<Rating>();
				if (line.equals("---")) {
					for (int i = 0; i < 8; i++) {
						switch (i) {
						case 0:
							name = reader.readLine();
							break;
						case 1:
							des = reader.readLine();
							break;
						case 2:
							line = reader.readLine();
							icon = line.split(",")[0];
							banner = line.split(",")[1];
							break;
						case 3:
							address = reader.readLine();
							break;
						case 4:
							line = reader.readLine();
							String[] split = line.split(",");
							pn = split[0];
							web = split[1];
							cuisine = split[2];
							dinning = split[3];
							priceRng = new PriceRange(Double.parseDouble(split[4]), Double.parseDouble(split[5]));
							break;
						case 5:
							line = reader.readLine();
							String[] split2 = line.split(",");
							hoursOfOp = new HoursOfOperation(split2[0], split2[1], split2[2], split2[3], split2[4],
									split2[5], split2[6]);
							break;
						case 6:
							line = reader.readLine();
							String[] split3 = line.split(";");
							for (int x = 0; x < split3.length; x++) {
								ratings.add(
										new Rating(Integer.parseInt(split3[x].split("-")[0]), split3[x].split("-")[1]));
							}
							break;
						case 7:
							line = reader.readLine();
							String[] split4 = line.split(";");
							for (int x = 0; x < split4.length; x++) {
								menu.add(
										new FoodItem(split4[x].split(":")[1].split("-")[0], split4[x].split(":")[1].split("-")[1],Double.parseDouble(split4[x].split(":")[0])));
							}
							break;
						}
					}
					restaurants.add(new Restaurant(name, des, icon, banner, address, new PhoneNumber(pn), web, cuisine, dinning, priceRng, hoursOfOp, ratings, menu));
				}
			}
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}

		return restaurants;
	}
	
	public ArraySortedList<Restaurant> writeRestaurantDatabase(String url, ArraySortedList<Restaurant> r) {
		ArraySortedList<Restaurant> restaurants = r;
		restaurants.reset();
		Charset charset = Charset.forName("US-ASCII");
		//Charset charset = Charset.forName("UTF-8");
		Path path = FileSystems.getDefault().getPath(url);
		try (BufferedWriter writer = Files.newBufferedWriter(path, charset)) {
			String line = null;
			String name = null;
			String des = null;
			String icon = null;
			String banner = null;
			String address = null;
			String pn = null;
			String web = null;
			String cuisine = null;
			String dinning = null;
			PriceRange priceRng = null;
			HoursOfOperation hoursOfOp = null;
			ArraySortedList<Rating> ratings = new ArraySortedList<Rating>();
			ArraySortedList<FoodItem> menu = new ArraySortedList<FoodItem>();
			Restaurant res = null;
			for (int x = 0; x<restaurants.size(); x++) {
				res = restaurants.getNext();
				ratings = res.getRatings();
				menu = res.getMenu();
				writer.write("---");
					for (int i = 0; i < 8; i++) {
						switch (i) {
						case 0:
							writer.write(res.getName());
							break;
						case 1:
							writer.write(res.getDescription());
							break;
						case 2:
							writer.write(res.getIconURL()+","+res.getBannerURL());
							break;
						case 3:
							writer.write(res.getAddress());
							break;
						case 4:
							writer.write(res.getPhoneNumber()+","+res.getWebsite()+","+res.getCuisineType()+","+res.getDinningType()+","+res.getPriceRange().getMin()+","+res.getPriceRange().getMax());
							break;
						case 5:
							writer.write(res.getHoursOfOp().getMonday()+","+res.getHoursOfOp().getTuesday()+","+res.getHoursOfOp().getWednesday()+","+res.getHoursOfOp().getThursday()+","+res.getHoursOfOp().getFriday()+","+res.getHoursOfOp().getSaturday()+","+res.getHoursOfOp().getSunday());
							break;
						case 6:
							String toPrint = "";
							Rating curRat = null;
							for (int y = 0; y < ratings.size(); y++) {
								curRat = ratings.getNext();
								toPrint+=curRat.getRating()+"-"+curRat.getDescription()+";";
							}
							writer.write(toPrint);
							break;
						case 7:
							line = reader.readLine();
							String[] split4 = line.split(";");
							for (int x = 0; x < split4.length; x++) {
								menu.add(
										new FoodItem(split4[x].split(":")[1].split("-")[0], split4[x].split(":")[1].split("-")[1],Double.parseDouble(split4[x].split(":")[0])));
							}
							break;
						}
					}
					restaurants.add(new Restaurant(name, des, icon, banner, address, new PhoneNumber(pn), web, cuisine, dinning, priceRng, hoursOfOp, ratings, menu));
				}
			}
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}

		return restaurants;
	}
}
