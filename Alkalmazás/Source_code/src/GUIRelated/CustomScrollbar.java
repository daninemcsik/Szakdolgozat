package GUIRelated;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicScrollBarUI;


import java.awt.*;

public class CustomScrollbar extends BasicScrollBarUI{
	
	private Color buttonBackgroundColor = new Color(33, 35, 39);
	private Color outlineColor = new Color(63, 66, 72);
	
	@Override
	public void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
		c.setBackground(outlineColor);
		c.setBorder(BorderFactory.createLineBorder(buttonBackgroundColor));
		c.setPreferredSize(new Dimension(10, 10));
	}
	@Override
	public void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
		 	int w = thumbBounds.width;
	        int h = thumbBounds.height;

	        g.translate(thumbBounds.x, thumbBounds.y);

	        g.setColor(buttonBackgroundColor);
	        g.fillRect(0, 0, w, h);
	        
	}
	private JButton createZeroButton() {
		JButton jbutton = new JButton();
		jbutton.setPreferredSize(new Dimension(0, 0));
		jbutton.setMinimumSize(new Dimension(0, 0));
		jbutton.setMaximumSize(new Dimension(0, 0));
		return jbutton;
	}

	@Override
    protected JButton createDecreaseButton(int orientation) {
        return createZeroButton();
    }

    @Override    
    protected JButton createIncreaseButton(int orientation) {
        return createZeroButton();
    }

   
}
