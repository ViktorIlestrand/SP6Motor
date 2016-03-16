package engine;

import gfx.Screen;
import gfx.UI;
import audio.audioPlayer;
import loader.Loader;
import obejcts.Obejct;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
/**
 * the Main class of the game engine the one that runs everything.
 *
 */
public class game extends Canvas implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int width=640,height=480;
	public boolean running=true;
	private Screen screen;
	private long gametimer=0;
	private UI ui;
	double time=0;
	private InputHandler input;
	private physics phys;
	private Obejct player;
	private controller controll;
	private int screenmovement=200; //how much the max is for the screen to move.
	private int thrustLimit=500; //how much thrust you have.
	private int thrustPower=4; //how fast you go.
	private int thrust=thrustLimit; // the amount of thrust.
	private int gravity=2; //how high the gravity is.
	audioPlayer thrustSound;
	audioPlayer backgroundMusic;
	private boolean firstmove=false;
	private int startmsg=3; //how many seconds the start message should appear
	
	public game(){
		screen = new Screen(width,height);
	}
	/**
	 * method to initiate all the stuff that is needed in the game.
	 */
	public void init(){
		input=new InputHandler(this);
		screen = new Screen(width,height);
		ui=new UI(screen,100);
		player=new Obejct(0,0,"/res/Spritesheet.png",32,32);
		controll=new controller(player,ui);
		Loader.init(controll);
		phys=controll.getPhysicsEngine();
		thrustSound=new audioPlayer("/res/Thrust.wav");
		backgroundMusic=new audioPlayer("/res/Artemis.wav");
		backgroundMusic.startPlay();
	}
	/**
	 * starts the game thread.
	 */
	public synchronized void start(){
		new Thread(this).start();
	}
	/**
	 * stops the game
	 */
	public synchronized void stop(){
		running=false;
	}
	/**
	 * the method that runs, it is set so that there is 60 ticks every second and the same amount of frames.
	 */
	public void run() {
		long lastTime=System.nanoTime();
		double nsPerTick=1000000000D/60D;
		double delta=0;
		boolean canRender=false;
		init();
		controll.addToScreen();
		while(running){
			long now =System.nanoTime();
			delta+=(now-lastTime)/nsPerTick;
			lastTime=now;
			while(delta>=1){
				tick();
				delta-=1;
				canRender=true;
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(canRender){
				render();
				canRender=false;
			}
		}
	}
	/**
	 * the logic of the game, the method that controls what happens.
	 */
	public void tick(){
		if(player.getY()<10){
			ui.setFinished(true);
			return;
		}
		if(!firstmove){
			firstMovement();
		}
		controll.moveScreen(screenmovement);
		phys.gravity(gravity);
		ui.removeFlames();
		if(input.up.isPressed() || input.left.isPressed() || input.right.isPressed() || input.down.isPressed()){
			firstmove =true;
		}
		if(!input.up.isPressed() && !input.left.isPressed() && !input.right.isPressed() && !input.down.isPressed()){
			thrustSound.stopPlay();
		}
		if(input.up.isPressed()&& thrust>=0){
			phys.jumpUp(thrustPower);
			ui.addFlames(2);
			thrust=thrust-2;
			thrustSound.startPlay();
		}
		if(input.left.isPressed()&&thrust>=0){
			phys.move(true,thrustPower);
			ui.addFlames(3);
			thrust=thrust-2;
			thrustSound.startPlay();
		}
		if(input.right.isPressed() &&thrust>=0){
			phys.move(false,thrustPower);
			thrust=thrust-2;
			ui.addFlames(4);
			thrustSound.startPlay();
		}
		if(input.down.isPressed() && thrust>=0){
			phys.thrustdown(thrustPower);
			thrust=thrust-1;
			thrustSound.startPlay();
		}
		if(thrust<thrustLimit){
			thrust=thrust+1;
		}
		if(gametimer==0){
			ui.info(thrust, gametimer,startmsg);
		}else{
			time=(double)(System.currentTimeMillis()/100-gametimer);
			time=time/10;
			ui.info(thrust,time,startmsg);
		}
	}
	/**
	 * method to draw out the obejcts to the screen. 
	 * sets a bufferstrategy then uses that to draw out the stuff on the screen.
	 */
	public void render(){
		BufferStrategy bs=getBufferStrategy();
		if(bs==null)
		{
			createBufferStrategy(2);
			requestFocus();
			return;
		}
		Graphics g = bs.getDrawGraphics();
		//Rendering obejct here
		ui.render();
		//done with the rendering
		g.drawImage(screen.image, 0, 0, width, height, null);
		g.dispose();
		bs.show();
	}
	public static void main(String[]args){
		game instance = new game();
		Dimension dimension=new Dimension(width,height);
		instance.setMinimumSize(new Dimension(dimension));
		instance.setMaximumSize(new Dimension(dimension));
		instance.setPreferredSize(new Dimension(dimension));
		instance.setSize(new Dimension(dimension));
		JFrame frame = new JFrame();
		frame.setTitle("game");
		frame.add(instance);
		frame.pack();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		instance.start();
	}
	//getters not sure if i use them anywhere.
	
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	public int getThrust(){
		return thrust;
	}
	public void firstMovement(){
		if(input.up.isPressed() || input.left.isPressed() || input.right.isPressed() || input.down.isPressed()){
			firstmove =true;
			gametimer=System.currentTimeMillis()/100;
		}
	}
}
