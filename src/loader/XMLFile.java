package loader;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import engine.game;
/**
 * class to create a document from a xml file.
 *
 */
public class XMLFile {
	private String loc;
	private Document xmlDoc;
	/**
	 * a class to parse a xml file to a java document.
	 * 
	 * @param location is where the xml file that we what to parse to Document is.
	 */
	public XMLFile(String location){
		loc=location;
		try {
			init();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		xmlDoc.normalize();
	}
	/**
	 * init creates a Document from an xmlfile.
	 * 
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	private void init() throws ParserConfigurationException, SAXException, IOException{
		DocumentBuilderFactory builder = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = builder.newDocumentBuilder();
		xmlDoc = docBuilder.parse(game.class.getResourceAsStream(loc));
	}
	public String getPath(){
		return loc;
	}
	public Document getDocument(){
		return xmlDoc;
	}

}
