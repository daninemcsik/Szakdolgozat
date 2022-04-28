package GUIRelated;

import javax.swing.UIManager;
import javax.swing.text.DefaultCaret;
import java.awt.event.FocusEvent;



public class CustomSelectionCaret extends DefaultCaret{

	private static final long serialVersionUID = 5544419258724845390L;

	public CustomSelectionCaret()
    {
        setBlinkRate( UIManager.getInt("JTextPane.caretBlinkRate") );
    }
	public void focusGained(FocusEvent e)
    {
        setVisible(true);
        setSelectionVisible(true);
    }

    public void focusLost(FocusEvent e)
    {
        setVisible(true);
    }
    
    
    
    
}
