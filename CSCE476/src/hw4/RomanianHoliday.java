
package hw4;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;

/***
 * Implement the data structures representing Romania's map and the search
 * algorithms for conducting search
 * 
 * @author jaellekondohoma
 *
 */

public class RomanianHoliday {

	private final static ArrayList<City> allCities = FileParser.getCities();
	private final static HashMap<String, HashMap<String, Integer>> allCitiesHash = FileParser.getCitiesHash(allCities);
	private final static String goal = "Bucharest";


	/**
	 * uninformed search strategy
	 * 
	 * @param cty
	 * @return
	 */

	public static ArrayList<String> uniformCostSearch(String root) {

		ArrayList<String> finalPath = new ArrayList<String>();
		ArrayList<String> runInformation = new ArrayList<String>();

		String currNode = getCityFromList(root).getName();

		HashMap<ArrayList<String>, Integer> frontier = new HashMap<ArrayList<String>, Integer>();
		// add first node of where we're at
		ArrayList<String> pathChoice = new ArrayList<String>();
		pathChoice.add(currNode);
		HashMap<String, Integer> neighbors = allCitiesHash.get(currNode);

//		System.out.printf("%s | %-12s | %s \n", "Location", "Choices", "Cost");
//		System.out.println("_________________________________________________________________");

		ArrayList<ArrayList<String>> fringe = generateFringe(neighbors, pathChoice);
		frontier = addToFrontier(fringe, frontier);
		

		int runs = 0;

		if (!root.equals(goal)) {
			Instant startTime = Instant.now();
			while (!currNode.equals(goal)) {

//				System.out.printf("%s \n", currNode);

				for (Entry<ArrayList<String>, Integer> entry : frontier.entrySet()) {
					ArrayList<String> path = entry.getKey();
					int cost = entry.getValue();
//					System.out.printf("\t%-80s %d\n", path, cost);

				}

				// find least cost fringe in frontier
				ArrayList<String> least = leastCostFrontier(frontier);
//				System.out.println("\t\t\tEXPAND: " + least);

				// expand that fringe
				currNode = least.get(least.size() - 1);
				frontier = new HashMap<ArrayList<String>, Integer>(expand(currNode, least, frontier));

//				System.out.println("_________________________________________________________________");
				runs++;
//				System.out.println(runs);

				if (currNode.equals(goal)) {
					finalPath = new ArrayList<String>(least);
//					System.out.println("\t\t\tDESTINATION REACHED! cost: " + pathCost(least));
				}
			}
			Instant endTime = Instant.now();
			Duration searchTime = Duration.between(startTime, endTime);

			int nodes = finalPath.size();

			runInformation.add(root);
			runInformation.add(Integer.toString(nodes));
			runInformation.add(finalPath.toString());
			runInformation.add(Integer.toString(pathCost(finalPath)));
			runInformation.add(Integer.toString(searchTime.getNano()));
		} else {	
			finalPath.add(root);
			runInformation.add(root);
			runInformation.add("1");
			runInformation.add(finalPath.toString());
			runInformation.add(Integer.toString(pathCost(finalPath)));
			runInformation.add("0");
			
//			System.out.println(runInformation);

		}

		return runInformation;

	}

	private static HashMap<ArrayList<String>, Integer> expand(String toExpand, ArrayList<String> least,
			HashMap<ArrayList<String>, Integer> frontier) {

		HashMap<ArrayList<String>, Integer> newFrontier = new HashMap<ArrayList<String>, Integer>();

		HashMap<String, Integer> neighbors = allCitiesHash.get(toExpand);
		ArrayList<ArrayList<String>> fringe = generateFringe(neighbors, least);

		newFrontier = addToFrontier(fringe, frontier);

		newFrontier.remove(least);

		return newFrontier;
	}

	private static HashMap<ArrayList<String>, Integer> addToFrontier(ArrayList<ArrayList<String>> fringe,
			HashMap<ArrayList<String>, Integer> frontier) {

		HashMap<ArrayList<String>, Integer> newFrontier = new HashMap<ArrayList<String>, Integer>(frontier);

		for (ArrayList<String> list : fringe) {
			int cost = pathCost(list);
			newFrontier.put(list, cost);
		}

		return newFrontier;

	}

