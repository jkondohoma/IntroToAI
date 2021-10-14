package hw4;

import java.io.FileWriter;
import java.io.IOException;
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

		StringBuilder sb = new StringBuilder();
		ArrayList<String> runInfo = new ArrayList<String>();
		String fileName = "";

		sb.append("City Name,#nodes visisted,Path to Bucharest,Total cost of path,CPU time\n");
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
				RomanianHolidayTree treeRun = new RomanianHolidayTree();
				runInfo = treeRun.uniformCostSearch(cities.get(i));
				String path = runInfo.get(2).replace(',', ';');
				sb.append(runInfo.get(0) + "," + runInfo.get(1) + "," + path + "," + runInfo.get(3) + ","
						+ runInfo.get(4) + "\n");
				fileName = "uniformCostTreeSearch.csv";
				displayRunInformation(runInfo);

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
				runInfo = graphRun.uniformCostSearch(cities.get(i));
				String path = runInfo.get(2).replace(',', ';');
				sb.append(runInfo.get(0) + "," + runInfo.get(1) + "," + path + "," + runInfo.get(3) + ","
						+ runInfo.get(4) + "\n");
				fileName = "uniformCostGraphSearch.csv";
				displayRunInformation(runInfo);

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
				RomanianHolidayTree treeRun = new RomanianHolidayTree();
				runInfo = treeRun.aStarBestFirstSearch(cities.get(i));
				String path = runInfo.get(2).replace(',', ';');
				sb.append(runInfo.get(0) + "," + runInfo.get(1) + "," + path + "," + runInfo.get(3) + ","
						+ runInfo.get(4) + "\n");
				fileName = "bestFirstAstarTreeSearch.csv";
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
				runInfo = graphRun.aStarBestFirstSearch(cities.get(i));
				String path = runInfo.get(2).replace(',', ';');
				sb.append(runInfo.get(0) + "," + runInfo.get(1) + "," + path + "," + runInfo.get(3) + ","
						+ runInfo.get(4) + "\n");
				fileName = "bestFirstAstarGraphSearch.csv";
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
				runInfo = graphRun.greedyBestFirstSearch(cities.get(i));
				String path = runInfo.get(2).replace(',', ';');
				sb.append(runInfo.get(0) + "," + runInfo.get(1) + "," + path + "," + runInfo.get(3) + ","
						+ runInfo.get(4) + "\n");
				fileName = "greedyBestFirstGraphSearch.csv";
				displayRunInformation(runInfo);

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
				RomanianHolidayTree treeRun = new RomanianHolidayTree();
				runInfo = treeRun.greedyBestFirstSearch(cities.get(i));
				String path = runInfo.get(2).replace(',', ';');
				sb.append(runInfo.get(0) + "," + runInfo.get(1) + "," + path + "," + runInfo.get(3) + ","
						+ runInfo.get(4) + "\n");
				fileName = "greedyBestFirstTreeSearch.csv";
				displayRunInformation(runInfo);

			}
			break;
		default:

			System.out.println("Unrecognized Search: " + search);

			break;

		}

		sb.append("\n\n");

		try {
			FileWriter w = new FileWriter("data/" + fileName);
			w.write(sb.toString());
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

	}

	public static void displayRunInformation(ArrayList<String> runInfo) {

		System.out.printf("%15s | %14s |  %65s | %18s | %10s ns\n", runInfo.get(0), runInfo.get(1), runInfo.get(2),
				runInfo.get(3), runInfo.get(4));

	}

}
