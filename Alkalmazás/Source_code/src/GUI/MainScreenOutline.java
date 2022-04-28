package GUI;


import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Event;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import com.google.firebase.cloud.StorageClient;

import Data.Content;
import Database.DatabaseFunctions;
import Encryption.EncryptionFunctions;
import GUIRelated.PopupWindow;
import XML.XMLWriter;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;

import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import javax.swing.JSeparator;
import javax.swing.KeyStroke;


@SuppressWarnings("deprecation")
public class MainScreenOutline {

	private JFrame frame;
	private JPanel upperPanel;
	private JLabel closeButton;
	private JLabel minimizeButton;
	private JLabel saveFileButton;
	private JLabel notesMenuButton;
	private JLabel searchMenuButton;
	private ImageIcon imageIcon;
	
	private static Point mouseDownScreenCoords;
    private static Point mouseDownCompCoords;
    
    private Color buttonBackgroundColor = new Color(33, 35, 39);
   	private Color outlineColor = new Color(63, 66, 72);
   	private Color selectionBlue = new Color(28, 73, 255);
   	private Color selectionRed = new Color(200, 10, 10);
   	private Color selectionGray = new Color(44, 47, 51);
   	
   	private StorageClient dbStorageClient;
   
   	private PopupWindow popupWindow = new PopupWindow();
   	
	public MainScreenOutline(Content notesAndPages, StorageClient dbStorageClient) {
		this.dbStorageClient = dbStorageClient;
		initialize(notesAndPages, dbStorageClient);
	}

	private void initialize(Content notesAndPages, StorageClient dbStorageClient) {
		imageIcon = new ImageIcon(new ImageIcon(MainScreenOutline.class.getResource("/IconsAndPictures/main_icon.png")).getImage().getScaledInstance(40, 40 , Image.SCALE_DEFAULT));	
		
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.BLACK);
		frame.getContentPane().setBackground(outlineColor);
		frame.setBounds(100, 100, 999, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setIconImage(imageIcon.getImage());
		
		
		
		
		NotesMenuScreen notesMenu = new NotesMenuScreen(notesAndPages);
		SearchMenuScreen searchMenu = new SearchMenuScreen(notesAndPages, notesMenu, this);
		frame.getContentPane().add(notesMenu);
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
		
		//FÁJL MENTÉS GOMB
		saveFileButton = new JLabel("");
		imageIcon = new ImageIcon(new ImageIcon(MainScreenOutline.class.getResource("/IconsAndPictures/save_file.png")).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		saveFileButton.setIcon(imageIcon);
		saveFileButton.setHorizontalAlignment(SwingConstants.CENTER);
		saveFileButton.setOpaque(true);
		saveFileButton.setForeground(Color.LIGHT_GRAY);
		saveFileButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		saveFileButton.setBackground(buttonBackgroundColor);
		saveFileButton.setBounds(0, 20, 40, 20);
		upperPanel.add(saveFileButton);

		//NOTES MENU BUTTON
		notesMenuButton = new JLabel("Notes");
		notesMenuButton.setBounds(0, 42, 500, 28);
		notesMenuButton.setBackground(buttonBackgroundColor);
		notesMenuButton.setForeground(Color.WHITE);
		notesMenuButton.setHorizontalAlignment(SwingConstants.CENTER);
		notesMenuButton.setBorder(BorderFactory.createEmptyBorder());
		notesMenuButton.setOpaque(true);
		upperPanel.add(notesMenuButton);
		
		//SEARCH MENU BUTTON
		searchMenuButton = new JLabel("Search");
		searchMenuButton.setBounds(500, 42, 500, 28);
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
			/**
			 * 
			 */
			private static final long serialVersionUID = 4661948930933794639L;

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					save(notesAndPages);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				 
			}
		};
		InputMap notesInputMap = notesMenu.getTextPane().getInputMap();
		InputMap searchInputMap = searchMenu.getTextPane().getInputMap();
		KeyStroke key;
		
