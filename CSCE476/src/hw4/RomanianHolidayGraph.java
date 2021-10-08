package hw4;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

/***
 * Implement the data structures representing Romania's map and the search
 * algorithms for conducting graph search
 * 
 * @author jaellekondohoma
 *
 */
public class RomanianHolidayGraph {
	private static RomaninaHolidayUtilities utils = new RomaninaHolidayUtilities();
	private final static String goal = "Bucharest";
	
	public RomanianHolidayGraph() {
		RomanianHolidayGraph.utils = new RomaninaHolidayUtilities();

	}
	
	/**
	 * A* search strategy
	 * 
	 * @param root
	 * @return
	 */
	
	public ArrayList<String> aStarBestFirstSearch(String root) {
		ArrayList<City> finalPath = new ArrayList<City>();
		ArrayList<String> runInformation = new ArrayList<String>();

		City currNode = RomaninaHolidayUtilities.getCityFromList(root);

		HashMap<ArrayList<City>, Integer> frontier = new HashMap<ArrayList<City>, Integer>();
		// add first node of where we're at
		ArrayList<City> pathChoice = new ArrayList<City>();
		currNode.setVisited(true);
		pathChoice.add(currNode);
		HashMap<City, Integer> neighbors = utils.getNeighboors(currNode);

//		System.out.println(neighbors);

//		System.out.printf("%s | %-12s | %s \n", "Location", "Choices", "Cost");
//		System.out.println("_________________________________________________________________");

		ArrayList<ArrayList<City>> fringe = RomaninaHolidayUtilities.generateFringeG(neighbors, pathChoice);
		frontier = utils.addToFrontierStarSearchG(fringe, frontier);

		int runs = 0;

		if (!root.equals(goal)) {

			Instant startTime = Instant.now();
			while (!currNode.getName().equals(goal)) {

//				System.out.printf("%s \n", currNode);

				for (Entry<ArrayList<City>, Integer> entry : frontier.entrySet()) {
					ArrayList<City> path = entry.getKey();
					int cost = entry.getValue();
//					System.out.printf("\t%-100s %d\n", path, cost);

				}

				// find least cost fringe in frontier
				ArrayList<City> least = RomaninaHolidayUtilities.leastCostFrontierG(frontier);
//				System.out.println("\t\t\tEXPAND: " + least);

				// expand that fringe
				currNode = least.get(least.size() - 1);

				frontier = new HashMap<ArrayList<City>, Integer>(
						utils.expandStarSearch(currNode, least, frontier));

//				System.out.println("_________________________________________________________________");
				runs++;
//				System.out.println(runs);

				if (currNode.getName().equals(goal)) {
					finalPath = new ArrayList<City>(least);
//					System.out.println("\t\t\tDESTINATION REACHED! cost: " + utils.pathCostG(least));
				}
			}
			Instant endTime = Instant.now();
			Duration searchTime = Duration.between(startTime, endTime);

			int nodes = finalPath.size();

			runInformation.add(root);
			runInformation.add(Integer.toString(nodes));
			runInformation.add(finalPath.toString());
			runInformation.add(Integer.toString(utils.pathCostG(finalPath)));
			runInformation.add(Integer.toString(searchTime.getNano()));
		} else {

			finalPath.add(RomaninaHolidayUtilities.getCityFromList(root));
			runInformation.add(root);
			runInformation.add("1");
			runInformation.add(finalPath.toString());
			runInformation.add(Integer.toString(utils.pathCostG(finalPath)));
			runInformation.add("0");

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
		
		ArrayList<City> finalPath = new ArrayList<City>();
		ArrayList<String> runInformation = new ArrayList<String>();

		City currNode = RomaninaHolidayUtilities.getCityFromList(root);

		HashMap<ArrayList<City>, Integer> frontier = new HashMap<ArrayList<City>, Integer>();
		// add first node of where we're at
		ArrayList<City> pathChoice = new ArrayList<City>();
		currNode.setVisited(true);
		pathChoice.add(currNode);
		HashMap<City, Integer> neighbors = utils.getNeighboors(currNode);

//		System.out.println(neighbors);

//		System.out.printf("%s | %-12s | %s \n", "Location", "Choices", "Cost");
//		System.out.println("_________________________________________________________________");

		ArrayList<ArrayList<City>> fringe = RomaninaHolidayUtilities.generateFringeG(neighbors, pathChoice);
		frontier = utils.addToFrontierGreedyBestFirstG(fringe, frontier);

		int runs = 0;

		if (!root.equals(goal)) {

			Instant startTime = Instant.now();
			while (!currNode.getName().equals(goal)) {

//				System.out.printf("%s \n", currNode);

				for (Entry<ArrayList<City>, Integer> entry : frontier.entrySet()) {
					ArrayList<City> path = entry.getKey();
					int cost = entry.getValue();
//					System.out.printf("\t%-100s %d\n", path, cost);

				}

				// find least cost fringe in frontier
				ArrayList<City> least = RomaninaHolidayUtilities.leastCostFrontierG(frontier);
//				System.out.println("\t\t\tEXPAND: " + least);

				// expand that fringe
				currNode = least.get(least.size() - 1);

				frontier = new HashMap<ArrayList<City>, Integer>(
						utils.expandGreedyBestFirst(currNode, least, frontier));

//				System.out.println("_________________________________________________________________");
				runs++;
//				System.out.println(runs);

				if (currNode.getName().equals(goal)) {
					finalPath = new ArrayList<City>(least);
//					System.out.println("\t\t\tDESTINATION REACHED! cost: " + utils.pathCostG(least));
				}
			}
			Instant endTime = Instant.now();
			Duration searchTime = Duration.between(startTime, endTime);

			int nodes = finalPath.size();

			runInformation.add(root);
			runInformation.add(Integer.toString(nodes));
			runInformation.add(finalPath.toString());
			runInformation.add(Integer.toString(utils.pathCostG(finalPath)));
			runInformation.add(Integer.toString(searchTime.getNano()));
		} else {

			finalPath.add(RomaninaHolidayUtilities.getCityFromList(root));
			runInformation.add(root);
			runInformation.add("1");
			runInformation.add(finalPath.toString());
			runInformation.add(Integer.toString(utils.pathCostG(finalPath)));
			runInformation.add("0");

		}

		return runInformation;
		
	}

	
	
	
	
	/**
	 * uninformed search strategy
	 * 
	 * @param root
	 * @return
	 */

	public ArrayList<String> uniformCostSearch(String root) {

		ArrayList<City> finalPath = new ArrayList<City>();
		ArrayList<String> runInformation = new ArrayList<String>();

		City currNode = RomaninaHolidayUtilities.getCityFromList(root);

		HashMap<ArrayList<City>, Integer> frontier = new HashMap<ArrayList<City>, Integer>();
		// add first node of where we're at
		ArrayList<City> pathChoice = new ArrayList<City>();
		currNode.setVisited(true);
		pathChoice.add(currNode);
		HashMap<City, Integer> neighbors = utils.getNeighboors(currNode);

//		System.out.println(neighbors);

//		System.out.printf("%s | %-12s | %s \n", "Location", "Choices", "Cost");
//		System.out.println("_________________________________________________________________");

		ArrayList<ArrayList<City>> fringe = RomaninaHolidayUtilities.generateFringeG(neighbors, pathChoice);
		frontier = utils.addToFrontierG(fringe, frontier);

		int runs = 0;

		if (!root.equals(goal)) {

			Instant startTime = Instant.now();
			while (!currNode.getName().equals(goal)) {

//				System.out.printf("%s \n", currNode);

				for (Entry<ArrayList<City>, Integer> entry : frontier.entrySet()) {
					ArrayList<City> path = entry.getKey();
					int cost = entry.getValue();
//					System.out.printf("\t%-100s %d\n", path, cost);

				}

				// find least cost fringe in frontier
				ArrayList<City> least = RomaninaHolidayUtilities.leastCostFrontierG(frontier);
//				System.out.println("\t\t\tEXPAND: " + least);

				// expand that fringe
				currNode = least.get(least.size() - 1);

				frontier = new HashMap<ArrayList<City>, Integer>(
						utils.expand(currNode, least, frontier));

//				System.out.println("_________________________________________________________________");
				runs++;
//				System.out.println(runs);

				if (currNode.getName().equals(goal)) {
					finalPath = new ArrayList<City>(least);
//					System.out.println("\t\t\tDESTINATION REACHED! cost: " + utils.pathCostG(least));
				}
			}
			Instant endTime = Instant.now();
			Duration searchTime = Duration.between(startTime, endTime);

			int nodes = finalPath.size();

			runInformation.add(root);
			runInformation.add(Integer.toString(nodes));
			runInformation.add(finalPath.toString());
			runInformation.add(Integer.toString(utils.pathCostG(finalPath)));
			runInformation.add(Integer.toString(searchTime.getNano()));
		} else {

			finalPath.add(RomaninaHolidayUtilities.getCityFromList(root));
			runInformation.add(root);
			runInformation.add("1");
			runInformation.add(finalPath.toString());
			runInformation.add(Integer.toString(utils.pathCostG(finalPath)));
			runInformation.add("0");

		}

		return runInformation;

	}

}
