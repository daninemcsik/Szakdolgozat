package Main;

import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.text.AttributeSet;
import javax.swing.text.Element;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import javax.swing.plaf.basic.BasicArrowButton;

import GUIRelated.ComboBoxRenderer;
import GUIRelated.CustomCorner;
import GUIRelated.CustomScrollbar;
import GUIRelated.CustomSelectionCaret;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JComboBox;

public class test{

	private JFrame frame;
	private static AttributeSet attributeSet;
	private StyledDocument doc;
	private Element element;
	private int start;
	private MutableAttributeSet asNew;
	private Color pickedColor;
	private JTextPane textPane;
	private JComboBox colorPickerMenuBar;
	private Color buttonBackgroundColor = new Color(33, 35, 39);
	private Color selectionBlue = new Color(28, 73, 255);
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test window = new test();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public test() {
		initialize();
	}


	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 261);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(79, 58, 345, 203);
		panel.add(scrollPane);
		
		textPane = new JTextPane();
		textPane.setContentType("text/html");
		textPane.setForeground(Color.ORANGE);
		textPane.setCaret(new CustomSelectionCaret());
		scrollPane.setViewportView(textPane);
		
		
		String[] colors = {"White", "Black", "Red", "Yellow", "Blue", "Green", "Orange", "Pink"};
		colorPickerMenuBar = new JComboBox(colors);
		colorPickerMenuBar.setBounds(79, 22, 80, 25);
		colorPickerMenuBar.setOpaque(true);
		colorPickerMenuBar.setForeground(Color.WHITE);
		colorPickerMenuBar.setBorder(BorderFactory.createEmptyBorder());
		colorPickerMenuBar.setBackground(buttonBackgroundColor);
		colorPickerMenuBar.setRenderer(new ComboBoxRenderer());
		colorPickerMenuBar.setUI(new BasicComboBoxUI() {
			
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
		                return new BasicComboPopup(colorPickerMenuBar) {
		                	
					private static final long serialVersionUID = 1L;
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
		                	    list.setSelectionMode( ListSelectionModel.SINGLE_SELECTION);
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
		panel.add(colorPickerMenuBar);
		
	colorPickerMenuBar.addItemListener(new ItemListener() {
		@Override
		public void itemStateChanged(ItemEvent e) {	
				try {
					setTextColor(colorPickerMenuBar);
					System.out.println(textPane.getText());
				}catch(Exception ex) {
					
				}
			}
		});			
	}
	
	public void setTextColor(JComboBox colorPickerMenuBar) {
		doc = textPane.getStyledDocument();
		start = textPane.getSelectionStart();
		element = doc.getCharacterElement(start);
		attributeSet = element.getAttributes();
		asNew = new SimpleAttributeSet(attributeSet.copyAttributes());
		String style = (String) colorPickerMenuBar.getSelectedItem();
		
		switch(style) {
			case "Black":
				pickedColor = Color.BLACK;
				break;
			case "Red":
				pickedColor = Color.RED;
				break;
			case "Yellow":
				pickedColor = Color.YELLOW;
				break;
			case "Blue":
				pickedColor = Color.BLUE;
				break;
			case "Green":
				pickedColor = Color.GREEN;
				break;
			case "Orange":
				pickedColor = Color.ORANGE;
				break;
			case "Pink":
				pickedColor = Color.PINK;
				break;
			case "White":
				pickedColor = Color.WHITE;
				break;
		}

		StyleConstants.setForeground(asNew, pickedColor);
		doc.setCharacterAttributes(start, textPane.getSelectedText().length(), asNew, false);
				
	}
}
