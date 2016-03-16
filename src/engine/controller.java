package engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import obejcts.Obejct;
import gfx.UI;

/**
 * class that Controls what obejcts should be on the screen and if the screens center point in the level should move.
 *
 */
public class controller {
	private List<Obejct> obejcts=new ArrayList<Obejct>();
	private Obejct player;
	private UI ui;
	private Level level;
	private physics phys;
	private int screenMiddleX,screenMiddleY;
	Random r=new Random();
	public controller(Obejct player,UI ui){
		this.player=player;
		this.ui=ui;
		ui.addPlayer(player);
	}
	/**
	 * takes the param that a level needs and then creates a level and sets all the things in controller and player that is depend on the level
	 * to their values.
	 * @param x the width of the level in pixels
	 * @param y the height of the level in pixels
	 * @param ground how high the ground is. So if it is 100 for exampel you cant move on the lowest pixels.
	 */
	public void setLevel(int x,int y,int ground){
		level=new Level(x,y,ground);
		ui.setGroundLevel(level.getGroundLevel());
		screenMiddleX=level.getMapWidth()/2;
		screenMiddleY=level.getMapHeight()- game.height/2;
		player.setX(screenMiddleX);
		player.setY(screenMiddleY);
		player.setScreenX(game.width/2);
		player.setScreenY(game.height/2);
		phys=new physics(player,level);
	}
	/**
	 * a method to add obejct to the ui class so that it can be rendered.
	 * it check if the obejct is within the screen and if it is where on the screen it is then sets those param so that the render can get them.
	 */
	public void addToScreen(){
		for(Obejct o:obejcts){
			if(checkOutOfScreen(o)){
				int pointDiffX=o.getX()-screenMiddleX;
				int pointDiffY=o.getY()-screenMiddleY;
				o.setScreenX(pointDiffX+game.width/2);
				o.setScreenY(pointDiffY+game.height/2);
				ui.addObejct(o);
			}
		}
	}
	/**
	 * adds an obejct to the obejct list and to the level.
	 * @param obejct the obejct to be added
	 * @return true if it was added and false if it wasnt
	 */
	public boolean addObejct(Obejct obejct){
		obejcts.add(obejct);
		return level.addObejct(obejct);
		
	}
	/**
	 * move's where the screen focuses on the level, it checks with shouldMove which way or if it needs to move
	 * 
	 * @param max	the Maxium distances you want to move
	 */
	public void moveScreen(int max){
		int move=shouldMoveScreen(max/4);
		if(move==0){
			return;
		}
		switch(move){
		case 1:
			moveScreenXR(canMoveXR(max));
			ui.removeAllObejcts();
			addToScreen();
			break;
		case 2:
			moveScreenYD(canMoveYD(max));
			ui.removeAllObejcts();
			addToScreen();
			ui.setGroundLevel(ui.getGroundLevel()+canMoveYD(max));
			break;
		case 3:
			moveScreenXL(canMoveXL(max));
			ui.removeAllObejcts();
			addToScreen();
			break;
		case 4:
			moveScreenYU(canMoveYU(max));
			ui.removeAllObejcts();
			addToScreen();
			ui.setGroundLevel(ui.getGroundLevel()-canMoveYU(max));
			break;
		}
	}
	/**
	 * All the moveScreen takes an int then it moves the screenmiddle and the players screen pos.
	 * can be made into 1 function, that will be done.
	 * 
	 * @param moveDistance the amount to move the screen.
	 */	
	private void moveScreenXR(int moveDistance){
			player.setScreenX(player.getScreenX()-moveDistance);
			screenMiddleX=screenMiddleX+moveDistance;
	}
	private void moveScreenXL(int moveDistance){
			player.setScreenX(player.getScreenX()+moveDistance);
			screenMiddleX=screenMiddleX-moveDistance;
	}
	private void moveScreenYU(int moveDistance){
			player.setScreenY(player.getScreenY()+moveDistance);
			screenMiddleY=screenMiddleY-moveDistance;
	}
	private void moveScreenYD(int moveDistance){
		player.setScreenY(player.getScreenY()-moveDistance);
			screenMiddleY=screenMiddleY+moveDistance;
			
	}
	/**
	 * checks if an obejct is outside the screen.
	 * 
	 * @param o the obejct to be check.
	 * @return
	 */
	private boolean checkOutOfScreen(Obejct o){
		if(o.getX()>(screenMiddleX+game.width/2)){
			return false;
		}
		if(o.getY()>(screenMiddleY+game.height/2)){
			return false;
		}
		if(o.getX()<screenMiddleX-game.width/2){
			return false;
		}
		if(o.getY()<screenMiddleY-game.height/2){
			return false;
		}
		return true;
	}
	/**
	 * checks if the player is close to an edge of the screen and if you can move the screen in that direction.
	 * 
	 * @return 1 if you should move the screen to the right, 2 for down, 3 for left and 4 for up.
	 */
	private int shouldMoveScreen(int limit){
		if(player.getScreenX()>=game.width-(limit*2)){
			return 1;
		}
		if(player.getScreenY()>=game.height-(limit*2)){
			return 2;
		}
		if(player.getScreenX()<=limit){
			return 3;
		}
		if(player.getScreenY()<=limit){
			return 4;
		}
		return 0;
	}
	/**
	 * it check if the screen can be move in a direction, and then returns how much it can be moved
	 * if it is more then max it returns max.
	 * 
	 * @param max the maximum amount to check.
	 * @return
	 */
	private int canMoveXR(int max){
		int moveDistance=level.getMapWidth()-screenMiddleX+game.width/2;
		if(moveDistance>max){
			return max;
		}
		return moveDistance;
	}
	private int canMoveYD(int max){
		int moveDistance=level.getMapHeight()-screenMiddleY+game.height/2;
		if(moveDistance>max){
			return max;
		}
		return moveDistance;
	}
	private int canMoveXL(int max){
		int moveDistance=screenMiddleX-game.width/2;
		if(moveDistance>max){
			return max;
		}
		return moveDistance;
	}
	private int canMoveYU(int max){
		int moveDistance=screenMiddleY-game.height/2;
		if(moveDistance>max){
			return max;
		}
		return moveDistance;
	}
	//get methods
	public int getScreenMiddleY(){
		return screenMiddleY;
	}
	public void setScreenMiddleY(int n){
		screenMiddleY=n;
	}
	public int getScreenMiddleX(){
		return screenMiddleX;
	}
	public void setScreenMiddleX(int n){
		screenMiddleY=n;
	}
	public physics getPhysicsEngine(){
		return phys;
	}

}
