package hw4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

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

		ArrayList<String> cities = RomanianHolidayUtilities.allCitiesFromList();
		ArrayList<City> cityObjects = FileParser.getCities();
		Collections.sort(cities);

		RomanianHolidayGraph graphRun = new RomanianHolidayGraph();
		ArrayList<String> runInfo = graphRun.uniformCostSearch(cityObjects.get(n).getName());

		System.out.println("4.1.1 Tasks");
		System.out.println(
				"********************************************************************************************************************************************");
		System.out.println("1. Design, implement and test your map.\n");
		System.out.printf(
				"\tType:    %s\n\tSize:    %d\n\n\tGraph Uniform Cost Search\n\tExample: %s\n\tPath:    %s\n\tCost:    %s\n\n",
				cities.getClass(), cities.size(), cityObjects.get(n).display(), runInfo.get(2), runInfo.get(3));
		System.out.println(
				"2. Write a function allCitiesFromList that takes a global variable,\n"
				+ "  allCitiesList, and returns a list of all names of cities on the map. \n");
		for (String s : cities) {
			System.out.println("\t" + s);
		}
		System.out.println();
		System.out.println(
				"3. Write a function allCitiesFromHtable that takes a variable,\n"
				+ "   allCitiesHtable and returns a list of all the structures of cities on the map.\n");

		ArrayList<City> citiesFromH = RomanianHolidayUtilities.allCitiesFromHtable();
		for (City city : citiesFromH) {
			System.out.println("\t" + city.display());
		}

		System.out.println();
		System.out.println(
				"4. Write two functions getCityFromList and getCityFromHtable that take the name of a\n"
				+ "  city as input and return the corresponding structure (by accessing a variable, allCitiesList and\n"
				+ "  allCitiesHtable, respectively).\n");
		City fromList = RomanianHolidayUtilities.getCityFromList(cities.get(7));
		Entry<String, HashMap<String, Integer>> fromHtable = RomanianHolidayUtilities.getCityFromHtable(cities.get(7));
		
		System.out.println("\tCity: "+cities.get(7));
		System.out.println("\t\tValue in allCitiesList");
		System.out.println("\t\t"+fromList.display());
		System.out.println("\n\t\tValue in allCitiesHtable");
		System.out.println("\t\t"+fromHtable);
		
		System.out.println();
		System.out.println(
				"5.Write two functions neighborsUsingList and neighborsUsingHtable that take the name\n"
				+ "  of a city as input and return the list of structures of its direct neighbors. neighborsUsingList and\n"
				+ "  neighborsUsingHtable should use getCityFromList and getCityFromHtable, respectively. \n");

//		DisplaySearch.displaySearchResult(cities, "UCST"); //Uniform Cost Tree Search (Uninformed)
//		DisplaySearch.displaySearchResult(cities, "UCSG"); // Uniform Cost Graph Search (Uninformed)
//		DisplaySearch.displaySearchResult(cities, "GRDT");//Greedy Best First Tree Search
//		DisplaySearch.displaySearchResult(cities, "GRDG"); // Greedy Best First Graph Search
//		DisplaySearch.displaySearchResult(cities, "A*T"); //Best First A* Tree Search
//		DisplaySearch.displaySearchResult(cities, "A*G"); // Best First A* Graph Search

	}

}
