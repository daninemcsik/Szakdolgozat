package XML;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import Data.*;
import GUIRelated.PopupWindow;

public class XMLReader extends DefaultHandler{
	private Note note;
	private Page page;
	private Content content;
	private boolean noteName;
	private boolean id;
	private boolean pageName;
	private boolean pageText;
	private boolean pageId;
	private boolean noteId;
	private PopupWindow popupWindow = new PopupWindow();
	
	public void parseFile(String file, Content content){
		try {
			this.content = content;
			SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
			parser.parse(new File(file), this);
			
		} catch (SAXException e) {
			int val =popupWindow.createErrorHandlingWindow("Code error 5", "SAXException while parsing XML file");
			while(val == 1) {
				
			}
		} catch (IOException e) {
			int val =popupWindow.createErrorHandlingWindow("Code error 4", "Error while parsing XML file");
			while(val == 1) {
				
			}
		} catch (ParserConfigurationException e) {
			int val =popupWindow.createErrorHandlingWindow("Code error 6", "ParserConfigurationException while parsing XML file");
			while(val == 1) {
				
			}
		}
				
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) {
	    switch(qName) {
	    case "noteName":
	    	noteName = true;
	    	break;
	    case "id":
	    	id = true;
	    	break;
	    case "pageName":
	    	pageName = true;
	    	break;
	    case "pageText":
	    	pageText = true;
	    	break;
	    case "pageId":
	    	pageId = true;
	    	break;
	    case "noteId":
	    	noteId = true;
	    	break;
	    }
	}

	@Override
	public void endElement(String uri, String localName, String qName) {
		switch(qName) {
	    case "noteName":
	    	noteName = false;
	    	break;
	    case "id":
	    	id = false;
	    	break;
	    case "pageName":
	    	pageName = false;
	    	break;
	    case "pageText":
	    	pageText = false;
	    	break;
	    case "pageId":
	    	pageId = false;
	    	break;
	    case "noteId":
	    	noteId = false;
	    	break;
	    case "page":
	    	note.addPageToNote(page);
	    	break;
	    case "note":
	    	content.addNoteTolistModel(note);
	    	break;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) {
	    String innerString = new String(ch, start, length);
		if(noteName) {
	    	note = new Note();
	    	note.setNoteName(innerString);
	    }else if(id) {
	    	note.setId(Integer.parseInt(innerString));
	    }else if(pageName) {
	    	page = new Page(innerString);
	    }else if(pageText) {
	    	page.setPageText(page.getPageText().concat(innerString));
	    }else if(pageId) {
	    	page.setId(Integer.parseInt(innerString));
	    }else if(noteId) {
	    	page.setNoteId(Integer.parseInt(innerString));
	    }
	}
}
