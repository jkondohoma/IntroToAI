package hw4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;

/**
 * a generator of random states, to be used to generate an initial and a goal
 * state.
 * 
 * @author jaellekondohoma
 *
 */

public class RandomStateGenerator {

	private final static int n = 3;
	private final static int min = 1;
	private final static int max = 8;
	private final static int[] combinationID = { 1, 2 ,3};
	
	/**
	 * given a combination ID generate a random puzzle with numbers that corresponds
	 * to that ID
	 * 
	 * @param comboID
	 * @return
	 */

	private static Puzzle randomState(int comboID) {

		String[][] puzzle = new String[n][n];
		String topOption = "";
		String middleOption = "";
		String bottomOption = "";
		String[] arrTop = new String[n];
		String[] arrMid = new String[n];
		String[] arrBot = new String[n];

		switch (comboID) {
		case 1:

			topOption = "12 ";
			middleOption = "345";
			bottomOption = "678";
			break;

		case 2:

			topOption = "123";
			middleOption = " 45";
			bottomOption = "678";
			break;
			
		case 3:

			topOption = "123";
			middleOption = "4 5";
			bottomOption = "678";
			break;

		}

		arrTop = toArray(topOption);
		arrMid = toArray(middleOption);
		arrBot = toArray(bottomOption);

		// generate top row
		for (int col = 0; col < n; col++) {
			int row = 0;
			puzzle[row][col] = arrTop[col];

		}

		// generate middle row
		for (int col = 0; col < n; col++) {
			int row = 1;
			puzzle[row][col] = arrMid[col];

		}

		// generate bottom row
		for (int col = 0; col < n; col++) {
			int row = 2;
			puzzle[row][col] = arrBot[col];

		}

		Puzzle puzzleObject = new Puzzle(puzzle);
		return puzzleObject;

	}


	/**
	 * Randomly generates a start and end state of a eight piece puzzle
	 * 
	 * @return
	 */
	public static HashMap<Puzzle, Puzzle> getStates() {
		HashMap<Puzzle, Puzzle> states = new HashMap<Puzzle, Puzzle>();
		int startCombo = combinationID[new Random().nextInt(combinationID.length)];

		Puzzle start = randomState(startCombo);

		int endCombo = startCombo + 2;

		if (endCombo > combinationID.length) {
			endCombo = 1;
		}

		Puzzle end = randomState(endCombo);

		states.put(start, end);

		return states;

	}
	/**
	 * convert row combination to a 1D array
	 * 
	 * @param row
	 * @return
	 */
	private static String[] toArray(String row) {
		String[] str = new String[n];
		char[] charArr = row.toCharArray();

		int i = 0;
		for (char c : charArr) {

			str[i] = Character.toString(c);

			i++;

		}

		return str;
	}

	/**
	 * give a set of combinationID pick one randomly
	 * 
	 * @param combinationID
	 * @return
	 */

	private static String[] pickOneCombination(Set<String> combinations) {

		Random rand = new Random();
		Object[] cmb = combinations.toArray();
		int index = rand.nextInt(cmb.length);

		String str = (String) cmb[index];
//		System.out.println(str);
		char[] choice = str.toCharArray();

		String[] arr = new String[n];

		for (int i = 0; i < n; i++) {
			String s = Character.toString(choice[i]);

			arr[i] = s;
		}

		return arr;
	}

	/**
	 * recursively generate combinations of a string
	 * 
	 * @param start
	 * @param inputs
	 * @param result
	 */
	private static void combinations(String start, String inputs, Set<String> result) {
		if (inputs.length() == 0) {
			result.add(start);
		} else {
			for (int i = 0; i < inputs.length(); i++) {
				String x = inputs.substring(i, i + 1);
				String y = inputs.substring(0, i) + inputs.substring(i + 1);
				combinations(start + x, y, result);
			}
		}
	}

	/**
	 * given a puzzle display it nicely on screen
	 * 
	 * @param least
	 */
	public static String displayPuzzle(Puzzle least) {

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < n; i++) {

			for (int j = 0; j < n; j++) {

				sb.append(least.getMatrix()[i][j] + " ");

			}
			sb.append("\n");
		}

//		sb.append("\n");

//		System.out.print(sb.toString());
		return sb.toString();
	}

	/**
	 * given a HashMap containing end and start states, display the puzzles
	 * 
	 * @param states
	 */
	public static void displayStates(HashMap<Puzzle, Puzzle> states) {

		StringBuilder sb = new StringBuilder();

		sb.append("Start\n");

		for (Entry<Puzzle, Puzzle> entry : states.entrySet()) {

			sb.append(displayPuzzle(entry.getKey()));
			sb.append("\nEnd\n");
			sb.append(displayPuzzle(entry.getValue()));

		}

		System.out.println(sb.toString());

	}

	/**
	 * given a 8 piece puzzle verify if valid (contains numbers 0-8, with zero
	 * representing empty space)
	 * 
	 * @param start
	 * @return
	 */

	private static boolean verify(Puzzle puzzle) {
		boolean valid = true;
		ArrayList<String> list = toList(puzzle);

		for (int i = min; i <= max; i++) {
			valid = list.contains(Integer.toString(i));
			if (!valid) {
				break;
			}
		}

		return valid;

	}

	/**
	 * determine if given puzzle is solvable
	 * 
	 * @param puzzle
	 * @return
	 */

	public static boolean solvable(Puzzle puzzle) {
		boolean valid = verify(puzzle);
		boolean solvable = false;
		int count = inversionCount(puzzle);

		if ((count % 2 == 0) && valid) {
			solvable = true;
		}
		return solvable;

	}

	/**
	 * 
	 * @param puzzle
	 * @return
	 */
	private static int inversionCount(Puzzle puzzle) {
		int count = 0;
		int[][] arr = puzzle.integerRepresentation();

		for (int row = 0; row < n - 1; row++) {

			for (int col = row + 1; col < n; col++) {

				if ((arr[col][row] > 0) && (arr[col][row] > arr[row][col])) {
					count++;

				}

			}
		}
		return count;
	}

	/**
	 * convert a 2D array to a 1D array list
	 * 
	 * @param start
	 * @return
	 */
	private static ArrayList<String> toList(Puzzle start) {

		ArrayList<String> list = new ArrayList<String>();

		for (int i = 0; i < n; i++) {

			for (int j = 0; j < n; j++) {
				list.add(start.getMatrix()[i][j]);

			}
		}

		return list;
	}

}
