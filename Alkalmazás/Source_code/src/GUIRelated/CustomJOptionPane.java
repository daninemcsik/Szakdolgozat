package GUIRelated;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.HeadlessException;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;

import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class CustomJOptionPane extends JOptionPane{
	
	public CustomJOptionPane() {
            
	}


	@Override
	public JDialog createDialog(String title) throws HeadlessException {
		
		JDialog dialog = new JDialog((Dialog)null, title, true);
		
        Container contentPane = dialog.getContentPane();

        contentPane.setLayout(new BorderLayout());
        contentPane.add(this, BorderLayout.CENTER);
        
        dialog.setResizable(false);
        dialog.setUndecorated(true);      
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        
        return dialog;
    }
	
	
	
}
