package GUIRelated;

import java.awt.Component;
import java.awt.BorderLayout;

import javax.swing.*;


import java.awt.Color;

/*
 * 
 * 
 * 
 * 
 * 
 * TÖRÖLHETÕ
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */

public class FilteredComboBoxRenderer extends JLabel implements ListCellRenderer<String>{

	private Color buttonBackgroundColor = new Color(33, 35, 39);
	
	public FilteredComboBoxRenderer() {
		setOpaque(true);
		setBackground(buttonBackgroundColor);
		
		
	}
	
	

	@Override
	public Component getListCellRendererComponent(JList list, String value, int index, boolean isSelected,
			boolean cellHasFocus) {	
		
        setForeground(Color.WHITE);
        setBackground(buttonBackgroundColor);
        
        
		if (isSelected) {
			setBorder(BorderFactory.createLineBorder(Color.WHITE));
			//setBackground(buttonBackgroundColor);
        } else {
        	setBorder(BorderFactory.createEmptyBorder());
        	//setBackground(buttonBackgroundColor);
        }
		return this;
	}

	
}