package hw4;

import java.util.ArrayList;

public class DisplaySearch {

	/**
	 * given type of search and list of city names run search for each individual
	 * city and display results
	 * 
	 * @param cities
	 * @param search
	 */
	public static void displaySearchResult(ArrayList<String> cities, String search) {

		switch (search) {

		case "UCST":
			System.out.println(
					"________________________________________________________________________________________________________________________________________________________");
			System.out.printf("%65s\n", "Uniform Cost Tree Search (Uninformed)");
			System.out.println(
					"________________________________________________________________________________________________________________________________________________________");
			System.out.printf("%15s | %11s | \t\t\t%-45s | %s | %s\n", "City Name", "#nodes visited",
					"Path to Bucharest", "Total cost of path", "CPU time");
			System.out.println(
					"________________________________________________________________________________________________________________________________________________________");

			for (int i = 0; i < cities.size(); i++) {
				ArrayList<String> runInfo1 = RomanianHolidayTree.uniformCostSearch(cities.get(i));

				displayRunInformation(runInfo1);

			}
			break;

		case "UCSG":
			System.out.println(
					"________________________________________________________________________________________________________________________________________________________");
			System.out.printf("%65s\n", "Uniform Cost Graph Search (Uninformed)");
			System.out.println(
					"________________________________________________________________________________________________________________________________________________________");
			System.out.printf("%15s | %11s | \t\t\t%-45s | %s | %s\n", "City Name", "#nodes visited",
					"Path to Bucharest", "Total cost of path", "CPU time");
			System.out.println(
					"________________________________________________________________________________________________________________________________________________________");

			for (int i = 0; i < cities.size(); i++) {
				RomanianHolidayGraph graphRun = new RomanianHolidayGraph();
				ArrayList<String> runInfo2 = graphRun.uniformCostSearch(cities.get(i));

				displayRunInformation(runInfo2);

			}
			break;

		case "A*T":

			System.out.println(
					"____________________________________________________________________________________________________________________________________________");
			System.out.printf("%80s\n", "Best First A* Tree Search");
			System.out.println(
					"____________________________________________________________________________________________________________________________________________");
			System.out.printf("%-15s | %s | \t\t\t%-45s | %s | %s\n", "City Name", "#nodes visited",
					"Path to Bucharest", "Total cost of path", "CPU time");
			System.out.println(
					"____________________________________________________________________________________________________________________________________________");

			for (int i = 0; i < cities.size(); i++) {
				ArrayList<String> runInfo = RomanianHolidayTree.aStarBestFirstSearch(cities.get(i));
				displayRunInformation(runInfo);

			}

			break;

		case "A*G":

			System.out.println(
					"____________________________________________________________________________________________________________________________________________");
			System.out.printf("%80s\n", "Best First A* Graph Search");
			System.out.println(
					"____________________________________________________________________________________________________________________________________________");
			System.out.printf("%-15s | %s | \t\t\t%-45s | %s | %s\n", "City Name", "#nodes visited",
					"Path to Bucharest", "Total cost of path", "CPU time");
			System.out.println(
					"____________________________________________________________________________________________________________________________________________");

			for (int i = 0; i < cities.size(); i++) {
				RomanianHolidayGraph graphRun = new RomanianHolidayGraph();
				ArrayList<String> runInfo = graphRun.aStarBestFirstSearch(cities.get(i));
				displayRunInformation(runInfo);

			}

			break;

		case "GRDG":

			System.out.println(
					"________________________________________________________________________________________________________________________________________________________");
			System.out.printf("%80s\n", "Greedy Best First Graph Search");
			System.out.println(
					"________________________________________________________________________________________________________________________________________________________");
			System.out.printf("%15s | %11s | \t\t\t%-45s | %s | %s\n", "City Name", "#nodes visited",
					"Path to Bucharest", "Total cost of path", "CPU time");
			System.out.println(
					"________________________________________________________________________________________________________________________________________________________");
			for (int i = 0; i < cities.size(); i++) {
				RomanianHolidayGraph graphRun = new RomanianHolidayGraph();
				ArrayList<String> runInfo3 = graphRun.greedyBestFirstSearch(cities.get(i));

				displayRunInformation(runInfo3);

			}
			break;

		case "GRDT":

			System.out.println(
					"________________________________________________________________________________________________________________________________________________________");
			System.out.printf("%80s\n", "Greedy Best First Tree Search");
			System.out.println(
					"________________________________________________________________________________________________________________________________________________________");
			System.out.printf("%15s | %11s | \t\t\t%-45s | %s | %s\n", "City Name", "#nodes visited",
					"Path to Bucharest", "Total cost of path", "CPU time");
			System.out.println(
					"________________________________________________________________________________________________________________________________________________________");
			for (int i = 0; i < cities.size(); i++) {
				ArrayList<String> runInfo3 = RomanianHolidayTree.greedyBestFirstSearch(cities.get(i));

				displayRunInformation(runInfo3);

			}
			break;
		default:

			System.out.println("Unrecognized Search: " + search);

			break;

		}

		System.out.println("\n\n");

	}

	public static void displayRunInformation(ArrayList<String> runInfo) {

		System.out.printf("%15s | %14s |  %65s | %18s | %10s ns\n", runInfo.get(0), runInfo.get(1), runInfo.get(2),
				runInfo.get(3), runInfo.get(4));

	}

}
