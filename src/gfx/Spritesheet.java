package gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import engine.game;

/**
 * class to take a image and create a bit map, or a bitmap[][].
 * @author Mikael Svensson.
 *
 */
public class Spritesheet {
	private BitMap build;
	/**
	 * takes a image, then a size for x and y and a start pos on the images and makes a bit map of it.
	 * @param path the image you want to create from
	 * @param width how wide the bitmap you want to create is.
	 * @param height how high the bit map you want to create is.
	 * @param startx where to start on the x axel in the image.
	 * @param starty where to start on the y axel in the image.
	 */
	public Spritesheet(String path,int width,int height,int startx,int starty){
		try {
			BufferedImage image = ImageIO.read(game.class.getResourceAsStream(path));
			build = new BitMap(width,height);
			image.getRGB(startx, starty,width,height,build.pixels,0,width);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	//does cut on 3 images and saves it.
	public static final BitMap[][] sprites =cut("/res/icon0.png",32,32);
	public static final BitMap[][] flames =cut("/res/flamesSprite.png",32,32);
	public static final BitMap[][] font=cut("/res/font.png",16,16);
	
	private static BitMap[][]cut(String string,int width,int height){
		return cut(string, width, height,0,0);
	}
	/**
	 * takes a image and cuts it up in to bitmaps.
	 * @param path the image you want to get bits maps out of.
	 * @param width the width of the bit maps you want to create.
	 * @param height the height of the bit maps you want to create.
	 * @param startx where in the image you want to start in the x-axel.
	 * @param starty where in the image you want to start in the y-axel.
	 * @return
	 */
	private static BitMap[][] cut(String path,int width,int height,int startx,int starty){
		try {
			BufferedImage image = ImageIO.read(game.class.getResourceAsStream(path));
			int xTiles=(image.getWidth()-startx)/width;
			int yTiles=(image.getHeight()-starty)/height;
			BitMap [][] result = new BitMap[xTiles][yTiles];
			for(int x=0; x<xTiles;x++){
				for(int y=0;y<yTiles;y++){
					result[x][y] = new BitMap(width,height);
					image.getRGB(startx+x*width, starty+y*height,width,height,result[x][y].pixels,0,width);
				}
			}
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	// gets the bit map the constructor created.
	public BitMap getBuild(){
		return build;
	}
}

