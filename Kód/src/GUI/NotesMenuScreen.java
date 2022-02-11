package GUI;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyleConstants;

import Data.Content;
import Data.Note;
import Data.Page;
import GUIRelated.NotesRenderer;
import GUIRelated.PagesRenderer;
import GUIRelated.CustomCorner;
import GUIRelated.CustomJOptionPane;
import GUIRelated.CustomJOptionPaneLayout;
import GUIRelated.CustomScrollbar;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

public class NotesMenuScreen extends JPanel{


	private JPanel notesPanel;
	private JLabel notesLabel;
	private JLabel renameNoteButton;
	private JLabel addNoteButton;
	private JLabel deleteNoteButton;
	private JList<Note> noteList;
	private JScrollPane noteListScroll;
	
	private JPanel pagesPanel;
	private JLabel pagesLabel;
	private JLabel renamePageButton;
	private JLabel addPageButton;
	private JLabel deletePageButton;
	private JList<Page> pageList;
	private JScrollPane pagesListScroll;
	
	private JScrollPane textsScrollPane;
	private JTextPane textPane;
	
	private Color buttonBackgroundColor = new Color(33, 35, 39);
	private Color outlineColor = new Color(63, 66, 72);
	private Color selectionGray = new Color(44, 47, 51);
	private Color panelColor = new Color(80, 83, 87);
	private Color selectionBlue = new Color(28, 73, 255);
	private Color selectionRed = new Color(200, 10, 10);
	
	private TextEditorButtonFunctions textEditorButtons;

	private Content content;
	
	
	
