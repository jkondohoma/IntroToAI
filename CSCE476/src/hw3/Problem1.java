package hw3;


public class Problem1 {

	private static final String dirty = "dirty";
	private static final String clean = "clean";
	private static final String right = "right";
	private static final String left = "left";

	public static void main(String[] args) {
		
		
		

		// 1st parameter is status of left cell
		// 2nd parameter is status of right cell
		// 3rd parameter is where the agent starts

		System.out.println("==============================");
		System.out.println("Vacuum Reflex Agent state 1");
		System.out.println("=============================");
		CleanWorld.cleanWorld(dirty, dirty, left);
		System.out.println();
		
		System.out.println("==============================");
		System.out.println("Vacuum Reflex Agent state 2");
		System.out.println("=============================");
		CleanWorld.cleanWorld(dirty, dirty, right);
		System.out.println();
		
		System.out.println("==============================");
		System.out.println("Vacuum Reflex Agent state 3");
		System.out.println("=============================");
		CleanWorld.cleanWorld(clean, clean, left);
		System.out.println();
		
		System.out.println("==============================");
		System.out.println("Vacuum Reflex Agent state 4");
		System.out.println("=============================");
		CleanWorld.cleanWorld(clean, clean, right);
		System.out.println();

		System.out.println("==============================");
		System.out.println("Vacuum Reflex Agent state 5");
		System.out.println("=============================");
		CleanWorld.cleanWorld(dirty, clean, right);
		System.out.println();
		
		System.out.println("==============================");
		System.out.println("Vacuum Reflex Agent state 6");
		System.out.println("=============================");
		CleanWorld.cleanWorld(dirty, clean, left);
		System.out.println();
		
		System.out.println("==============================");
		System.out.println("Vacuum Reflex Agent state 7");
		System.out.println("=============================");
		CleanWorld.cleanWorld(clean, dirty, left);
		System.out.println();
		
		System.out.println("==============================");
		System.out.println("Vacuum Reflex Agent state 8");
		System.out.println("=============================");
		CleanWorld.cleanWorld(clean, dirty, right);
		System.out.println();

	}

}
