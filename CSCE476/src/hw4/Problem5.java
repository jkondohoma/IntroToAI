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

		HashMap<Puzzle, Puzzle> states = RandomStateGenerator.getStates();
		RandomStateGenerator.displayStates(states);

		for (Entry<Puzzle, Puzzle> entry : states.entrySet()) {
			Puzzle start = entry.getKey();
			Puzzle end = entry.getValue();

			boolean startValid = RandomStateGenerator.verify(start);
			boolean endValid= RandomStateGenerator.verify(end);
			
			if (startValid && endValid) {
				ArrayList<String> runInfo = EightPiecePuzzle.eightPieceDisplacedTile(start, end);
			}
			

		}

	}

}
