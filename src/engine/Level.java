package engine;


import obejcts.Obejct;
/**
 * the level of the game, creates a map of where everything is.
 * @author Mikael Svensson.
 *
 */
public class Level {
	private int[][] map;
	private int groundLevel;
	/**
	 * constructor takes 2 ints for how big the map should be, it can't be smaller then the games height and width.
	 * and a int for the groundlevel.
	 * 
	 * @param x the width of the map.
	 * @param y the height of the map.
	 * @param groundLevel the amount of the map from the bottom that is covered with ground.
	 */
	public Level(int x,int y,int groundLevel){
		if(x<game.width || y<game.height){
			throw new IllegalArgumentException("Not big enough map");
		}
		this.groundLevel=groundLevel;
		map=new int[x][y];
		for(int xx=0;xx<map.length;xx++){
			for(int yy=0;y<map[0].length;yy++){
				map[xx][yy]=0;
			}
		}
		addGround(groundLevel);
	}
	/**
	 * adds a obejct to the map by changing the points where the obejct is to 1., the mothod will take x,y 
	 * from the obejct as the start point in the map, sizeX and sizeY from the obejct as for much of the map it will take up.
	 * if the obejct is outside the map it will put it at the nearest edge.
	 * 
	 * @param obejct the obejct to be added
	 * @return true if it was added false if it wasnt.
	 */
	public boolean addObejct(Obejct obejct){
		int x=obejct.getX();
		int y=obejct.getY();
		int xEnd=obejct.getSizeX()+x;
		int yEnd=obejct.getSizeY()+y;
		if(xEnd>=map.length){
			xEnd=map.length-1;
		}
		if(yEnd>=map[0].length){
			yEnd=map[0].length-1;
		}
		if(x<0){
			x=0;
		}
		if(y<0){
			y=0;
		}
		obejct.setX(x);
		obejct.setY(y);
		for(int xx=x;xx<=xEnd;xx++)
		{
			for(int yy=y;yy<=yEnd;yy++)
				map[xx][yy]=1;
		}
		return true;
	}
	/**
	 * removes an obejct by doing the reverse of the add by setting the point its the obejct covers to 0.
	 * @param obejct the obejct you want to remove from the map.
	 */
	public void removeObejct(Obejct obejct){
		int x=obejct.getX();
		int y=obejct.getY();
		int xEnd=obejct.getSizeX()+x;
		int yEnd=obejct.getSizeY()+y;
		if(xEnd>=map.length){
			xEnd=map.length-1;
		}
		if(yEnd>=map[0].length){
			yEnd=map[0].length-1;
		}
		if(x<0){
			x=0;
		}
		if(y<0){
			y=0;
		}
		for(int xx=x;xx<=xEnd;xx++)
		{
			for(int yy=0;yy<=yEnd;yy++)
				map[xx][yy]=0;
		}
	}
	/**
	 * method that adds ground to the map bottom of the map(the highest numbers.)
	 * @param groundlevel the amount above the bottom you want to change to ground.
	 */
	
	public void addGround(int groundlevel){
		for(int x=0;x<this.getMapWidth();x++)
		{
			for(int y=this.getMapHeight()-groundlevel;y<this.getMapHeight();y++)
				map[x][y]=1;
		}
	}
	// getter for the value on a point in the map, the maps width and height and the groundlevel.
	public int getPointValue(int x,int y){
		return map[x][y];
	}
	public int getMapWidth(){
		return map.length;
	}
	public int getMapHeight(){
		return map[0].length;
	}
	public int getGroundLevel(){
		return groundLevel;
	}

}
