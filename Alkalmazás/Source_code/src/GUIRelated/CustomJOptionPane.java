package GUIRelated;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class CustomJOptionPane extends JOptionPane{
	
	/**
	 * Randomly generated serial version ID
	 */
	private static final long serialVersionUID = -7131964459818495492L;


	public CustomJOptionPane() {
            
	}


	@Override
	public JDialog createDialog(String title) {
		
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
