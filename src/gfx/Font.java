package gfx;
/**
 * class for a font style with images.
 *
 */
public class Font {
	public static final String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ    "+
										"0123456789-.!?/%$\\=*+,;:()&#\"'"+
										"abcdefghijklmnopqrstuvwxyz    ";
	private Font(){
		
	}
	/**
	 * method to say how long the text is.
	 * @param text the text you want to length of.
	 * @return the text length*12.
	 */
	public static int getStringWidth(String text){
		return text.length()*12;
	}
	/**
	 * method that takes a screen and the write out the msg on it.
	 * @param screen the screen you want to write to.
	 * @param msg the msg you want writen.
	 * @param x where you want the message to start in the width.
	 * @param y where you want the message to start in the height.
	 */
	public static void draw(Screen screen,String msg,int x,int y){
		msg=msg.toUpperCase();
		int length = msg.length();
		for(int i=0; i<length;i++){
			int c = letters.indexOf(msg.charAt(i));
			if(c>0){
				screen.render(Spritesheet.font[c % 30][c/30], x, y);
				x+=12;
			}
			
		}
	}

}
