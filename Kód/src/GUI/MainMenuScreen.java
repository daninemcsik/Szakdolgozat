package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextPane;

import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import GUIRelated.CustomCorner;
import GUIRelated.CustomScrollbar;


public class MainMenuScreen extends JPanel{


	private JPanel calendarPanel;
	private JLabel calendarLabel;
	
	private JPanel remindersPanel;
	private JLabel remindersLabel;
	private JLabel addReminderButton;
	private JLabel deleteReminderButton;
	
	private JScrollPane textsScrollPane;
	private JTextPane textPane;
	
	
	private TextEditorButtonFunctions textEditorButtons;
	
	private Color buttonBackgroundColor = new Color(33, 35, 39);
   	private Color outlineColor = new Color(63, 66, 72);
   	private Color panelColor = new Color(80, 83, 87);
   	private Color selectionBlue = new Color(28, 73, 255);
   	private Color selectionRed = new Color(200, 10, 10);
  
   	
	public MainMenuScreen() {
		initialize();
	}


	private void initialize() {
		ImageIcon imageIcon;
		
		setVisible(false);
		setBackground(outlineColor);
		setBounds(0, 70, 1000, 580);
		setLayout(null);
		
		//CALENDAR PANEL
		calendarPanel = new JPanel();
		calendarPanel.setBounds(5, 5, 195, 570);
		calendarPanel.setBackground(panelColor);
		calendarPanel.setLayout(null);
		add(calendarPanel);
		
		//REMINDERS PANEL
		remindersPanel = new JPanel();
		remindersPanel.setBounds(201, 5, 195, 570);
		remindersPanel.setBackground(panelColor);
		remindersPanel.setLayout(null);
		add(remindersPanel);
		
		//CALENDAR LABEL
		calendarLabel = new JLabel("CALENDAR");
		calendarLabel.setForeground(Color.WHITE);
		calendarLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		calendarLabel.setBounds(10, 0, 185, 35);
		calendarPanel.add(calendarLabel);
		
		JSeparator calendarSeparator = new JSeparator();
		calendarSeparator.setForeground(outlineColor);
		calendarSeparator.setBackground(outlineColor);
		calendarSeparator.setBounds(0, 33, 195, 2);
		calendarPanel.add(calendarSeparator);
		
		//REMINDERS LABEL
		remindersLabel = new JLabel("REMINDERS");
		remindersLabel.setForeground(Color.WHITE);
		remindersLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		remindersLabel.setBounds(10, 0, 185, 35);
		remindersPanel.add(remindersLabel);
		
		JSeparator remindersSeparator = new JSeparator();
		remindersSeparator.setBackground(outlineColor);
		remindersSeparator.setForeground(outlineColor);
		remindersSeparator.setBounds(0, 33, 195, 2);
		remindersPanel.add(remindersSeparator);
		
		//NEW REMINDER BUTTON
		addReminderButton = new JLabel("New reminder");
		addReminderButton.setHorizontalAlignment(SwingConstants.LEFT);
		addReminderButton.setOpaque(true);
		addReminderButton.setHorizontalAlignment(SwingConstants.CENTER);
		addReminderButton.setForeground(Color.WHITE);
		addReminderButton.setBorder(BorderFactory.createEmptyBorder());
		addReminderButton.setBackground(buttonBackgroundColor);
		addReminderButton.setBounds(2, 540, 141, 28);
		remindersPanel.add(addReminderButton);
		
		//DELETE REMINDER BUTTON
		deleteReminderButton = new JLabel("");
		imageIcon = new ImageIcon(new ImageIcon(MainMenuScreen.class.getResource("/IconsAndPictures/trash.png")).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		deleteReminderButton.setIcon(imageIcon);
		deleteReminderButton.setHorizontalAlignment(SwingConstants.CENTER);
		deleteReminderButton.setOpaque(true);	
		deleteReminderButton.setForeground(Color.WHITE);		
		deleteReminderButton.setBorder(BorderFactory.createEmptyBorder());		
		deleteReminderButton.setBackground(buttonBackgroundColor);
		deleteReminderButton.setBounds(145, 540, 48, 28);
		remindersPanel.add(deleteReminderButton);
		
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
		textsScrollPane.setViewportView(textPane);
				
		//SZERKESZTÕ GOMBJAI
		textEditorButtons = new TextEditorButtonFunctions(this, textPane);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//ACTION / MOUSE LISTENERS
		addReminderButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				addReminderButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				addReminderButton.setBorder(BorderFactory.createEmptyBorder());
			}
			@Override
			public void mousePressed(MouseEvent e) {
				addReminderButton.setBackground(selectionBlue);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				addReminderButton.setBackground(buttonBackgroundColor);
			}
		});
		deleteReminderButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				deleteReminderButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				deleteReminderButton.setBorder(BorderFactory.createEmptyBorder());
			}
			@Override
			public void mousePressed(MouseEvent e) {
				deleteReminderButton.setBackground(selectionRed);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				deleteReminderButton.setBackground(buttonBackgroundColor);
			}
		});
		
	}
}
