
import java.util.HashMap;
import java.util.HashSet;
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

	/**
	 * Randomly generates a start and end state of a eight piece puzzle
	 * 
	 * @return
	 */
	public static HashMap<Puzzle, Puzzle> getStates() {
		HashMap<Puzzle, Puzzle> states = new HashMap<Puzzle, Puzzle>();
		String numbers = "12345678 ";

		Set<String> combos = new HashSet<>();
		combinations("", numbers, combos);
		String[][] matrix = pickOneCombination(combos);
		Puzzle start = new Puzzle(matrix);

		String[][] endMat = pickOneCombination(combos);
		Puzzle end = new Puzzle(endMat);
		states.put(start, end);

		return states;

	}

	/**
	 * give a set of combinationID pick one randomly
	 * 
	 * @param combinationID
	 * @return
	 */

	private static String[][] pickOneCombination(Set<String> combinations) {

		Random rand = new Random();
		Object[] cmb = combinations.toArray();
		int index = rand.nextInt(cmb.length);

		String str = (String) cmb[index];
//		System.out.println(str);
		char[] choice = str.toCharArray();

		String[] arr = new String[9];

		for (int i = 0; i < 9; i++) {
			String s = Character.toString(choice[i]);

			arr[i] = s;
		}

		return to2D(arr);
	}

	/**
	 * convert 1D array to 2D
	 * 
	 * @param arr
	 * @return
	 */
	private static String[][] to2D(String[] arr) {
//		System.out.println(arr.length);
		String[][] d_2 = new String[n][n];
		int index = 0;
		for (int row = 0; row < n; row++) {
			for (int col = 0; col < n; col++) {
//				System.out.printf("(%d,%d) = %s\n", row,col,arr[index]);
				d_2[row][col] = arr[index];
				index++;
			}

		}
		return d_2;
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
	 * determine if given puzzle is solvable
	 * 
	 * @param puzzle
	 * @return
	 */

	public static boolean solvable(Puzzle puzzle) {
		boolean solvable = false;
		int count = inversionCount(puzzle);

		if (count % 2 == 0) {
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

}
