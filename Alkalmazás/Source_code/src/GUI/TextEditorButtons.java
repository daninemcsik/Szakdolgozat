	package GUI;

import java.awt.Color;
import java.awt.Event;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.text.AttributeSet;
import javax.swing.text.Element;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.undo.UndoManager;

import java.awt.event.*;


import GUIRelated.ComboBoxRenderer;
import GUIRelated.CustomScrollbar;

@SuppressWarnings("deprecation")
public class TextEditorButtons {

	//private JFrame frame;
	private JLabel boldTextButton;
	private JLabel italicTextButton;
	private JLabel underlineTextButton;
	private JLabel undoButton;
	private JLabel redoButton;
	@SuppressWarnings("rawtypes")
	private JComboBox fontSizeMenuBar;
	@SuppressWarnings("rawtypes")
	private JComboBox fontStyleMenuBar;
	@SuppressWarnings("rawtypes")
	private JComboBox colorPickerMenuBar;
	private UndoManager manager;
	
	private Color buttonBackgroundColor = new Color(33, 35, 39);
	private Color selectionBlue = new Color(0, 155, 155);
	
	private JTextPane textPane;
	private static AttributeSet attributeSet;
	private StyledDocument doc;
	private Element element;
	private int start;
	private MutableAttributeSet asNew;
	private Color pickedColor;
	private int len;
	private int counter;
	
	public TextEditorButtons(JPanel frame, JTextPane textPane) {
		this.textPane = textPane;
		this.doc = textPane.getStyledDocument();
		initialize(frame);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize(JPanel frame) {
		ImageIcon imageIcon;
		manager = new UndoManager();
		
		//BOLD TEXT BUTTON
		boldTextButton = new JLabel("B");
		boldTextButton.setHorizontalAlignment(SwingConstants.CENTER);
		boldTextButton.setOpaque(true);
		boldTextButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		boldTextButton.setBounds(669, 5, 40, 25);
		boldTextButton.setBackground(buttonBackgroundColor);
		boldTextButton.setForeground(Color.WHITE);		
		boldTextButton.setBorder(BorderFactory.createEmptyBorder());
		frame.add(boldTextButton);
				
		//ITALIC TEXT BUTTON
		italicTextButton = new JLabel("D");
		italicTextButton.setHorizontalAlignment(SwingConstants.CENTER);
		italicTextButton.setOpaque(true);
		italicTextButton.setFont(new Font("Tahoma", Font.ITALIC, 11));	
		italicTextButton.setBounds(709, 5, 40, 25);
		italicTextButton.setBackground(buttonBackgroundColor);
		italicTextButton.setForeground(Color.WHITE);	
		italicTextButton.setBorder(BorderFactory.createEmptyBorder());
		frame.add(italicTextButton);
				
		//UNDERLINE TEXT BUTTON
		underlineTextButton = new JLabel("<HTML><U>A</U></HTML>");
		underlineTextButton.setHorizontalAlignment(SwingConstants.CENTER);
		underlineTextButton.setOpaque(true);
		underlineTextButton.setBounds(749, 5, 40, 25);
		underlineTextButton.setBackground(buttonBackgroundColor);
		underlineTextButton.setForeground(Color.WHITE);	
		underlineTextButton.setBorder(BorderFactory.createEmptyBorder());
		frame.add(underlineTextButton);
				
		//UNDO BUTTON
		undoButton = new JLabel("");
		imageIcon = new ImageIcon(new ImageIcon(TextEditorButtons.class.getResource("/IconsAndPictures/left_arrow.png")).getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));
		undoButton.setIcon(imageIcon);
		undoButton.setHorizontalAlignment(SwingConstants.CENTER);
		undoButton.setOpaque(true);
		undoButton.setForeground(Color.WHITE);
		undoButton.setBorder(BorderFactory.createEmptyBorder());
		undoButton.setBackground(buttonBackgroundColor);
		undoButton.setBounds(799, 5, 30, 25);
		frame.add(undoButton);
			
		//REDO BUTTON
		redoButton = new JLabel("");		
		imageIcon = new ImageIcon(new ImageIcon(TextEditorButtons.class.getResource("/IconsAndPictures/right_arrow.png")).getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));
		redoButton.setIcon(imageIcon);
		redoButton.setHorizontalAlignment(SwingConstants.CENTER);
		redoButton.setOpaque(true);
		redoButton.setForeground(Color.WHITE);
		redoButton.setBorder(BorderFactory.createEmptyBorder());
		redoButton.setBackground(buttonBackgroundColor);
		redoButton.setBounds(829, 5, 30, 25);
		frame.add(redoButton);	
		
