package vam.gui.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.Rectangle2D;

import vam.Util;
import vam.VAM;
import vam.gui.VamComponent;

public class FaderButton extends VamComponent{

	public static Color centerActivatedColor = new Color(112, 195, 153),
						centerDeactivatedColor = new Color(95, 120, 137),
						circleColor = new Color(65, 153, 108),
						txtColor = new Color(65, 153, 108);

	private int x, y, radius, pixWidth, pixHeight;
	private int min = -60, max = 60;
	private String txt;

	private int value = 0;
	private boolean activated = false;

	public FaderButton(char txt, int x, int y, int radius) {
		this.x = x;
		this.y = y;
		this.setTxt(txt);
		this.radius = radius;
		this.pixWidth = radius;
		this.pixHeight = radius+20;
	}

	@Override
	public void draw(Graphics g) {
		//circle
		g.setColor(centerActivatedColor);
		g.fillArc(x, y, radius, radius, 90, (int) -Util.map(value, min, max, -355, 355));

		//centre
		g.setColor(activated ? centerActivatedColor : centerDeactivatedColor);
		g.fillOval((int) (x+radius*0.1), (int) (y+radius*0.1), (int) (radius*0.8), (int) (radius*0.8));

		//txt
		g.setFont(new Font(VAM.FONTNAME, Font.PLAIN, (int) (radius*0.6)));

		FontMetrics fm = g.getFontMetrics();
		Rectangle2D strBounds = fm.getStringBounds(txt, g);
        int txtX = (int) (x + radius/2 - strBounds.getWidth()/2);
        int txtY = (int) (y + radius*1.5 - strBounds.getHeight());

		g.setColor(txtColor);
		g.drawString(txt, txtX, txtY);

		g.setColor(Color.red);
		g.drawRect(x, y, pixWidth, pixHeight);

		super.draw(g);
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if(new Rectangle(x, y, radius, radius).contains(e.getX(), e.getY())){
			value += e.getWheelRotation()*-8;
			value = (int) Util.constraint(value, min, max);
		}
		super.mouseWheelMoved(e);
	}

	@Override
	public String getStatus() {
		return "Fader Button. Selected value: " + value;
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(max, y, pixWidth, pixHeight);
	}

	/**
	 * @return the min
	 */
	public int getMin() {
		return min;
	}

	/**
	 * @param min the min to set
	 */
	public void setMin(int min) {
		this.min = min;
	}

	/**
	 * @return the max
	 */
	public int getMax() {
		return max;
	}

	/**
	 * @param max the max to set
	 */
	public void setMax(int max) {
		this.max = max;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * @return the txt
	 */
	public char getTxt() {
		return txt.charAt(0);
	}

	/**
	 * @param txt the txt to set
	 */
	public void setTxt(char txt) {
		this.txt = Character.toString(txt);
	}

	/**
	 * @return the activated
	 */
	public boolean isActivated() {
		return activated;
	}

	/**
	 * @param activated the activated to set
	 */
	public void setActivated(boolean activated) {
		this.activated = activated;
	}

}
