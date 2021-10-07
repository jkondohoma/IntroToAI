package hw3;

/**
 * Implementing a simple-reflex agent for the vacuum cleaner problem
 * 
 * @author jaellekondohoma
 *
 */

public class CleanWorld {

	private static final String dirty = "dirty";
	private static final String clean = "clean";
	private static final String right = "right";
	private static final String left = "left";

	/**
	 * given initial state clean up world
	 * 
	 * @param leftStatus
	 * @param rightStatus
	 * @param location
	 */
	public static void cleanWorld(String leftStatus, String rightStatus, String location) {

		World world = new World(leftStatus, rightStatus);
		Agent roomba = new Agent(location, world);

		ReflexAgent(roomba);

	}

	/**
	 * given an agent go trough the process of cleaning up its world
	 * 
	 * @param roomba
	 */
	private static void ReflexAgent(Agent roomba) {
		boolean goalReached = false;
		boolean reachedForFirstTime = false;
		boolean finalGoalAlreadyReached = roomba.getWorld().goalReached();

		int maxsteps = 30;
		int interval = 5;
		// index that will be used to make a room dirty at after certain amount of time
		// has passed
		int time = 0;

		System.out.println("Begin State");
		System.out.printf("%s %s, %s %s, %s %s\n", "Location: ", roomba.getLocation(), "Right Status: ",
				roomba.getWorld().getRightStatus(), "Left Status: ", roomba.getWorld().getLeftStatus());
		System.out.println();

		if (finalGoalAlreadyReached) {

			System.out.println("No actions needed");
			System.out.printf("Cost: %d\n", Agent.getPenalty());

			System.out.println();
		} else {

			// since agent penalized after each possible steps every time we can update
			// agent penalty point increases
			// when we reach a certain number of penalty points we exit the program (just so
			// we're not stuck in an infinite loop)
			System.out.println("Start Cleaning......");
			System.out.println();
			while (Agent.getPenalty() < maxsteps) {

				// Find where you are and what to do
				String location = roomba.getLocation();
				String action = Agent.ReflexVaccuumAgent();
				String leftStat = roomba.getWorld().getLeftStatus();
				String rightStat = roomba.getWorld().getRightStatus();

				System.out.printf("%s %s, %s %s, %s %s, %s %s\n", "Location: ", location, "Right Status: ", rightStat,
						"Left Status: ", leftStat, "Action: ", action);

				System.out.println("perform action......");
				Agent.updateAgent(action);

				location = roomba.getLocation();
				leftStat = roomba.getWorld().getLeftStatus();
				rightStat = roomba.getWorld().getRightStatus();
				goalReached = roomba.getWorld().goalReached();

				if (action.equals(left) || action.equals(right)) {
					System.out.printf("%s %s", "Location: ", location);

				} else {
					System.out.printf("%s %s, %s %s\n", "Right Status: ", rightStat, "Left Status: ", leftStat);
				}

				if (goalReached == true && reachedForFirstTime == false) {
					System.out.println();
					System.out.println("!!!!!!!!!!!!!!!!!!!");
					System.out.println("Goal Reached for the first time");
					System.out.printf("Cost: -%d\n", Agent.getPenalty());
					System.out.println("!!!!!!!!!!!!!!!!!!!");
					System.out.println();
					reachedForFirstTime = true;

				}

				System.out.println("\n******************************************************************");

				// occasionally one room might get dirty again (for the purpose of this program
				// its always the room where the agent is not currently in and rooms start
				// getting dirty after state goal is reached)
				if (Agent.getPenalty() < maxsteps && reachedForFirstTime == true) {

					if (time % interval == 0) {
						System.out.println();

						if (roomba.getLocation().equals(right)) {
							if (roomba.getWorld().getLeftStatus().equals(clean)) {
								roomba.getWorld().setLeftStatus(dirty);
								System.out.println(":( left room got dirty again!!".toUpperCase());
							}

						} else if (roomba.getLocation().equals(left)) {

							if (roomba.getWorld().getRightStatus().equals(clean)) {
								roomba.getWorld().setRightStatus(dirty);
								System.out.println(":( right room got dirty again!!".toUpperCase());
							}

						}

					}

				} else if (Agent.getPenalty() == maxsteps) {
					System.out.println("\n\tSHUTTING DOWN!!........too many steps performed, lost power");
				}
				System.out.println();

				time++;
			}

			System.out.println();
		}
	}

}
