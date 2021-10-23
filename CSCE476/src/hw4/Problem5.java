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

		System.out.println("\t\t\tDisplaced tile");
		System.out.println("____________________________________________________________________________________");
		System.out.printf("%s | %s | %s | %s \n", "Combination","#NV","Path Cost","CPU Time");
		System.out.println("____________________________________________________________________________________");
		for (int i = 1; i <= combinations; i++) {
			HashMap<Puzzle, Puzzle> states = RandomStateGenerator.getStates();
			
			
			for (Entry<Puzzle, Puzzle> entry : states.entrySet()) {
				Puzzle start = entry.getKey();
				Puzzle end = entry.getValue();

				
				boolean startValid = RandomStateGenerator.solvable(start);
				boolean endValid = RandomStateGenerator.solvable(end);
				
//				System.out.println(startValid);
//				System.out.println(endValid);

				if (startValid && endValid) {
					EightPiecePuzzle solve = new EightPiecePuzzle(start,end);
					ArrayList<String> runInfo = solve.eightPieceDisplacedTile();
					System.out.printf("%s%d| %s | %s | %s\n", "Combination",i,runInfo.get(0), runInfo.get(1),runInfo.get(2));
					
					
				} else {
					System.out.println("combination" + i+ "| not solvable");
				}

			}

		}

	
//		String[][] startTest = {{"2", "5","8"},{"3", "1"," "},{"4", "6","7"}};
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
//		System.out.printf("#NV = %s | Cost = %s | CPU Time %s\n",runInfo.get(0), runInfo.get(1),runInfo.get(2));
	}

}
