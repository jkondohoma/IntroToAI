package hw4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;

public class RomanianHolidayUtilities {
	private static ArrayList<City> allCities = FileParser.getCities();
	private static HashMap<String, HashMap<String, Integer>> allCitiesHash = FileParser.getCitiesHash(allCities);

	public RomanianHolidayUtilities() {

		RomanianHolidayUtilities.allCities = FileParser.getCities();
		RomanianHolidayUtilities.allCitiesHash = FileParser.getCitiesHash(allCities);

	}

	/***
	 * given node to expand, least cost path and our current frontier get neighbors
	 * of node "to expand" and add those to our frontier
	 * 
	 * @param toExpand
	 * @param least
	 * @param frontier
	 * @return
	 */
	public HashMap<ArrayList<String>, Integer> expand(String toExpand, ArrayList<String> least,
			HashMap<ArrayList<String>, Integer> frontier) {

		HashMap<ArrayList<String>, Integer> newFrontier = new HashMap<ArrayList<String>, Integer>();

		HashMap<String, Integer> neighbors = allCitiesHash.get(toExpand);
		ArrayList<ArrayList<String>> fringe = generateFringe(neighbors, least);

		newFrontier = addToFrontier(fringe, frontier);

		newFrontier.remove(least);

		return newFrontier;
	}

	public HashMap<ArrayList<City>, Integer> expand(City toExpand, ArrayList<City> least,
			HashMap<ArrayList<City>, Integer> frontier) {

		HashMap<ArrayList<City>, Integer> newFrontier = new HashMap<ArrayList<City>, Integer>();

		toExpand.setVisited(true);
		HashMap<City, Integer> neighbors = getNeighboors(toExpand);
		ArrayList<ArrayList<City>> fringe = generateFringeG(neighbors, least);

		newFrontier = addToFrontierG(fringe, frontier);

		newFrontier.remove(least);

		return newFrontier;
	}

	/**
	 * given a possible path (fringe) and it to our frontier
	 * 
	 * @param fringe
	 * @param frontier
	 * @return
	 */
	public static HashMap<ArrayList<String>, Integer> addToFrontier(ArrayList<ArrayList<String>> fringe,
			HashMap<ArrayList<String>, Integer> frontier) {

		HashMap<ArrayList<String>, Integer> newFrontier = new HashMap<ArrayList<String>, Integer>(frontier);

		for (ArrayList<String> list : fringe) {
			int cost = pathCost(list);
			newFrontier.put(list, cost);
		}

		return newFrontier;

	}

	public HashMap<ArrayList<City>, Integer> addToFrontierG(ArrayList<ArrayList<City>> fringe,
			HashMap<ArrayList<City>, Integer> frontier) {

		HashMap<ArrayList<City>, Integer> newFrontier = new HashMap<ArrayList<City>, Integer>(frontier);

		for (ArrayList<City> list : fringe) {
			int cost = pathCostG(list);
			newFrontier.put(list, cost);
		}

		return newFrontier;

	}

	/**
	 * given a list of neighbors and a possible path choice generate a new fringe
	 * (new path) that includes the neighbors
	 * 
	 * @param neighbors
	 * @param pathChoice
	 * @return
	 */

	public static ArrayList<ArrayList<String>> generateFringe(HashMap<String, Integer> neighbors,
			ArrayList<String> pathChoice) {

		ArrayList<ArrayList<String>> fringe = new ArrayList<ArrayList<String>>();
		for (Entry<String, Integer> entry : neighbors.entrySet()) {
			ArrayList<String> pathChoiceOption = new ArrayList<String>(pathChoice);
			pathChoiceOption.add(entry.getKey());
			fringe.add(pathChoiceOption);
//		System.out.println(pathChoiceOption);

		}

		return fringe;
	}

	public static ArrayList<ArrayList<City>> generateFringeG(HashMap<City, Integer> neighbors,
			ArrayList<City> pathChoice) {

		ArrayList<ArrayList<City>> fringe = new ArrayList<ArrayList<City>>();

		for (Entry<City, Integer> entry : neighbors.entrySet()) {
			ArrayList<City> pathChoiceOption = new ArrayList<City>(pathChoice);

			City option = entry.getKey();
			if (!option.getVisisted()) {
				pathChoiceOption.add(entry.getKey());
				fringe.add(pathChoiceOption);
			}

//		System.out.println(pathChoiceOption);

		}

		return fringe;
	}

	/**
	 * given our frontier (every possible path) choose least costly where cost is
	 * path cost of every possible path
	 * 
	 * @param frontier
	 * @return
	 */

