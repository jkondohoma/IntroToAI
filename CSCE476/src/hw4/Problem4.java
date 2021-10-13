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

		int n = 5;

		ArrayList<String> cities = RomaninaHolidayUtilities.allCitiesFromList();
		ArrayList<City> cityObjects = FileParser.getCities();
		Collections.sort(cities);

		RomanianHolidayGraph graphRun = new RomanianHolidayGraph();
		ArrayList<String> runInfo = graphRun.uniformCostSearch(cityObjects.get(n).getName());

		System.out.println("4.1.1 Tasks");
		System.out.println(
				"********************************************************************************************************************************************");
		System.out.println("1. Design, implement and test your map.");
		System.out.printf(
				"\tType:    %s\n\tSize:    %d\n\n\tGraph Uniform Cost Search\n\tExample: %s\n\tPath:    %s\n\tCost:    %s\n\n",
				cities.getClass(), cities.size(), cityObjects.get(n).display(), runInfo.get(2), runInfo.get(3));
		System.out.println(
				"2. Write a function allCitiesFromList that takes a global variable allCitiesList, and returns a list of all names of cities on the map.");
		for (String s : cities) {
			System.out.println("\t" + s);
		}
		System.out.println();
		System.out.println(
				"3. Write a function allCitiesFromHtable that takes a variable, allCitiesHtable and returns a list of all the structures of cities on the map.");
//		DisplaySearch.displaySearchResult(cities, "UCST"); //Uniform Cost Tree Search (Uninformed)
//		DisplaySearch.displaySearchResult(cities, "UCSG"); // Uniform Cost Graph Search (Uninformed)
//		DisplaySearch.displaySearchResult(cities, "GRDT");//Greedy Best First Tree Search
//		DisplaySearch.displaySearchResult(cities, "GRDG"); // Greedy Best First Graph Search
//		DisplaySearch.displaySearchResult(cities, "A*T"); //Best First A* Tree Search
//		DisplaySearch.displaySearchResult(cities, "A*G"); // Best First A* Graph Search

	}

}
