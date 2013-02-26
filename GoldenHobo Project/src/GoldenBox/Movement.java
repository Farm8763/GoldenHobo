package GoldenBox;

import robocode.AdvancedRobot;
import static robocode.util.Utils.normalRelativeAngleDegrees;

public class Movement {

	public final int FLEE = 0;
	public final int ATTACK = 1;
	static int count = 0;
	static int distanceFromWall = 150;
	public enum Direction {
		UP, DOWN, LEFT, RIGHT
	}
	public enum Rotation {
		CLOCKWISE, COUNTERCLOCKWISE
	}

	/**
	 * Orbit around the suplied point
	 * 
	 * @param x
	 * @param y
	 * @param myRobot
	 * @param distance
	 */
	public static void orbitPoint(int x, int y, GoldenHobo myRobot, int distance, Rotation ourRotation){
		
		//setsetTurnRight(normalRelativeAngleDegrees(e.getBearing() + 90))
	}

	/**
	 * Orbit around the suplied point
	 * 
	 * @param x
	 * @param y
	 * @param myRobot
	 * @param distance
	 */
	public static void orbitPoint(double bearing, GoldenHobo myRobot, Rotation ourRotation){
		
		if (getNearWall(myRobot, 36)!= null){

			 // Move ahead 100
			if (ourRotation == Rotation.CLOCKWISE){
				myRobot.setTurnRight(normalRelativeAngleDegrees(bearing + 90));
		
			}
			else if (ourRotation == Rotation.COUNTERCLOCKWISE){
				myRobot.setTurnLeft(normalRelativeAngleDegrees(bearing + 90));
			}
			myRobot.distance1 = -100;
		}
		else{
			if (ourRotation == Rotation.CLOCKWISE){
				myRobot.setTurnRight(normalRelativeAngleDegrees(bearing + 90));
		
			}
			else if (ourRotation == Rotation.COUNTERCLOCKWISE){
				myRobot.setTurnLeft(normalRelativeAngleDegrees(bearing + 90));
			}
			myRobot.distance1 = 100;
		}

	}
		
	/**
	 * Used for development
	 * 
	 * @deprecated
	 */
	public static void move(GoldenHobo myRobot){
		faceRandomWall(myRobot);
	}

	public static Direction getNearWall(GoldenHobo myRobot, double distance){
		Direction nearWall = null;
		if(nearRightWall(myRobot, distance)){
			nearWall = Direction.RIGHT;
		}
		else if(nearLeftWall(myRobot, distance)){
			nearWall = Direction.LEFT;
		}
		else if(nearTopWall(myRobot, distance)){
			nearWall = Direction.UP;
		}
		else if(nearBottomWall(myRobot, distance)){
			nearWall = Direction.DOWN;
		}
		return nearWall;
	}
	
	/**
	 * Moves our robot in a random direction away from enemy
	 * 
	 * @param myRobot
	 * @param enemyDirection
	 */
	public static void randomMoveAway(GoldenHobo myRobot, Direction enemyDirection){

		int randomNum = 1 + (int)(Math.random() * ((3 - 1) + 1));

		if (Direction.DOWN == enemyDirection){
			if (1 == randomNum){
				faceTopWall(myRobot);
			}
			else if(2 == randomNum){
				faceLeftWall(myRobot);
			}
			else if(3 == randomNum){
				faceRightWall(myRobot);
			}
		}
		else if (Direction.UP == enemyDirection){
			if(1 == randomNum){
				faceLeftWall(myRobot);
			}
			else if(2 == randomNum){
				faceRightWall(myRobot);
			}
			else if(3 == randomNum){
				faceBottomWall(myRobot);
			}
		}
		else if (Direction.LEFT == enemyDirection){
			if (1 == randomNum){
				faceTopWall(myRobot);
			}
			else if(2 == randomNum){
				faceRightWall(myRobot);
			}
			else if(3 == randomNum){
				faceBottomWall(myRobot);
			}
		}
		else if (Direction.RIGHT == enemyDirection){
			if (1 == randomNum){
				faceTopWall(myRobot);
			}
			else if(2 == randomNum){
				faceLeftWall(myRobot);
			}
			else if(3 == randomNum){
				faceBottomWall(myRobot);
			}
		}
	}
	
