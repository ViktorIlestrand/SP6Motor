package engine;
import obejcts.Obejct;
/**
 * simple physic motor for the game does collisions without consequences and simple gravity. 
 * @author Mikael Svensson.
 *
 */
public class physics {
	private Obejct player;
	private Level level;
	boolean inAir,collision;
	/**
	 * constructor for the physics class, takes a obejct and a level
	 * @param player the obejct that we have as a player.
	 * @param level the level you are playing on.
	 */
	public physics(Obejct player,Level level){
		this.player=player;
		this.level=level;
	}
	/**
	 * moves the player upwards in the map and the screen,
	 *  after checking so that he dont collide with anything above him.
	 *  
	 * @param move the amount you want to move the player.
	 */
	public void jumpUp(int move){
		if(!checkCollisonUp()){
			player.setY(player.getY()-move);
			player.setScreenY(player.getScreenY()-move);
		}
	}
	/**
	 * the gravity in the game, as long as the player doesn't have anything beneath him,
	 * if he doesn't have also sets the varible isAir to true.
	 * 
	 * @param n how high you want the gravity to be.
	 */
	public void gravity(int n){
		//check om player är på marken
		if(!checkCollisonDown()){
			inAir=true;
			player.setY(player.getY()+n);
			player.setScreenY(player.getScreenY()+n);
		}
		else{
			inAir=false;
		}
		//ta bort n från player.setY och n player.setscreenY
	}
	/**
	 * moves the player down on the screen and map unless he have anything beneath him
	 * 
	 * @param move the amount you want to move.
	 */
	public void thrustdown(int move){
		if(!checkCollisonDown()){
			player.setScreenY(player.getScreenY()+move);
			player.setY(player.getY()+move);
		}
	}
	/**
	 * method to move the player sideways checks so that its clear in the direction you want to move and then moves you
	 * in that direction.
	 * 
	 * @param left if true moves player to the left if false moves player to the right
	 * @param move the amount you want to move.
	 */
	public void move(boolean left,int move){
		if(!checkCollisoleft()&&left){
			player.setX(player.getX()-move);
			player.setScreenX(player.getScreenX()-move);
		}
		if(!checkCollisoRight()&&!left){
			player.setX(player.getX()+move);
			player.setScreenX(player.getScreenX()+move);
		}
	}
	/**
	 * checks if the player have anything above him, the ceiling (in this case the upper end of the map array.)
	 * 
	 * @return true if he have anything above him, false if he dont.
	 */
	private boolean checkCollisonUp(){
		if(player.getY()-5<=0){
			return true;
		}
		for(int x=player.getX();x<=(player.getX()+player.getSizeX());x++){
			if(level.getPointValue(x, player.getY()-1)==1){
				return true;
			}
		}
		return false;
	}
	/**
	 * checks if the player have anything below him, the ceiling (in this case the upper end of the map array.)
	 * 
	 * @return true if he have anything above him, false if he dont.
	 */
	private boolean checkCollisonDown(){
		if(player.getY()+player.getSizeY()+5>=level.getMapHeight()){
			return true;
		}
		for(int x=player.getX();x<=(player.getX()+player.getSizeX());x++){
			if(level.getPointValue(x, player.getY()+player.getSizeY()+1)==1){
				return true;
			}
		}
		return false;
	}
	/**
	 * checks if the player have anything to the left of him, the ceiling (in this case the upper end of the map array.)
	 * 
	 * @return true if he have anything above him, false if he don't.
	 */
	private boolean checkCollisoleft(){
		if(player.getX()-5<=0){
			return true;
		}
		for(int y=player.getY();y<=(player.getY()+player.getSizeY());y++){
			if(level.getPointValue(player.getX()-1, y)==1){
				return true;
			}
		}
		return false;
	}
	/**
	 * checks if the player have anything to the right of him, the ceiling (in this case the upper end of the map array.)
	 * 
	 * @return true if he have anything above him, false if he don't.
	 */
	private boolean checkCollisoRight(){
		if(player.getX()+player.getSizeX()+5>=level.getMapWidth()){
			return true;
		}
		for(int y=player.getY();y<=(player.getY()+player.getSizeY());y++){
			if(level.getPointValue(player.getX()+player.getSizeX()+1, y)==1){
				return true;
			}
		}
		return false;
	}
	
}