	public static ArrayList<City> leastCostFrontierG(HashMap<ArrayList<City>, Integer> frontier) {
		ArrayList<City> least = new ArrayList<City>();

		Collection<Integer> values = frontier.values();
		ArrayList<Integer> listOfValues = new ArrayList<>(values);
		Object[] keys = frontier.keySet().toArray();

		least = (ArrayList<City>) keys[0];
		int leastCost = listOfValues.get(0);

//		System.out.println(leastCost);

		for (Entry<ArrayList<City>, Integer> entry : frontier.entrySet()) {

			int cost = entry.getValue();
			if (cost < leastCost) {
				ArrayList<City> path = entry.getKey();
//				System.out.println("yes");
				least = path;
				leastCost = cost;
			}

		}

//		System.out.println(leastCost);

		return least;
	}

	public static ArrayList<String> leastCostFrontier(HashMap<ArrayList<String>, Integer> frontier) {
		ArrayList<String> least = new ArrayList<String>();

		Collection<Integer> values = frontier.values();
		ArrayList<Integer> listOfValues = new ArrayList<>(values);
		Object[] keys = frontier.keySet().toArray();

		least = (ArrayList<String>) keys[0];
		int leastCost = listOfValues.get(0);

//		System.out.println(leastCost);

		for (Entry<ArrayList<String>, Integer> entry : frontier.entrySet()) {

			int cost = entry.getValue();
			if (cost < leastCost) {
				ArrayList<String> path = entry.getKey();
//				System.out.println("yes");
				least = path;
				leastCost = cost;
			}

		}

//		System.out.println(leastCost);

		return least;
	}

	/**
	 * given a possible path (fringe) and it to our frontier, cost is straight line
	 * distance
	 * 
	 * @param fringe
	 * @param frontier
	 * @return
	 */
	public static HashMap<ArrayList<String>, Integer> addToFrontierGreedyBestFirst(ArrayList<ArrayList<String>> fringe,
			HashMap<ArrayList<String>, Integer> frontier) {

		HashMap<ArrayList<String>, Integer> newFrontier = new HashMap<ArrayList<String>, Integer>(frontier);

		for (ArrayList<String> list : fringe) {

			int cost = getCityFromList(list.get(list.size() - 1)).getH();
			newFrontier.put(list, cost);
		}

		return newFrontier;

	}

	public HashMap<ArrayList<City>, Integer> addToFrontierGreedyBestFirstG(ArrayList<ArrayList<City>> fringe,
			HashMap<ArrayList<City>, Integer> frontier) {

		HashMap<ArrayList<City>, Integer> newFrontier = new HashMap<ArrayList<City>, Integer>(frontier);

		for (ArrayList<City> list : fringe) {

			int cost = getCityFromList(list.get(list.size() - 1).getName()).getH();
			newFrontier.put(list, cost);
		}

		return newFrontier;

	}

	/**
	 * given a possible path (fringe) and it to our frontier, cost is h(n) + g(n)
	 * 
	 * @param fringe
	 * @param frontier
	 * @return
	 */
	public static HashMap<ArrayList<String>, Integer> addToFrontierStarSearch(ArrayList<ArrayList<String>> fringe,
			HashMap<ArrayList<String>, Integer> frontier) {

		HashMap<ArrayList<String>, Integer> newFrontier = new HashMap<ArrayList<String>, Integer>(frontier);

		for (ArrayList<String> list : fringe) {

			int gn = pathCost(list);
			int hn = getCityFromList(list.get(list.size() - 1)).getH();
			newFrontier.put(list, (gn + hn));
		}

		return newFrontier;

	}

	public HashMap<ArrayList<City>, Integer> addToFrontierStarSearchG(ArrayList<ArrayList<City>> fringe,
			HashMap<ArrayList<City>, Integer> frontier) {

		HashMap<ArrayList<City>, Integer> newFrontier = new HashMap<ArrayList<City>, Integer>(frontier);

		for (ArrayList<City> list : fringe) {

			int gn = pathCostG(list);
			int hn = getCityFromList(list.get(list.size() - 1).getName()).getH();
			newFrontier.put(list, (gn + hn));
		}

		return newFrontier;

	}

	/**
	 * given node to expand, least cost path and our current frontier get neighbors
	 * of node "to expand" and add those to our frontier cost is straight line
	 * distance
	 * 
	 * @param toExpand
	 * @param least
	 * @param frontier
	 * @return
	 */
	public static HashMap<ArrayList<String>, Integer> expandGreedyBestFirst(String toExpand, ArrayList<String> least,
			HashMap<ArrayList<String>, Integer> frontier) {
		HashMap<ArrayList<String>, Integer> newFrontier = new HashMap<ArrayList<String>, Integer>();

		HashMap<String, Integer> neighbors = allCitiesHash.get(toExpand);
		ArrayList<ArrayList<String>> fringe = generateFringe(neighbors, least);

		newFrontier = addToFrontierGreedyBestFirst(fringe, frontier);

		newFrontier.remove(least);

		return newFrontier;
	}

