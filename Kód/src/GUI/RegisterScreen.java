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

public class RegisterScreen {

	private JFrame frame;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JLabel logoLabel;
	private JPasswordField passwordAgainField;
    private JTextField emailField;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel registerButton;
    private JPanel upperPanel;
    private JLabel closeButton;
    private JLabel minimizeButton;
    private JLabel passwordAgainLabel;
    private JLabel emailLabel;
    private JLabel backToLoginButton;

	private static Point mouseDownScreenCoords;
	private static Point mouseDownCompCoords;
	
	private Color buttonBackgroundColor = new Color(33, 35, 39);
	private Color outlineColor = new Color(63, 66, 72);
	private Color selectionBlue = new Color(28, 73, 255);
	private Color selectionRed = new Color(200, 10, 10);
	private Color textFieldColor = new Color(44, 47, 51);
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterScreen window = new RegisterScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public RegisterScreen() {
		initialize();
	}

	private void initialize() {
	
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.BLACK);
		frame.getContentPane().setBackground(outlineColor);
		frame.setBounds(100, 100, 470, 470);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		//LOGO
		logoLabel = new JLabel("Your Personal Database");
		logoLabel.setForeground(Color.WHITE);
		logoLabel.setBackground(Color.WHITE);
		logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		logoLabel.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 18));
		logoLabel.setBounds(0, 62, 470, 20);
		frame.getContentPane().add(logoLabel);
		
		//FELHASZNÁLÓNÉV LABEL
		usernameLabel = new JLabel("Username");
		usernameLabel.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 14));
		usernameLabel.setForeground(Color.WHITE);
		usernameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		usernameLabel.setBounds(135, 194, 200, 20);
		frame.getContentPane().add(usernameLabel);
		
		//FELHASZNÁLÓNÉV MEZÕ
		usernameField = new JTextField();
		usernameField.setForeground(Color.WHITE);
		usernameField.setBackground(textFieldColor);
		usernameField.setBounds(135, 217, 200, 30);
		usernameField.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		usernameField.setCaretColor(Color.WHITE);
		frame.getContentPane().add(usernameField);
		
		//JELSZÓ LABEL
		passwordLabel = new JLabel("Password");
		passwordLabel.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 14));
		passwordLabel.setForeground(Color.WHITE);
		passwordLabel.setHorizontalAlignment(SwingConstants.LEFT);
		passwordLabel.setBounds(135, 258, 200, 20);
		frame.getContentPane().add(passwordLabel);
		
		//JELSZÓ MEZÕ
		passwordField = new JPasswordField();
		passwordField.setForeground(Color.WHITE);
		passwordField.setBackground(textFieldColor);
		passwordField.setBounds(135, 281, 200, 30);
		passwordField.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		passwordField.setCaretColor(Color.WHITE);
		frame.getContentPane().add(passwordField);
		
		//JELSZÓ ÚJRA MEZÕ
		passwordAgainField = new JPasswordField();
		passwordAgainField.setForeground(Color.WHITE);
		passwordAgainField.setCaretColor(Color.WHITE);
		passwordAgainField.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		passwordAgainField.setBackground(textFieldColor);
		passwordAgainField.setBounds(135, 345, 200, 30);
		frame.getContentPane().add(passwordAgainField);
				
		//JELSZÓ ÚJRA LABEL
		passwordAgainLabel = new JLabel("Password again");
		passwordAgainLabel.setHorizontalAlignment(SwingConstants.LEFT);
		passwordAgainLabel.setForeground(Color.LIGHT_GRAY);
		passwordAgainLabel.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 14));
		passwordAgainLabel.setBounds(135, 322, 200, 20);
		frame.getContentPane().add(passwordAgainLabel);
				
		//EMAIL LABEL
		emailLabel = new JLabel("Email");
		emailLabel.setHorizontalAlignment(SwingConstants.LEFT);
		emailLabel.setForeground(Color.LIGHT_GRAY);
		emailLabel.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 14));
		emailLabel.setBounds(135, 130, 200, 20);
		frame.getContentPane().add(emailLabel);
				
		//EMAIL MEZÕ
		emailField = new JTextField();
		emailField.setForeground(Color.WHITE);
		emailField.setCaretColor(Color.WHITE);
		emailField.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		emailField.setBackground(new Color(44, 47, 51));
		emailField.setBounds(135, 153, 200, 30);
		frame.getContentPane().add(emailField);		
		
		//REGISTER GOMB
		registerButton = new JLabel("Register");
		registerButton.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 11));
		registerButton.setOpaque(true);
		registerButton.setBackground(buttonBackgroundColor);
		registerButton.setForeground(Color.WHITE);
		registerButton.setHorizontalAlignment(SwingConstants.CENTER);
		registerButton.setBounds(135, 386, 200, 23);
		frame.getContentPane().add(registerButton);
		
		//BACK TO LOGIN GOMB
		backToLoginButton = new JLabel("Back to login");
		backToLoginButton.setForeground(Color.WHITE);
		backToLoginButton.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 11));
		backToLoginButton.setBackground(buttonBackgroundColor);
		backToLoginButton.setBounds(135, 410, 200, 23);
		backToLoginButton.setOpaque(true);
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
		closeButton.setBackground(buttonBackgroundColor);
		closeButton.setBounds(430, 0, 40, 20);
		closeButton.setOpaque(true);
		closeButton.setHorizontalAlignment(SwingConstants.CENTER);
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