	/**
	 * Create the application.
	 */
	public NotesMenuScreen(Content content) {
		this.content = content;
		initialize(content);
	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Content listItems) {
		ImageIcon imageIcon;

		
		setBounds(0, 70, 1000, 580);
		setVisible(false);
		validate();
		setBackground(outlineColor);
		setLayout(null);
		
		
		
		//A JEGYZET ÍRHATÓ FELÜLET GÖRGÕJE
		textsScrollPane = new JScrollPane();
		textsScrollPane.setBounds(406, 40, 583, 535);
		textsScrollPane.setBorder(BorderFactory.createEmptyBorder());
		textsScrollPane.getVerticalScrollBar().setUI(new CustomScrollbar());
		textsScrollPane.getHorizontalScrollBar().setUI(new CustomScrollbar());
		textsScrollPane.setCorner(JScrollPane.LOWER_RIGHT_CORNER, new CustomCorner());

		add(textsScrollPane);
			
		//JEGYZET ÍRHATÓ FELÜLET, IDE LEHET A SZÖVEGET ÍRNI
		textPane = new JTextPane();
		textPane.setBackground(panelColor);
		textPane.disable();
		textPane.setCaretColor(Color.WHITE);
		textPane.setContentType("text/html");
		textsScrollPane.setViewportView(textPane);
		
		//SZERKESZTÕ GOMBJAI
		textEditorButtons = new TextEditorButtonFunctions(this, textPane);
		
		
		//NOTES PANEL
		notesPanel = new JPanel();
		notesPanel.setBounds(5, 5, 195, 570);
		notesPanel.setBackground(panelColor);
		notesPanel.setLayout(null);
		add(notesPanel);
		
		//NOTES LABEL
		notesLabel = new JLabel("NOTES");
		notesLabel.setBounds(10, 0, 185, 35);
		notesLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		notesLabel.setForeground(Color.WHITE);
		notesPanel.add(notesLabel);
		
		JSeparator notesSeparator = new JSeparator();
		notesSeparator.setBackground(outlineColor);
		notesSeparator.setForeground(outlineColor);
		notesSeparator.setBounds(0, 33, 195, 2);
		notesPanel.add(notesSeparator);
		
		//NOTE ÁTNEVEZÉS GOMB
		renameNoteButton = new JLabel();
		imageIcon = new ImageIcon(new ImageIcon(NotesMenuScreen.class.getResource("/IconsAndPictures/rename.png")).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		renameNoteButton.setIcon(imageIcon);
		renameNoteButton.setHorizontalAlignment(SwingConstants.CENTER);
		renameNoteButton.setOpaque(true);
		renameNoteButton.setBackground(buttonBackgroundColor);
		renameNoteButton.setForeground(Color.WHITE);
		renameNoteButton.setBounds(2, 540, 48, 28);
		renameNoteButton.setBorder(BorderFactory.createEmptyBorder());
		notesPanel.add(renameNoteButton);
		
		//ÚJ NOTE GOMB
		addNoteButton = new JLabel("New note");
		addNoteButton.setHorizontalAlignment(SwingConstants.CENTER);
		addNoteButton.setOpaque(true);
		addNoteButton.setBackground(buttonBackgroundColor);
		addNoteButton.setForeground(Color.WHITE);
		addNoteButton.setBounds(52, 540, 91, 28);
		addNoteButton.setBorder(BorderFactory.createEmptyBorder());
		notesPanel.add(addNoteButton);
		
		//NOTE TÖRLÉS GOMB
		deleteNoteButton = new JLabel("");
		deleteNoteButton.setHorizontalAlignment(SwingConstants.CENTER);
		deleteNoteButton.setOpaque(true);
		imageIcon = new ImageIcon(new ImageIcon(NotesMenuScreen.class.getResource("/IconsAndPictures/trash.png")).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		deleteNoteButton.setIcon(imageIcon);
		deleteNoteButton.setBounds(145, 540, 48, 28);
		deleteNoteButton.setBackground(buttonBackgroundColor);
		deleteNoteButton.setForeground(Color.WHITE);
		deleteNoteButton.setBorder(BorderFactory.createEmptyBorder());
		notesPanel.add(deleteNoteButton);		
		
		//NOTE LISTA
		noteList = new JList<Note>(listItems.getNoteListModel());
		noteList.setBounds(2, 36, 192, 500);
		noteList.setBackground(panelColor);
		noteList.setBorder(BorderFactory.createEmptyBorder());
		noteList.setSelectionBackground(selectionBlue);
		noteList.setFixedCellHeight(40);
		noteList.setFixedCellWidth(192);
		noteList.setCellRenderer(new NotesRenderer(listItems, textPane, textEditorButtons));
		
		//NOTE LISTA GÖRGÕ
		noteListScroll = new JScrollPane(noteList);
		noteListScroll.setBounds(2, 36, 192, 500);
		noteListScroll.setBorder(BorderFactory.createEmptyBorder());
		noteListScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		noteListScroll.getVerticalScrollBar().setUI(new CustomScrollbar());
		notesPanel.add(noteListScroll);
		
		
		
		
		
		//PAGES PANEL
		pagesPanel = new JPanel();
		pagesPanel.setBounds(201, 5, 195, 570);
		pagesPanel.setBackground(panelColor);
		pagesPanel.setLayout(null);
		add(pagesPanel);

		//PAGES LABEL
		pagesLabel = new JLabel("PAGES");
		pagesLabel.setForeground(Color.WHITE);
		pagesLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		pagesLabel.setBounds(10, 0, 185, 35);
		pagesPanel.add(pagesLabel);
		
		JSeparator pagesSeparator = new JSeparator();
		pagesSeparator.setBackground(outlineColor);
		pagesSeparator.setForeground(outlineColor);
		pagesSeparator.setBounds(0, 33, 195, 2);
		pagesPanel.add(pagesSeparator);
		
		//OLDAL ÁTNEVEZÉS GOMB
		renamePageButton = new JLabel();
		imageIcon = new ImageIcon(new ImageIcon(NotesMenuScreen.class.getResource("/IconsAndPictures/rename.png")).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		renamePageButton.setIcon(imageIcon);
		renamePageButton.setHorizontalAlignment(SwingConstants.CENTER);
		renamePageButton.setOpaque(true);
		renamePageButton.setBackground(buttonBackgroundColor);
		renamePageButton.setForeground(Color.WHITE);
		renamePageButton.setBounds(2, 540, 48, 28);
		renamePageButton.setBorder(BorderFactory.createEmptyBorder());
		pagesPanel.add(renamePageButton);
		
		//ÚJ OLDAL GOMB
		addPageButton = new JLabel("New page");
		addPageButton.setHorizontalAlignment(SwingConstants.CENTER);
		addPageButton.setOpaque(true);
		addPageButton.setForeground(Color.WHITE);
		addPageButton.setBorder(BorderFactory.createEmptyBorder());
		addPageButton.setBackground(buttonBackgroundColor);
		addPageButton.setBounds(52, 540, 91, 28);
		pagesPanel.add(addPageButton);
		
		//OLDAL TÖRLÉS GOMB
		deletePageButton = new JLabel("");	
		deletePageButton.setOpaque(true);
		imageIcon = new ImageIcon(new ImageIcon(NotesMenuScreen.class.getResource("/IconsAndPictures/trash.png")).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		deletePageButton.setIcon(imageIcon);
		deletePageButton.setHorizontalAlignment(SwingConstants.CENTER);
		deletePageButton.setForeground(Color.WHITE);				
		deletePageButton.setBorder(BorderFactory.createEmptyBorder());		
		deletePageButton.setBackground(buttonBackgroundColor);
		deletePageButton.setBounds(145, 540, 48, 28);
		pagesPanel.add(deletePageButton);
		

		//PAGES LISTA
		pageList = new JList<Page>(listItems.getPageListModel());
		pageList.setBounds(2, 36, 192, 500);
		pageList.setBackground(panelColor);
		pageList.setBorder(BorderFactory.createEmptyBorder());
		pageList.setSelectionBackground(selectionBlue);
		pageList.setFixedCellHeight(40);
		pageList.setFixedCellWidth(192);
		pageList.setCellRenderer(new PagesRenderer(textPane, textEditorButtons));
		
		//PAGES LISTA GÖRGÕ
		pagesListScroll = new JScrollPane(pageList);
		pagesListScroll.setBounds(2, 36, 192, 500);
		pagesListScroll.setBorder(BorderFactory.createEmptyBorder());
		pagesListScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		pagesListScroll.getVerticalScrollBar().setUI(new CustomScrollbar());
		pagesPanel.add(pagesListScroll);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//TOOLTIPS
		renameNoteButton.setToolTipText("Rename note");
		renamePageButton.setToolTipText("Rename page");
		deleteNoteButton.setToolTipText("Delete note");
		deletePageButton.setToolTipText("Delete page");
		
		
		
		UIManager.put("ToolTip.background", buttonBackgroundColor);
		UIManager.put("ToolTip.foreground", Color.WHITE);
		UIManager.put("ToolTip.border", BorderFactory.createLineBorder(Color.WHITE));
		
		
		
		
		
		
		
		
		
		
		
		
		//LISTENERS		
		renameNoteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				renameNoteButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				renameNoteButton.setBorder(BorderFactory.createEmptyBorder());
			}
			@Override
			public void mousePressed(MouseEvent e) {
				renameNoteButton.setBackground(selectionBlue);
				//noteList.repaint();
				createPopupMenu("note", "rename");
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				renameNoteButton.setBackground(buttonBackgroundColor);
			}
		});
		addNoteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				addNoteButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				addNoteButton.setBorder(BorderFactory.createEmptyBorder());
			}
			@Override
			public void mousePressed(MouseEvent e) {
				addNoteButton.setBackground(selectionBlue);
				listItems.addNoteTolistModel(new Note("New Note"));
				
				noteList.setSelectedIndex((noteList.getModel().getSize())-1);
				noteList.ensureIndexIsVisible(noteList.getSelectedIndex());
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				addNoteButton.setBackground(buttonBackgroundColor);
			}
		});
		deleteNoteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				deleteNoteButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				deleteNoteButton.setBorder(BorderFactory.createEmptyBorder());
			}
			@Override
			public void mousePressed(MouseEvent e) {
				deleteNoteButton.setBackground(selectionRed);
				createPopupMenu("note", "delete");	
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				deleteNoteButton.setBackground(buttonBackgroundColor);
			}
		});
		renamePageButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				renamePageButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				renamePageButton.setBorder(BorderFactory.createEmptyBorder());
			}
			@Override
			public void mousePressed(MouseEvent e) {
				renamePageButton.setBackground(selectionBlue);
				createPopupMenu("page", "rename");						
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				renamePageButton.setBackground(buttonBackgroundColor);
			}
		});
		addPageButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				addPageButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				addPageButton.setBorder(BorderFactory.createEmptyBorder());
			}
			@Override
			public void mousePressed(MouseEvent e) {
				addPageButton.setBackground(selectionBlue);
				noteList.getSelectedValue().addPageToNote(new Page("New Page", "", noteList.getSelectedValue().getId()));
				//testNoteListItems.addPageToListModel(new Page("New Page", "", noteList.getSelectedValue().getId()));
				listItems.refreshPageListModel(noteList);
				pageList.setSelectedIndex((pageList.getModel().getSize())-1);
				pageList.ensureIndexIsVisible(pageList.getSelectedIndex());
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				addPageButton.setBackground(buttonBackgroundColor);
			}
		});
		deletePageButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				deletePageButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				deletePageButton.setBorder(BorderFactory.createEmptyBorder());
			}
			@Override
			public void mousePressed(MouseEvent e) {
				deletePageButton.setBackground(selectionRed);
				createPopupMenu("page", "delete");
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				deletePageButton.setBackground(buttonBackgroundColor);
			}
		});
		
		
	}
	
	
	
	public JTextPane getTextPane() {
		return textPane;
	}

	public JList<Note> getNoteList() {
		return noteList;
	}

	public JList<Page> getPageList() {
		return pageList;
	}
	
	public TextEditorButtonFunctions getTextEditorButtons() {
		return textEditorButtons;
	}
	
	
	private void createPopupMenu(String type, String action){
		UIManager.put("Panel.background", outlineColor);
		UIManager.put("OptionPane.messageForeground", Color.WHITE);
		UIManager.put("TextField.background", selectionGray);
		UIManager.put("TextField.foreground", Color.WHITE);
		
		JLabel msg = new JLabel();
		msg.setHorizontalAlignment(SwingConstants.CENTER);
		msg.setForeground(Color.WHITE);
		
		CustomJOptionPane pane = new CustomJOptionPane();
		
		if(type.equalsIgnoreCase("note") && action.equalsIgnoreCase("rename")) {
			msg.setText("Rename '" + noteList.getSelectedValue().getNoteName() + "' to:");
			pane.setWantsInput(true);
			
		}else if (type.equalsIgnoreCase("page") && action.equalsIgnoreCase("rename")) {
			msg.setText("Rename '" + pageList.getSelectedValue().getPageName() + "' to:");
			pane.setWantsInput(true);
			
		}else if(type.equalsIgnoreCase("note") && action.equalsIgnoreCase("delete")) {
			msg.setText("Are you sure you want to delete '" + noteList.getSelectedValue().getNoteName() + "'?");
			pane.setWantsInput(false);
			
		}else if (type.equalsIgnoreCase("page") && action.equalsIgnoreCase("delete")) {
			msg.setText("Are you sure you want to delete '" + pageList.getSelectedValue().getPageName() + "'?");
			pane.setWantsInput(false);
		}
		
		
	    pane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    pane.setOpaque(false);
	    pane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
	    pane.setUI(new CustomJOptionPaneLayout(content, noteList, pageList, type, action));
	  	pane.setMessage(msg);
	  	
	  	
	  	
	    JDialog f = pane.createDialog("");
	    f.show();
	    f.dispose();   
	}
	
	
	
}
