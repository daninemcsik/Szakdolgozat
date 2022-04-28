package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Event;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Data.Content;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.InputMap;

import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.JInternalFrame;


public class MainScreenOutline {

	private JFrame frame;
	private JPanel upperPanel;
	private JLabel closeButton;
	private JLabel minimizeButton;
	private JLabel openFileButton;
	private JLabel saveFileButton;
	private JLabel mainMenuButton;
	private JLabel notesMenuButton;
	private JLabel searchMenuButton;
	
	private static Point mouseDownScreenCoords;
    private static Point mouseDownCompCoords;
	
    //private int lastSelectedNoteIndex, lastSelectedPageIndex;
    
    private Color buttonBackgroundColor = new Color(33, 35, 39);
   	private Color outlineColor = new Color(63, 66, 72);
   	private Color selectionBlue = new Color(28, 73, 255);
   	private Color selectionRed = new Color(200, 10, 10);
   	private Color selectionGray = new Color(44, 47, 51);
    
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreenOutline window = new MainScreenOutline();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainScreenOutline() {
		initialize();
	}

	private void initialize() {
		ImageIcon imageIcon;
		Content notesAndPages = new Content();
		
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.BLACK);
		frame.getContentPane().setBackground(outlineColor);
		frame.setBounds(100, 100, 999, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		
		
		
		NotesMenuScreen notesMenu = new NotesMenuScreen(notesAndPages);
		MainMenuScreen mainMenu = new MainMenuScreen();
		SearchMenuScreen searchMenu = new SearchMenuScreen(notesAndPages, notesMenu, this);
		frame.getContentPane().add(notesMenu);
		frame.getContentPane().add(mainMenu);
		frame.getContentPane().add(searchMenu);
		
		//FELSÕ SÁV
		upperPanel = new JPanel();
		upperPanel.setBackground(buttonBackgroundColor);
		upperPanel.setBounds(0, 0, 1000, 70);
		upperPanel.setLayout(null);
		frame.getContentPane().add(upperPanel);
		
		//BEZÁRÁS GOMB
		closeButton = new JLabel("X");
		closeButton.setHorizontalAlignment(SwingConstants.CENTER);
		closeButton.setOpaque(true);
		closeButton.setForeground(Color.WHITE);
		closeButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		closeButton.setBackground(buttonBackgroundColor);
		closeButton.setBounds(959, 0, 40, 20);
		upperPanel.add(closeButton);
		
		//TÁLCÁZÓ GOMB
		minimizeButton = new JLabel("-");
		minimizeButton.setHorizontalAlignment(SwingConstants.CENTER);
		minimizeButton.setOpaque(true);
		minimizeButton.setBounds(919, 0, 40, 20);
		minimizeButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		minimizeButton.setForeground(Color.WHITE);
		minimizeButton.setBackground(buttonBackgroundColor);
		upperPanel.add(minimizeButton);
		
		//FÁJL MEGNYITÁS GOMB
		openFileButton = new JLabel("");
		imageIcon = new ImageIcon(new ImageIcon(MainScreenOutline.class.getResource("/IconsAndPictures/open_file.png")).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		openFileButton.setIcon(imageIcon);
		openFileButton.setHorizontalAlignment(SwingConstants.CENTER);
		openFileButton.setOpaque(true);
		openFileButton.setForeground(Color.LIGHT_GRAY);
		openFileButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		openFileButton.setBackground(buttonBackgroundColor);
		openFileButton.setBounds(0, 20, 40, 20);
		upperPanel.add(openFileButton);
		
		//FÁJL MENTÉS GOMB
		saveFileButton = new JLabel("");
		imageIcon = new ImageIcon(new ImageIcon(MainScreenOutline.class.getResource("/IconsAndPictures/save_file.png")).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		saveFileButton.setIcon(imageIcon);
		saveFileButton.setHorizontalAlignment(SwingConstants.CENTER);
		saveFileButton.setOpaque(true);
		saveFileButton.setForeground(Color.LIGHT_GRAY);
		saveFileButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		saveFileButton.setBackground(buttonBackgroundColor);
		saveFileButton.setBounds(40, 20, 40, 20);
		upperPanel.add(saveFileButton);
		
		//MAIN MENU BUTTON
		mainMenuButton = new JLabel("Main menu");
		mainMenuButton.setBounds(0, 42, 333, 28);
		mainMenuButton.setBackground(buttonBackgroundColor);
		mainMenuButton.setForeground(Color.WHITE);
		mainMenuButton.setHorizontalAlignment(SwingConstants.CENTER);
		mainMenuButton.setBorder(BorderFactory.createEmptyBorder());
		mainMenuButton.setOpaque(true);
		upperPanel.add(mainMenuButton);
		
		//NOTES MENU BUTTON
		notesMenuButton = new JLabel("Notes");
		notesMenuButton.setBounds(333, 42, 333, 28);
		notesMenuButton.setBackground(buttonBackgroundColor);
		notesMenuButton.setForeground(Color.WHITE);
		notesMenuButton.setHorizontalAlignment(SwingConstants.CENTER);
		notesMenuButton.setBorder(BorderFactory.createEmptyBorder());
		notesMenuButton.setOpaque(true);
		upperPanel.add(notesMenuButton);
		
		//SEARCH MENU BUTTON
		searchMenuButton = new JLabel("Search");
		searchMenuButton.setBounds(666, 42, 333, 28);
		searchMenuButton.setBackground(buttonBackgroundColor);
		searchMenuButton.setForeground(Color.WHITE);
		searchMenuButton.setHorizontalAlignment(SwingConstants.CENTER);
		searchMenuButton.setBorder(BorderFactory.createEmptyBorder());
		searchMenuButton.setOpaque(true);
		upperPanel.add(searchMenuButton);
		
		JSeparator menuSeparator = new JSeparator();
		menuSeparator.setBounds(0, 40, 999, 2);
		menuSeparator.setBackground(selectionGray);
		menuSeparator.setForeground(selectionGray);
		menuSeparator.setBorder(BorderFactory.createEmptyBorder());
		upperPanel.add(menuSeparator);
		
		

		

		
		
		
		
		//KEYBINDS
		Action saveFile = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				save(notesAndPages, notesMenu, searchMenu);
				 
			}
		};
		InputMap notesInputMap = notesMenu.getTextPane().getInputMap();
		InputMap searchInputMap = searchMenu.getTextPane().getInputMap();
		KeyStroke key;
		
		key = KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK);
		notesInputMap.put(key, saveFile);
		searchInputMap.put(key, saveFile);
		
		
		
		
		//ACTION / MOUSE LISTENERS
		upperPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				mouseDownScreenCoords = null;
                mouseDownCompCoords = null;
			}
			@Override
			public void mousePressed(MouseEvent e) {
				 mouseDownScreenCoords = e.getLocationOnScreen();
	             mouseDownCompCoords = e.getPoint();
			}
		});
		upperPanel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				Point currentCoords = e.getLocationOnScreen();
				frame.setLocation(mouseDownScreenCoords.x + (currentCoords.x - mouseDownScreenCoords.x) - mouseDownCompCoords.x,
                        mouseDownScreenCoords.y + (currentCoords.y - mouseDownScreenCoords.y) - mouseDownCompCoords.y);
			}
		});
		openFileButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				openFileButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				openFileButton.setBorder(BorderFactory.createEmptyBorder());
			}
			@Override
			public void mousePressed(MouseEvent e) {
				openFileButton.setBackground(selectionBlue);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				openFileButton.setBackground(buttonBackgroundColor);
			}
		});
		saveFileButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				saveFileButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				saveFileButton.setBorder(BorderFactory.createEmptyBorder());
			}
			@Override
			public void mousePressed(MouseEvent e) {
				saveFileButton.setBackground(selectionBlue);
				save(notesAndPages, notesMenu, searchMenu);
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				saveFileButton.setBackground(buttonBackgroundColor);
			}
		});
		closeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				closeButton.setBackground(selectionRed);
				closeButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				closeButton.setBackground(buttonBackgroundColor);
				closeButton.setBorder(BorderFactory.createEmptyBorder());
			}
			@Override
			public void mousePressed(MouseEvent e) {
				frame.dispose();
			}
		});
		minimizeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				minimizeButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				minimizeButton.setBorder(BorderFactory.createEmptyBorder());
			}
			@Override
			public void mousePressed(MouseEvent e) {
				frame.setState(JFrame.ICONIFIED);
			}
		});
		mainMenuButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				mainMenuButton.setBackground(selectionGray);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				mainMenuButton.setBackground(buttonBackgroundColor);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				//lastSelectedNoteIndex = notesMenu.getNoteList().getSelectedIndex();
				//lastSelectedPageIndex = notesMenu.getPageList().getSelectedIndex();
				notesMenu.setVisible(false);
				mainMenu.setVisible(true);
				searchMenu.setVisible(false);
				mainMenuButton.setBorder(BorderFactory.createLoweredBevelBorder());
				notesMenuButton.setBorder(BorderFactory.createEmptyBorder());
				searchMenuButton.setBorder(BorderFactory.createEmptyBorder());
			}
		});
		notesMenuButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				notesMenuButton.setBackground(selectionGray);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				notesMenuButton.setBackground(buttonBackgroundColor);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				notesMenu.setVisible(true);
				//notesMenu.getNoteList().setSelectedIndex(lastSelectedNoteIndex);
				//notesMenu.getPageList().setSelectedIndex(lastSelectedPageIndex);
				mainMenu.setVisible(false);
				searchMenu.setVisible(false);
				mainMenuButton.setBorder(BorderFactory.createEmptyBorder());
				notesMenuButton.setBorder(BorderFactory.createLoweredBevelBorder());
				searchMenuButton.setBorder(BorderFactory.createEmptyBorder());
			}
		});
		searchMenuButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				searchMenuButton.setBackground(selectionGray);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				searchMenuButton.setBackground(buttonBackgroundColor);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				//lastSelectedNoteIndex = notesMenu.getNoteList().getSelectedIndex();
				//lastSelectedPageIndex = notesMenu.getPageList().getSelectedIndex();
				notesMenu.setVisible(false);
				mainMenu.setVisible(false);
				searchMenu.setVisible(true);
				mainMenuButton.setBorder(BorderFactory.createEmptyBorder());
				notesMenuButton.setBorder(BorderFactory.createEmptyBorder());
				searchMenuButton.setBorder(BorderFactory.createLoweredBevelBorder());
			}
		});
		
		
	}
	
	public void save(Content notesAndPages, NotesMenuScreen notesMenu, SearchMenuScreen searchMenu) {
		if(notesMenu.isVisible()) {
			notesMenu.getPageList().getSelectedValue().setPageText(notesMenu.getTextPane().getText());
			notesMenu.getPageList().getSelectedValue().setPageTextStyle(notesMenu.getTextPane().getStyledDocument());
		}
		if(searchMenu.isVisible()) {
			
			for(int i = 0; i < notesAndPages.getNoteListModel().getSize(); i++) {
				
				for(int j = 0; j < notesAndPages.getNoteListModel().get(i).getNotesPages().size(); j++) {
					
					if(notesAndPages.getNoteListModel().get(i).getNotesPages().get(j).equals(searchMenu.getPageList().getSelectedValue())) {
						notesAndPages.getNoteListModel().get(i).getNotesPages().get(j).setPageText(searchMenu.getTextPane().getText());
					}
					
				}
			}
			
			searchMenu.getPageList().getSelectedValue().setPageText(searchMenu.getTextPane().getText());	
		}
		
	}

	public JLabel getMainMenuButton() {
		return mainMenuButton;
	}

	public JLabel getNotesMenuButton() {
		return notesMenuButton;
	}

	public JLabel getSearchMenuButton() {
		return searchMenuButton;
	}
	
}