	public HashMap<ArrayList<City>, Integer> expandGreedyBestFirst(City toExpand, ArrayList<City> least,
			HashMap<ArrayList<City>, Integer> frontier) {
		HashMap<ArrayList<City>, Integer> newFrontier = new HashMap<ArrayList<City>, Integer>();

		HashMap<City, Integer> neighbors = getNeighboors(toExpand);
		ArrayList<ArrayList<City>> fringe = generateFringeG(neighbors, least);

		newFrontier = addToFrontierGreedyBestFirstG(fringe, frontier);

		newFrontier.remove(least);

		return newFrontier;
	}

	/**
	 * given node to expand, least cost path and our current frontier get neighbors
	 * of node "to expand" and add those to our frontier cost h(n) + g(n)
	 * 
	 * @param toExpand
	 * @param least
	 * @param frontier
	 * @return
	 */
	public static HashMap<ArrayList<String>, Integer> expandStarSearch(String toExpand, ArrayList<String> least,
			HashMap<ArrayList<String>, Integer> frontier) {
		HashMap<ArrayList<String>, Integer> newFrontier = new HashMap<ArrayList<String>, Integer>();

		HashMap<String, Integer> neighbors = allCitiesHash.get(toExpand);
		ArrayList<ArrayList<String>> fringe = generateFringe(neighbors, least);

		newFrontier = addToFrontierStarSearch(fringe, frontier);

		newFrontier.remove(least);

		return newFrontier;
	}

	public HashMap<ArrayList<City>, Integer> expandStarSearch(City toExpand, ArrayList<City> least,
			HashMap<ArrayList<City>, Integer> frontier) {
		HashMap<ArrayList<City>, Integer> newFrontier = new HashMap<ArrayList<City>, Integer>();

		HashMap<City, Integer> neighbors = getNeighboors(toExpand);
		ArrayList<ArrayList<City>> fringe = generateFringeG(neighbors, least);

		newFrontier = addToFrontierStarSearchG(fringe, frontier);

		newFrontier.remove(least);

		return newFrontier;
	}

	/**
	 * calculate path cost of given path
	 * 
	 * @param pathChoice
	 * @return
	 */
	public static int pathCost(ArrayList<String> pathChoice) {
		int gn = 0;
		String curr = pathChoice.get(0);
		for (int i = 1; i < pathChoice.size(); i++) {
			String next = pathChoice.get(i);
//			System.out.println(curr+" "+next);

			gn += neighborsP(curr, next);
			curr = next;
		}

		return gn;
	}

	public int pathCostG(ArrayList<City> pathChoice) {
		int gn = 0;
		City curr = pathChoice.get(0);
		for (int i = 1; i < pathChoice.size(); i++) {
			City next = pathChoice.get(i);
//			System.out.println(curr+" "+next);

			gn += neighborsP(curr, next);
			curr = next;
		}

		return gn;
	}

	/**
	 * given a hashmap of cities and cost function pick one with the least cost
	 * function
	 * 
	 * @param choices
	 * @return
	 */
	public static String leastCost(HashMap<String, Integer> choices) {
		String choice = "";
		Collection<Integer> values = choices.values();
		ArrayList<Integer> listOfValues = new ArrayList<>(values);
//		System.out.println(listOfValues);
		int least = listOfValues.get(0);
		for (Entry<String, Integer> entry : choices.entrySet()) {
			if (entry.getValue() < least) {
				choice = entry.getKey();
				least = entry.getValue();
			}

			for (Entry<String, Integer> entry1 : choices.entrySet()) {
				if (entry1.getValue() == least) {
					choice = entry1.getKey();
				}
			}

		}

		return choice;
	}

	/**
	 * Using allCitiesHtable, a function that takes the name of two cities cityOne
	 * and cityTwo, and returns the distance between them if they are directly
	 * connected or nil if they are not
	 * 
	 * @return
	 */
	public static int neighborsP(String cityOne, String cityTwo) {
		int dist = 0;
		HashMap<String, Integer> neighbors = allCitiesHash.get(cityOne);
		for (Entry<String, Integer> entry : neighbors.entrySet()) {

			if (entry.getKey().equals(cityTwo)) {
				dist = entry.getValue();
			}

		}

		return dist;
	}

