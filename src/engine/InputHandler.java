package engine;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * class that checks what is pressed and not.
 * @author Mikael Svenson.
 *
 */
public class InputHandler implements KeyListener{
	public InputHandler(game g){
		g.addKeyListener(this);
	}
	/**
	 * class for the keys that keep track of if the key is pressed or not.
	 * @author Misve
	 *
	 */
	public class Key{
		private boolean pressed = false;
		public boolean isPressed(){
			return pressed;
		}
		public void toggle(boolean isPressed){
			pressed=isPressed;
		}
	}
	public Key up=new Key();
	public Key down=new Key();
	public Key left=new Key();
	public Key right=new Key();
	@Override
	public void keyPressed(KeyEvent e) {
		toggleKey(e.getKeyCode(),true);
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		toggleKey(e.getKeyCode(),false);
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void toggleKey(int keyCode, boolean isPressed){
		if(keyCode == KeyEvent.VK_W){
			up.toggle(isPressed);
		}
		if(keyCode == KeyEvent.VK_S){
			down.toggle(isPressed);
		}
		if(keyCode == KeyEvent.VK_A){
			left.toggle(isPressed);
		}
		if(keyCode == KeyEvent.VK_D){
			right.toggle(isPressed);
		}
		
	}
	

}
