package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class LoginScreen {

	private JFrame frame;
	private JTextField usernameField;
	private JPasswordField passwordField;
	
	private JLabel logoLabel;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	
	private JLabel loginButton;
	private JLabel forgotPasswordButton;
	private JLabel registerButton;
	
	private JPanel upperPanel;
	private JLabel closeButton;
	private JLabel minimizeButton;
	
	static Point mouseDownScreenCoords;
    static Point mouseDownCompCoords;
	
    private Color buttonBackgroundColor = new Color(33, 35, 39);
   	private Color outlineColor = new Color(63, 66, 72);
   	private Color selectionBlue = new Color(28, 73, 255);
   	private Color selectionRed = new Color(200, 10, 10);
   	private Color textFieldColor = new Color(44, 47, 51);
    
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginScreen window = new LoginScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginScreen() {
		initialize();
	}

	private void initialize() {
	
		
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.BLACK);
		frame.getContentPane().setBackground(outlineColor);
		frame.setBounds(100, 100, 470, 400);
		frame.getContentPane().setLayout(null);
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		//FELHASZNÁLÓNÉV LABEL
		usernameLabel = new JLabel("Username");
		usernameLabel.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 14));
		usernameLabel.setForeground(Color.WHITE);
		usernameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		usernameLabel.setBounds(135, 163, 200, 20);
		frame.getContentPane().add(usernameLabel);
		
		//FELHASZNÁLÓNÉV MEZÕ
		usernameField = new JTextField();
		usernameField.setForeground(Color.WHITE);
		usernameField.setBackground(textFieldColor);
		usernameField.setBounds(135, 186, 200, 30);
		usernameField.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		usernameField.setCaretColor(Color.WHITE);
		frame.getContentPane().add(usernameField);
		
		//JELSZÓ LABEL
		passwordLabel = new JLabel("Password");
		passwordLabel.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 14));
		passwordLabel.setForeground(Color.WHITE);
		passwordLabel.setHorizontalAlignment(SwingConstants.LEFT);
		passwordLabel.setBounds(135, 227, 200, 20);
		frame.getContentPane().add(passwordLabel);
		
		//JELSZÓ MEZÕ
		passwordField = new JPasswordField();
		passwordField.setForeground(Color.WHITE);
		passwordField.setBackground(textFieldColor);
		passwordField.setBounds(135, 250, 200, 30);
		passwordField.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		passwordField.setCaretColor(Color.WHITE);
		frame.getContentPane().add(passwordField);
		
		//LOGO
		logoLabel = new JLabel("Your Personal Database");
		logoLabel.setForeground(Color.WHITE);
		logoLabel.setBackground(Color.WHITE);
		logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		logoLabel.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 18));
		logoLabel.setBounds(0, 95, 470, 20);
		frame.getContentPane().add(logoLabel);
		
		//LOGIN GOMB
		loginButton = new JLabel("Login");
		loginButton.setHorizontalAlignment(SwingConstants.CENTER);
		loginButton.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 11));
		loginButton.setBackground(buttonBackgroundColor);
		loginButton.setOpaque(true);
		loginButton.setForeground(Color.WHITE);
		loginButton.setBounds(135, 291, 200, 23);
		frame.getContentPane().add(loginButton);
		
		//FORGOT PASSWORD GOMB
		forgotPasswordButton = new JLabel("Forgot Password?");
		forgotPasswordButton.setHorizontalAlignment(SwingConstants.CENTER);
		forgotPasswordButton.setOpaque(true);
		forgotPasswordButton.setForeground(Color.WHITE);
		forgotPasswordButton.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 11));
		forgotPasswordButton.setBackground(buttonBackgroundColor);
		forgotPasswordButton.setBounds(135, 339, 200, 23);
		frame.getContentPane().add(forgotPasswordButton);
		
		//REGISTER GOMB
		registerButton = new JLabel("Register");
		registerButton.setHorizontalAlignment(SwingConstants.CENTER);
		registerButton.setOpaque(true);
		registerButton.setForeground(Color.WHITE);
		registerButton.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 11));
		registerButton.setBackground(buttonBackgroundColor);
		registerButton.setBounds(135, 315, 200, 23);
		frame.getContentPane().add(registerButton);
		
		//FELSÕ SÁV
		upperPanel = new JPanel();
		upperPanel.setBackground(buttonBackgroundColor);
		upperPanel.setBounds(0, 0, 470, 20);
		upperPanel.setLayout(null);
		frame.getContentPane().add(upperPanel);
		
		//BEZÁRÁS GOMB
		closeButton = new JLabel("X");
		closeButton.setOpaque(true);
		closeButton.setHorizontalAlignment(SwingConstants.CENTER);
		closeButton.setForeground(Color.WHITE);
		closeButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		closeButton.setBackground(buttonBackgroundColor);
		closeButton.setBounds(430, 0, 40, 20);
		upperPanel.add(closeButton);
		
		//TÁLCÁZÓ GOMB
		minimizeButton = new JLabel("-");
		minimizeButton.setOpaque(true);
		minimizeButton.setHorizontalAlignment(SwingConstants.CENTER);
		minimizeButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		minimizeButton.setForeground(Color.WHITE);
		minimizeButton.setBackground(buttonBackgroundColor);
		minimizeButton.setBounds(390, 0, 40, 20);
		upperPanel.add(minimizeButton);
		
		JLabel lblNewLabel = new JLabel("Valamilyen logo");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(175, 54, 149, 14);
		frame.getContentPane().add(lblNewLabel);
		
		
		
		
		
		
		
		
		//LISTENERS
		loginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				loginButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				loginButton.setBorder(BorderFactory.createEmptyBorder());
			}
			@Override
			public void mousePressed(MouseEvent e) {
				loginButton.setBackground(selectionBlue);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				loginButton.setBackground(buttonBackgroundColor);
			}
		});
		forgotPasswordButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				forgotPasswordButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				forgotPasswordButton.setBorder(BorderFactory.createEmptyBorder());
			}
			@Override
			public void mousePressed(MouseEvent e) {
				forgotPasswordButton.setBackground(selectionBlue);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				forgotPasswordButton.setBackground(buttonBackgroundColor);
			}
		});
		registerButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				registerButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				registerButton.setBorder(BorderFactory.createEmptyBorder());
			}
			@Override
			public void mousePressed(MouseEvent e) {
				registerButton.setBackground(selectionBlue);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				registerButton.setBackground(buttonBackgroundColor);
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