	private int neighborsP(City cityOne, City cityTwo) {
		int dist = 0;
		HashMap<City, Integer> neighbors = getNeighboors(cityOne);
		for (Entry<City, Integer> entry : neighbors.entrySet()) {

			if (entry.getKey().equals(cityTwo)) {
				dist = entry.getValue();
			}

		}

		return dist;
	}

	/**
	 * Using allCitiesHtable,a function that takes the name of a city myCity and a
	 * number distance, then returns, for all direct neighbors within distance from
	 * myCity (LEQ), an association list of the structures of the neighbors of
	 * myCity and their distance to myCity
	 * 
	 * @return
	 */

	public static HashMap<String, Integer> neighborsWithinD(String myCity, int distance) {
		HashMap<String, Integer> neighbors = new HashMap<String, Integer>();
		City city = getCityFromList(myCity);

		for (Entry<String, Integer> entry : city.getNeighboors().entrySet()) {
			String cite = entry.getKey();
			int dist = entry.getValue();
			if (distance <= dist) {
				neighbors.put(cite, dist);
			}

		}

		return neighbors;
	}

	/**
	 * take the name of a city as input and return the list of structures of its
	 * direct neighbors using getCityFromHtable,
	 * 
	 * @param city
	 * @return
	 */

	public static ArrayList<String> neighborsUsingHtable(String city) {
		ArrayList<String> neighbors = new ArrayList<String>();

		Entry<String, HashMap<String, Integer>> struct = getCityFromHtable(city);

		for (Entry<String, Integer> entry : struct.getValue().entrySet()) {
			neighbors.add(entry.getKey());

		}

		return neighbors;

	}

	/**
	 * take the name of a city as input and return the list of structures of its
	 * direct neighbors using getCityFromList
	 * 
	 * @param city
	 */
	public static ArrayList<String> neighborsUsingList(String city) {
		ArrayList<String> neighbors = new ArrayList<String>();

		for (Entry<String, Integer> entry : getCityFromList(city).getNeighboors().entrySet()) {
			neighbors.add(entry.getKey());

		}

		return neighbors;

	}

	/**
	 * take the name of a city as input and return the corresponding structure from
	 * allCities list
	 * 
	 * @param city
	 * @return
	 */
	public static City getCityFromList(String input) {

		City structure = null;
//		System.out.println(input);
		for (City city : allCities) {
			if (city.getName().equals(input)) {
//				System.out.println("YES");
				structure = city;

			}
		}

		return structure;

	}

	/**
	 * take the name of a city as input and return the corresponding structure from
	 * allCities hash table
	 * 
	 * @param city
	 * @return
	 */

	public static Entry<String, HashMap<String, Integer>> getCityFromHtable(String city) {
		Entry<String, HashMap<String, Integer>> structure = null;
		for (Entry<String, HashMap<String, Integer>> entry : allCitiesHash.entrySet()) {
			String key = entry.getKey();

			if (city.equals(key)) {
				structure = entry;
			}

		}
		return structure;

	}

	public HashMap<City, Integer> getNeighboors(City city) {

		HashMap<City, Integer> allCitiesHash = new HashMap<City, Integer>();

		HashMap<String, Integer> neighbors = FileParser.getCitiesHash(allCities).get(city.getName());

		for (Entry<String, Integer> entry : neighbors.entrySet()) {
			City cte = getCityFromList(entry.getKey());
			allCitiesHash.put(cte, entry.getValue());

		}

		return allCitiesHash;
	}

	/**
	 * takes a variable, allCitiesHtable and returns a list of all the structures of
	 * cities on the map.
	 * 
	 * take the hash of the cities and return it back into a list
	 * 
	 * @param allCitieshash
	 */
	public static ArrayList<City> allCitiesFromHtable() {

		ArrayList<City> structures = new ArrayList<City>();

		for (Entry<String, HashMap<String, Integer>> entry : allCitiesHash.entrySet()) {
			String key = entry.getKey();
			HashMap<String, Integer> value = entry.getValue();

			for (Entry<String, Integer> cities : value.entrySet()) {

				String item = cities.getKey();
				City cite = getCityFromList(item);
				structures.add(cite);

			}

		}
		return structures;
	}

	/***
	 * takes a global variable, allCitiesList, and returns a list of all names of
	 * cities on the map.
	 * 
	 * @param allCities
	 * @return
	 */
	public static ArrayList<String> allCitiesFromList() {

		ArrayList<String> cities = new ArrayList<String>();
		for (City city : allCities) {
			cities.add(city.getName());
		}

		return cities;

	}

}
