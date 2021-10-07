
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
	 * @param root
	 * @return
	 */

	public static ArrayList<String> uniformCostSearch(String root) {

		ArrayList<String> finalPath = new ArrayList<String>();
		ArrayList<String> runInformation = new ArrayList<String>();

		String currNode = RomanianHolidayUtilities.getCityFromList(root).getName();

		HashMap<ArrayList<String>, Integer> frontier = new HashMap<ArrayList<String>, Integer>();
		// add first node of where we're at
		ArrayList<String> pathChoice = new ArrayList<String>();
		pathChoice.add(currNode);
		HashMap<String, Integer> neighbors = allCitiesHash.get(currNode);

//		System.out.printf("%s | %-12s | %s \n", "Location", "Choices", "Cost");
//		System.out.println("_________________________________________________________________");

		ArrayList<ArrayList<String>> fringe = RomanianHolidayUtilities.generateFringe(neighbors, pathChoice);
		frontier = RomanianHolidayUtilities.addToFrontier(fringe, frontier);

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
				ArrayList<String> least = RomanianHolidayUtilities.leastCostFrontier(frontier);
//				System.out.println("\t\t\tEXPAND: " + least);

				// expand that fringe
				currNode = least.get(least.size() - 1);
				frontier = new HashMap<ArrayList<String>, Integer>(RomanianHolidayUtilities.expand(currNode, least, frontier));

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
			runInformation.add(Integer.toString(RomanianHolidayUtilities.pathCost(finalPath)));
			runInformation.add(Integer.toString(searchTime.getNano()));
		} else {
			finalPath.add(root);
			runInformation.add(root);
			runInformation.add("1");
			runInformation.add(finalPath.toString());
			runInformation.add(Integer.toString(RomanianHolidayUtilities.pathCost(finalPath)));
			runInformation.add("0");

//			System.out.println(runInformation);

		}

		return runInformation;

	}

	/**
	 * greedy search strategy
	 * 
	 * @param root
	 * @return
	 */
	public static ArrayList<String> greedyBestFirstSearch(String root) {

		ArrayList<String> finalPath = new ArrayList<String>();
		ArrayList<String> runInformation = new ArrayList<String>();

		String currNode = RomanianHolidayUtilities.getCityFromList(root).getName();

		HashMap<ArrayList<String>, Integer> frontier = new HashMap<ArrayList<String>, Integer>();
		// add first node of where we're at
		ArrayList<String> pathChoice = new ArrayList<String>();
		pathChoice.add(currNode);
		HashMap<String, Integer> neighbors = allCitiesHash.get(currNode);

//		System.out.printf("%s | %-12s | %s \n", "Location", "Choices", "Cost");
//		System.out.println("_________________________________________________________________");

		ArrayList<ArrayList<String>> fringe = RomanianHolidayUtilities.generateFringe(neighbors, pathChoice);
		frontier = RomanianHolidayUtilities.addToFrontierGreedyBestFirst(fringe, frontier);

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
				ArrayList<String> least = RomanianHolidayUtilities.leastCostFrontier(frontier);
//				System.out.println("\t\t\tEXPAND: " + least);

				// expand that fringe
				currNode = least.get(least.size() - 1);
				frontier = new HashMap<ArrayList<String>, Integer>(RomanianHolidayUtilities.expandGreedyBestFirst(currNode, least, frontier));

//				System.out.println("_________________________________________________________________");
				runs++;
//				System.out.println(runs);

				if (currNode.equals(goal)) {
					finalPath = new ArrayList<String>(least);
//					System.out.println("\t\t\tDESTINATION REACHED! cost: " + RomanianHolidayUtilities.pathCost(least));
				}
			}
			Instant endTime = Instant.now();
			Duration searchTime = Duration.between(startTime, endTime);

			int nodes = finalPath.size();

			runInformation.add(root);
			runInformation.add(Integer.toString(nodes));
			runInformation.add(finalPath.toString());
			runInformation.add(Integer.toString(RomanianHolidayUtilities.pathCost(finalPath)));
			runInformation.add(Integer.toString(searchTime.getNano()));
		} else {
			
			finalPath.add(root);
			runInformation.add(root);
			runInformation.add("1");
			runInformation.add(finalPath.toString());
			runInformation.add(Integer.toString(RomanianHolidayUtilities.pathCost(finalPath)));
			runInformation.add("0");

//			System.out.println(runInformation);

		}

		return runInformation;

	}

	/**
	 * A* search strategy
	 * 
	 * @param root
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
				int gn = RomanianHolidayUtilities.pathCost(pathChoice);

				int hn = RomanianHolidayUtilities.getCityFromList(neighbor).getH();
				int fn = gn + hn;

//				System.out.println("\n" + neighbor);

				choices.put(neighbor, fn);

				System.out.printf("%17s| %-4d |  %-7d | %-4d |\n", neighbor, gn, hn, fn);

//				pathSofar = temp;
			}

//			System.out.println(choices);
			String choice = RomanianHolidayUtilities.leastCost(choices);
			currentNode = choice;
			path.add(choice);
//			System.out.println(pathSofar.toString());

			cost = RomanianHolidayUtilities.pathCost(path);

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


}
