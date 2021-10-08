package hw4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Problem5 {

	public static void main(String[] args) {
		int combinations = 100;
	
		HashMap<int[][], int[][]> states = RandomStateGenerator.getStates();
		RandomStateGenerator.displayStates(states);
		
		for (Entry<int[][], int[][]> entry : states.entrySet()) {
			int [][] start = entry.getKey();
			int [][] end = entry.getValue();
					
			
			
		}
		
		
//		ArrayList<String> runInfo = EightPiecePuzzle.eightPlaceDisplacedTile(null, null);
		

	}

}
