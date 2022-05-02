package XML;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import GUIRelated.PopupWindow;


public class ConfigManager extends DefaultHandler{

	private boolean dbJsonPathBoolean;
	private boolean dbStoragePathBoolean;
	
	private String dbJsonPath;
	private String dbStoragePath;
	
	private String configFilePath;
	private PopupWindow popupWindow = new PopupWindow();
	
	public ConfigManager() {
		try {
			this.configFilePath = new File(ConfigManager.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent() + "\\config.xml";
			System.out.println(configFilePath);
		} catch(URISyntaxException e) {
			int val = popupWindow.createErrorHandlingWindow("Code error 12", 
					"URISyntaxException while creating config file");
    		while(val == 1) {}
		}
		init();
		
	}
	
	public void init(){
		File file = new File(configFilePath);
		if(!file.exists()) {
			write();
		}else {
			parseConfigFile(configFilePath);
		}
		
		
	}
	
	public void parseConfigFile(String file) {
		try {
			SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
			parser.parse(new File(file), this);
			
			
		} catch (SAXException e) {
			int val =popupWindow.createErrorHandlingWindow("Code error 5", "SAXException while parsing config file");
			while(val == 1) {
				
			}
		} catch (IOException e) {
			int val =popupWindow.createErrorHandlingWindow("Code error 4", "Error while parsing config file");
			while(val == 1) {
				
			}
		} catch (ParserConfigurationException e) {
			int val =popupWindow.createErrorHandlingWindow("Code error 6", "ParserConfigurationException while parsing config file");
			while(val == 1) {
				
			}
		}	
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes){
	    switch(qName) {
	    case "dbJsonPath":
	    	dbJsonPathBoolean = true;
	    	break;
	    
		case "dbStoragePath":
			dbStoragePathBoolean = true;
	    	break;
	    }
	}

	@Override
	public void endElement(String uri, String localName, String qName) {
		switch(qName) {
		case "dbJsonPath":
	    	dbJsonPathBoolean = false;
	    	break;
	    
		case "dbStoragePath":
			dbStoragePathBoolean = false;
	    	break;
	    }
	}

	@Override
	public void characters(char[] ch, int start, int length) {
	    String innerString = new String(ch, start, length);
		if(dbJsonPathBoolean) {
			dbJsonPath = innerString;
	    }else if(dbStoragePathBoolean) {
	    	dbStoragePath = innerString;
	    }
	}
	
	public void write(){
		XMLOutputFactory xof = XMLOutputFactory.newInstance();
		XMLStreamWriter xsw = null;
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(configFilePath);
		} catch (IOException e1) {
			int val =popupWindow.createErrorHandlingWindow("Code error 3", "Incorrect config file path");
			while(val == 1) {
				
			}
		}
		try {
			xsw = xof.createXMLStreamWriter(fileWriter);
			xsw.writeStartDocument();
			xsw.writeStartElement("config");
			
				xsw.writeStartElement("editable");
					xsw.writeStartElement("dbJsonPath");
					xsw.writeCharacters("C:\\Users\\Example\\Downloads\\firebase_service_key_path.json");
					xsw.writeEndElement();
					
					xsw.writeStartElement("dbStoragePath");
					xsw.writeCharacters("firebase_example-15f47.appspot.com");
					xsw.writeEndElement();		
				xsw.writeEndElement();
							
			xsw.writeEndElement();
			xsw.writeEndDocument();
			xsw.flush();
			
			format(configFilePath);
			
			xsw.close();
			fileWriter.close();
			
		} catch (Exception e) {
			int val = popupWindow.createErrorHandlingWindow("Code error 11", 
					"Exception while writing config file");
			while(val == 1) {}
		}
	
	}
	
	public void format(String file) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new InputStreamReader(new FileInputStream(file))));
		
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			
			Source source = new DOMSource(document);
			Result result = new StreamResult(new File(file));
			transformer.transform(source, result);
		}catch(Exception e) {
			int val = popupWindow.createErrorHandlingWindow("Code error 11", 
					"Exception while writing config file");
			while(val == 1) {}
		}
	}
	
	public String getDbJsonPath() {
		return dbJsonPath;
	}
	public String getDbStoragePath() {
		return dbStoragePath;
	}
	
}
