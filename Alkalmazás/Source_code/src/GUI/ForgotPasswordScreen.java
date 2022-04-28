package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class ForgotPasswordScreen {

	private JFrame frame;
	private JTextField usernameField;
	private JLabel logoLabel;
	private JLabel usernameLabel;
	private JLabel sendButton;
	private JLabel backToLoginButton;
	private JPanel upperPanel;
	private JLabel closeButton;
	private JLabel minimizeButton;
	private JLabel emailLabel;
	

	static Point mouseDownScreenCoords;
    static Point mouseDownCompCoords;
    private JTextField emailField;
    
    private Color buttonBackgroundColor = new Color(33, 35, 39);
	private Color outlineColor = new Color(63, 66, 72);
	private Color selectionBlue = new Color(28, 73, 255);
	private Color selectionRed = new Color(200, 10, 10);
	private Color textFieldColor = new Color(44, 47, 51);
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ForgotPasswordScreen window = new ForgotPasswordScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ForgotPasswordScreen() {
		initialize();
	}

	private void initialize() {
	
		
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.BLACK);
		frame.getContentPane().setBackground(outlineColor);
		frame.setBackground(Color.WHITE);
		frame.setBounds(100, 100, 470, 400);
		frame.getContentPane().setLayout(null);
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		//FELHASZNÁLÓNÉV LABEL
		usernameLabel = new JLabel("Username");
		usernameLabel.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 14));
		usernameLabel.setForeground(new Color(192, 192, 192));
		usernameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		usernameLabel.setBounds(135, 227, 200, 20);
		frame.getContentPane().add(usernameLabel);
		
		//FELHASZNÁLÓNÉV MEZÕ
		usernameField = new JTextField();
		usernameField.setForeground(Color.WHITE);
		usernameField.setBackground(textFieldColor);
		usernameField.setBounds(135, 250, 200, 30);
		usernameField.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		usernameField.setCaretColor(Color.WHITE);
		frame.getContentPane().add(usernameField);
		
		//EMAIL MEZÕ
		emailField = new JTextField();
		emailField.setForeground(Color.WHITE);
		emailField.setCaretColor(Color.WHITE);
		emailField.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		emailField.setBackground(textFieldColor);
		emailField.setBounds(135, 186, 200, 30);
		frame.getContentPane().add(emailField);
				
		//EMAIL LABEL
		emailLabel = new JLabel("Email");
		emailLabel.setHorizontalAlignment(SwingConstants.LEFT);
		emailLabel.setForeground(Color.LIGHT_GRAY);
		emailLabel.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 14));
		emailLabel.setBounds(135, 163, 200, 20);
		frame.getContentPane().add(emailLabel);
		
		//LOGO
		logoLabel = new JLabel("Your Personal Database");
		logoLabel.setForeground(Color.WHITE);
		logoLabel.setBackground(Color.WHITE);
		logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		logoLabel.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 18));
		logoLabel.setBounds(0, 95, 470, 20);
		frame.getContentPane().add(logoLabel);
		
		//KÜLDÉS GOMB
		sendButton = new JLabel("Send");
		sendButton.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 11));
		sendButton.setBackground(buttonBackgroundColor);
		sendButton.setForeground(Color.WHITE);
		sendButton.setBounds(135, 291, 200, 23);
		sendButton.setOpaque(true);
		sendButton.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(sendButton);
		
		//VISSZA A BELÉPÉSHEZ GOMB
		backToLoginButton = new JLabel("Back to login");
		backToLoginButton.setForeground(Color.LIGHT_GRAY);
		backToLoginButton.setOpaque(true);
		backToLoginButton.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 11));
		backToLoginButton.setForeground(Color.WHITE);
		backToLoginButton.setBackground(buttonBackgroundColor);
		backToLoginButton.setBounds(135, 315, 200, 23);
		backToLoginButton.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(backToLoginButton);
		
		//FELSÕ SÁV
		upperPanel = new JPanel();
		upperPanel.setBackground(buttonBackgroundColor);
		upperPanel.setBounds(0, 0, 470, 20);
		frame.getContentPane().add(upperPanel);
		upperPanel.setLayout(null);
		
		//BEZÁRÁS GOMB
		closeButton = new JLabel("X");
		closeButton.setForeground(Color.WHITE);
		closeButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		closeButton.setBackground(new Color(33, 35, 39));
		closeButton.setBounds(430, 0, 40, 20);
		closeButton.setHorizontalAlignment(SwingConstants.CENTER);
		closeButton.setOpaque(true);
		upperPanel.add(closeButton);
		
		//TÁLCÁZÓ GOMB
		minimizeButton = new JLabel("-");
		minimizeButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		minimizeButton.setForeground(Color.WHITE);
		minimizeButton.setBackground(buttonBackgroundColor);
		minimizeButton.setBounds(390, 0, 40, 20);
		minimizeButton.setOpaque(true);
		minimizeButton.setHorizontalAlignment(SwingConstants.CENTER);
		upperPanel.add(minimizeButton);
		
		
		
		
		
		
		
		
		
		//LISTENERS
		sendButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				sendButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sendButton.setBorder(BorderFactory.createEmptyBorder());
			}
			@Override
			public void mousePressed(MouseEvent e) {
				sendButton.setBackground(selectionBlue);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				sendButton.setBackground(buttonBackgroundColor);
			}
		});
		backToLoginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backToLoginButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				backToLoginButton.setBorder(BorderFactory.createEmptyBorder());
			}
			@Override
			public void mousePressed(MouseEvent e) {
				backToLoginButton.setBackground(selectionBlue);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				backToLoginButton.setBackground(buttonBackgroundColor);
			}
		});
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
		
		
		
		
		
		
	}
}
