package GUI;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.MutableComboBoxModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import Data.Content;
import Data.Note;
import Data.Page;
import GUIRelated.ComboBoxFilter;
import GUIRelated.ComboBoxRenderer;
import GUIRelated.CustomCorner;
import GUIRelated.CustomScrollbar;
import GUIRelated.FilteredComboBoxRenderer;
import GUIRelated.NotesRenderer;
import GUIRelated.PagesRenderer;

import javax.swing.JList;


public class SearchMenuScreen extends JPanel{

	//private JFrame frame;
	private JPanel searchPanel;
	private JComboBox searchBar;
	private JLabel searchButton;
	private JLabel notesSearchResultTab;
	private JLabel pagesSearchResultTab;
	
	private JScrollPane textsScrollPane;
	private JTextPane textPane;
	
	private JList<Note> noteList;
	private JScrollPane noteListScroll;
	
	private JList<Page> pageList;
	private JScrollPane pageListScroll;
	
	
	private Color buttonBackgroundColor = new Color(33, 35, 39);
	private Color outlineColor = new Color(63, 66, 72);
	private Color panelColor = new Color(80, 83, 87);
	private Color selectionBlue = new Color(28, 73, 255);
	private Color textFieldColor = new Color(44, 47, 51);
	
	private TextEditorButtonFunctions textEditorButtons;
	private String tempText = "";
	
