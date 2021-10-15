

import java.util.HashMap;

/**
 * data structure for a city
 * 
 * @author jaellekondohoma
 *
 */

public class City implements Comparable<City> {

	private String name;
	private HashMap<String, Integer> neighboors;
	private boolean visited;

	private int h;

	public City(String name, HashMap<String, Integer> neighboors, int h) {
		this.name = name;
		this.neighboors = neighboors;
		this.h = h;
		this.visited = false;
	}

	public String getName() {
		return name;
	}

	public HashMap<String, Integer> getNeighboors() {
		return neighboors;
	}

	public int getH() {
		return h;
	}

	public boolean getVisisted() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public String display() {
		return "City [name=" + name + ", neighboors=" + neighboors + ", h=" + h + "]";
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public int compareTo(City city) {

		return name.compareTo(city.getName());

	}

}