	/**
	 * Moves our robot in one of four random directions 
	 * 
	 * @param myRobot
	 */
	public static void randomMove(GoldenHobo myRobot){

		int randomNum = 1 + (int)(Math.random() * ((4 - 1) + 1));

		if (1 == randomNum){
			myRobot.setTurnRight(45);
		}
		else if(2 == randomNum){
			myRobot.setTurnRight(135);
		}
		else if(3 == randomNum){
			myRobot.setTurnLeft(135);
		}
		else if(4 == randomNum){
			myRobot.setTurnLeft(45);
		}
		
	}
	
	/**
	 * Faces our robot in one of UP, DOWN, LEFT, RIGHT directions 
	 * 
	 * @param myRobot
	 */
	public static void faceRandomWall(GoldenHobo myRobot){

		int randomNum = 1 + (int)(Math.random() * ((4 - 1) + 1));

		if (1 == randomNum){
			faceTopWall(myRobot);
		}
		else if(2 == randomNum){
			faceLeftWall(myRobot);
		}
		else if(3 == randomNum){
			faceRightWall(myRobot);
		}
		else if(4 == randomNum){
			faceBottomWall(myRobot);
		}
		
	}
	
	/**
	 * A check to see if our robot is distanceToWall from a corner
	 * 
	 * @param myRobot
	 * @param distanceToWall
	 * @return
	 */
	public static boolean nearCorner(GoldenHobo myRobot, double distanceToWall){
		boolean nearCorner = false;
		if ((myRobot.ourLocation.distanceToBottomWall() < distanceToWall) && (myRobot.ourLocation.distanceToLeftWall() < distanceToWall)){
			nearCorner = true;
		}
		else if ((myRobot.ourLocation.distanceToTopWall() < distanceToWall)&& (myRobot.ourLocation.distanceToLeftWall() < distanceToWall)){
			nearCorner = true;
		}
		else if ((myRobot.ourLocation.distanceToBottomWall() < distanceToWall) && (myRobot.ourLocation.distanceToRightWall() < distanceToWall)){
			nearCorner = true;
		}
		else if ((myRobot.ourLocation.distanceToTopWall() < distanceToWall)&& (myRobot.ourLocation.distanceToRightWall() < distanceToWall )){
			nearCorner = true;
		}
		return nearCorner;
	}
	
	/**
	 * Turns the robot in a random direction away from the corner.
	 * 
	 * @param myRobot
	 * @param direction, Direction of the wall
	 */
	public static void moveAwayFromWall(GoldenHobo myRobot, Direction direction){
		
		int randomNum = 1 + (int)(Math.random() * ((3 - 1) + 1));

		if (direction == Direction.UP){
			if (1 == randomNum){
				faceLeftWall(myRobot);
			}
			else if(2 == randomNum){
				faceRightWall(myRobot);
			}
			else if(3 == randomNum){
				faceBottomWall(myRobot);
			}
		} else if (direction == Direction.DOWN) {
			if (1 == randomNum){
				faceLeftWall(myRobot);
			}
			else if(2 == randomNum){
				faceRightWall(myRobot);
			}
			else if(3 == randomNum){
				faceTopWall(myRobot);
			}
		} else if (direction == Direction.RIGHT) {
			if (1 == randomNum){
				faceLeftWall(myRobot);
			}
			else if(2 == randomNum){
				faceBottomWall(myRobot);
			}
			else if(3 == randomNum){
				faceTopWall(myRobot);
			}
		} else if (direction == Direction.LEFT) {
			if (1 == randomNum){
				faceRightWall(myRobot);
			}
			else if(2 == randomNum){
				faceBottomWall(myRobot);
			}
			else if(3 == randomNum){
				faceTopWall(myRobot);
			}
		}
	}
	
