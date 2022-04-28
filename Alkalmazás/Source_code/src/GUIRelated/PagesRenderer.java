package GUIRelated;

import java.awt.Component;
import java.awt.GridLayout;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.ListCellRenderer;

import Data.Page;
import GUI.TextEditorButtonFunctions;


public class PagesRenderer extends JPanel implements ListCellRenderer<Page>{
	
	private JLabel pagesNameLabel = new JLabel();
	private JTextPane pageTextPane;
	private TextEditorButtonFunctions buttons;
	
	private Color seperatorColor = new Color(63, 66, 72);
	
	public PagesRenderer(JTextPane pageTextPane, TextEditorButtonFunctions buttons) {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelText = new JPanel(new GridLayout(0, 1));
		panelText.add(pagesNameLabel);
		add(new JLabel("  "), BorderLayout.WEST);
		add(panelText, BorderLayout.CENTER);

		this.pageTextPane = pageTextPane;
		this.buttons = buttons;
	}
	
	@Override
	public Component getListCellRendererComponent(JList<? extends Page> list, Page page, 
		int index, boolean isSelected, boolean cellHasFocus) {

			pagesNameLabel.setText(page.getPageName());
			pagesNameLabel.setOpaque(true);
			pagesNameLabel.setForeground(Color.WHITE);
						
			
			if(isSelected) {
				pagesNameLabel.setBackground(list.getSelectionBackground());
				setBorder(BorderFactory.createLineBorder(Color.WHITE));
				setBackground(list.getSelectionBackground());
				
				if(page.getPageTextStyle() != null) {
					pageTextPane.setStyledDocument(page.getPageTextStyle());
				}
				
				pageTextPane.setOpaque(true);
				pageTextPane.enable();
				pageTextPane.setText(page.getPageText());
				//buttons.enableButtons();
				
			} else {
				pagesNameLabel.setBackground(list.getBackground());
				setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,  seperatorColor));
				setBackground(list.getBackground());
			}
			return this;
		}
}
