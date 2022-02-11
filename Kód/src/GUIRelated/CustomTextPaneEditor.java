package GUIRelated;

import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.Element;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import java.awt.Color;


public class CustomTextPaneEditor{
	private JTextPane textPane;
	private static AttributeSet attributeSet;
	private StyledDocument doc;
	private Element element;
	private int start;
	private MutableAttributeSet asNew;
	private Color pickedColor;
	
	
	public CustomTextPaneEditor(JTextPane textPane) {
		this.textPane = textPane;
		this.doc = textPane.getStyledDocument();
	}
	
	public void setSelectedTextBold(JTextPane textPane){
		start = textPane.getSelectionStart();
		element = doc.getCharacterElement(start);
		attributeSet = element.getAttributes();
		asNew = new SimpleAttributeSet(attributeSet.copyAttributes());
			
		StyleConstants.setBold(asNew, !StyleConstants.isBold(attributeSet));
		doc.setCharacterAttributes(start, textPane.getSelectedText().length(), asNew, false);
	}
	
	public void setSelectedTextItalic(JTextPane textPane){
		start = textPane.getSelectionStart();
		element = doc.getCharacterElement(start);
		attributeSet = element.getAttributes();
		asNew = new SimpleAttributeSet(attributeSet.copyAttributes());
		
		StyleConstants.setItalic(asNew, !StyleConstants.isItalic(attributeSet));
		doc.setCharacterAttributes(start, textPane.getSelectedText().length(), asNew, false);
	}
	
	public void setSelectedTextUnderline(JTextPane textPane){
		int start = textPane.getSelectionStart();
		Element element = doc.getCharacterElement(start);
		attributeSet = element.getAttributes();
		MutableAttributeSet asNew = new SimpleAttributeSet(attributeSet.copyAttributes());
		
		StyleConstants.setUnderline(asNew, !StyleConstants.isUnderline(attributeSet));
		doc.setCharacterAttributes(start, textPane.getSelectedText().length(), asNew, false);
	}
	
	public void setAlignment(String align) {
		SimpleAttributeSet sas = new SimpleAttributeSet();
		
		if(align.equalsIgnoreCase("left")) {
			StyleConstants.setAlignment(sas, StyleConstants.ALIGN_LEFT);
			textPane.setParagraphAttributes(sas, false);
		
		} else if(align.equalsIgnoreCase("center")) {
			StyleConstants.setAlignment(sas, StyleConstants.ALIGN_CENTER);
			textPane.setParagraphAttributes(sas, false);
		
		} else if(align.equalsIgnoreCase("right")) {
			StyleConstants.setAlignment(sas, StyleConstants.ALIGN_RIGHT);
			textPane.setParagraphAttributes(sas, false);
		} 	
		
	}
	
	public void setFontStyle(JComboBox fontStyleMenuBar) {
		start = textPane.getSelectionStart();
		element = doc.getCharacterElement(start);
		attributeSet = element.getAttributes();
		asNew = new SimpleAttributeSet(attributeSet.copyAttributes());
		String style = (String) fontStyleMenuBar.getSelectedItem();
		
		StyleConstants.setFontFamily(asNew, style);
		doc.setCharacterAttributes(start, textPane.getSelectedText().length(), asNew, false);
	}
	
	public void setFontSize(JComboBox fontSizeMenuBar) {
		start = textPane.getSelectionStart();
		element = doc.getCharacterElement(start);
		attributeSet = element.getAttributes();
		asNew = new SimpleAttributeSet(attributeSet.copyAttributes());
		String size = (String) fontSizeMenuBar.getSelectedItem();

		StyleConstants.setFontSize(asNew, Integer.parseInt(size));
		doc.setCharacterAttributes(start, textPane.getSelectedText().length(), asNew, false);
	}
	
	public void setTextColor(JComboBox colorPickerMenuBar) {
		start = textPane.getSelectionStart();
		element = doc.getCharacterElement(start);
		attributeSet = element.getAttributes();
		asNew = new SimpleAttributeSet(attributeSet.copyAttributes());
		String style = (String) colorPickerMenuBar.getSelectedItem();
		
		switch(style) {
			case "Black":
				pickedColor = Color.BLACK;
				break;
			case "Red":
				pickedColor = Color.RED;
				break;
			case "Yellow":
				pickedColor = Color.YELLOW;
				break;
			case "Blue":
				pickedColor = Color.BLUE;
				break;
			case "Green":
				pickedColor = Color.GREEN;
				break;
			case "Orange":
				pickedColor = Color.ORANGE;
				break;
			case "Pink":
				pickedColor = Color.PINK;
				break;
			case "White":
				pickedColor = Color.WHITE;
				break;
		}

		StyleConstants.setForeground(asNew, pickedColor);
		doc.setCharacterAttributes(start, textPane.getSelectedText().length(), asNew, false);
	}
}
