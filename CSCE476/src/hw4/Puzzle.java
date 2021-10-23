package hw4;
import java.util.Arrays;

public class Puzzle {

	private String[][] matrix;
	private int cost;
	private boolean visited;

	public Puzzle(String[][] matrix) {
		this.matrix = matrix;
		this.visited = false;
	}

	public Puzzle(Puzzle puzzle, int cost) {
		this.matrix = puzzle.getMatrix();
		this.visited = puzzle.isVisited();
		this.cost = cost;
	}

	public String[][] getMatrix() {
		return matrix;
	}

	public int getCost() {
		return cost;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public int[][] integerRepresentation() {

		int n = 3;
		int[][] intMatrix = new int[n][n];

		for (int row = 0; row < n; row++) {

			for (int col = 0; col < n; col++) {

				String str = this.matrix[row][col];
				
				if (str.isBlank()) {
					intMatrix[row][col] = 0;
				} else {
					intMatrix[row][col] = Integer.parseInt(str);
				}
			}

		}

		return intMatrix;
	}





}
