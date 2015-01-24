package vam.gui.components;

import java.awt.Graphics;
import java.awt.Rectangle;

import vam.VAM;
import vam.core.AsioSoundCard;

public class InputStrip extends Strip {

	public static int width = 150, height = 500;

	private int x, y;

	AsioSoundCard card;
	VUMeter meter;
	FaderButton[] buttons = new FaderButton[VAM.outputCards.length];

	public InputStrip(int x, int y, AsioSoundCard card) {
		this.x = x;
		this.y = y;
		this.card = card;
		meter = new VUMeter(new Rectangle(x+10, y+150, width/4, 300));
		this.childeren.add(meter);
		for(int i = 0; i < buttons.length; i++){
			buttons[i] = new FaderButton((char) (i+65), x+55, y+i*55+150, 50);
			this.childeren.add(buttons[i]);
		}


	}


	@Override
	public void draw(Graphics g) {
		meter.setDB(card.getPreFaderVolume());
		super.draw(g);
	}

	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}


}