	private static ArrayList<ArrayList<String>> generateFringe(HashMap<String, Integer> neighbors,
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

	private static ArrayList<String> leastCostFrontier(HashMap<ArrayList<String>, Integer> frontier) {
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

	public static ArrayList<String> greedyBestFirstearch(String root) {

		ArrayList<String> finalPath = new ArrayList<String>();
		ArrayList<String> runInformation = new ArrayList<String>();

		String currNode = getCityFromList(root).getName();

		HashMap<ArrayList<String>, Integer> frontier = new HashMap<ArrayList<String>, Integer>();
		// add first node of where we're at
		ArrayList<String> pathChoice = new ArrayList<String>();
		pathChoice.add(currNode);
		HashMap<String, Integer> neighbors = allCitiesHash.get(currNode);

		System.out.printf("%s | %-12s | %s \n", "Location", "Choices", "Cost");
		System.out.println("_________________________________________________________________");

		ArrayList<ArrayList<String>> fringe = generateFringe(neighbors, pathChoice);
		frontier = addToFrontierGreedyBestFirst(fringe, frontier);
		
		int runs = 0;

		if (!root.equals(goal)) {
			Instant startTime = Instant.now();
			while (!currNode.equals(goal)) {

				System.out.printf("%s \n", currNode);

				for (Entry<ArrayList<String>, Integer> entry : frontier.entrySet()) {
					ArrayList<String> path = entry.getKey();
					int cost = entry.getValue();
					System.out.printf("\t%-80s %d\n", path, cost);

				}

				// find least cost fringe in frontier
				ArrayList<String> least = leastCostFrontier(frontier);
				System.out.println("\t\t\tEXPAND: " + least);

				// expand that fringe
				currNode = least.get(least.size() - 1);
				frontier = new HashMap<ArrayList<String>, Integer>(expandGreedyBestFirst(currNode, least, frontier));

				System.out.println("_________________________________________________________________");
				runs++;
				System.out.println(runs);

				if (currNode.equals(goal)) {
					finalPath = new ArrayList<String>(least);
					System.out.println("\t\t\tDESTINATION REACHED! cost: " + pathCost(least));
				}
			}
			Instant endTime = Instant.now();
			Duration searchTime = Duration.between(startTime, endTime);

			int nodes = finalPath.size();

			runInformation.add(root);
			runInformation.add(Integer.toString(nodes));
			runInformation.add(finalPath.toString());
			runInformation.add(Integer.toString(pathCost(finalPath)));
			runInformation.add(Integer.toString(searchTime.getNano()));
		} else {	
			finalPath.add(root);
			runInformation.add(root);
			runInformation.add("1");
			runInformation.add(finalPath.toString());
			runInformation.add(Integer.toString(pathCost(finalPath)));
			runInformation.add("0");
			
//			System.out.println(runInformation);

		}

		return runInformation;

	}



	private static HashMap<ArrayList<String>, Integer> expandGreedyBestFirst(String toExpand,
			ArrayList<String> least, HashMap<ArrayList<String>, Integer> frontier) {
		HashMap<ArrayList<String>, Integer> newFrontier = new HashMap<ArrayList<String>, Integer>();

		HashMap<String, Integer> neighbors = allCitiesHash.get(toExpand);
		ArrayList<ArrayList<String>> fringe = generateFringe(neighbors, least);

		newFrontier = addToFrontierGreedyBestFirst(fringe, frontier);

		newFrontier.remove(least);

		return newFrontier;
	}

	private static HashMap<ArrayList<String>, Integer> addToFrontierGreedyBestFirst(ArrayList<ArrayList<String>> fringe,
			HashMap<ArrayList<String>, Integer> frontier) {
		
		HashMap<ArrayList<String>, Integer> newFrontier = new HashMap<ArrayList<String>, Integer>(frontier);

		for (ArrayList<String> list : fringe) {
			
			
			int cost = getCityFromList(list.get(list.size()-1)).getH();
			newFrontier.put(list, cost);
		}

		return newFrontier;

	}

	/**
	 * A* search for a given city with detailed output at each step
	 * 
	 * @param city
	 * @return
	 */
	public static ArrayList<String> aStarBestFirstSearch(String root) {

		ArrayList<String> runInformation = new ArrayList<String>();
		ArrayList<String> path = new ArrayList<String>();
		String currentNode = root;
		path.add(currentNode);

		int cost = 0;

		System.out.printf("%s | %-30s | %s \n", "Location", "Choices", "Choice");
		System.out.println("_________________________________________________________________");

		System.out.printf("%-8s %s | %s |  %-7s | %s |\n", currentNode, "Towards", "g(n)", "h(n)", "f(n)");
		int runs = 0;

		Instant startTime = Instant.now();
		while (!currentNode.equals(goal) && runs < 7) {
			HashMap<String, Integer> neighbors = allCitiesHash.get(currentNode);

//			System.out.println(neighbors);
			HashMap<String, Integer> choices = new HashMap<String, Integer>();
			ArrayList<String> currentPath = new ArrayList<String>(path);

			for (Entry<String, Integer> entry : neighbors.entrySet()) {

				String neighbor = entry.getKey();
				int distance = entry.getValue();
				ArrayList<String> pathChoice = new ArrayList<String>(currentPath);

				pathChoice.add(neighbor);
//				System.out.println(pathChoice);
				int gn = pathCost(pathChoice);

				int hn = getCityFromList(neighbor).getH();
				int fn = gn + hn;

//				System.out.println("\n" + neighbor);

				choices.put(neighbor, fn);

				System.out.printf("%17s| %-4d |  %-7d | %-4d |\n", neighbor, gn, hn, fn);

//				pathSofar = temp;
			}

//			System.out.println(choices);
			String choice = leastCost(choices);
			currentNode = choice;
			path.add(choice);
//			System.out.println(pathSofar.toString());

			cost = pathCost(path);

			neighbors = allCitiesHash.get(currentNode);
			System.out.printf("%60s\n", choice);
			System.out.println("_________________________________________________________________");
			System.out.printf("%s\n", choice);

			runs++;

		}
		Instant endTime = Instant.now();
		Duration searchTime = Duration.between(startTime, endTime);

		int nodes = path.size();
		if (currentNode.equals(goal)) {
			System.out.println("\t\t\tDESTINATION REACHED! cost: " + cost);
		}

		runInformation.add(root);
		runInformation.add(Integer.toString(nodes));
		runInformation.add(path.toString());
		runInformation.add(Integer.toString(cost));
		runInformation.add(Integer.toString(searchTime.getNano()));
		return runInformation;

	}

	/**
	 * calculate g(n) of given path
	 * 
	 * @param pathChoice
	 * @return
	 */
	private static int pathCost(ArrayList<String> pathChoice) {
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

	/**
	 * given a map hashmap of cities and cost function pick one with the least cost
	 * function
	 * 
	 * @param choices
	 * @return
	 */
	private static String leastCost(HashMap<String, Integer> choices) {
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
	 * chooses the node n closest to the goal such as h(n) is minimal
	 * 
	 * @param city
	 * @return
	 */
	public static ArrayList<String> greedyBestFirstSearch(String city) {
		return null;

	}

	/**
	 * Using allCitiesHtable, a function that takes the name of two cities cityOne
	 * and cityTwo, and returns the distance between them if they are directly
	 * connected or nil if they are not
	 * 
	 * @return
	 */
	private static int neighborsP(String cityOne, String cityTwo) {
		int dist = 0;
		HashMap<String, Integer> neighbors = allCitiesHash.get(cityOne);
		for (Entry<String, Integer> entry : neighbors.entrySet()) {

			if (entry.getKey().equals(cityTwo)) {
				dist = entry.getValue();
			}

		}

		return dist;
	}

	/**
	 * Using allCitiesHtable,a function that takes the name of a city myCity and a
	 * number distance, then returns, for all direct neighbors within distance from
	 * myCity ( ), an association list of the structures of the neighbors of myCity
	 * and their distance to myCity
	 * 
	 * @return
	 */

	private static ArrayList<String> neighborsWithinD() {
		ArrayList<String> neighbors = new ArrayList<String>();

		return null;
	}

	/**
	 * take the name of a city as input and return the list of structures of its
	 * direct neighbors using getCityFromHtable,
	 * 
	 * @param city
	 * @return
	 */

	private static ArrayList<String> neighborsUsingHtable(String city) {
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
	private static ArrayList<String> neighborsUsingList(String city) {
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
	private static City getCityFromList(String input) {

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

	private static Entry<String, HashMap<String, Integer>> getCityFromHtable(String city) {
		Entry<String, HashMap<String, Integer>> structure = null;
		for (Entry<String, HashMap<String, Integer>> entry : allCitiesHash.entrySet()) {
			String key = entry.getKey();

			if (city.equals(key)) {
				structure = entry;
			}

		}
		return structure;

	}

	/**
	 * takes a variable, allCitiesHtable and returns a list of all the structures of
	 * cities on the map.
	 * 
	 * given a city return a list of cities where the given city is part of
	 * neighboors
	 * 
	 * @param allCitieshash
	 */
	private static ArrayList<String> allCitiesFromHtable(String city) {

		ArrayList<String> structures = new ArrayList<String>();

		for (Entry<String, HashMap<String, Integer>> entry : allCitiesHash.entrySet()) {
			String key = entry.getKey();
			HashMap<String, Integer> value = entry.getValue();

			System.out.println(value);

			for (Entry<String, Integer> cities : value.entrySet()) {

				String item = cities.getKey();
				if (item.equals(city))
					structures.add(key);

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
