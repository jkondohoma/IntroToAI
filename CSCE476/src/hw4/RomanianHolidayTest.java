package hw4;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class RomanianHolidayTest {
	/**
	 * performs tasks on section 4.1.1 in HW4
	 * 
	 * @param cities
	 * @param cityObjects
	 * @param cityOne
	 * @param cityTwo
	 * @param km
	 */
	public void testFunctions(ArrayList<String> cities, ArrayList<City> cityObjects, int cityOne, int cityTwo, int km) {

		if ((cityOne < 0) || (cityOne > (cities.size() - 1))) {
			System.out.println("In cities list index " + cityOne + " does not exist");
			System.out.println("Index must be betwee 0 and 19");

		} else if ((cityTwo < 0) || (cityTwo > (cities.size() - 1))) {
			System.out.println("In cities list index " + cityTwo + " does not exist");
			System.out.println("Index must be betwee 0 and " + (cities.size() - 1));
		} else if (km < 0) {
			System.out.println("Distance between neighbors must be a positive interger");
		} else {

			RomanianHolidayGraph graphRun = new RomanianHolidayGraph();
			ArrayList<String> runInfo = graphRun.uniformCostSearch(cityObjects.get(cityOne).getName());
			System.out.println(
					"********************************************************************************************************************************************");
			System.out.println("4.1.1 Tasks");
			System.out.println(
					"********************************************************************************************************************************************");
			System.out.printf("City1: %s\nCity2: %s\nDistance for neighbors: %d \n", cities.get(cityOne),
					cities.get(cityTwo), km);
			System.out.println("__________________________________________________");

			// Q1
			System.out.println("1. Design, implement and test your map.\n");
			System.out.printf(
					"\tType:    %s\n\tSize:    %d\n\n\tGraph Uniform Cost Search\n\tExample: %s\n\tPath:    %s\n\tCost:    %s\n\n",
					cities.getClass(), cities.size(), cityObjects.get(cityOne).display(), runInfo.get(2),
					runInfo.get(3));

			// Q2
			System.out.println("2. Write a function allCitiesFromList that takes a global variable,\n"

					+ "  allCitiesList, and returns a list of all names of cities on the map. \n");
			for (String s : RomanianHolidayUtilities.allCitiesFromList()) {
				System.out.println("\t" + s);
			}
			System.out.println();

			// Q3
			System.out.println("3. Write a function allCitiesFromHtable that takes a variable,\n"
					+ "   allCitiesHtable and returns a list of all the structures of cities on the map.\n");

			ArrayList<City> citiesFromH = RomanianHolidayUtilities.allCitiesFromHtable();
			for (City city : citiesFromH) {
				System.out.println("\t" + city.display());
			}

			System.out.println();

			// Q4
			System.out.println("4. Write two functions getCityFromList and getCityFromHtable that take the name of a\n"
					+ "  city as input and return the corresponding structure (by accessing a variable, allCitiesList and\n"
					+ "  allCitiesHtable, respectively).\n");
			City fromList = RomanianHolidayUtilities.getCityFromList(cities.get(cityOne));
			Entry<String, HashMap<City, Integer>> fromHtable = RomanianHolidayUtilities
					.getCityFromHtableObject(cities.get(cityOne));

			System.out.println("\tCity: " + cities.get(cityOne));
			System.out.println("\t\tValue in allCitiesList");
			System.out.println("\t\t" + fromList.display());
			System.out.println("\n\t\tValue in allCitiesHtable");
			System.out.println("\t\t" + fromHtable);

			System.out.println();

			// Q5
			System.out.println("5.Write two functions neighborsUsingList and neighborsUsingHtable that take the name\n"
					+ "  of a city as input and return the list of structures of its direct neighbors. neighborsUsingList and\n"
					+ "  neighborsUsingHtable should use getCityFromList and getCityFromHtable, respectively. \n");

			ArrayList<City> neighborsList = RomanianHolidayUtilities.neighborsUsingList(cities.get(cityOne));
			ArrayList<City> neighborsHTable = RomanianHolidayUtilities.neighborsUsingHtable(cities.get(cityOne));
			System.out.println("\tCity: " + cities.get(cityOne));
			System.out.println("\t\tNeighbors using list");
			for (int i = 0; i < neighborsList.size(); i++) {
				System.out.println("\t\t" + neighborsList.get(i).display());
			}
			System.out.println("\n\t\tNeighbors using hash table");
			for (int i = 0; i < neighborsHTable.size(); i++) {
				System.out.println("\t\t" + neighborsHTable.get(i).display());
			}

			System.out.println();

			// Q6
			System.out.println(
					"6. Using allCitiesHtable, write a function neighborsWithinD that takes the name of a city\n"
							+ "  myCity and a number distance, then returns, for all direct neighbors within distance from myCity\n"
							+ "  (less than or equal), an association list of the structures of the neighbors of myCity and their distance to myCity. \n");

			HashMap<City, Integer> neighbors = RomanianHolidayUtilities.neighborsWithinD(cities.get(cityOne), km);
			System.out.println("\tCity: " + cities.get(cityOne));
			System.out.println("\tNieghbors within " + km + " km");
			if (neighbors.isEmpty()) {
				System.out.println("\tNONE");
			} else {

				System.out.println("\t" + neighbors);

			}

			System.out.println();

			// Q7
			System.out
					.println("7.Using allCitiesHtable, write a function neighborsP that takes the name of two cities\n"
							+ "  cityOne and cityTwo, and returns the distance between them if they are directly connected or nil if\n"
							+ "  they are not.\n");

			System.out.println("\tExample: " + cities.get(cityOne) + " and " + cities.get(cityTwo));
			System.out.println(
					"\tdistance: " + RomanianHolidayUtilities.neighborsP(cities.get(cityOne), cities.get(cityTwo)));

		}
	}

}
