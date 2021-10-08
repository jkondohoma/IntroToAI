package hw4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * implement A search for solving the Eight-Piece Sliding Puzzle Problem with
 * the two admissible heuristics: the displaced tile and the Manhattan distance
 * heuristics.
 * 
 * @author jaellekondohoma
 *
 */

public class Problem5 {

	public static void main(String[] args) {
		int combinations = 100;

		HashMap<int[][], int[][]> states = RandomStateGenerator.getStates();
		RandomStateGenerator.displayStates(states);

		for (Entry<int[][], int[][]> entry : states.entrySet()) {
			int[][] start = entry.getKey();
			int[][] end = entry.getValue();

			System.out.println("Start Valid: " + RandomStateGenerator.verify(start));
			System.out.println("End Valid: " + RandomStateGenerator.verify(end));

		}

//		ArrayList<String> runInfo = EightPiecePuzzle.eightPlaceDisplacedTile(null, null);

	}

}
