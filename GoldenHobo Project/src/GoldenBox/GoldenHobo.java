package GoldenBox;


import java.awt.Color;

import robocode.BattleEndedEvent;

import robocode.AdvancedRobot;
import robocode.BulletHitBulletEvent;
import robocode.BulletHitEvent;
import robocode.BulletMissedEvent;
import robocode.CustomEvent;
import robocode.DeathEvent;
import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.MessageEvent;
import robocode.RobotDeathEvent;
import robocode.ScannedRobotEvent;
import robocode.SkippedTurnEvent;
import robocode.WinEvent;
import GoldenBox.Location;
import GoldenBox.Movement;

/**
 * GoldenGobo
 *  ...a work-in-progress.
 */
public class GoldenHobo extends AdvancedRobot {
	public static int radarDir;
	boolean nearTheEnemy = false;
	Location ourLocation = new Location();
	double distance1 = 100;
	Movement.Rotation current = Movement.Rotation.CLOCKWISE;
	
	/**
	 * GoldenHobo's run method.
	 */
	@SuppressWarnings("deprecation")
	public void run() {

		ourLocation = new Location(this);
		//Set colors to golden!
		setBodyColor(Color.yellow);
		setGunColor(Color.yellow);
		setRadarColor(Color.yellow);
		setScanColor(Color.yellow);
		setBulletColor(Color.yellow);

		//Set radar movement direction to clockwise (+1, -1 being counter-clockwise).
		radarDir = 1; 

		//Move the radar independent of the gun/body.
		setAdjustRadarForRobotTurn(true);
		setAdjustRadarForGunTurn(true);
		setAdjustGunForRobotTurn(true);

		setTurnGunRight(360);
		setTurnRadarRight(360);


		while (true) {
			ourLocation.updateRobot(this);
			if (!nearTheEnemy){
			//Movement.move(this);
				}
			
			ahead(distance1); // Move ahead 100
			
			if( getRadarTurnRemaining() == 0)
				setTurnRadarRight(360);
			//turnGunRight(360); // Spin gun around
			//back(100); // Move back 100
			//turnGunRight(360); // Spin gun around

		}
	}

	/**
	 * Fire when we see a robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		//Figure out if we should fire or not. Is also the method that turns the gun.
		boolean doWeFire = decideFire(e);
		if( doWeFire )
			setFire(1);
		
		//Try to keep the enemy within scanner by changing rotation direction.
		radarDir *= -1;
		setTurnRadarRightRadians(radarDir * 120);
		Movement.orbitPoint(e.getBearing(), this, current);
	}
	/* Move the gun and decide if it's an opportune time to fire ze missiles. */
	public boolean decideFire(ScannedRobotEvent e)
	{
		//Get info about enemy.
		double eDist = e.getDistance();
		double eVel = e.getVelocity();
		double eHead = e.getHeading();
		double eBear = e.getBearing();
		//Get info about GoldenHobo.
		double aGHead = getGunHeading();
		double aHead = getHeading();
		
		//Determine where they are compared to our gun.
		double bearingFromGun = eBear - aGHead + aHead;
	
		//Turn gun to angle of the enemy.
		setTurnGunRight( bearingFromGun );
		//Print out some useful debugging info.
		System.out.println("E: " + eBear + ", A: " + aGHead + ".  C: " + bearingFromGun + " D: " + (int)eDist );
			
		//Is the enemy with x degrees of our gun? If yes, return permission to fire.
		if( Math.abs(bearingFromGun) < 12)
			return true;
		else
			return false;
	}

	/**
	 * We were hit!  Turn perpendicular to the bullet,
	 * so our seesaw might avoid a future shot.
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		//turnLeft(90 - e.getBearing());
	}
	
	/** A couple more standard events. **/

	//High Priority
	//we collide with the enemy
	public void onHitRobotEvent(HitRobotEvent e) {}
	
	//our bullet hits their robot
	public void onBulletHitEvent(BulletHitEvent e) {}
	
	//we missed, bullet has hit a wall
	public void onBulletMissedEvent(BulletMissedEvent e) {}
	
	//we have hit a wall
	public void onHitWallEvent(HitWallEvent e) {

	}
	
	
	//medium
	//bullet hits bullet
	public void onBulletHitBulletEvent(BulletHitBulletEvent e) {}
	//our robot has died
	public void onDeathEvent(DeathEvent e) {}
	
	
	//another robot has died
	public void onRobotDeathEvent(RobotDeathEvent e) {}
	
	//we skipped an event
	public void onSkippedTurnEvent(SkippedTurnEvent e) {}
	
	
	//Low priority
	public void onWinEvent(WinEvent e) {}
	public void onBattleEndedEvent(BattleEndedEvent e) {}
	public void onCustomEvent(CustomEvent e) {}
	public void onMessageEvent(MessageEvent e) {}
	/** **/
	
	public void determineMap(){
		turnGunRight(90);
		
		turnGunRight(90);
		
		turnGunRight(90);
		
		turnGunRight(90);
	}
}												
