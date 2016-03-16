package gfx;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
/**
 * class to create bitMaps from a image it extends bitmap.
 * @author Mikael Svensson.
 *
 */
public class Screen extends BitMap{
	public BufferedImage image;
	public Screen(int width, int height){
		super(width,height);
		image = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
		pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

	}
}
