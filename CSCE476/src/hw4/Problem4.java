package hw4;

import java.util.ArrayList;
import java.util.Collections;

public class Problem4 {

	public static void main(String[] args) {

		ArrayList<String> cities = RomanianHoliday.allCitiesFromList();
		Collections.sort(cities);

//		System.out.println(
//				"________________________________________________________________________________________________________________________________________________________");
//		System.out.printf("%65s\n", "Uninformed Breadth First Search");
//		System.out.println(
//				"________________________________________________________________________________________________________________________________________________________");
//		System.out.printf("%15s | %11s | \t\t\t%-45s | %s | %s\n", "City Name", "#nodes visited", "Path to Bucharest",
//				"Total cost of path", "CPU time");
//		System.out.println(
//				"________________________________________________________________________________________________________________________________________________________");
//		
//		for (int i = 0; i < cities.size(); i++) {
//			ArrayList<String> runInfo = RomanianHoliday.uniformCostSearch(cities.get(i));
//
//			displayRunInformation(runInfo);
//
//		}

		System.out.println(
				"________________________________________________________________________________________________________________________________________________________");
		System.out.printf("%65s\n", "Greedy Best First Search");
		System.out.println(
				"________________________________________________________________________________________________________________________________________________________");
		System.out.printf("%15s | %11s | \t\t\t%-45s | %s | %s\n", "City Name", "#nodes visited", "Path to Bucharest",
				"Total cost of path", "CPU time");
		System.out.println(
				"________________________________________________________________________________________________________________________________________________________");
//		for (int i = 0; i < cities.size(); i++) {
			ArrayList<String> runInfo = RomanianHoliday.greedyBestFirstearch(cities.get(1));

//			displayRunInformation(runInfo);

//		}

//		System.out.println(
//				"____________________________________________________________________________________________________________________________");
//		System.out.printf("%40s\n", "Best First A* Search");
//		System.out.println(
//				"____________________________________________________________________________________________________________________________");
//		System.out.printf("%10s | %s | \t\t\t%-33s | %s | %s\n", "City Name", "#nodes visited", "Path to Bucharest",
//				"Total cost of path", "CPU time");
//		System.out.println(
//				"____________________________________________________________________________________________________________________________");

//		for (int i = 0; i < 3; i++) {
//			ArrayList<String> runInfo = RomanianHoliday.bestFirstAStarSearch(cities.get(i));
//			displayRunInformation(runInfo);
//
//		}

	}

	public static void displayRunInformation(ArrayList<String> runInfo) {

		System.out.printf("%15s | %14s |  %65s | %18s | %s ns\n", runInfo.get(0), runInfo.get(1), runInfo.get(2),
				runInfo.get(3), runInfo.get(4));

	}

}
