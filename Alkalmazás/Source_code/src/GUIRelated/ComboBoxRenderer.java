package GUIRelated;

import java.awt.Component;

import javax.swing.*;

import java.awt.Color;


public class ComboBoxRenderer extends JLabel implements ListCellRenderer<String>{

	private Color buttonBackgroundColor = new Color(33, 35, 39);
	
	public ComboBoxRenderer() {
		setOpaque(true);
		setBackground(buttonBackgroundColor);
		
		
	}
	
	

	@Override
	public Component getListCellRendererComponent(JList list, String value, int index, boolean isSelected,
			boolean cellHasFocus) {	
		
        setText("  " + value.toString());
        setForeground(Color.WHITE);
        setBackground(buttonBackgroundColor);
        
		if (isSelected) {
			setBorder(BorderFactory.createLineBorder(Color.WHITE));
        } else {
        	setBorder(BorderFactory.createEmptyBorder());
        	//setBackground(buttonBackgroundColor);
        }
		
		return this;
	}

	
}
