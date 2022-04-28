package GUIRelated;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import Data.Content;
import Data.Note;
import Data.Page;

public class PopupWindow {
	
   	private Color outlineColor = new Color(63, 66, 72);
   	private Color selectionGray = new Color(44, 47, 51);
	private Color buttonBackgroundColor = new Color(33, 35, 39);
	
   	public int createErrorHandlingWindow(String str1, String str2) {   					

   			JFrame frame = new JFrame();
   			frame.setBounds(100, 100, 500, 150);
   			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   			frame.getContentPane().setLayout(null);
   			frame.setUndecorated(true);
   			frame.setResizable(false);
   			frame.setLocationRelativeTo(null);
   			
   			JPanel panel = new JPanel();
   			panel.setBackground(outlineColor);
   			panel.setBounds(0, 0, 500, 150);
   			panel.setLayout(null);
   			panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
   			panel.setVisible(true);
   			frame.getContentPane().add(panel);
   			
   			JLabel msg = new JLabel("ERROR");
   			msg.setHorizontalAlignment(SwingConstants.CENTER);
   			msg.setBounds(5, 0, 490, 60);
   			msg.setFont(new Font("Tahoma", Font.BOLD, 20));
   			msg.setForeground(Color.RED);
   			msg.setVisible(true);
   			panel.add(msg);
   			
   			JLabel textUp = new JLabel(str1);
   			textUp.setBounds(5, 60, 490, 30);
   			textUp.setHorizontalAlignment(SwingConstants.CENTER);
   			textUp.setOpaque(true);
   			textUp.setBackground(outlineColor);
   			textUp.setFont(new Font("Tahoma", Font.BOLD, 14));
   			textUp.setForeground(Color.WHITE);
   			textUp.setVisible(true);
   			panel.add(textUp);
   			
   			JLabel textDown = new JLabel(str2);
   			textDown.setBounds(5, 90, 490, 30);
   			textDown.setHorizontalAlignment(SwingConstants.CENTER);
   			textDown.setOpaque(true);
   			textDown.setBackground(outlineColor);
   			textDown.setFont(new Font("Tahoma", Font.BOLD, 14));
   			textDown.setForeground(Color.WHITE);
   			textDown.setVisible(true);
   			panel.add(textDown);
   			
   			JLabel okButton = new JLabel("OK");
   			okButton.setBounds(225, 120, 50, 25);
   			okButton.setBackground(buttonBackgroundColor);
   			okButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
   			okButton.setHorizontalAlignment(SwingConstants.CENTER);
   			okButton.setForeground(Color.WHITE);
   			okButton.setOpaque(true);
   			panel.add(okButton);
   			
   			okButton.addMouseListener(new MouseAdapter() {
   				@Override
   				public void mousePressed(MouseEvent e) {
   					System.exit(0);
   				}
   				@Override
   				public void mouseEntered(MouseEvent e) {
   					okButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
   				}
   				@Override
   				public void mouseExited(MouseEvent e) {
   					okButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
   				}
   				
   			});
   			
   			
   			frame.revalidate();
   			frame.setVisible(true);
   			return 1;
   	}
   	
  	
   	
	@SuppressWarnings("deprecation")
	public void createNoInternetPopupMenu(String type, String action){
		UIManager.put("Panel.background", outlineColor);
		UIManager.put("OptionPane.messageForeground", Color.WHITE);
		UIManager.put("TextField.background", selectionGray);
		UIManager.put("TextField.foreground", Color.WHITE);
		
		JLabel msg = new JLabel();
		msg.setHorizontalAlignment(SwingConstants.CENTER);
		msg.setForeground(Color.WHITE);
		
		CustomJOptionPane pane = new CustomJOptionPane();
		
		msg.setText("Error: No Internet connection! The application will close!'");
		pane.setWantsInput(false);
		
	    pane.setBorder(BorderFactory.createLineBorder(Color.RED));
	    pane.setOpaque(false);
	    pane.setOptionType(JOptionPane.OK_OPTION);
	    pane.setUI(new CustomJOptionPaneLayout(type, action));
	  	pane.setMessage(msg);
	  	
	  	
	  	
	    JDialog f = pane.createDialog("");
	    f.show();
	    f.dispose();   
	}
	
	@SuppressWarnings("deprecation")
	public void createPopupMenu(String type, String action, Content content, JList<Note> noteList, JList<Page> pageList){
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