		key = KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK);
		notesInputMap.put(key, saveFile);
		searchInputMap.put(key, saveFile);
		
		
		
		//LISTENERS
		upperPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				try {
					mouseDownScreenCoords = null;
	                mouseDownCompCoords = null;
				}catch (Exception e1) {
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					mouseDownScreenCoords = e.getLocationOnScreen();
		             mouseDownCompCoords = e.getPoint();
				}catch (Exception e1) {
				}
			}
		});
		upperPanel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				try {
					Point currentCoords = e.getLocationOnScreen();
					frame.setLocation(mouseDownScreenCoords.x + (currentCoords.x - mouseDownScreenCoords.x) - mouseDownCompCoords.x,
	                        mouseDownScreenCoords.y + (currentCoords.y - mouseDownScreenCoords.y) - mouseDownCompCoords.y);
				}catch (Exception e1) {
				}
			}
		});
		
		saveFileButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				try {
					saveFileButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
				}catch (Exception e1) {
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				try {
				saveFileButton.setBorder(BorderFactory.createEmptyBorder());
				}catch (Exception e1) {
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				saveFileButton.setBackground(selectionBlue);
				try {
					save(notesAndPages);
				} catch (Exception e1) {
					
				}
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				try {
					saveFileButton.setBackground(buttonBackgroundColor);
				}catch (Exception e1) {
				}
			}
		});
		closeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				try {
					closeButton.setBackground(selectionRed);
					closeButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
				}catch (Exception e1) {
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				try {
					closeButton.setBackground(buttonBackgroundColor);
					closeButton.setBorder(BorderFactory.createEmptyBorder());
				}catch (Exception e1) {
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					save(notesAndPages);
					frame.dispose();
					System.exit(0);
				}catch (Exception e1) {
				}
			}
		});
		minimizeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				try {
					minimizeButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
				}catch (Exception e1) {
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				try {
					minimizeButton.setBorder(BorderFactory.createEmptyBorder());
				}catch (Exception e1) {
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					frame.setState(JFrame.ICONIFIED);
				}catch (Exception e1) {
				}
			}
		});
		notesMenuButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				try {
					notesMenuButton.setBackground(selectionGray);
				}catch (Exception e1) {
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				try {
					notesMenuButton.setBackground(buttonBackgroundColor);
				}catch (Exception e1) {
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					notesMenu.setVisible(true);
					searchMenu.setVisible(false);
					notesMenuButton.setBorder(BorderFactory.createLoweredBevelBorder());
					searchMenuButton.setBorder(BorderFactory.createEmptyBorder());
				}catch (Exception e1) {
				}
			}
		});
		searchMenuButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				try {
					searchMenuButton.setBackground(selectionGray);
				}catch (Exception e1) {
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				try {
					searchMenuButton.setBackground(buttonBackgroundColor);
				}catch (Exception e1) {
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					notesMenu.setVisible(false);
					searchMenu.setVisible(true);
					notesMenuButton.setBorder(BorderFactory.createEmptyBorder());
					searchMenuButton.setBorder(BorderFactory.createLoweredBevelBorder());
				}catch (Exception e1) {
				}
			}
		});
	}
		
		
		
		

			

	
	public void save(Content notesAndPages) throws FileNotFoundException {
		try {
			String dirPath = new File(XMLWriter.class.getProtectionDomain().getCodeSource()
					.getLocation().toURI()).getParent();
			XMLWriter xmlWriter = new XMLWriter();
			DatabaseFunctions dbFunc = new DatabaseFunctions();
			EncryptionFunctions encFunc = new EncryptionFunctions(dbStorageClient);
			
			xmlWriter.write(notesAndPages); //output.xml
			
			String encryptedFilePath = dirPath + "\\encryptedFile.xml";
			File encryptedFile = new File(encryptedFilePath);
			
			String inputFilePath = dirPath + "\\tempXMLWriter.xml";
			File inputFile = new File(inputFilePath);
			encFunc.encrypt(inputFile, encryptedFile, dbStorageClient);
			
			dbFunc.storageUpload(dbStorageClient, encryptedFilePath, "applicationData.xml");

			encryptedFile.delete();
			inputFile.delete();
		} catch(URISyntaxException e) {
			int val = popupWindow.createErrorHandlingWindow("Code error 12", "URISyntaxException while saving files");
			while(val == 1) {
				
			}
		}
	}

	public JLabel getNotesMenuButton() {
		return notesMenuButton;
	}

	public JLabel getSearchMenuButton() {
		return searchMenuButton;
	}
	
	public void setVisible(boolean state) {
		frame.setVisible(state);
	}
}
