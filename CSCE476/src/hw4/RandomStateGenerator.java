package hw4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;

public class RandomStateGenerator {

	private final static int n = 3;
	private final static int min = 1;
	private final static int max = 8;
	private final static int[] combinations = { 1, 2 };

	public static HashMap<int[][], int[][]> getStates() {
		HashMap<int[][], int[][]> states = new HashMap<int[][], int[][]>();
		int startCombo = combinations[new Random().nextInt(combinations.length)];

		int[][] start = randomState(startCombo);

		int endCombo = startCombo + 2;

		if (endCombo > combinations.length) {
			endCombo = 1;
		}

		int[][] end = randomState(endCombo);

		states.put(start, end);

		return states;

	}

	private static int[][] randomState(int combo) {

		int[][] puzzle = new int[n][n];
		String topOption = "";
		String middleOption = "";
		String bottomOption = "";
		int[] arrTop = new int[n];
		int[] arrMid =  new int[n];
		int[] arrBot = new int[n];
		Set<String> top = new HashSet<>();
		Set<String> mid = new HashSet<>();
		Set<String> bot = new HashSet<>();

		switch (combo) {
		case 1:

			topOption = "012";
			middleOption = "345";
			bottomOption = "768";

			// generate top row
			combinations("", topOption, top);
			 arrTop = pickOneCombination(top);

			for (int col = 0; col < n; col++) {
				int row = 0;
				puzzle[row][col] = arrTop[col];

			}

			// generate middle row
			combinations("", middleOption, mid);
			arrMid = pickOneCombination(mid);

			for (int col = 0; col < n; col++) {
				int row = 1;
				puzzle[row][col] = arrMid[col];

			}

			// generate bottom row
			combinations("", bottomOption, bot);
			 arrBot = pickOneCombination(bot);

			for (int col = 0; col < n; col++) {
				int row = 2;
				puzzle[row][col] = arrBot[col];

			}
			break;
			
		case 2:

			topOption = "345";
			middleOption = "012";
			bottomOption = "678";

			// generate top row
			combinations("", topOption, top);
			 arrTop = pickOneCombination(top);

			for (int col = 0; col < n; col++) {
				int row = 0;
				puzzle[row][col] = arrTop[col];

			}

			// generate middle row
			combinations("", middleOption, mid);
			arrMid = pickOneCombination(mid);

			for (int col = 0; col < n; col++) {
				int row = 1;
				puzzle[row][col] = arrMid[col];

			}

			// generate bottom row
			combinations("", bottomOption, bot);
			 arrBot = pickOneCombination(bot);

			for (int col = 0; col < n; col++) {
				int row = 2;
				puzzle[row][col] = arrBot[col];

			}
			break;
			
		}
		return puzzle;

	}

	public static int[] pickOneCombination(Set<String> combinations) {

		Random rand = new Random();
		Object[] cmb = combinations.toArray();
		int index = rand.nextInt(cmb.length);

		String str = (String) cmb[index];
//		System.out.println(str);
		char[] choice = str.toCharArray();

		int[] arr = new int[n];

		for (int i = 0; i < n; i++) {
			String s = Character.toString(choice[i]);

			arr[i] = Integer.parseInt(s);
		}

		return arr;
	}

	private static void combinations(String start, String inputs, Set<String> result) {
		if (inputs.length() == 0) {
			result.add(start);
		} else {
			for (int i = 0; i < inputs.length(); i++) {
				String letter = inputs.substring(i, i + 1);
				String otherLetters = inputs.substring(0, i) + inputs.substring(i + 1);
				combinations(start + letter, otherLetters, result);
			}
		}
	}

	public static void displayPuzzle(int[][] puzzle) {

		for (int i = 0; i < n; i++) {

			for (int j = 0; j < n; j++) {

				System.out.print(puzzle[i][j] + " ");
			}
			System.out.println();
		}

		System.out.println();
	}

	public static void displayStates(HashMap<int[][], int[][]> states) {

		System.out.println("Start");

		for (Entry<int[][], int[][]> entry : states.entrySet()) {

			displayPuzzle(entry.getKey());

			System.out.println("End");
			displayPuzzle(entry.getValue());

		}

	}

	private static boolean verify(int[][] arr) {
		boolean valid = true;
		ArrayList<Integer> list = toList(arr);
		int runs = 0;

		while (valid || runs < (max + 1)) {
			for (int i = min; i <= max; i++) {
				valid = list.contains(i);
			}

			runs++;
		}

		return valid;

	}

	private static ArrayList<Integer> toList(int[][] arr) {

		ArrayList<Integer> list = new ArrayList<Integer>();

		for (int i = 0; i < n; i++) {

			for (int j = 0; j < n; j++) {
				list.add(arr[i][j]);

			}
		}

		return list;
	}

}
