package GUIRelated;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import Data.Note;


public class NotesRenderer extends JPanel implements ListCellRenderer<Note>{

	/**
	 * Randomly generated serial version ID
	 */
	private static final long serialVersionUID = 9071289206298888144L;
	
	private JLabel notesNameLabel = new JLabel();
	
	private Color seperatorColor = new Color(63, 66, 72);
	
	public NotesRenderer() {
		setLayout(new BorderLayout(0, 0));

		JPanel panelText = new JPanel(new GridLayout(0, 1));
		panelText.add(notesNameLabel);
		add(new JLabel("  "), BorderLayout.WEST);
		add(panelText, BorderLayout.CENTER);
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


		} else {
			notesNameLabel.setBackground(list.getBackground());
			setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, seperatorColor));
			setBackground(list.getBackground());
		}
		return this;
	}
	
	
}