	public SearchMenuScreen(Content listItems, NotesMenuScreen notesMenu, MainScreenOutline mainScreen) {
		initialize(listItems, notesMenu, mainScreen);
	}

	
	private void initialize(Content listItems, NotesMenuScreen notesMenu, MainScreenOutline mainScreen) {
		ImageIcon imageIcon;
		ComboBoxFilter comboBoxFilter = new ComboBoxFilter(listItems);
		
		setVisible(false);
		setBackground(outlineColor);
		setBounds(0, 70, 1000, 580);
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
		textPane.setForeground(Color.WHITE);
		textPane.disable();
		textPane.setCaretColor(Color.WHITE);
		textPane.setContentType("text/html");
		textsScrollPane.setViewportView(textPane);
			
		//KERESÉS PANEL
		searchPanel = new JPanel();
		searchPanel.setBounds(5, 5, 390, 570);
		searchPanel.setBackground(new Color(80, 83, 87));
		searchPanel.setLayout(null);
		add(searchPanel);
		
		//KERESÕ SOR2
		searchBar = new JComboBox();
		searchBar.setBounds(40, 0, 350, 35);
		searchBar.setBackground(buttonBackgroundColor);
		searchBar.setBorder(BorderFactory.createEmptyBorder());
		searchBar.setEditable(true);
		searchBar.setRenderer(new ComboBoxRenderer());
		searchBar.setOpaque(true);
		searchBar.setUI(new BasicComboBoxUI() {	
			
            @Override
    	    protected JButton createArrowButton() {
    	    	JButton button = new BasicArrowButton(BasicArrowButton.SOUTH, buttonBackgroundColor, buttonBackgroundColor, Color.WHITE, buttonBackgroundColor);
    	    	button.setFocusPainted(true);
    	    	button.setBorderPainted(true);
    	    	button.setFocusPainted(false);
    	    	button.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, buttonBackgroundColor));
    	    	return button;
    	    }
            @Override
            protected ComboPopup createPopup() {
               return new BasicComboPopup(searchBar) {
            	   	@Override
	           		protected void configureList() {
	               	    list.setFont( comboBox.getFont() );
	               	    list.setForeground( comboBox.getForeground() );
	               	    list.setBackground( comboBox.getBackground() );
	               	    list.setSelectionForeground( Color.WHITE );
	               	    list.setSelectionBackground( buttonBackgroundColor );
	               	    list.setBorder( null );
	               	    list.setCellRenderer( comboBox.getRenderer() );
	               	    list.setFocusable( false );
	               	    list.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
	               	    installListListeners();
	           	    }
	                @Override
                    protected JScrollPane createScroller() {
                    	JScrollPane scroller = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                    	scroller.getVerticalScrollBar().setUI(new CustomScrollbar());
                    	return scroller;
                    }
                };
            }
        
        });
		JTextField text = ((JTextField) searchBar.getEditor().getEditorComponent());
        text.setBackground(buttonBackgroundColor);
        text.setForeground(Color.WHITE);
        text.setCaretColor(Color.WHITE);
        searchPanel.add(searchBar);
		
		//KERESÕ GOMB
		searchButton = new JLabel("");
		searchButton.setHorizontalAlignment(SwingConstants.CENTER);
		imageIcon = new ImageIcon(new ImageIcon(SearchMenuScreen.class.getResource("/IconsAndPictures/search.png")).getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT));
		searchButton.setIcon(imageIcon);
		searchButton.setBounds(0, 0, 40, 35);
		searchButton.setBackground(buttonBackgroundColor);
		searchButton.setOpaque(true);
		searchButton.setBorder(BorderFactory.createEmptyBorder());
		searchPanel.add(searchButton);
		
		JSeparator notesSeparator = new JSeparator();
		notesSeparator.setBackground(outlineColor);
		notesSeparator.setForeground(outlineColor);
		notesSeparator.setBounds(0, 35, 390, 2);
		searchPanel.add(notesSeparator);
		
		
		//NOTE TÍPUSÚ KERESÉSI TALÁLATOK LISTÁJÁT MEGJELENÍTÕ GOMB
		notesSearchResultTab = new JLabel("Notes");
		notesSearchResultTab.setHorizontalAlignment(SwingConstants.CENTER);
		notesSearchResultTab.setBackground(buttonBackgroundColor);
		notesSearchResultTab.setBounds(0, 37, 195, 20);
		notesSearchResultTab.setOpaque(true);
		notesSearchResultTab.setForeground(Color.WHITE);
		searchPanel.add(notesSearchResultTab);
		
		//PAGE TÍPUSÚ KERESÉSI TALÁLATOK LISTÁJÁT MEGJELENÍTÕ GOMB
		pagesSearchResultTab = new JLabel("Pages");
		pagesSearchResultTab.setHorizontalAlignment(SwingConstants.CENTER);
		pagesSearchResultTab.setBounds(195, 37, 195, 20);
		pagesSearchResultTab.setBackground(buttonBackgroundColor);
		pagesSearchResultTab.setOpaque(true);
		pagesSearchResultTab.setForeground(Color.WHITE);
		searchPanel.add(pagesSearchResultTab);
		
		noteList = new JList<Note>();
		noteList.setBounds(0, 59, 390, 511);
		noteList.setBackground(panelColor);
		noteList.setBorder(BorderFactory.createEmptyBorder());
		noteList.setSelectionBackground(selectionBlue);
		noteList.setFixedCellHeight(40);
		noteList.setFixedCellWidth(390);
		noteList.setCellRenderer(new NotesRenderer(listItems, textPane, textEditorButtons));
		
		noteListScroll = new JScrollPane(noteList);
		noteListScroll.setBounds(0, 59, 390, 511);
		noteListScroll.setBorder(BorderFactory.createEmptyBorder());
		noteListScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		noteListScroll.getVerticalScrollBar().setUI(new CustomScrollbar());
		searchPanel.add(noteListScroll);
		noteListScroll.setVisible(false);
		
		
		pageList = new JList<Page>();
		pageList.setBounds(0, 59, 390, 511);
		pageList.setBackground(panelColor);
		pageList.setBorder(BorderFactory.createEmptyBorder());
		pageList.setSelectionBackground(selectionBlue);
		pageList.setFixedCellHeight(40);
		pageList.setFixedCellWidth(192);
		pageList.setCellRenderer(new PagesRenderer(textPane, textEditorButtons));
		
		pageListScroll = new JScrollPane(pageList);
		pageListScroll.setBounds(0, 59, 390, 511);
		pageListScroll.setBorder(BorderFactory.createEmptyBorder());
		pageListScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		pageListScroll.getVerticalScrollBar().setUI(new CustomScrollbar());
		searchPanel.add(pageListScroll);
		pageListScroll.setVisible(false);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
		//SZERKESZTÕ GOMBJAI
		textEditorButtons = new TextEditorButtonFunctions(this, textPane);
		
		
		
		
		
		
		
		
		
		
		
		
		//ACTION / MOUSE LISTENERS
		searchButton.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				search(noteList, pageList, listItems, searchBar);
				if(!noteListScroll.isVisible() && !pageListScroll.isVisible()) {
					noteListScroll.setVisible(true);
					notesSearchResultTab.setBorder(BorderFactory.createLoweredBevelBorder());
				}
				searchButton.setBackground(textFieldColor);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				searchButton.setBackground(buttonBackgroundColor);
			}
		});
		
		searchBar.addMouseListener(new MouseAdapter()  {

			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println(searchBar.getSelectedItem().toString());
				text.setText(searchBar.getSelectedItem().toString());
			}
			
		});
		text.getDocument().addDocumentListener(new DocumentListener() {
			
			@SuppressWarnings("unchecked")
			@Override
			public void insertUpdate(DocumentEvent e) {
				tempText = text.getText();
				try {
					searchBar.setModel(comboBoxFilter.filterElements(tempText));
					
				}catch(Exception f) {
					if(searchBar.getModel().getSize() == 0) {
						searchBar.setPopupVisible(false);
					}else {
						searchBar.setPopupVisible(true);
					}
					
				}
			}

			@SuppressWarnings("unchecked")
			@Override
			public void removeUpdate(DocumentEvent e) {
				tempText = text.getText();
				try {
					searchBar.setModel(comboBoxFilter.filterElements(tempText));
					
				}catch(Exception f) {
					if(searchBar.getModel().getSize() == 0) {
						searchBar.setPopupVisible(false);
					}else {
						searchBar.setPopupVisible(true);
					}
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}
			
			
		});
		
		text.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				
				
			}

			@SuppressWarnings("unchecked")
			@Override
			public void mousePressed(MouseEvent e) {
				
				if(tempText.length() == 0) {
					searchBar.setModel(comboBoxFilter.initializeEveryItem());
					searchBar.setSelectedItem(null);
					if(searchBar.getModel().getSize() == 0) {
						searchBar.setPopupVisible(false);
						
					}else {
						searchBar.setPopupVisible(true);
					}
				}else {
					text.setText(tempText);
				}
				searchBar.setPopupVisible(true);
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				
			}
			
		});
		
		
		text.addKeyListener(new java.awt.event.KeyAdapter()  {

			@Override
			public void keyPressed(java.awt.event.KeyEvent e) {
				if(e.getKeyCode() == java.awt.event.KeyEvent.VK_BACK_SPACE) {
					try {
						text.setText(tempText.substring(0, tempText.length()));
						tempText = text.getText().substring(0, text.getText().length()-1);
					}catch (Exception f) {
						searchBar.setSelectedItem(null);
					}
					
				}else if(e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
					search(noteList, pageList, listItems, searchBar);
					if(!noteListScroll.isVisible() && !pageListScroll.isVisible()) {
						noteListScroll.setVisible(true);
						notesSearchResultTab.setBorder(BorderFactory.createLoweredBevelBorder());
					}
				}
				
			}
			
		});
		
		/*
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * TODO
		 * 
		 * - az elsõ kiválasztás mindig new note 0-t ad valamiért
		 * - számokkal történõ összehasonlítás vagy kis-nagy betûvel nem megy rendesen törléskor (kiválasztod, törlöd, majd megint beírod és nem hoz ki rá semmit)
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
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
		notesSearchResultTab.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				notesSearchResultTab.setBackground(textFieldColor);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				notesSearchResultTab.setBackground(buttonBackgroundColor);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				noteListScroll.setVisible(true);
				pageListScroll.setVisible(false);
				notesSearchResultTab.setBorder(BorderFactory.createLoweredBevelBorder());
				pagesSearchResultTab.setBorder(BorderFactory.createEmptyBorder());
			}
		});
		pagesSearchResultTab.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				pagesSearchResultTab.setBackground(textFieldColor);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pagesSearchResultTab.setBackground(buttonBackgroundColor);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				noteListScroll.setVisible(false);
				pageListScroll.setVisible(true);
				notesSearchResultTab.setBorder(BorderFactory.createEmptyBorder());
				pagesSearchResultTab.setBorder(BorderFactory.createLoweredBevelBorder());
			}
		});
		noteList.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				clickOnNoteAction(notesMenu, listItems);
				setVisible(false);
				notesMenu.setVisible(true);
				mainScreen.getMainMenuButton().setBorder(BorderFactory.createEmptyBorder());
				mainScreen.getSearchMenuButton().setBorder(BorderFactory.createEmptyBorder());
				mainScreen.getNotesMenuButton().setBorder(BorderFactory.createLoweredBevelBorder());
			}
		});
		
	}
	
	/*
	 * Determines if the input is contained in any note's name, page's name or content.
	 * 
	 */
	private void search(JList<Note> noteList, JList<Page> pageList, Content listItems, JComboBox searchBar) {
		DefaultListModel<Note> noteListModel = new DefaultListModel<Note>();
		DefaultListModel<Page> pageListModel = new DefaultListModel<Page>();
		
		String searchedText = (String) searchBar.getEditor().getItem();
		for(int i = 0; i < listItems.getNoteListModel().getSize(); i++) {
			for(int j = 0; j < listItems.getNoteListModel().get(i).getNotesPages().size(); j++) {
				if(listItems.getNoteListModel().get(i).getNotesPages().get(j).getPageName().toLowerCase().contains(searchedText.toLowerCase()) || listItems.getNoteListModel().get(i).getNotesPages().get(j).getPageText().toLowerCase().contains(searchedText.toLowerCase())) {
					pageListModel.addElement(listItems.getNoteListModel().get(i).getNotesPages().get(j));
				}
			}
			if(listItems.getNoteListModel().get(i).getNoteName().toLowerCase().contains(searchedText.toLowerCase())) {
				noteListModel.addElement(listItems.getNoteListModel().get(i));
			}
		}
		noteList.setModel(noteListModel);
		pageList.setModel(pageListModel);
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
	/*
	 * Selects the same note on the notes menu that was 
	 * selected in the search menu's note list.
	 */
	@SuppressWarnings("deprecation")
	private void clickOnNoteAction(NotesMenuScreen notesMenu, Content listItems) {
		for(int i = 0; i < listItems.getNoteListModel().size(); i++) {
			if(listItems.getNoteListModel().get(i).equals(noteList.getSelectedValue())){
				notesMenu.getNoteList().setSelectedValue(noteList.getSelectedValue(), true);
				notesMenu.getTextPane().setText("");
				notesMenu.getTextPane().disable();
			}
		}
	}
	
}
