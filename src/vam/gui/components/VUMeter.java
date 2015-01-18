package vam.gui.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import vam.Util;
import vam.gui.SinComponent;

public class VUMeter extends SinComponent {

	public static Color background = new Color(0, 0, 0);
	public static Color blueColor = new Color(136, 176, 188);
	public static Color greenColor = new Color(30, 254, 91);
	public static Color redColor = new Color(250, 0, 0);

	private static float greenFromPersentage = 0.70f;

	private Rectangle bounds;
	private int min, max;
	private float db;

	public VUMeter(Rectangle r) {
		super();
		this.bounds = r;
		this.min = -60;
		this.max = 0;
		this.db = 0;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(background);
		g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);

		float logged = (float) (10*Math.log(db));
		logged = Util.constraint(logged, -60, 0);

		int height = (int) Util.map(logged, min, max, 0, bounds.height);

		g.setColor(blueColor);
		g.fillRect(bounds.x, bounds.y+bounds.height-height, bounds.width, height);

		g.setColor(greenColor);
		height = (int) Util.constraint(height - this.bounds.height*greenFromPersentage, 0, (int) (bounds.height*greenFromPersentage));
		g.fillRect(bounds.x, (int) (bounds.y+(bounds.height*(1-greenFromPersentage))-height), bounds.width, height);


		super.draw(g);
	}

	public float getDB() {
		return db;
	}

	public void setDB(float db) {
		this.db = db;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}



}
