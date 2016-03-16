package audio;

import java.applet.Applet;
import java.applet.AudioClip;
/**
 * Simple audio player that takes a and then have the option to play that sound.
 * @author Mikael Svensson.
 *
 */
public class audioPlayer {
	private boolean plays=false;
	private AudioClip clip;
	/**
	 * takes a string for a path to what you file you want to set into the clip.
	 * @param path
	 */
	public audioPlayer(String path) {
		try {
			clip = Applet.newAudioClip(audioPlayer.class.getResource(path));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	/**
	 * a private play option that creates a new thread to play the sound this makes it possible to loop the sound from what i have seen.
	 */
	private void play() {
		try {
			new Thread() {
				public void run() {
					clip.play();
				}
			}.start();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	/**
	 * method that says that we should start playing the sound file, and sets plays as true. if it is true startPlay does nothing,
	 * this to make sure as with the thruster that you can keep the key pressed and it continues to play from the same and not start over all the time.
	 * it sets the clip to a loop so that if you keep the key pressed for longer then the clip is it starts over.
	 */
	public void startPlay(){
		if(!plays)
		{
			play();
			plays=true;
			clip.loop();
		}
	}
	/**
	 * method to stop the sound from playing, sets plays to false so that you can start it again if you want.
	 */
	public void stopPlay(){
		clip.stop();
		plays=false;
	}
}
