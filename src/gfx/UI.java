package gfx;

import java.util.ArrayList;
import java.util.List;

import obejcts.Obejct;
import engine.game;
import gfx.level.tile;
/**
 * the main render method takes all the info that is needed and then adds it to the screen.
 *
 */
public class UI {
	private int groundLevel;
	private List<Obejct> Obejcts=new ArrayList<Obejct>();
	private Obejct player;
	private int whichFlames=0;
	private Screen screen;
	private int thrust=0;
	private int startmsg=0;
	double time=0;
	private boolean flames=false,finished=false;
	public UI(Screen screen,int ground){
		this.screen=screen;
		groundLevel=game.height-ground;
	}
	public void render(){
		//This draws the background of the level, at the moment it is allways the BitMap for Spritesheet sprites and the first 32 pixels of it.
		for(int i =0;i<game.width/tile.SIZE;i++){
			for(int j =0;j<game.height/tile.SIZE;j++){
			screen.render(Spritesheet.sprites[0][0],i*tile.SIZE, j*tile.SIZE);
			}
		}
		// if we have thrust it render out the flames that we get.
		if(flames){
			renderFlames();
		}
		//render out the obejct from the list obejcts.
		for(Obejct o:Obejcts){
			o.render(screen);
			
		}
		//The ground, can be made faster probobly as it is now it goes through 
		//all the pixels on the screen when it could just go through those that is for the ground, it is to be fixed at a later date.
		for(int i=0;i<game.width;i++){
			for(int j=0;j<game.height;j++){
				int xPos=(i)*tile.SIZE;
				int yPos=(j)*tile.SIZE+groundLevel;
				if(xPos<game.width && yPos<game.height){
					screen.render(Spritesheet.sprites[3][0],xPos,yPos);
				}
			}
		}
		// render out the player.
		player.render(screen);
		//checks if the time elapsed is less then 5 since the game start and if it is it prints "get to the top" on the screen
		if(time<startmsg){
			String line="GET TO THE TOP";
			Font.draw(screen,line, game.width/2-(Font.getStringWidth(line))/2, game.height/2-50);
		}
		//checks if you have finished the level and if you have (you have gotten to the top) prints "you finished the level with
		// A time of" and the time the game has run + "seconds" in two rows.
		if(finished){
			String line1= "YOU FINISHED THE LEVEL WITH";
			String line2="A TIME OF "+time+" SECONDS";
			Font.draw(screen,line1, (game.width/2)-(Font.getStringWidth(line1))/2, game.height/2-60);
			Font.draw(screen,line2, (game.width/2)-(Font.getStringWidth(line2))/2, game.height/2-40);
		}
		//prints out in the corner of the screen the amount of thrust left and the time since the game started.
		Font.draw(screen, "Thrust Left: "+thrust, 10, 10);
		Font.draw(screen, "TIME: "+time, 20, 20);
	}
	/**
	 * method that you call when you want to add flames around the player, it sets flames to true which will do that the flames render.
	 * and it sets whichFlames to sort.
	 * 
	 * @param sort which side the flames should be on.
	 */
	public void addFlames(int sort){
		flames=true;
		whichFlames=sort;
	}
	/**
	 * a method to stop render flames, does it by setting flames to false.
	 */
	public void removeFlames(){
		flames=false;
	}
	/**
	 * a method to render flames, it takes the instanced verible whichflames which says which side you want the flames
	 * and then render the flames on that side.
	 */
	public void renderFlames(){
		switch(whichFlames){
		case 0:
			screen.render(Spritesheet.flames[0][0],player.getScreenX()+player.getSizeX(),player.getScreenY());
			screen.render(Spritesheet.flames[1][0],player.getScreenX(),player.getScreenY()+player.getSizeY());			
			break;
		case 1:
			screen.render(Spritesheet.flames[3][0],player.getScreenX()-32,player.getScreenY());
			screen.render(Spritesheet.flames[1][0],player.getScreenX(),player.getScreenY()+player.getSizeY());
			break;
		case 2:
			screen.render(Spritesheet.flames[1][0],player.getScreenX(),player.getScreenY()+player.getSizeY());
			break;
		case 3:
			screen.render(Spritesheet.flames[0][0],player.getScreenX()+32,player.getScreenY());
			break;
		case 4:
			screen.render(Spritesheet.flames[3][0],player.getScreenX()-32,player.getScreenY());
			break;
		}
	}
	/**
	 * a method to add the player to the ui, adds the player if the instanced verible player is null, otherwise it doesn't do anything.
	 * @param p the obejct to be added to the player.
	 * @return true if it added the obejct false if the already existed a player. 
	 */
	public boolean addPlayer(Obejct p){
		if(player==null){
			player=p;
			return true;
		}
		return false;
	}
	/**
	 * method to call if you want to add an obejct to be rendered, it adds the obejct to the list that is being rendered.
	 * @param o the obejct to be rendered.
	 */
	public void addObejct(Obejct o){
		Obejcts.add(o);
	}
	/**
	 * removes an obejct from being rendered.
	 * @param o the obejct to remove for the list of obejct to be rendered.
	 */
	public void removeObejct(Obejct o){
		Obejcts.remove(o);
	}
	public void removeAllObejcts(){
		Obejcts.removeAll(Obejcts);
	}
	/**
	 * method that gets the groundlevel on the screen.
	 * @return the groundlevel.
	 */
	public int getGroundLevel(){
		return game.height-groundLevel;
	}
	/**
	 * method that sets the groundlevel to the height of the game - the param level.
	 * 
	 * @param level what you want to set the groundlevel to.
	 */
	public void setGroundLevel(int level){
		groundLevel=game.height-level;
	}
	/**
	 * a method to set the time and thrust.
	 * 
	 * @param thrust the thrust level from the game.
	 * @param time the time the game have been running.
	 */
	public void info(int thrust,double time, int start){
		this.thrust=thrust;
		this.time=time;
		startmsg=start;
	}
	/**
	 * method to get the message you get when you finish
	 * 
	 * @param fin true if the game is over false if its not.
	 */
	public void setFinished(boolean fin){
		finished=fin;
	}

}
