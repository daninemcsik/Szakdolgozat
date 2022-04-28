package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class LoadingScreen {

	
   	private Color outlineColor = new Color(63, 66, 72);
	private JFrame frame;
	private JPanel panel;
	private JLabel icon;
	private JLabel textUp;
	private JLabel textDown;
	private ImageIcon imageIcon;
	
	public LoadingScreen() {
		initialize();
	}


	private void initialize() {
		imageIcon = new ImageIcon(new ImageIcon(MainScreenOutline.class.getResource("/IconsAndPictures/main_icon.png")).getImage().getScaledInstance(40, 40 , Image.SCALE_DEFAULT));	
				
		frame = new JFrame();
		frame.setBounds(100, 100, 300, 150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setIconImage(imageIcon.getImage());
		
		panel = new JPanel();
		panel.setBackground(outlineColor);
		panel.setBounds(0, 0, 300, 150);
		panel.setLayout(null);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel.setVisible(true);
		frame.getContentPane().add(panel);
		
		icon = new JLabel("");
		icon.setHorizontalAlignment(SwingConstants.CENTER);
		icon.setBounds(5, 0, 290, 58);
		imageIcon = new ImageIcon(new ImageIcon(MainScreenOutline.class.getResource("/IconsAndPictures/main_icon.png")).getImage().getScaledInstance(40, 40 , Image.SCALE_DEFAULT));
		icon.setIcon(imageIcon);
		icon.setVisible(true);
		panel.add(icon);
		
		textUp = new JLabel("Your data is being loaded!");
		textUp.setBounds(5, 60, 290, 30);
		textUp.setHorizontalAlignment(SwingConstants.CENTER);
		textUp.setOpaque(true);
		textUp.setBackground(outlineColor);
		textUp.setFont(new Font("Tahoma", Font.BOLD, 20));
		textUp.setForeground(Color.WHITE);
		textUp.setVisible(true);
		panel.add(textUp);
		
		textDown = new JLabel("Please wait!");
		textDown.setBounds(5, 90, 290, 30);
		textDown.setHorizontalAlignment(SwingConstants.CENTER);
		textDown.setOpaque(true);
		textDown.setBackground(outlineColor);
		textDown.setFont(new Font("Tahoma", Font.BOLD, 20));
		textDown.setForeground(Color.WHITE);
		textDown.setVisible(true);
		panel.add(textDown);
				
		
		frame.revalidate();
		frame.setVisible(true);
	}
	
	public void close() {
		frame.dispose();
	}
}
