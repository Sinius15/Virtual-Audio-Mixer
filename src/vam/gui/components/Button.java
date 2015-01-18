package vam.gui.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import vam.gui.SinComponent;

public class Button extends SinComponent {

	private int x, y, width, height;
	private Color backgroundColor, foreGroundColor, borderColor;
	private String txt;
	private Font font;

	private ButtonAction action;

	public Button(String txt, int x, int y, int width, int height, ButtonAction action) {
		super();
		this.setTxt(txt);
		this.action = action;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.backgroundColor = Color.black;
		this.foreGroundColor = Color.white;
		this.font = new Font("Verdana", Font.PLAIN, (int) (height*0.7));
		setBorderColor(null);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(backgroundColor);
		g.fillRect(x, y, width, height);
		g.setFont(font);
		g.setColor(foreGroundColor);

		FontMetrics fm = g.getFontMetrics();
        int txtX = ((width - fm.stringWidth(txt)) / 2);
        int txtY = ((height - fm.getHeight()) / 2) + fm.getAscent();

		g.drawString(txt, txtX+x, txtY+y);
		super.draw(g);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getX() >= x && e.getY() >= y && e.getX() <= x+width && e.getY() <= y + height)
			action.trigger();

		super.mouseReleased(e);
	}

	public interface ButtonAction {
		void trigger();
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x
	 *            the x to set
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
	 * @param y
	 *            the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return the backgroundColor
	 */
	public Color getBackgroundColor() {
		return backgroundColor;
	}

	/**
	 * @param backgroundColor
	 *            the backgroundColor to set
	 */
	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	/**
	 * @return the foreGroundColor
	 */
	public Color getForeGroundColor() {
		return foreGroundColor;
	}

	/**
	 * @param foreGroundColor
	 *            the foreGroundColor to set
	 */
	public void setForeGroundColor(Color foreGroundColor) {
		this.foreGroundColor = foreGroundColor;
	}

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	/**
	 * @return the borderColor
	 */
	public Color getBorderColor() {
		return borderColor;
	}

	/**
	 * @param borderColor the borderColor to set
	 */
	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public Font getFont() {
		return font;
	}

}
