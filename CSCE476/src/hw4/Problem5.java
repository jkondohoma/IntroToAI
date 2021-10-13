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

	private final static String[][] matrix = { { "1", "2", "3" }, { "4", "5", "6" }, { "7", "8", " " } };
	private final static Puzzle goal = new Puzzle(matrix);

	public static void main(String[] args) {
		int combinations = 100;

		ArrayList<HashMap<Puzzle, Puzzle>> allStates = new ArrayList<HashMap<Puzzle, Puzzle>>();

		for (int i = 0; i < combinations; i++) {
			HashMap<Puzzle, Puzzle> states = RandomStateGenerator.getStates();
			allStates.add(states);
		}

//		for (Entry<Puzzle, Puzzle> entry : states.entrySet()) {
//			Puzzle start = entry.getKey();
//			Puzzle end = entry.getValue();
//
//			
//			boolean startValid = RandomStateGenerator.solvable(start);
//			boolean endValid = RandomStateGenerator.solvable(end);
//			
//			System.out.println(startValid);
//			System.out.println(endValid);
//
//			if (startValid && endValid) {
//				EightPiecePuzzle solve = new EightPiecePuzzle(start,end);
//				ArrayList<String> runInfo = solve.eightPieceDisplacedTile();
//				
//				
//			} else {
//				System.out.println("combination" + " not solvable");
//			}
//
//		}

//		String[][] startTest = {{"1", "2","3"},{" ", "4","6"},{"7", "5","8"}};
//		String[][] endTest = {{"1", "2","3"},{"4", "5","6"},{"7", "8"," "}};
////		
//		Puzzle start =new Puzzle(startTest);//  RandomStateGenerator.randomState(1);//new Puzzle(startTest);
//		Puzzle end = new Puzzle(endTest);
////		
//		System.out.println(RandomStateGenerator.displayPuzzle(start));
//		System.out.println(RandomStateGenerator.displayPuzzle(end));
////		
//		
//		System.out.println( RandomStateGenerator.solvable(start));
//		System.out.println( RandomStateGenerator.solvable(end));
////		System.out.println(EightPiecePuzzle.displacedTiles(start, end));
//		
//		EightPiecePuzzle solve = new EightPiecePuzzle(start,end);
//
//		ArrayList<String> runInfo = solve.eightPieceDisplacedTile();
	}

	public static void solveAllSates(ArrayList<HashMap<Puzzle, Puzzle>> allStates) {

		for (HashMap<Puzzle, Puzzle> states : allStates) {

			for (Entry<Puzzle, Puzzle> entry : states.entrySet()) {
				Puzzle start = entry.getKey();
				Puzzle end = entry.getValue();
				
				boolean startValid = RandomStateGenerator.solvable(start);
				boolean endValid = RandomStateGenerator.solvable(end);
				
				if (startValid && endValid) {
				EightPiecePuzzle solve = new EightPiecePuzzle(start,end);
				ArrayList<String> runInfo = solve.eightPieceDisplacedTile();
				System.out.printf("%s %s %s %s %s", "Instance", "#NV","Path Cost", "CPU Time");
				
				
			} else {
				System.out.println("combination" + " not solvable");
			}
				
				
			}

		}

	}

}
