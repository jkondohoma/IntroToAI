package hw4;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;

public class FileParser {

	public static ArrayList<City> getCities() {

		ArrayList<City> cities = new ArrayList<City>();
		// read file
		Scanner s = null;
		try {
			s = new Scanner(new File("data/Cities.dat"));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);

		}

		while (s.hasNext()) {

			String line = s.nextLine();
			String[] token = line.split(";");

			String name = token[0];
			String adjacency = token[1];
			int sld = Integer.parseInt(token[2]);

			HashMap<String, Integer> neighboors = parseNeighbors(adjacency);
//			System.out.println(name+" "+sld);
			City city = new City(name, neighboors, sld);
			cities.add(city);

		}

		s.close();

		return cities;

	}

	/***
	 * given a string containing neighbors in the format neighboor1:distance,
	 * neighboor2:distance...etc create a hashmap that store the neighbor and its
	 * distance
	 * 
	 * @param adjacency
	 * @return
	 */
	private static HashMap<String, Integer> parseNeighbors(String adjacency) {
		HashMap<String, Integer> neighboors = new HashMap<String, Integer>();

		String token[] = adjacency.split(",");

		for (String str : token) {
			String[] cityToken = str.split(":");

			neighboors.put(cityToken[0], Integer.parseInt(cityToken[1]));

		}

		return neighboors;

	}

	/**
	 * All cities hash table
	 * 
	 * @return
	 */
	public static HashMap<String, HashMap<String, Integer>> getCitiesHash(ArrayList<City> cities) {

		HashMap<String, HashMap<String, Integer>> allCitiesHash = new HashMap<String, HashMap<String, Integer>>();

		for (City city : cities) {
			allCitiesHash.put(city.getName(), city.getNeighboors());
		}
		return allCitiesHash;
	}




	public static HashMap<String, HashMap<City, Integer>> getCitiesHashObject(ArrayList<City> cities) {
		HashMap<String, HashMap<City, Integer>> allCitiesHash = new HashMap<String, HashMap<City, Integer>>();

		for (City city : cities) {
			allCitiesHash.put(city.getName(), getNeighboors(city.getName()));
		}
		return allCitiesHash;
	}

	private static HashMap<City, Integer> getNeighboors(String city) {

		ArrayList<City> cities = getCities();
		HashMap<City, Integer> neighbors = new HashMap<City, Integer>();
		City cte = getCityFromList(city, cities);

		for (Entry<String, Integer> entry : cte.getNeighboors().entrySet()) {
			City n = getCityFromList(entry.getKey(), cities);
			neighbors.put(n, entry.getValue());

		}

		return neighbors;
	}



	public static City getCityFromList(String input, ArrayList<City> allCities) {

		City structure = null;
		for (City city : allCities) {
			if (city.getName().equals(input)) {
				structure = city;

			}
		}

		return structure;

	}
//	/**
//	 * Get all locations on a map
//	 * @param cities
//	 * @return
//	 */
//	
//	public static ArrayList<String> getLocations(ArrayList<City> cities){
//		
//		ArrayList<String> locations = new ArrayList<Location>();
//		
//		for (City city: cities) {
//			locations.add(city.getLocation());
//		}
//		
//		return locations;
//		
//	}

}
