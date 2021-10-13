package hw4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class EightPiecePuzzle {

	private final static String empty = " ";
	private final static String up = "up";
	private final static String down = "down";
	private final static String right = "right";
	private final static String left = "left";
	private final static int n = 3;
	private ArrayList<Puzzle> frontier;
	private ArrayList<Puzzle> expanded;
	private Puzzle start;
	private Puzzle end;

	public EightPiecePuzzle(Puzzle start, Puzzle end) {

		this.frontier = new ArrayList<Puzzle>();
		this.expanded = new ArrayList<Puzzle>();
		this.start = start;
		this.end = end;

	}

	public ArrayList<String> eightPieceDisplacedTile() {

		int gn = 1;
		ArrayList<String> runInfo = new ArrayList<String>();

		Puzzle curr = start;
		expanded.add(curr);

		boolean goalReached = false;
		ArrayList<Puzzle> options = getOptionsDisplaced(curr, gn);
		while (!goalReached) {

			if (!goalReached) {
				System.out.println("___________________________________");
				System.out.println("run: " + gn);
				System.out.println("# of options: " + options.size());
				System.out.println("Current Node");
				System.out.print(RandomStateGenerator.displayPuzzle(curr));
				System.out.println("___________________________________");
				frontier = addToFrontier(options);
				displayFrontier();
				Puzzle least = leastCostPuzzle();

				goalReached = goalReahed(curr, end);

				System.out.println("*************");
				System.out.println("Expand");
				System.out.println("*************");
				System.out.println(RandomStateGenerator.displayPuzzle(least));
				System.out.println("f(n)= " + least.getCost() + "\n");

				curr = least;
			}

			if (goalReached) {

				System.out.println("GOAL STATE REACHED! NV: " + gn);
				System.out.println(RandomStateGenerator.displayPuzzle(curr));

			}

			gn++;
			options = getOptionsDisplaced(curr, gn);
		}

		return runInfo;

	}

	/**
	 * add given options of expansions to the frontier
	 * 
	 * @param options
	 * @return
	 */
	private ArrayList<Puzzle> addToFrontier(ArrayList<Puzzle> options) {
		ArrayList<Puzzle> newFrontier = new ArrayList<Puzzle>(frontier);
		for (Puzzle puzzle : options) {
			int index = expanded.indexOf(puzzle);
			if ((index == -1) && (!(puzzle.isVisited()))) {
				newFrontier.add(puzzle);
			}
		}
		;
		return newFrontier;
	}

	/**
	 * check if we've reached our goal (current state is equal to end state)
	 * 
	 * @param curr
	 * @param end
	 * @return
	 */
	private static boolean goalReahed(Puzzle curr, Puzzle end) {
		boolean valid = true;
		String[][] currState = curr.getMatrix();
		String[][] goal = end.getMatrix();

		for (int row = 0; row < n; row++) {

			for (int col = 0; col < n; col++) {

				if (!(currState[row][col].equals(goal[row][col]))) {
					valid = false;
				}
			}
		}

		return valid;
	}

	/**
	 * given a frontier find the least cost puzzle
	 * 
	 * @param frontier
	 * @param visited
	 * @return
	 */

	private Puzzle leastCostPuzzle() {
		Puzzle leastPuzzle = frontier.get(0);

		int leastCost = leastPuzzle.getCost();

		for (Puzzle puzzle : frontier) {
			int cost = puzzle.getCost();

			if ((cost < leastCost)) {
				leastPuzzle = puzzle;

			}

		}

		leastPuzzle.setVisited(true);
		expanded.add(leastPuzzle);
		return leastPuzzle;
	}

	/**
	 * given a puzzle generate new puzzles with empty tile moved (up,down,left or
	 * right) and the cost (as long as they are possible)
	 * 
	 * @param curr
	 * @return
	 */

	private ArrayList<Puzzle> getOptionsDisplaced(Puzzle curr, int gn) {

		ArrayList<Puzzle> fringe = new ArrayList<Puzzle>();
		Puzzle u = getOption(curr, up);
		Puzzle d = getOption(curr, down);
		Puzzle l = getOption(curr, left);
		Puzzle r = getOption(curr, right);
		int hn = 0;

		// if array not empty (meaning that the move is possible) add it

		if (u != null) {

			hn = displacedTiles(u, end);
			int fn = gn + hn;
			Puzzle moved = new Puzzle(u, fn);
			fringe.add(moved);
		}

		if (d != null) {
			hn = displacedTiles(d, end);
			int fn = gn + hn;
			Puzzle moved = new Puzzle(d, fn);
			fringe.add(moved);
		}

		if (l != null) {
			hn = displacedTiles(l, end);
			int fn = gn + hn;
			Puzzle moved = new Puzzle(l, fn);
			fringe.add(moved);
		}
		if (r != null) {
			hn = displacedTiles(r, end);
			int fn = gn + hn;
			Puzzle moved = new Puzzle(r, fn);
			fringe.add(moved);

		}
		return fringe;
	}

	/**
	 * given original puzzle and a tile with a moved piece count number of misplaced
	 * tiles
	 * 
	 * @param puzzle
	 * @param goal
	 * @return
	 */
	public static int displacedTiles(Puzzle puzzle, Puzzle goal) {
		int count = 0;

		for (int row = 0; row < n; row++) {

			for (int col = 0; col < n; col++) {

				String val1 = puzzle.getMatrix()[row][col];
				String val2 = goal.getMatrix()[row][col];

				if ((!(val1.equals(val2))) && (!(val1.equals(empty)))) {
//						System.out.println(val1+ " "+ val2);

					count++;
				}

//				}

			}
		}

//		System.out.println("----------------");

//		System.out.println("Displaced tiles: " + count);
		return count;
	}

	/**
	 * given a puzzle, move empty tile in the given direction
	 * 
	 * @param curr
	 * @return
	 */
	private Puzzle getOption(Puzzle curr, String direction) {
		Puzzle newPuzzle = null;

		// find where empty tile is
		HashMap<Integer, Integer> index = findEmptyTileIndex(curr);
		// if possible
		if (movePossible(index, direction)) {
			// move
			newPuzzle = move(curr, index, direction);

		}

		return newPuzzle;
	}

	/**
	 * given index of empty title and direction move that empty tile
	 * 
	 * @param curr
	 * @param index
	 * @return
	 */
	public Puzzle move(Puzzle puzzle, HashMap<Integer, Integer> index, String direction) {
		String value = "";

		String[][] curr = puzzle.getMatrix();
		String[][] newPuzzle = copyValues(curr);

		for (Entry<Integer, Integer> entry : index.entrySet()) {
			int row = entry.getKey();
			int col = entry.getValue();

			switch (direction) {

			case up:

				value = curr[row - 1][col];
				newPuzzle[row - 1][col] = empty;
				newPuzzle[row][col] = value;
				break;
			case down:

				value = curr[row + 1][col];
				newPuzzle[row + 1][col] = empty;
				newPuzzle[row][col] = value;
				break;

			case left:
				value = curr[row][col - 1];
				newPuzzle[row][col - 1] = empty;
				newPuzzle[row][col] = value;
				break;

			case right:
				value = curr[row][col + 1];
				newPuzzle[row][col + 1] = empty;
				newPuzzle[row][col] = value;
				break;
			}

		}

		Puzzle puzzObject = new Puzzle(newPuzzle);
		return puzzObject;

	}

	/**
	 * given and index and a direction make sure the move is possible (index is not
	 * out of bound)
	 * 
	 * @param index
	 * @param direction
	 * @return
	 */
	private boolean movePossible(HashMap<Integer, Integer> index, String direction) {
		boolean valid = false;

		for (Entry<Integer, Integer> entry : index.entrySet()) {

			int row = entry.getKey();
			int col = entry.getValue();
			switch (direction) {
			case up:

				if ((row - 1) >= 0) {
					valid = true;

				}

				break;

			case down:

				if ((row + 1) < n) {

					valid = true;

				}

				break;

			case left:
				if ((col - 1) >= 0) {
					valid = true;
				}

				break;

			case right:

				if ((col + 1) < n) {
					valid = true;
				}

				break;
			}

		}

		return valid;

	}

	/**
	 * copy values of puzzle array into a new array (simply setting newPuzzle =
	 * puzzle does not work)
	 * 
	 * @param puzzle
	 * @return
	 */
	private String[][] copyValues(String[][] puzzle) {
		String[][] newPuzzle = new String[n][n];

		for (int row = 0; row < n; row++) {

			for (int col = 0; col < n; col++) {

				newPuzzle[row][col] = puzzle[row][col];

			}
		}
		return newPuzzle;

	}

	/**
	 * given a puzzle determine index of empty tile
	 * 
	 * @param curr
	 * @return
	 */
	private HashMap<Integer, Integer> findEmptyTileIndex(Puzzle curr) {
		HashMap<Integer, Integer> index = new HashMap<Integer, Integer>();

		for (int row = 0; row < n; row++) {

			for (int col = 0; col < n; col++) {
				String val = curr.getMatrix()[row][col];
				if (val.equals(empty)) {
					index.put(row, col);
					break;
				}

			}
		}

		return index;
	}

	/**
	 * nicely display array list of puzzles
	 * 
	 * @param options
	 */
	private void displayFrontier() {

		for (Puzzle puzzle : frontier) {
			System.out.print(RandomStateGenerator.displayPuzzle(puzzle));
			System.out.println("f(n)= " + puzzle.getCost() + "\n");
//			System.out.println("visited= " + puzzle.isVisited()+ "\n");
		}

	}

}
