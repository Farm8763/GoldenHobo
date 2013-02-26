package GoldenBox;
import robocode.AdvancedRobot;
import robocode.Robot;

public class Location {
	
//map height
private double biggestY = 0;
//map width
private double biggestX = 0;
private final double smallestY = 0;
private final double smallestX = 0;
private double currentX = 0;
private double currentY = 0;

/**
 * Creates default location object
 */
public Location(){
}

/**
 * Call with the robot object. Creates location object
 */
public Location(AdvancedRobot myRobot){
	updateRobot(myRobot);
}

public void updateRobot(AdvancedRobot myRobot){
	updateLocation(myRobot);
	setUpBattlefeild(myRobot);
}

/**
 * Find the distance to the top bottom, from current location.
 * 
 * @return distanceToWall
 */
public double distanceToBottomWall(){
	return (currentY);
}

/**
 * Find the distance to the top wall, from current location.
 * 
 * @return distanceToWall
 */
public double distanceToTopWall(){
	return (biggestY - currentY);
}

/**
 * Find the distance to the left wall, from current location.
 * 
 * @return distanceToWall
 */
public double distanceToLeftWall(){
	return currentX;
}
/**
 * Find the distance to the right wall, from current location.
 * 
 * @return distanceToWall
 */
public double distanceToRightWall(){
	return (biggestX - currentX);
}


private void updateLocation(Robot myRobot){
	setCurrentX(myRobot.getX());
	setCurrentY(myRobot.getY());
}

private void setUpBattlefeild(Robot myRobot){
	setBiggestY(myRobot.getBattleFieldHeight());
	setBiggestX(myRobot.getBattleFieldWidth());
}

public double getBiggestY() {
	return biggestY;
}

public void setBiggestY(double biggestY) {
	this.biggestY = biggestY;
}

public double getBiggestX() {
	return biggestX;
}

public void setBiggestX(double biggestX) {
	this.biggestX = biggestX;
}

public double getSmallestY() {
	return smallestY;
}

public double getSmallestX() {
	return smallestX;
}

public double getCurrentX() {
	return currentX;
}

public void setCurrentX(double d) {
	this.currentX = d;
}

public double getCurrentY() {
	return currentY;
}

public void setCurrentY(double d) {
	this.currentY = d;
}

}
