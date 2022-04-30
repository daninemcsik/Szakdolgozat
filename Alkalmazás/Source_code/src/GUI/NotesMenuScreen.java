package GUI;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.undo.UndoManager;

import Data.Content;
import Data.Note;
import Data.Page;
import GUIRelated.NotesRenderer;
import GUIRelated.PagesRenderer;
import GUIRelated.PopupWindow;
import GUIRelated.CustomCorner;
import GUIRelated.CustomEditorKit;
import GUIRelated.CustomScrollbar;
import GUIRelated.CustomSelectionCaret;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

public class NotesMenuScreen extends JPanel{


	private static final long serialVersionUID = -5598512108954675576L;
	
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
	private ImageIcon imageIcon;
	
	private Color buttonBackgroundColor = new Color(33, 35, 39);
	private Color outlineColor = new Color(63, 66, 72);
	private Color panelColor = new Color(80, 83, 87);
	private Color selectionBlue = new Color(0, 155, 155);
	private Color selectionRed = new Color(200, 10, 10);
	
	private TextEditorButtons textEditorButtons;
	
	private Content content;
	private PopupWindow popupWindow = new PopupWindow();
	private CustomEditorKit customEditorKit = new CustomEditorKit();
	
	public NotesMenuScreen(Content content) {
		this.content = content;
		initialize(content);
	
	}

	@SuppressWarnings("deprecation")
	private void initialize(Content listItems) {
		
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
		textPane.setCaret(new CustomSelectionCaret());
		textPane.setSelectionColor(selectionBlue);
		textsScrollPane.setViewportView(textPane);
		
		customEditorKit.init(textPane);
		
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
		noteList.setCellRenderer(new NotesRenderer());
		
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
		pageList.setCellRenderer(new PagesRenderer());
		
		//PAGES LISTA GÖRGÕ
		pagesListScroll = new JScrollPane(pageList);
		pagesListScroll.setBounds(2, 36, 192, 500);
		pagesListScroll.setBorder(BorderFactory.createEmptyBorder());
		pagesListScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		pagesListScroll.getVerticalScrollBar().setUI(new CustomScrollbar());
		pagesPanel.add(pagesListScroll);

		
		//SZERKESZTÕ GOMBJAI
		textEditorButtons = new TextEditorButtons(this, textPane);
		
		
		
		
		
		
		
		
		
		
		
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
				try {
					renameNoteButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
				}catch (Exception e1) {
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				try {
					renameNoteButton.setBorder(BorderFactory.createEmptyBorder());
				}catch (Exception e1) {
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					renameNoteButton.setBackground(selectionBlue);
					popupWindow.createPopupMenu("note", "rename", content, noteList, pageList);
				}catch (Exception e1) {
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				try {
					renameNoteButton.setBackground(buttonBackgroundColor);
				}catch (Exception e1) {
				}
			}
		});
		addNoteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				try {
					addNoteButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
				}catch (Exception e1) {
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				try {
					addNoteButton.setBorder(BorderFactory.createEmptyBorder());
				}catch (Exception e1) {
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					addNoteButton.setBackground(selectionBlue);
					listItems.addNoteTolistModel(new Note("New Note"));
					noteList.setSelectedIndex((noteList.getModel().getSize())-1);
					noteList.ensureIndexIsVisible(noteList.getSelectedIndex());
				}catch (Exception e1) {
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				try {
					addNoteButton.setBackground(buttonBackgroundColor);
				}catch (Exception e1) {
				}
			}
		});
		deleteNoteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				try {
					deleteNoteButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
				}catch (Exception e1) {
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				try {
					deleteNoteButton.setBorder(BorderFactory.createEmptyBorder());
				}catch (Exception e1) {
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					deleteNoteButton.setBackground(selectionRed);
					popupWindow.createPopupMenu("note", "delete", content, noteList, pageList);
				}catch (Exception e1) {
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				try {
					deleteNoteButton.setBackground(buttonBackgroundColor);
				}catch (Exception e1) {
				}
			}
		});
		renamePageButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				try {
					renamePageButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
				}catch (Exception e1) {
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				try {
					renamePageButton.setBorder(BorderFactory.createEmptyBorder());
				}catch (Exception e1) {
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					renamePageButton.setBackground(selectionBlue);	
					popupWindow.createPopupMenu("page", "rename", content, noteList, pageList);
				}catch (Exception e1) {
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				try {
					renamePageButton.setBackground(buttonBackgroundColor);
				}catch (Exception e1) {
				}
			}
		});
		addPageButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				try {
					addPageButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
				}catch (Exception e1) {
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				try {
					addPageButton.setBorder(BorderFactory.createEmptyBorder());
				}catch (Exception e1) {
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					addPageButton.setBackground(selectionBlue);
					noteList.getSelectedValue().addPageToNote(new Page("New Page", "", noteList.getSelectedValue().getId()));
					listItems.refreshPageListModel(noteList);
					pageList.setSelectedIndex((pageList.getModel().getSize())-1);
					pageList.ensureIndexIsVisible(pageList.getSelectedIndex());
				}catch (Exception e1) {
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				try {
					addPageButton.setBackground(buttonBackgroundColor);
				}catch (Exception e1) {
				}
			}
		});
		deletePageButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				try {
					deletePageButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
				}catch (Exception e1) {
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				try {
					deletePageButton.setBorder(BorderFactory.createEmptyBorder());
				}catch (Exception e1) {
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					deletePageButton.setBackground(selectionRed);
					popupWindow.createPopupMenu("page", "delete", content, noteList, pageList);
				}catch (Exception e1) {
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				try {
					deletePageButton.setBackground(buttonBackgroundColor);
				}catch (Exception e1) {
				}
			}
		});
		textPane.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				if(!pageList.isSelectionEmpty()) {
					pageList.getSelectedValue().setPageText(textPane.getText());
				}
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				if(!pageList.isSelectionEmpty()) {
					pageList.getSelectedValue().setPageText(textPane.getText());
				}
				
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				if(!pageList.isSelectionEmpty()) {
					pageList.getSelectedValue().setPageText(textPane.getText());
				}
			}
			
		});	
		noteList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				noteList.repaint();
				((DefaultListModel<Page>)pageList.getModel()).clear();
				textPane.disable();
				textPane.getCaret().setVisible(false);
				textPane.setText("");
				if(!noteList.isSelectionEmpty()) {
					listItems.refreshPageListModel(noteList);
					
				}
				
			}
			
		});
		pageList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if(!pageList.isSelectionEmpty()) {
					textPane.enable();
					textPane.getCaret().setVisible(true);
					textPane.setText(pageList.getSelectedValue().getPageText());
				}else if (pageList.isSelectionEmpty()) {
					textPane.disable();
					textPane.getCaret().setVisible(false);
					textPane.setText("");
				}
				textEditorButtons.setUndoManager(new UndoManager());
				textEditorButtons.setDefaultButtonSettings();
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
	
	public TextEditorButtons getTextEditorButtons() {
		return textEditorButtons;
	}
	
	
		
}
