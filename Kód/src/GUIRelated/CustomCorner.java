package GUIRelated;

import javax.swing.JComponent;
import java.awt.*;

public class CustomCorner extends JComponent {
	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(new Color(80, 83, 87));
		g.fillRect(0, 0, getWidth(), getHeight());
	}
}
