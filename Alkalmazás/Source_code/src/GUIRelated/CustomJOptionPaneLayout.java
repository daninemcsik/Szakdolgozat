package GUIRelated;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicOptionPaneUI;

import Data.Content;
import Data.Note;
import Data.Page;

import java.awt.Color;

public class CustomJOptionPaneLayout extends BasicOptionPaneUI{
	
	private Color buttonBackgroundColor = new Color(33, 35, 39);
	private Color outlineColor = new Color(63, 66, 72);
	private Color selectionBlue = new Color(0, 155, 155);
 	
	private JTextField temp;
	private JList<Note> noteList;
	private JList<Page> pageList;
	private String type;
	private String action;
	private Content content;
	
	public CustomJOptionPaneLayout(Content content, JList<Note> noteList, JList<Page> pageList, String type, String action) {
		this.noteList = noteList;
		this.pageList = pageList;
		this.type = type;
		this.action = action;
		this.content = content;
	}

	public CustomJOptionPaneLayout(String type, String action) {
		this.type = type;
		this.action = action;
	}
	
	
	public JButton createButton() {
		CustomButton btn = new CustomButton();
		btn.setHorizontalAlignment(SwingConstants.CENTER);
		btn.setOpaque(true);
		btn.setBackground(buttonBackgroundColor);
		btn.setForeground(Color.WHITE);
		btn.setBorder(BorderFactory.createEmptyBorder());
		btn.setPreferredSize(new Dimension(70, 25));
		btn.setFocusPainted(false);
		btn.setHoverBackgroundColor(buttonBackgroundColor);
		btn.setPressedBackgroundColor(selectionBlue);
		return btn;
	}
	
