package GUI;

import java.awt.Color;
import java.awt.Event;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
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
import javax.swing.undo.UndoManager;

import GUIRelated.ComboBoxRenderer;
import GUIRelated.CustomScrollbar;
import GUIRelated.CustomTextPaneEditor;

public class TextEditorButtonFunctions {

	//private JFrame frame;
	private JLabel boldTextButton;
	private JLabel italicTextButton;
	private JLabel underlineTextButton;
	private JLabel leftAlignButton;
	private JLabel centerAlignButton;
	private JLabel rightAlignButton;
	private JLabel undoButton;
	private JLabel redoButton;
	private JComboBox fontSizeMenuBar;
	private JComboBox fontStyleMenuBar;
	private JComboBox colorPickerMenuBar;
	
	private JTextPane pagesTextPane;
	
	private CustomTextPaneEditor textPaneEditor;
	private Color buttonBackgroundColor = new Color(33, 35, 39);
	private Color selectionBlue = new Color(28, 73, 255);
	
	
	public TextEditorButtonFunctions(JPanel frame, JTextPane pagesTextPane) {
		this.pagesTextPane = pagesTextPane;
		initialize(frame, pagesTextPane);
	}

	private void initialize(JPanel frame, JTextPane pagesTextPane) {
		ImageIcon imageIcon;
		UndoManager manager = new UndoManager();
		
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
				
		//LEFT ALIGN TEXT BUTTON
		leftAlignButton = new JLabel("");
		imageIcon = new ImageIcon(new ImageIcon(TextEditorButtonFunctions.class.getResource("/IconsAndPictures/align_left.png")).getImage().getScaledInstance(23, 23, Image.SCALE_DEFAULT));
		leftAlignButton.setIcon(imageIcon);
		leftAlignButton.setOpaque(true);
		leftAlignButton.setHorizontalAlignment(SwingConstants.CENTER);
		leftAlignButton.setBounds(799, 5, 40, 25);
		leftAlignButton.setBackground(buttonBackgroundColor);
		leftAlignButton.setForeground(Color.WHITE);	
		leftAlignButton.setBorder(BorderFactory.createEmptyBorder());
		frame.add(leftAlignButton);
				
		//CENTER ALIGN BUTTON
		centerAlignButton = new JLabel("");
		imageIcon = new ImageIcon(new ImageIcon(TextEditorButtonFunctions.class.getResource("/IconsAndPictures/align_center.png")).getImage().getScaledInstance(23, 23, Image.SCALE_DEFAULT));
		centerAlignButton.setIcon(imageIcon);
		centerAlignButton.setHorizontalAlignment(SwingConstants.CENTER);
		centerAlignButton.setOpaque(true);
		centerAlignButton.setBounds(839, 5, 40, 25);
		centerAlignButton.setBackground(buttonBackgroundColor);
		centerAlignButton.setForeground(Color.WHITE);	
		centerAlignButton.setBorder(BorderFactory.createEmptyBorder());
		frame.add(centerAlignButton);
				
		//RIGHT ALIGN TEXT BUTTON
		rightAlignButton = new JLabel("");
		imageIcon = new ImageIcon(new ImageIcon(TextEditorButtonFunctions.class.getResource("/IconsAndPictures/align_right.png")).getImage().getScaledInstance(23, 23, Image.SCALE_DEFAULT));
		rightAlignButton.setIcon(imageIcon);
		rightAlignButton.setHorizontalAlignment(SwingConstants.CENTER);
		rightAlignButton.setOpaque(true);
		rightAlignButton.setBounds(879, 5, 40, 25);
		rightAlignButton.setBackground(buttonBackgroundColor);
		rightAlignButton.setForeground(Color.WHITE);		
		rightAlignButton.setBorder(BorderFactory.createEmptyBorder());
		frame.add(rightAlignButton);
				
		//UNDO BUTTON
		undoButton = new JLabel("");
		imageIcon = new ImageIcon(new ImageIcon(TextEditorButtonFunctions.class.getResource("/IconsAndPictures/left_arrow.png")).getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));
		undoButton.setIcon(imageIcon);
		undoButton.setHorizontalAlignment(SwingConstants.CENTER);
		undoButton.setOpaque(true);
		undoButton.setForeground(Color.WHITE);
		undoButton.setBorder(BorderFactory.createEmptyBorder());
		undoButton.setBackground(buttonBackgroundColor);
		undoButton.setBounds(929, 5, 30, 25);
		frame.add(undoButton);
			