		UIManager.put("ComboBox.background",new javax.swing.plaf.ColorUIResource(Color.GREEN));
		
		//FONT SIZE PICKER MENU
		String[] sizes = {"8", "10", "12", "14", "18", "24", "36"};
		fontSizeMenuBar = new JComboBox(sizes);
		fontSizeMenuBar.setSelectedItem("12");
		fontSizeMenuBar.setBounds(516, 5, 53, 25);
		fontSizeMenuBar.setBackground(buttonBackgroundColor);
		fontSizeMenuBar.setForeground(Color.WHITE);	
		fontSizeMenuBar.setOpaque(true);
		fontSizeMenuBar.setBorder(BorderFactory.createEmptyBorder());
		fontSizeMenuBar.setRenderer(new ComboBoxRenderer());
		fontSizeMenuBar.setUI(new BasicComboBoxUI() {
			
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
            	return new BasicComboPopup(fontSizeMenuBar) {
                	
                	/**
					 * 
					 */
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
		frame.add(fontSizeMenuBar);
				
		//COLOR PICKER MENU
		String[] colors = {"White", "Black", "Red", "Yellow", "Blue", "Green", "Orange", "Pink"};
		colorPickerMenuBar = new JComboBox(colors);
		colorPickerMenuBar.setOpaque(true);
		colorPickerMenuBar.setSelectedItem("White");
		colorPickerMenuBar.setBounds(579, 5, 80, 25);
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
		frame.add(colorPickerMenuBar);
		
		//FONT STYLE PICKER MENU		
		String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		fontStyleMenuBar = new JComboBox(fonts);
		fontStyleMenuBar.setSelectedItem("Dialog");
		fontStyleMenuBar.setBounds(406, 5, 100, 25);
		fontStyleMenuBar.setOpaque(true);
		fontStyleMenuBar.setBorder(BorderFactory.createEmptyBorder());
		fontStyleMenuBar.setForeground(Color.WHITE);
		fontStyleMenuBar.setBackground(buttonBackgroundColor);
		fontStyleMenuBar.setRenderer(new ComboBoxRenderer());
		fontStyleMenuBar.setUI(new BasicComboBoxUI() {	
			
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
               return new BasicComboPopup(fontStyleMenuBar) {
                	
                /**
				 * 
				 */
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
		frame.add(fontStyleMenuBar);
		
				
		
		
		
		//KEYBINDS
		Action boldButtonAction = new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					setSelectedTextBold(textPane);
				}catch(Exception ex) {
					
				}
			} 
		};
		Action italicButtonAction = new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					setSelectedTextItalic(textPane);	
				}catch(Exception ex) {
					
				}
				
						
			}
		};
		Action underlineButtonAction = new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					setSelectedTextUnderline(textPane);
				}catch(Exception ex) {
					
				}
				
					
			}
		};
		Action undoButtonAction = new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					manager.undo();
				}catch(Exception ex) {
					
				}
				
			}
		};
		Action redoButtonAction = new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					manager.redo();
				}catch(Exception ex) {
					
				}
				
			}
		};
		InputMap inputMap = textPane.getInputMap();
		KeyStroke key;
		
		key = KeyStroke.getKeyStroke(KeyEvent.VK_B, Event.CTRL_MASK);
		inputMap.put(key, boldButtonAction);
				
		key = KeyStroke.getKeyStroke(KeyEvent.VK_I, Event.CTRL_MASK);
		inputMap.put(key, italicButtonAction);
				
		key = KeyStroke.getKeyStroke(KeyEvent.VK_U, Event.CTRL_MASK);
		inputMap.put(key, underlineButtonAction);
				
		key = KeyStroke.getKeyStroke(KeyEvent.VK_Z, Event.CTRL_MASK);
		inputMap.put(key, undoButtonAction);
				
		key = KeyStroke.getKeyStroke(KeyEvent.VK_Y, Event.CTRL_MASK);
		inputMap.put(key, redoButtonAction);
				
		
		
		//TOOLTIPS
		boldTextButton.setToolTipText("Bold text (Ctrl + B)");
		italicTextButton.setToolTipText("Italic text (Ctrl + I)");
		underlineTextButton.setToolTipText("Underline text (Ctrl + U)");
		undoButton.setToolTipText("Undo edit (Ctrl + Z)");
		redoButton.setToolTipText("Redo edit (Ctrl + Y)");
		fontStyleMenuBar.setToolTipText("Font style");
		fontSizeMenuBar.setToolTipText("Font size");
		colorPickerMenuBar.setToolTipText("Font color");
		
		UIManager.put("ToolTip.background", buttonBackgroundColor);
		UIManager.put("ToolTip.foreground", Color.WHITE);
		UIManager.put("ToolTip.border", BorderFactory.createLineBorder(Color.WHITE));
		
		
		//LISTENERS
		boldTextButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				boldTextButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				boldTextButton.setBorder(BorderFactory.createEmptyBorder());
			}
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					boldTextButton.setBackground(selectionBlue);
					setSelectedTextBold(textPane);
					
				}catch(Exception ex) {
					
				}
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				boldTextButton.setBackground(buttonBackgroundColor);
			}
		});
		italicTextButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				italicTextButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				italicTextButton.setBorder(BorderFactory.createEmptyBorder());
			}
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					italicTextButton.setBackground(selectionBlue);
					setSelectedTextItalic(textPane);
					
				}catch(Exception ex) {
					
				}
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				italicTextButton.setBackground(buttonBackgroundColor);
			}
		});
		underlineTextButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				underlineTextButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				underlineTextButton.setBorder(BorderFactory.createEmptyBorder());
			}
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					underlineTextButton.setBackground(selectionBlue);
					setSelectedTextUnderline(textPane);
					
				}catch(Exception ex) {
					
				}
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				underlineTextButton.setBackground(buttonBackgroundColor);
			}
		});
		colorPickerMenuBar.addItemListener(getColorPickerListener());
		fontStyleMenuBar.addItemListener(getFontStyleListener());
		fontSizeMenuBar.addItemListener(getFontSizeListener());
		
		
		undoButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				undoButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				undoButton.setBorder(BorderFactory.createEmptyBorder());
			}
			@Override
			public void mousePressed(MouseEvent e) {
				undoButton.setBackground(selectionBlue);
				try {
					manager.undo();
					
				} catch(Exception ex) {		
				
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				undoButton.setBackground(buttonBackgroundColor);
			}
		});
		redoButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				redoButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				redoButton.setBorder(BorderFactory.createEmptyBorder());
			}
			@Override
			public void mousePressed(MouseEvent e) {
				redoButton.setBackground(selectionBlue);
				try {
					manager.redo();
					
				} catch(Exception ex) {
						
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				redoButton.setBackground(buttonBackgroundColor);
			}
		});
		textPane.getDocument().addUndoableEditListener(new UndoableEditListener() {
			public void undoableEditHappened(UndoableEditEvent e) {
				manager.addEdit(e.getEdit());					
			}
		});
		
	}

	public void setSelectedTextBold(JTextPane textPane){
		len = textPane.getSelectedText().length();
		counter = 0;
		for(int i = textPane.getSelectionStart(); i < textPane.getSelectionEnd(); i++) {
			element = doc.getCharacterElement(i);
			attributeSet = element.getAttributes();
			asNew = new SimpleAttributeSet(attributeSet.copyAttributes());
			boolean isBold = StyleConstants.isBold(attributeSet);
			if(isBold) {
				counter++;
			}
		}
		if(counter < (int)len/2) {
			for(int i = textPane.getSelectionStart(); i < textPane.getSelectionEnd(); i++) {
				element = doc.getCharacterElement(i);
				attributeSet = element.getAttributes();
				asNew = new SimpleAttributeSet(attributeSet.copyAttributes());
				StyleConstants.setBold(asNew, true);
				doc.setCharacterAttributes(i, 1, asNew, false);
			}
		}else{
			for(int i = textPane.getSelectionStart(); i < textPane.getSelectionEnd(); i++) {
				element = doc.getCharacterElement(i);
				attributeSet = element.getAttributes();
				asNew = new SimpleAttributeSet(attributeSet.copyAttributes());
				StyleConstants.setBold(asNew, false);
				doc.setCharacterAttributes(i, 1, asNew, false);
			}
		}
	}
	
	public void setSelectedTextItalic(JTextPane textPane){
		len = textPane.getSelectedText().length();
		counter = 0;
		for(int i = textPane.getSelectionStart(); i < textPane.getSelectionEnd(); i++) {
			element = doc.getCharacterElement(i);
			attributeSet = element.getAttributes();
			asNew = new SimpleAttributeSet(attributeSet.copyAttributes());
			boolean isItalic = StyleConstants.isItalic(attributeSet);
			if(isItalic) {
				counter++;
			}
		}
		if(counter < (int)len/2) {
			for(int i = textPane.getSelectionStart(); i < textPane.getSelectionEnd(); i++) {
				element = doc.getCharacterElement(i);
				attributeSet = element.getAttributes();
				asNew = new SimpleAttributeSet(attributeSet.copyAttributes());
				StyleConstants.setItalic(asNew, true);
				doc.setCharacterAttributes(i, 1, asNew, false);
			}
		}else{
			for(int i = textPane.getSelectionStart(); i < textPane.getSelectionEnd(); i++) {
				element = doc.getCharacterElement(i);
				attributeSet = element.getAttributes();
				asNew = new SimpleAttributeSet(attributeSet.copyAttributes());
				StyleConstants.setItalic(asNew, false);
				doc.setCharacterAttributes(i, 1, asNew, false);
			}
		}
	}
	
	public void setSelectedTextUnderline(JTextPane textPane){
		len = textPane.getSelectedText().length();
		counter = 0;
		for(int i = textPane.getSelectionStart(); i < textPane.getSelectionEnd(); i++) {
			element = doc.getCharacterElement(i);
			attributeSet = element.getAttributes();
			boolean isUnderline = StyleConstants.isUnderline(attributeSet);
			if(isUnderline) {
				counter++;
			}
		}
		if(counter < (int)len/2) {
			for(int i = textPane.getSelectionStart(); i < textPane.getSelectionEnd(); i++) {
				element = doc.getCharacterElement(i);
				attributeSet = element.getAttributes();
				asNew = new SimpleAttributeSet(attributeSet.copyAttributes());
				StyleConstants.setUnderline(asNew, true);
				doc.setCharacterAttributes(i, 1, asNew, false);
			}
		}else{
			for(int i = textPane.getSelectionStart(); i < textPane.getSelectionEnd(); i++) {
				element = doc.getCharacterElement(i);
				attributeSet = element.getAttributes();
				asNew = new SimpleAttributeSet(attributeSet.copyAttributes());
				StyleConstants.setUnderline(asNew, false);
				doc.setCharacterAttributes(i, 1, asNew, false);
			}
		}

	}
	
	@SuppressWarnings("rawtypes")
	public void setFontStyle(JTextPane textPane, JComboBox fontStyleMenuBar) {
		for(int i = textPane.getSelectionStart(); i < textPane.getSelectionEnd(); i++) {
			element = doc.getCharacterElement(i);
			attributeSet = element.getAttributes();
			asNew = new SimpleAttributeSet(attributeSet.copyAttributes());
			String style = (String) fontStyleMenuBar.getSelectedItem();
			
			StyleConstants.setFontFamily(asNew, style);
			doc.setCharacterAttributes(i, 1, asNew, false);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public void setFontSize(JTextPane textPane, JComboBox fontSizeMenuBar) {
		for(int i = textPane.getSelectionStart(); i < textPane.getSelectionEnd(); i++) {
			element = doc.getCharacterElement(i);
			attributeSet = element.getAttributes();
			asNew = new SimpleAttributeSet(attributeSet.copyAttributes());
			String size = (String) fontSizeMenuBar.getSelectedItem();

			StyleConstants.setFontSize(asNew, Integer.parseInt(size));
			doc.setCharacterAttributes(i, 1, asNew, false);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public void setTextColor(JTextPane textPane, JComboBox colorPickerMenuBar) {
		for(int i = textPane.getSelectionStart(); i < textPane.getSelectionEnd(); i++) {
			element = doc.getCharacterElement(i);
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
			doc.setCharacterAttributes(i, 1, asNew, false);
		}
	}
	
	public void setUndoManager(UndoManager manager) {
		this.manager = manager;
	}
	
	public void setDefaultButtonSettings() {
		fontSizeMenuBar.setSelectedItem("12");
		colorPickerMenuBar.setSelectedItem("White");
		fontStyleMenuBar.setSelectedItem("Dialog");	
	}
	
	public void setButtonSettings() {
		start = textPane.getCaretPosition();
		element = doc.getCharacterElement(start);
		attributeSet = element.getAttributes();
		asNew = new SimpleAttributeSet(attributeSet.copyAttributes());
		
		if(attributeSet.getAttribute(StyleConstants.FontConstants.FontSize) != null) {
			fontSizeMenuBar.removeItemListener(getFontSizeListener());

			fontSizeMenuBar.setSelectedItem(attributeSet.getAttribute(StyleConstants.FontConstants.FontSize).toString());
			
			fontSizeMenuBar.addItemListener(getFontSizeListener());
		} 
		
		if(attributeSet.getAttribute(StyleConstants.ColorConstants.Foreground) != null){
			String color = attributeSet.getAttribute(StyleConstants.ColorConstants.Foreground).toString();
			switch(color) {
				case "java.awt.Color[r=0,g=0,b=0]":
					colorPickerMenuBar.removeItemListener(getColorPickerListener());
					colorPickerMenuBar.setSelectedItem("Black");
					colorPickerMenuBar.addItemListener(getColorPickerListener());
					break;
				case "java.awt.Color[r=255,g=0,b=0]":
					colorPickerMenuBar.removeItemListener(getColorPickerListener());
					colorPickerMenuBar.setSelectedItem("Red");
					colorPickerMenuBar.addItemListener(getColorPickerListener());
					break;
				case "java.awt.Color[r=255,g=255,b=0]":
					colorPickerMenuBar.removeItemListener(getColorPickerListener());
					colorPickerMenuBar.setSelectedItem("Yellow");
					colorPickerMenuBar.addItemListener(getColorPickerListener());
					break;
				case "java.awt.Color[r=0,g=0,b=255]":
					colorPickerMenuBar.removeItemListener(getColorPickerListener());
					colorPickerMenuBar.setSelectedItem("Blue");
					colorPickerMenuBar.addItemListener(getColorPickerListener());
					break;
				case "java.awt.Color[r=0,g=255,b=0]":
					colorPickerMenuBar.removeItemListener(getColorPickerListener());
					colorPickerMenuBar.setSelectedItem("Green");
					colorPickerMenuBar.addItemListener(getColorPickerListener());
					break;
				case "java.awt.Color[r=255,g=200,b=0]":
					colorPickerMenuBar.removeItemListener(getColorPickerListener());
					colorPickerMenuBar.setSelectedItem("Orange");
					colorPickerMenuBar.addItemListener(getColorPickerListener());
					break;
				case "java.awt.Color[r=255,g=175,b=175]":
					colorPickerMenuBar.removeItemListener(getColorPickerListener());
					colorPickerMenuBar.setSelectedItem("Pink");
					colorPickerMenuBar.addItemListener(getColorPickerListener());
					break;
				case "java.awt.Color[r=255,g=255,b=255]":
					colorPickerMenuBar.removeItemListener(getColorPickerListener());
					colorPickerMenuBar.setSelectedItem("White");
					colorPickerMenuBar.addItemListener(getColorPickerListener());
					break;
			}
		}
		
		if(attributeSet.getAttribute(StyleConstants.FontConstants.FontFamily) != null){
			fontStyleMenuBar.removeItemListener(getFontStyleListener());
			
			fontStyleMenuBar.setSelectedItem(attributeSet.getAttribute(StyleConstants.FontConstants.FontFamily).toString());
			
			fontStyleMenuBar.addItemListener(getFontStyleListener());
		}
	}
		
	public ItemListener getFontSizeListener() {
		ItemListener fontSizeItemListener = new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				try {
					setFontSize(textPane, fontSizeMenuBar);
					
				}catch(Exception ex) {
					
				}
							
			}

		};
		return fontSizeItemListener;
	}
	
	public ItemListener getFontStyleListener() {
		ItemListener fontStyleItemListener = new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				try {
					setFontStyle(textPane, fontStyleMenuBar);
					
				}catch(Exception ex) {
					
				}
				
			}
		};
		return fontStyleItemListener;	
	}
	
	public ItemListener getColorPickerListener() {
		ItemListener colorPickerItemListener = new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {	
				try {
					setTextColor(textPane, colorPickerMenuBar);
					
					
				}catch(Exception ex) {
					
				}
			}
		};
		return colorPickerItemListener;
	}
}
	

