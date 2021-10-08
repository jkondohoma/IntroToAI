package hw4;

public class RandomStateGenerator {
	
	public static int[][] randomState () {
		
		int n = 9;
		
		int[][] puzzle = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j< 9; j++) {
				System.out.println(i+" "+ j);
			}
		}
		
		
		
		
		return puzzle;
		
	}

}
