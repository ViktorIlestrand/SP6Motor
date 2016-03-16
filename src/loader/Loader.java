package loader;

import obejcts.Obejct;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import engine.controller;
/**
 * class to load the info in a xml file to the game.
 *
 */
public class Loader {
	/**
	 * a static method that takes a controller obejct and then runs the static method loadLevel, later on will make it so that
	 * loadlevel takes a input also so that you can switch level. doesn't have any controll on the indata at the moment.
	 * 
	 * @param con the controller that we want to add the obejcts to.
	 */
	public static void init(controller con){
		loadLevel(con);
	}
	/**
	 * it takes a XML file then finds all the obejcts on floor 1 and first sets the level on the controller to the levels found in the document
	 * then adds all the obejct to the controller.
	 * 
	 * @param con the controller you want to add stuff to.
	 */
	private static void loadLevel(controller con){
		XMLFile LevelFile = new XMLFile("/res/Level.xml");
		Document doc=LevelFile.getDocument();
		NodeList levelList =((Element)doc.getElementsByTagName("Levels").item(0)).getElementsByTagName("Floor");
		Element root=(Element)levelList.item(0);
		NodeList level=root.getElementsByTagName("level");
		NodeList Obejcts =root.getElementsByTagName("Obejct");
		Element lvl=(Element)level.item(0);
		int x=Integer.parseInt(lvl.getAttribute("x"));
		int y= Integer.parseInt(lvl.getAttribute("y"));
		int ground= Integer.parseInt(lvl.getAttribute("groundLevel"));
		con.setLevel(x,y ,ground);
		for(int i =0;i<Obejcts.getLength();i++){
			Element ob=((Element) Obejcts.item(i));
			x=Integer.parseInt(ob.getAttribute("x"));
			y=Integer.parseInt(ob.getAttribute("y"));
			String path=ob.getAttribute("path");
			int sizeX=Integer.parseInt(ob.getAttribute("sizeX"));
			int sizeY=Integer.parseInt(ob.getAttribute("sizeY"));
			int startX=Integer.parseInt(ob.getAttribute("startX"));
			int startY=Integer.parseInt(ob.getAttribute("startY"));
			con.addObejct(new Obejct(x,y,path,sizeX,sizeY,startX,startY));		
			
		}
	}

}
