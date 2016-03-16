package obejcts;

import gfx.Screen;
import gfx.Spritesheet;

/**
 * a class for player and other items in the game such as stationary blocks.
 *
 */
public class Obejct {
	private int x,y,sizeY,sizeX,screenX,screenY;
	private Spritesheet sheet;
	/**
	 * Constructor that only takes the position and the image path and then assigns default values to size and start on the image.
	 * @param x
	 * @param y
	 * @param path
	 */
	public Obejct(int x,int y,String path){
		this(x,y,path,32,32,0,0);
	}
	/**
	 * the constructor for obejct, sets the startx and startY to zero and then calls the second constructor with those values.
	 * should take a tile instead of a path not had time to fix yet so that you can have bigger obejcts.
	 * 
	 * @param x its position in the map on the x axel.
	 * @param y its position in the map on the y axel.
	 * @param path the path to the file you want to get the image from.
	 * @param sizeX the size of the obejct on the x axel.
	 * @param sizeY the size of the obejct on the y axel.
	 */
	public Obejct(int x,int y,String path,int sizeX, int sizeY){
		this(x,y,path,sizeX,sizeY,0,0);
	}
	/**
	 * same as the first constructor expect that you set where in the picture you want to start for the rendering of how it looks.
	 * 
	 *@param x its position in the map on the x axel.
	 * @param y its position in the map on the y axel.
	 * @param path the path to the file you want to get the image from.
	 * @param sizeX the size of the obejct on the x axel.
	 * @param sizeY the size of the obejct on the y axel.
	 * @param startX the start of where the bitmap should begin in the image, on the x axel.
	 * @param startY the start of where the bitmap should begin in the image, on the y axel.
	 */
	public Obejct(int x,int y,String path,int sizeX, int sizeY,int startX,int startY){
		this.x=x;
		this.y=y;
		this.sizeX=sizeX;
		this.sizeY=sizeY;
			sheet = new Spritesheet(path,sizeX,sizeY,startX,startY);
	}
	//getters and setters for x and y.
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public void setX(int x){
		this.x=x;
	}
	public void setY(int y){
		this.y=y;
	}
	//getters for sizeX and sizeY
	public int getSizeX(){
		return sizeX;
	}
	public int getSizeY(){
		return sizeY;
	}
	//gets the Spritesheet for the obejct.
	public Spritesheet getSheet(){
		return sheet;
	}
	// setters and getters for where on the screen you want to start.
	public void setScreenX(int screenX){
		this.screenX=screenX;
	}
	public void setScreenY(int screenY){
		this.screenY=screenY;
	}
	public int getScreenX(){
		return screenX;
	}
	public int getScreenY(){
		return screenY;
	}
	/**
	 * method that renders out the obejct on a screen. 
	 * @param screen the screen you want to render to.
	 */
	public void render(Screen screen){
	screen.render(sheet.getBuild(), screenX, screenY);	
	}
}
