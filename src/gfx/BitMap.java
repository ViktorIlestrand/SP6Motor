package gfx;

import java.util.Arrays;
/**
 * class that creates a map of the pixels and what color it should be.
 * @author Mikael Svensson.
 *
 */
public class BitMap {
	public int width,height;
	public int[] pixels;
	
	/**
	 * A map of the pixels in the obejct.
	 * @param width the width of the obejct.
	 * @param height the height of the obejct.
	 */
	public BitMap(int width,int height){
		this.width=width;
		this.height=height;
		pixels=new int[width*height];
	}
	//fills the array with one color.
	public void clear (int color)
	{
		Arrays.fill(pixels,color);
	}
	/**
	 * takes a bit map that you want to change, and adds stuff to it.
	 * @param bitmap the bitmap you want to change
	 * @param x where in the bit map you want to start on the x axel.
	 * @param y where in the bit map you want to start on the y axel.
	 */
	public void render(BitMap bitmap,int x, int y){
		int xStart=x;
		int xEnd = x+bitmap.width;
		int yStart=y;
		int yEnd = y+bitmap.height;
		if(xStart<0){
			xStart=0;
		}
		if(xEnd>width){
			xEnd=width;
		}
		if(yStart<0){
			yStart=0;
		}
		if(yEnd<0){
			yEnd=height;
		}
		int AbsoluteWidth =xEnd - xStart;
		for(int yy= yStart;yy<yEnd;yy++){
			int tp = yy * width+xStart;
			int sp = (yy-y)*bitmap.width+(xStart-x); // to find where in the array the pixel is.
			tp -=sp;
			for(int xx =sp; xx<sp+AbsoluteWidth;xx++){
				int col=bitmap.pixels[xx];
				if(col<0 && tp+xx<pixels.length){
					pixels[tp+xx]=col;
				}
			}
		}
	}
}
