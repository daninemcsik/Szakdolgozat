package XML;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
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
import org.xml.sax.InputSource;

import Data.*;
import GUIRelated.PopupWindow;

public class XMLWriter {
	private PopupWindow popupWindow = new PopupWindow();
	
	public void write(Content content){
		XMLOutputFactory xof = XMLOutputFactory.newInstance();
		XMLStreamWriter xsw = null;
		String dirPath = null;
		try {
			dirPath = new File(XMLWriter.class.getProtectionDomain().getCodeSource()
					.getLocation().toURI()).getParent();
		} catch (URISyntaxException e2) {
			int val = popupWindow.createErrorHandlingWindow("Code error 12", "URISyntaxException while writing XML files");
			while(val == 1) {
				
			}
		}
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(dirPath + "\\tempXMLWriter.xml");
		} catch (IOException e1) {
			int val = popupWindow.createErrorHandlingWindow("Code error 11", "IOException while writing XML files");
			while(val == 1) {
				
			}
		}
		try {
			xsw = xof.createXMLStreamWriter(fileWriter);
			xsw.writeStartDocument();
			xsw.writeStartElement("notelist");
			
			for(int i = 0; i < content.getNoteListModel().getSize(); i++) {
				xsw.writeStartElement("note");
				
					xsw.writeStartElement("noteName");
					xsw.writeCharacters(content.getNoteListModel().get(i).getNoteName());
					xsw.writeEndElement();
				
					xsw.writeStartElement("id");
					xsw.writeCharacters(Integer.toString(content.getNoteListModel().get(i).getId()));
					xsw.writeEndElement();
					
					xsw.writeStartElement("pagelist");
					
					for(int j = 0; j < content.getNoteListModel().get(i).getNotesPages().size(); j++) {
						xsw.writeStartElement("page");
						
							xsw.writeStartElement("pageName");
							xsw.writeCharacters(content.getNoteListModel().get(i).getNotesPages().get(j).getPageName());
							xsw.writeEndElement();
							
							xsw.writeStartElement("pageText");
							xsw.writeCData(content.getNoteListModel().get(i).getNotesPages().get(j).getPageText());
							
							xsw.writeEndElement();
							
							xsw.writeStartElement("pageId");
							xsw.writeCharacters(Integer.toString(content.getNoteListModel().get(i).getNotesPages().get(j).getId()));
							xsw.writeEndElement();
							
							xsw.writeStartElement("noteId");
							xsw.writeCharacters(Integer.toString(content.getNoteListModel().get(i).getNotesPages().get(j).getNoteId()));
							xsw.writeEndElement();
					
						xsw.writeEndElement();
					}
					
					xsw.writeEndElement();
				
				xsw.writeEndElement();
			}
			
			xsw.writeEndElement();
			xsw.writeEndDocument();
			xsw.flush();
			
			format(dirPath + "\\tempXMLWriter.xml");
			
			xsw.close();
			fileWriter.close();
			
			
			
			
			
	
			
			
			
		} catch (Exception e) {
			int val = popupWindow.createErrorHandlingWindow("Code error 13", "XML stream error while writing XML files");
			while(val == 1) {
				
			}
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
			
		}
	}
}
