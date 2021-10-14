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

		

		ArrayList<String> cities = RomanianHolidayUtilities.allCitiesFromList();
		ArrayList<City> cityObjects = FileParser.getCities();
		Collections.sort(cities);
		Collections.sort(cityObjects);

		DisplaySearch.displaySearchResult(cities, "UCST"); // Uniform Cost Tree Search (Uninformed)
		DisplaySearch.displaySearchResult(cities, "UCSG"); // Uniform Cost Graph Search (Uninformed)
		DisplaySearch.displaySearchResult(cities, "GRDT");// Greedy Best First Tree Search
		DisplaySearch.displaySearchResult(cities, "GRDG"); // Greedy Best First Graph Search
		DisplaySearch.displaySearchResult(cities, "A*T"); // Best First A* Tree Search
		DisplaySearch.displaySearchResult(cities, "A*G"); // Best First A* Graph Search
		System.out.println();
	
		int cityOneIndex = 0;
		int cityTwoIndex = 7;
		int km = 204;
//		RomanianHolidayTest.testFunctions(cities, cityObjects, cityOneIndex, cityTwoIndex, km);


	}

}
