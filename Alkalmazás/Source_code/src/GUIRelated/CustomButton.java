package GUIRelated;

import javax.swing.JButton;

import java.awt.Color;
import java.awt.Graphics;
public class CustomButton extends JButton{
	
	/**
	 * Randomly generated serial version ID
	 */
	private static final long serialVersionUID = 6531371007240993732L;
	private Color hoverBackgroundColor;
	private Color pressedBackgroundColor;
	
	public CustomButton() {
        this(null);
    }

    public CustomButton(String text) {
        super(text);
        super.setContentAreaFilled(false);
    }
	
	@Override
	protected void paintComponent(Graphics g) {
		if (getModel().isPressed()) {
            g.setColor(pressedBackgroundColor);
        } else if (getModel().isRollover()) {
           g.setColor(hoverBackgroundColor);
        } else {
            g.setColor(hoverBackgroundColor);
        }
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
	}
	
	@Override
    public void setContentAreaFilled(boolean b) {
    }

    public Color getHoverBackgroundColor() {
        return hoverBackgroundColor;
    }

    public void setHoverBackgroundColor(Color hoverBackgroundColor) {
        this.hoverBackgroundColor = hoverBackgroundColor;
    }

    public Color getPressedBackgroundColor() {
        return pressedBackgroundColor;
    }

    public void setPressedBackgroundColor(Color pressedBackgroundColor) {
        this.pressedBackgroundColor = pressedBackgroundColor;
    }
}
