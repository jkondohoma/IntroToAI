package hw4;

public class Puzzle {

	private String[][] matrix;
	private int cost;

	public Puzzle(String[][] matrix) {
		this.matrix = matrix;
	}

	public Puzzle(Puzzle puzzle, int cost) {
		this.matrix = puzzle.getMatrix();
		this.cost = cost;
	}

	public String[][] getMatrix() {
		return matrix;
	}

	public int getCost() {
		return cost;
	}
	
}