	@Override
	protected void addButtonComponents(Container container, Object[] buttons, int initialIndex) {
		
		
		JButton customButton = createButton();
		JButton customButton2 = createButton();
		
		container.add(customButton);
		container.add(customButton2);
		
		if(action.equalsIgnoreCase("rename")) {
			customButton.setText("OK");
			customButton2.setText("Cancel");
		}else if(action.equalsIgnoreCase("delete")) {
			customButton.setText("Yes");
			customButton2.setText("No");
		}else if(action.equalsIgnoreCase("inform")) {
			customButton.setText("OK");
			container.remove(customButton2);
		}
		
		customButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				customButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				customButton.setBorder(BorderFactory.createEmptyBorder());
			}
			@Override
			public void mousePressed(MouseEvent e) {
				acceptAction(customButton);	
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				customButton.setBackground(buttonBackgroundColor);
			}
		});
		
		customButton2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				customButton2.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				customButton2.setBorder(BorderFactory.createEmptyBorder());
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Window w = SwingUtilities.getWindowAncestor(customButton2);
				if(w != null) {
					w.dispose();
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				customButton2.setBackground(buttonBackgroundColor);
			}
		});
		
		
		/*
		Action acceptButtonAction = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				acceptAction(customButton);
			}
		};
		Action declineButtonAction = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Window w = SwingUtilities.getWindowAncestor(customButton2);
				if(w != null) {
					w.dispose();
				}
			}
		};
		
		InputMap inputMap;
		KeyStroke key;
		
		inputMap = customButton.getInputMap();
		key = KeyStroke.getKeyStroke( (char) KeyEvent.VK_ENTER); 
		inputMap.put(key, acceptButtonAction);
			
		inputMap = customButton2.getInputMap();
		key = KeyStroke.getKeyStroke( (char) KeyEvent.VK_ESCAPE); 
		inputMap.put(key, declineButtonAction);
		*/
		
	}
	
	public void acceptAction(JButton customButton) {
		Window w = SwingUtilities.getWindowAncestor(customButton);
		
		if(type.equalsIgnoreCase("note") && action.equalsIgnoreCase("rename")) {
			int selectedIndex = noteList.getSelectedIndex();
			noteList.getSelectedValue().setNoteName(temp.getText());
			noteList.revalidate();
			noteList.repaint();
			noteList.setSelectedIndex(selectedIndex);
			if(w != null) {
				w.dispose();
			}
			
		}else if(type.equalsIgnoreCase("page") && action.equalsIgnoreCase("rename")) {
			int selectedIndex = pageList.getSelectedIndex();
			noteList.getSelectedValue().getNotesPages().get(pageList.getSelectedIndex()).setPageName(temp.getText());
			pageList.revalidate();
			pageList.repaint();
			pageList.setSelectedIndex(selectedIndex);
			if(w != null) {
				w.dispose();
			}
			
		}else if(type.equalsIgnoreCase("note") && action.equalsIgnoreCase("delete")) {
			noteList.getSelectedValue().deleteEveryPage();
			content.getNoteListModel().remove(noteList.getSelectedIndex());
			content.getPageListModel().removeAllElements();

			if(w != null) {
				w.dispose();
			}
			
		}else if(type.equalsIgnoreCase("page") && action.equalsIgnoreCase("delete")) {
			noteList.getSelectedValue().getNotesPages().remove(pageList.getSelectedValue());
			content.getPageListModel().removeElement(pageList.getSelectedValue());

			if(w != null) {
				w.dispose();
			}
			
		}else if(type.equalsIgnoreCase("inform") && action.equalsIgnoreCase("inform")) {
			if(w != null) {
				w.dispose();
			}
			try {
				System.exit(0);
			}catch(Exception e) {
				
			}
		}
	}
	
	@Override
	protected Container createButtonArea() {
		JPanel bottom = new JPanel();   	
		bottom.setBorder(BorderFactory.createLineBorder(outlineColor));
		bottom.setBackground(outlineColor);
		bottom.setLayout(new ButtonAreaLayout(true, 30));
		addButtonComponents(bottom, getButtons(), getInitialValueIndex());
		return bottom;
	}
	
	@Override
	protected Object getMessage() {
        inputComponent = null;
        if (optionPane != null) {
            if (optionPane.getWantsInput()) {
                /* Create a user component to capture the input. If the
                   selectionValues are non null the component and there
                   are < 20 values it'll be a combobox, if non null and
                   >= 20, it'll be a list, otherwise it'll be a textfield. */
                Object             message = optionPane.getMessage();
                Object[]           sValues = optionPane.getSelectionValues();
                Object             inputValue = optionPane
                                           .getInitialSelectionValue();
                JComponent         toAdd;

                if (sValues != null) {
                    if (sValues.length < 20) {
                        JComboBox<Object> cBox = new JComboBox<>();

                        cBox.setName("OptionPane.comboBox");
                        for(int counter = 0, maxCounter = sValues.length;
                            counter < maxCounter; counter++) {
                            cBox.addItem(sValues[counter]);
                        }
                        if (inputValue != null) {
                            cBox.setSelectedItem(inputValue);
                        }
                        inputComponent = cBox;
                        toAdd = cBox;

                    } else {
                        JList<Object>      list = new JList<>(sValues);
                        JScrollPane          sp = new JScrollPane(list);

                        sp.setName("OptionPane.scrollPane");
                        list.setName("OptionPane.list");
                        list.setVisibleRowCount(10);
                        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                        if(inputValue != null)
                            list.setSelectedValue(inputValue, true);
                        toAdd = sp;
                        inputComponent = list;
                    }

                } else {
                    JTextField   tf = new JTextField(20);

                    tf.setName("OptionPane.textField");
                    if (inputValue != null) {
                        String inputString = inputValue.toString();
                        tf.setText(inputString);
                        tf.setSelectionStart(0);
                        tf.setSelectionEnd(inputString.length());
                    }
                    tf.addActionListener(new ActionListener(){
                    	public void actionPerformed(ActionEvent e) {
                            optionPane.setInputValue(((JTextField)e.getSource()).getText());
                        }
                    });
                    toAdd = inputComponent = tf;
                    temp = tf;
                }

                Object[]           newMessage;

                if (message == null) {
                    newMessage = new Object[1];
                    newMessage[0] = toAdd;

                } else {
                    newMessage = new Object[2];
                    newMessage[0] = message;
                    newMessage[1] = toAdd;
                }
                return newMessage;
            }
            return optionPane.getMessage();
        }
        return null;
	}
}
	
