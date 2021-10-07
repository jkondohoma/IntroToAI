package HW2;

import java.util.ArrayList;

/**
 * Missionaries & Cannibals puzzle
 * 
 * Three missionaries and three cannibals must cross a river using a boat that
 * can carry at most two people at a time. All six people start on one bank of
 * the river, and the goal is to find a series of boat rides that results in
 * everyone on the second bank, with the following restrictions:
 * 
 * 1. The boat cannot cross the river with no one on board 2. The cannibals on
 * either bank cannot out number the missionaries on that bank (lest the
 * cannibals eat the missionaries)
 * 
 * Write a program that solves this puzzle using recursion, and displays each of
 * the boat rides used to solve the puzzle when ran.
 * 
 * @author jaellekondohoma
 *
 */

public class Missionaries {

	public static void main(String[] args) {
		ArrayList<String> steps = new ArrayList<String>();
		ArrayList<String> validSteps = new ArrayList<String>();
		int missionaries = 3;
		int canibals = 3;

		possible(missionaries, canibals, 1, 0, 1, 0, 0, steps, validSteps);
		possible(missionaries-1, canibals-1, 0, 1, 1, 0, 1, steps, validSteps);

//		for (String str : steps) {
//			System.out.println(str);
//		}

	}

	/**
	 * checks current scenario
	 * @param missLeft
	 * @param canLeft
	 * @param missBoat
	 * @param canBoat
	 * @param missRight
	 * @param canRight
	 * @param boatLoc
	 * @return
	 */

	public static boolean possible(int missLeft, int canLeft, int missBoat, int canBoat, int missRight, int canRight,
			int boatLoc, ArrayList<String> steps, ArrayList<String> validSteps) {
		boolean valid = true;
		StringBuilder scene = new StringBuilder();

		scene.append(missLeft);
		scene.append(canLeft);
		scene.append(missBoat);
		scene.append(canBoat);
		scene.append(missRight);
		scene.append(canRight);
		scene.append(boatLoc);
		System.out.println(scene.toString());

		// if current scene is valid
		if (solution(missLeft, canLeft, missBoat, canBoat, missRight, canRight, boatLoc)) {
			System.out.println("1st case");
			valid = true;
		} else if (repeatedScenario(scene.toString(), steps)) {
			System.out.println("2nd case");
			valid = false;
			
		} else if (invalidVariables(missLeft, canLeft, missBoat, canBoat, missRight, canRight, boatLoc)) {
			System.out.println("3rd case");
			valid = false;

		} else {
			System.out.println("4th case");

			steps.add(scene.toString());
			valid = possible(missLeft - 2, canLeft, missBoat + 2, canBoat, missRight, canRight, 1, steps,validSteps);
			if (valid) {
				validSteps.add(scene.toString());
				
			}  else {
				valid = possible(missLeft - 2, canLeft, missBoat + 2, canBoat, missRight, canRight, 1, steps,validSteps);
				if (valid) {
					validSteps.add(scene.toString());
					
				} 
				
			}
			

//			// if boat on right go left (0 right 1 left)
//			if (boatLoc == 0) {
//				System.out.println("sure");
//
//				valid = possible(missLeft - 2, canLeft, missBoat + 2, canBoat, missRight, canRight, 1, steps,
//						validSteps);
//				if (valid) {
//					validSteps.add(scene.toString());
//				}
//			} else if (boatLoc == 1) {// if boat on left go right
//
//				valid = possible(missLeft, canLeft, missBoat + 2, canBoat - 2, missRight - 2, canRight + 2, 0, steps,
//						validSteps);
//				if (valid) {
//					validSteps.add(scene.toString());
//
//				}
//			}

		}

		return valid;

	}

	/**
	 * checks if any of the variable are not valid
	 * 
	 * @param missLeft
	 * @param canLeft
	 * @param missBoat
	 * @param canBoat
	 * @param missRight
	 * @param canRight
	 * @param boatLoc
	 * @return
	 */

	public static boolean invalidVariables(int missLeft, int canLeft, int missBoat, int canBoat, int missRight,
			int canRight, int boatLoc) {
		boolean valid = true;

		if (missLeft < 0 || missRight < 0 || canLeft < 0 || canRight < 0 || missBoat < 0 || canBoat < 0
				|| boatLoc < 0) {
			valid = false;

		} else if (missLeft > 3 || canLeft > 3 || missRight > 3 || canRight > 3) {
			valid = false;

		} else if ((missBoat + canBoat) > 2) {
			valid = false;
		}

		return valid;
	}

	/**
	 * 
	 * @param missLeft
	 * @param canLeft
	 * @param missBoat
	 * @param canBoat
	 * @param missRight
	 * @param canRight
	 * @param boatLoc
	 * @return
	 */
	public static boolean solution(int missLeft, int canLeft, int missBoat, int canBoat, int missRight, int canRight,
			int boatLoc) {
		boolean valid = true;

		// The boat cannot cross the river with no one on board
		// The cannibals on either bank cannot out number the missionaries on that bank
		// if boat is not in either right or left (0 right 1 left)

		if ((canLeft > missLeft || canRight > missRight) || (missBoat == 0 && canBoat == 0)) {
			valid = false;
		}

		return valid;

	}

	/**
	 * test if a scenario was already tried
	 * 
	 * @param scene
	 * @return
	 */

	public static boolean repeatedScenario(String scene, ArrayList<String> steps) {
		boolean valid = true;

		for (String str : steps) {
			if (scene.equals(str)) {
				valid = false;
			}
		}

		return valid;

	}

}