		//REDO BUTTON
		redoButton = new JLabel("");		
		imageIcon = new ImageIcon(new ImageIcon(TextEditorButtonFunctions.class.getResource("/IconsAndPictures/right_arrow.png")).getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));
		redoButton.setIcon(imageIcon);
		redoButton.setHorizontalAlignment(SwingConstants.CENTER);
		redoButton.setOpaque(true);
		redoButton.setForeground(Color.WHITE);
		redoButton.setBorder(BorderFactory.createEmptyBorder());
		redoButton.setBackground(buttonBackgroundColor);
		redoButton.setBounds(959, 5, 30, 25);
		frame.add(redoButton);	
		
		UIManager.put("ComboBox.background",new javax.swing.plaf.ColorUIResource(Color.GREEN));
		
		//FONT SIZE PICKER MENU
		String[] sizes = {"8", "9", "10", "11", "12", "14", "16", "18", "20", "22", "24", "26", "30"};
		fontSizeMenuBar = new JComboBox(sizes);
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
		String[] colors = {"Black", "White", "Red", "Yellow", "Blue", "Green", "Orange", "Pink"};
		colorPickerMenuBar = new JComboBox(colors);
		colorPickerMenuBar.setOpaque(true);
		colorPickerMenuBar.setSelectedItem("Black");
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
		frame.add(colorPickerMenuBar);
						
		//FONT STYLE PICKER MENU		
		String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		fontStyleMenuBar = new JComboBox(fonts);
		fontStyleMenuBar.setSelectedItem("Tahoma");
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
		
		
		textPaneEditor = new CustomTextPaneEditor(pagesTextPane);
		
		
		
		
		
		//KEYBINDS
		Action boldButtonAction = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					textPaneEditor.setSelectedTextBold(pagesTextPane);
				}catch(Exception ex) {
					
				}
			} 
		};
		Action italicButtonAction = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					textPaneEditor.setSelectedTextItalic(pagesTextPane);	
				}catch(Exception ex) {
					
				}
				
						
			}
		};
		Action underlineButtonAction = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					textPaneEditor.setSelectedTextUnderline(pagesTextPane);
				}catch(Exception ex) {
					
				}
				
					
			}
		};
		Action leftAlignButtonAction = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					textPaneEditor.setAlignment("left");
				}catch(Exception ex) {
					
				}
				
			}
		};
		Action centerAlignButtonAction = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					textPaneEditor.setAlignment("center");	
				}catch(Exception ex) {
					
				}
				
			}
		};
		Action rightAlignButtonAction = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					textPaneEditor.setAlignment("right");	
				}catch(Exception ex) {
					
				}
				 
			}
		};
		Action undoButtonAction = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					manager.undo();
				}catch(Exception ex) {
					
				}
				
			}
		};
		Action redoButtonAction = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					manager.redo();
				}catch(Exception ex) {
					
				}
				
			}
		};
		InputMap inputMap = pagesTextPane.getInputMap();
		KeyStroke key;
		
		key = KeyStroke.getKeyStroke(KeyEvent.VK_B, Event.CTRL_MASK);
		inputMap.put(key, boldButtonAction);
				
		key = KeyStroke.getKeyStroke(KeyEvent.VK_I, Event.CTRL_MASK);
		inputMap.put(key, italicButtonAction);
				
		key = KeyStroke.getKeyStroke(KeyEvent.VK_U, Event.CTRL_MASK);
		inputMap.put(key, underlineButtonAction);
			
		key = KeyStroke.getKeyStroke(KeyEvent.VK_Q, Event.CTRL_MASK);
		inputMap.put(key, leftAlignButtonAction);
				
		key = KeyStroke.getKeyStroke(KeyEvent.VK_E, Event.CTRL_MASK);
		inputMap.put(key, centerAlignButtonAction);
				
		key = KeyStroke.getKeyStroke(KeyEvent.VK_R, Event.CTRL_MASK);
		inputMap.put(key, rightAlignButtonAction);
				
		key = KeyStroke.getKeyStroke(KeyEvent.VK_Z, Event.CTRL_MASK);
		inputMap.put(key, undoButtonAction);
				
		key = KeyStroke.getKeyStroke(KeyEvent.VK_Y, Event.CTRL_MASK);
		inputMap.put(key, redoButtonAction);
				
		
		
		//TOOLTIPS
		boldTextButton.setToolTipText("Bold text (Ctrl + B)");
		italicTextButton.setToolTipText("Italic text (Ctrl + I)");
		underlineTextButton.setToolTipText("Underline text (Ctrl + U)");
		leftAlignButton.setToolTipText("Left align text (Ctrl + Q)");
		centerAlignButton.setToolTipText("Center align text (Ctrl + E)");
		rightAlignButton.setToolTipText("Right align text (Ctrl + R)");
		undoButton.setToolTipText("Undo edit (Ctrl + Z)");
		redoButton.setToolTipText("Redo edit (Ctrl + Y)");
		fontStyleMenuBar.setToolTipText("Font style");
		fontSizeMenuBar.setToolTipText("Font size");
		colorPickerMenuBar.setToolTipText("Font color");
		
		UIManager.put("ToolTip.background", buttonBackgroundColor);
		UIManager.put("ToolTip.foreground", Color.WHITE);
		UIManager.put("ToolTip.border", BorderFactory.createLineBorder(Color.WHITE));
		
		
		//LISTENERS
		int start, end;
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
					textPaneEditor.setSelectedTextBold(pagesTextPane);
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
					textPaneEditor.setSelectedTextItalic(pagesTextPane);
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
					textPaneEditor.setSelectedTextUnderline(pagesTextPane);
				}catch(Exception ex) {
					
				}
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				underlineTextButton.setBackground(buttonBackgroundColor);
			}
		});
		leftAlignButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				leftAlignButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				leftAlignButton.setBorder(BorderFactory.createEmptyBorder());
			}
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					leftAlignButton.setBackground(selectionBlue);
					textPaneEditor.setAlignment("left");
				}catch(Exception ex) {
					
				}
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				leftAlignButton.setBackground(buttonBackgroundColor);
			}
		});
		centerAlignButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				centerAlignButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				centerAlignButton.setBorder(BorderFactory.createEmptyBorder());
			}
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					centerAlignButton.setBackground(selectionBlue);
					textPaneEditor.setAlignment("center");
				}catch(Exception ex) {
					
				}
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				centerAlignButton.setBackground(buttonBackgroundColor);
			}			
		});
		rightAlignButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rightAlignButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				rightAlignButton.setBorder(BorderFactory.createEmptyBorder());
			}
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					rightAlignButton.setBackground(selectionBlue);
					textPaneEditor.setAlignment("right");
				}catch(Exception ex) {
					
				}
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				rightAlignButton.setBackground(buttonBackgroundColor);
			}
		});
		colorPickerMenuBar.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {	
				try {
					textPaneEditor.setTextColor(colorPickerMenuBar);
				}catch(Exception ex) {
					
				}
			}
		});	
		fontStyleMenuBar.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				try {
					textPaneEditor.setFontStyle(fontStyleMenuBar);
				}catch(Exception ex) {
					
				}
				
			}
		});
		fontSizeMenuBar.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				try {
					textPaneEditor.setFontSize(fontSizeMenuBar);		
				}catch(Exception ex) {
					
				}
							
			}
		});
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
		pagesTextPane.getDocument().addUndoableEditListener(new UndoableEditListener() {
			public void undoableEditHappened(UndoableEditEvent e) {
				manager.addEdit(e.getEdit());					
			}
		});
				
				
				
		
		
		
		
	}
	@SuppressWarnings("deprecation")
	public void disableButtons() {
		fontSizeMenuBar.disable();
		fontStyleMenuBar.disable();
		colorPickerMenuBar.disable();
	}
	
	@SuppressWarnings("deprecation")
	public void enableButtons() {
		fontSizeMenuBar.enable();
		fontStyleMenuBar.enable();
		colorPickerMenuBar.enable();
	}
}
