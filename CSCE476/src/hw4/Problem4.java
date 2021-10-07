package hw4;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Report the results of graph search and tree search applied to each city in
 * Romania
 * 
 * @author jaellekondohoma
 *
 */
public class Problem4 {

	public static void main(String[] args) {

		ArrayList<String> cities = RomanianHolidayUtilities.allCitiesFromList();
		Collections.sort(cities);
		displaySearchResult(cities, "UCST");
		displaySearchResult(cities, "GRDT");
		displaySearchResult(cities, "A*T");

	}

	public static void displaySearchResult(ArrayList<String> cities, String search) {

		switch (search) {

		case "UCST":
			System.out.println(
					"________________________________________________________________________________________________________________________________________________________");
			System.out.printf("%65s\n", "Uniform Cost Tree Search");
			System.out.println(
					"________________________________________________________________________________________________________________________________________________________");
			System.out.printf("%15s | %11s | \t\t\t%-45s | %s | %s\n", "City Name", "#nodes visited",
					"Path to Bucharest", "Total cost of path", "CPU time");
			System.out.println(
					"________________________________________________________________________________________________________________________________________________________");

			for (int i = 0; i < cities.size(); i++) {
				ArrayList<String> runInfo = RomanianHolidayTree.uniformCostSearch(cities.get(i));

				displayRunInformation(runInfo);

			}
			break;

		case "A*T":

			System.out.println(
					"____________________________________________________________________________________________________________________________________________");
			System.out.printf("%69s\n", "Best First A* Tree Search");
			System.out.println(
					"____________________________________________________________________________________________________________________________________________");
			System.out.printf("%-15s | %s | \t\t\t%-45s | %s | %s\n", "City Name", "#nodes visited", "Path to Bucharest",
					"Total cost of path", "CPU time");
			System.out.println(
					"____________________________________________________________________________________________________________________________________________");

			for (int i = 0; i < cities.size(); i++) {
				ArrayList<String> runInfo = RomanianHolidayTree.aStarBestFirstSearch(cities.get(i));
				displayRunInformation(runInfo);

			}

			break;

		case "GRDT":

			System.out.println(
					"________________________________________________________________________________________________________________________________________________________");
			System.out.printf("%65s\n", "Greedy Best First Tree Search");
			System.out.println(
					"________________________________________________________________________________________________________________________________________________________");
			System.out.printf("%15s | %11s | \t\t\t%-45s | %s | %s\n", "City Name", "#nodes visited",
					"Path to Bucharest", "Total cost of path", "CPU time");
			System.out.println(
					"________________________________________________________________________________________________________________________________________________________");
			for (int i = 0; i < cities.size(); i++) {
				ArrayList<String> runInfo = RomanianHolidayTree.greedyBestFirstSearch(cities.get(i));

				displayRunInformation(runInfo);

			}
			break;
		default:

			System.out.println("Unrecognized Search: " + search);

			break;

		}

		System.out.println("\n\n");

	}

	public static void displayRunInformation(ArrayList<String> runInfo) {

		System.out.printf("%15s | %14s |  %65s | %18s | %s ns\n", runInfo.get(0), runInfo.get(1), runInfo.get(2),
				runInfo.get(3), runInfo.get(4));

	}

}
