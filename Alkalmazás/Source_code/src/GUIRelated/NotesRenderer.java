package GUIRelated;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.ListCellRenderer;

import Data.Content;
import Data.Note;
import Data.Page;
import GUI.MainMenuScreen;
import GUI.TextEditorButtonFunctions;


public class NotesRenderer extends JPanel implements ListCellRenderer<Note>{

	private JLabel notesNameLabel = new JLabel();
	private Content datalist;
	private JTextPane textPane;
	private TextEditorButtonFunctions buttons;
	
	private Color seperatorColor = new Color(63, 66, 72);
	
	public NotesRenderer(Content datalist, JTextPane textPane, TextEditorButtonFunctions buttons) {
		setLayout(new BorderLayout(0, 0));

		JPanel panelText = new JPanel(new GridLayout(0, 1));
		panelText.add(notesNameLabel);
		add(new JLabel("  "), BorderLayout.WEST);
		add(panelText, BorderLayout.CENTER);

		
		this.datalist = datalist;
		this.textPane = textPane;
		this.buttons = buttons;
	}
	
	@Override
	public Component getListCellRendererComponent(JList<? extends Note> list, Note note, 
		int index, boolean isSelected, boolean cellHasFocus) {

		notesNameLabel.setText(note.getNoteName());
		notesNameLabel.setOpaque(true);
		notesNameLabel.setForeground(Color.WHITE);

		if(isSelected) {
			notesNameLabel.setBackground(list.getSelectionBackground());
			setBorder(BorderFactory.createLineBorder(Color.WHITE));
			setBackground(list.getSelectionBackground());
			
			datalist.getPageListModel().clear();
			
			for(int i = 0; i < note.getNotesPages().size(); i++) {
				datalist.addPageToListModel(note.getNotesPages().get(i));
			}
			textPane.setText("");
			textPane.disable();
			
			//buttons.disableButtons();
			
		} else {
			notesNameLabel.setBackground(list.getBackground());
			setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, seperatorColor));
			setBackground(list.getBackground());
		}
		return this;
	}

}
