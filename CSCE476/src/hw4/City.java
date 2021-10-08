package hw4;

import java.util.HashMap;

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

	


//	@Override
//	public String toString() {
//		return "City [name=" + name + ", neighboors=" + neighboors + ", visited=" + visited + ", h=" + h + "]";
//	}

//	@Override
//	public String toString() {
//		return name+": "+ visited;
//	}
	
	@Override
	public String toString() {
		return name;
	}


	@Override
	public int compareTo(City city) {

		return name.compareTo(city.getName());

	}

	

}
