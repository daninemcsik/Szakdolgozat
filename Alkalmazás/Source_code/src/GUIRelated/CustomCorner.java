package GUIRelated;

import javax.swing.JComponent;
import java.awt.*;

public class CustomCorner extends JComponent {
	/**
	 * Randomly generated serial version ID
	 */
	private static final long serialVersionUID = 5246713764995832005L;

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(new Color(80, 83, 87));
		g.fillRect(0, 0, getWidth(), getHeight());
	}
}