	/**
	 * Determines if our robot is within the distanceToWall to the wall. Returns true if we are near the wall.
	 * 
	 * @param myRobot
	 * @param distanceToWall
	 * @return boolean, true if close to wall
	 */
	public static boolean nearRightWall(GoldenHobo myRobot, double distanceToWall){
		return (myRobot.ourLocation.distanceToRightWall() < distanceToWall );
	}

	/**
	 * Determines if our robot is within the distanceToWall to the wall. Returns true if we are near the wall.
	 * 
	 * @param myRobot
	 * @param distanceToWall
	 * @return boolean, true if close to wall
	 */
	public static boolean nearLeftWall(GoldenHobo myRobot, double distanceToWall){
		return (myRobot.ourLocation.distanceToLeftWall() < distanceToWall );
	}
	
	/**
	 * Determines if our robot is within the distanceToWall to the wall. Returns true if we are near the wall.
	 * 
	 * @param myRobot
	 * @param distanceToWall
	 * @return boolean, true if close to wall
	 */
	public static boolean nearTopWall(GoldenHobo myRobot, double distanceToWall){
		return (myRobot.ourLocation.distanceToTopWall() < distanceToWall );
	}
	
	/**
	 * Determines if our robot is within the distanceToWall to the wall. Returns true if we are near the wall.
	 * 
	 * @param myRobot
	 * @param distanceToWall
	 * @return boolean, true if close to wall
	 */
	public static boolean nearBottomWall(GoldenHobo myRobot, double distanceToWall){
		return (myRobot.ourLocation.distanceToBottomWall() < distanceToWall );
	}
	
	/**
	 * Turns the robot 45 degrees to the right, moves ahead by distance
	 * 
	 * @param myRobot
	 * @param distance
	 */
	public static void FourtyFiveFlee(AdvancedRobot myRobot, double distance){
		myRobot.setTurnRight(45);
		myRobot.ahead(distance);
	}

	/**
	 * Turns the robot to face the right wall. Parameter is the
	 * current robot object
	 * 
	 * @param myRobot
	 */
	public static void faceRightWall(AdvancedRobot myRobot){

		double degrees = 0;
		degrees = myRobot.getHeading();
		if (degrees > 90 && degrees < 270){
			myRobot.setTurnLeft(degrees - 90);
		}
		else if (degrees < 90 ){
			myRobot.setTurnRight(90 - degrees);
		}
		else if (degrees > 269){
			myRobot.setTurnRight(360 - degrees + 90);
		}

	}	

	/**
	 * Turns the robot to face the left wall. Parameter is the
	 * current robot object
	 * 
	 * @param myRobot
	 */
	public static void faceLeftWall(AdvancedRobot myRobot){

		double degrees = 0;
		degrees = myRobot.getHeading();
		if (degrees > 90 && degrees < 270){
			myRobot.setTurnRight(270 - degrees);
		}
		else if (degrees < 91 ){
			myRobot.setTurnLeft(270 - degrees);
		}
		else if (degrees > 270){
			myRobot.setTurnLeft(degrees - 270);
		}
	}

	/**
	 * Turns the robot to face the bottom wall. Parameter is the
	 * current robot object
	 * 
	 * @param myRobot
	 */
	public static void faceBottomWall(AdvancedRobot myRobot){

		double degrees = 0;
		degrees = myRobot.getHeading();
		if (degrees > -1 && degrees < 180){
			myRobot.setTurnRight(180 - degrees);
		}
		else if (degrees > 179 ){
			myRobot.setTurnLeft(degrees - 180);
		}
	}

	/**
	 * Turns the robot to face the top wall. Parameter is the
	 * current robot object
	 * 
	 * @param myRobot
	 */
	public static void faceTopWall(AdvancedRobot myRobot){

		double degrees = 0;
		degrees = myRobot.getHeading();
		if (degrees > 0 && degrees < 180){
			myRobot.setTurnLeft(degrees);
		}
		else if (degrees > 179 ){
			myRobot.setTurnRight(360 - degrees);
		}

	}
}