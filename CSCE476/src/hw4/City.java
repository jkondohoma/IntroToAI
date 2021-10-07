package hw4;

import java.util.HashMap;

public class City implements Comparable<City> {

	private String name;
	private HashMap<String, Integer> neighboors;
	private String color;

	private int h;

	public City(String name, HashMap<String, Integer> neighboors, int h) {
		this.name = name;
		this.neighboors = neighboors;
		this.h = h;
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
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	

	@Override
	public String toString() {
		return "City [name=" + name + ", neighboors=" + neighboors + ", color=" + color + ", h=" + h + "]";
	}

	@Override
	public int compareTo(City city) {

		return name.compareTo(city.getName());

	}

	

}
