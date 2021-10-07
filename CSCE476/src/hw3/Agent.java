package hw3;

/**
 * Vacuum Cleaner Reflex Agent
 * 
 * @author jaellekondohoma
 *
 */

public class Agent {

	private static World world;
	private static String location;
	private static int penalty;

	public Agent(String location, World world) {
		Agent.location = location;
		Agent.world = world;
		Agent.penalty = 0;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		Agent.location = location;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		Agent.world = world;
	}

	/**
	 * get status of whichever room the agent is in
	 * 
	 * @return
	 */
	public static String getStatusLocation() {

		String status = "";

		if (location.equals("right")) {
			status = world.getRightStatus();
		} else if (location.equals("left")) {
			status = world.getLeftStatus();
		}

		return status;

	}

	/**
	 * function that ‘models’ the simple-reflex agent for the vacuum cleaner problem
	 * in an environment with two locations takes any of the 8 possible states of
	 * the vacuum-cleaner and runs the simple-reflect agent until the goal is
	 * reached
	 * 
	 */
	public static String ReflexVaccuumAgent() {

		String action = "";

		if (getStatusLocation().equals("dirty")) {
			action = "suck";

		} else if (location.equals("right")) {
			action = "left";
		} else if (location.equals("left")) {
			action = "right";
		}

		return action;
	}

	/**
	 * given the action the agent has performed the location of the agent and its environment is updated accordingly and
	 * also penalize agent accordingly
	 * 
	 * @param action
	 */
	public static void updateAgent(String action) {
		if (action.equals("right")) {
			penalty = getPenalty() + 1;
			Agent.location = "right";
		} else if (action.equals("left")) {
			Agent.location = "left";
			penalty = getPenalty() + 1;
			
		} else if (action.equals("suck")) {
			penalty = getPenalty() + 1;
			if (location.equals("right")) {
				Agent.world.setRightStatus("clean");
			} else if (location.equals("left")) {
				Agent.world.setLeftStatus("clean");
			}

		}

	}

	public static int getPenalty() {
		return penalty;
	}
}
