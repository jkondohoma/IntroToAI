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
 * algorithms for conducting tree search
 * 
 * @author jaellekondohoma
 *
 */

public class RomanianHolidayTree {

	private final ArrayList<City> allCities = FileParser.getCities();
	private final HashMap<String, HashMap<String, Integer>> allCitiesHash = FileParser.getCitiesHash(allCities);
	private final String goal = "Bucharest";
	private final RomanianHolidayUtilities utils = new RomanianHolidayUtilities();

	/**
	 * uninformed search strategy
	 * 
	 * @param root
	 * @return
	 */

	public ArrayList<String> uniformCostSearch(String root) {

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

		ArrayList<ArrayList<String>> fringe = utils.generateFringe(neighbors, pathChoice);
		frontier = RomanianHolidayUtilities.addToFrontier(fringe, frontier);

		int nodes = 1;

		if (!root.equals(goal)) {
			Instant startTime = Instant.now();
			while (!currNode.equals(goal)) {

//				System.out.printf("%s \n", currNode);

				for (Entry<ArrayList<String>, Integer> entry : frontier.entrySet()) {
					ArrayList<String> path = entry.getKey();
					int cost = entry.getValue();
//					System.out.printf("\t%-80s %d\n", path, cost);

				}

//				 find least cost fringe in frontier
				ArrayList<String> least = RomanianHolidayUtilities.leastCostFrontier(frontier);
//				System.out.println("\t\t\tEXPAND: " + least);

				// expand that fringe
				currNode = least.get(least.size() - 1);
				frontier = new HashMap<ArrayList<String>, Integer>(
						utils.expand(currNode, least, frontier));

//				System.out.println("_________________________________________________________________");
				nodes++;
//				System.out.println(runs);

				if (currNode.equals(goal)) {
					finalPath = new ArrayList<String>(least);
//					System.out.println("\t\t\tDESTINATION REACHED! cost: " + RomanianHolidayUtilities.pathCost(least));
				}
			}
			Instant endTime = Instant.now();
			Duration searchTime = Duration.between(startTime, endTime);

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
	public ArrayList<String> greedyBestFirstSearch(String root) {

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

		ArrayList<ArrayList<String>> fringe = utils.generateFringe(neighbors, pathChoice);
		frontier = RomanianHolidayUtilities.addToFrontierGreedyBestFirst(fringe, frontier);

		int nodes = 1;

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
				frontier = new HashMap<ArrayList<String>, Integer>(
						utils.expandGreedyBestFirst(currNode, least, frontier));

//				System.out.println("_________________________________________________________________");
				nodes++;
//				System.out.println(runs);

				if (currNode.equals(goal)) {
					finalPath = new ArrayList<String>(least);
//					System.out.println("\t\t\tDESTINATION REACHED! cost: " + RomanianHolidayUtilities.pathCost(least));
				}
			}
			Instant endTime = Instant.now();
			Duration searchTime = Duration.between(startTime, endTime);


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
	public ArrayList<String> aStarBestFirstSearch(String root) {

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

		ArrayList<ArrayList<String>> fringe = utils.generateFringe(neighbors, pathChoice);
		frontier = RomanianHolidayUtilities.addToFrontierStarSearch(fringe, frontier);

		int nodes = 1;

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
				frontier = new HashMap<ArrayList<String>, Integer>(
						utils.expandStarSearch(currNode, least, frontier));

//				System.out.println("_________________________________________________________________");
				nodes++;
//				System.out.println(runs);

				if (currNode.equals(goal)) {
					finalPath = new ArrayList<String>(least);
//					System.out.println("\t\t\tDESTINATION REACHED! cost: " + RomanianHolidayUtilities.pathCost(least));
				}
			}
			Instant endTime = Instant.now();
			Duration searchTime = Duration.between(startTime, endTime);

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

}
