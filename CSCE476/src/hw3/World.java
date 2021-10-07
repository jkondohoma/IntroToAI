package hw3;

/**
 * 2 Cell environment where a reflex agent will operate
 * 
 * @author jaellekondohoma
 *
 */

public class World {

	private String leftStatus;
	private String rightStatus;

	public World(String leftStatus, String rightStatus) {
		this.leftStatus = leftStatus;
		this.rightStatus = rightStatus;
	}


	public String getLeftStatus() {
		return leftStatus;
	}

	public String getRightStatus() {
		return rightStatus;
	}


	public void setLeftStatus(String leftStatus) {
		this.leftStatus = leftStatus;
	}

	public void setRightStatus(String rightStatus) {
		this.rightStatus = rightStatus;
	}

	public boolean goalReached() {
		boolean valid = false;
		
		if(this.rightStatus.equals("clean") && this.leftStatus.equals("clean")) {
			valid = true;
		}
		
		return valid;
	}
}
