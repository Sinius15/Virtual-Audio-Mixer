package vam.gui.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import vam.VAM;
import vam.gui.VamComponent;
import vam.gui.SinGui;

public class Header extends VamComponent {

	public static final Color BACKGROUND = new Color(54, 73, 90);
	public static final Color BUTTONCOLOR = new Color(95, 113, 125);
	public static final Color TEXT_COLOR = BUTTONCOLOR;
	public static final int height = 60;
	public static final String title = "Sinius Mixer";

	private Font font = new Font("Verdana", Font.PLAIN, 40);;
	private Button XButton, _Button;


	Point dragStart = new Point(-1, -1);

	public Header() {
		XButton = new Button("X", SinGui.WIDTH-height+10, 10, height-20, height-20, closeAction);
		XButton.setBackgroundColor(BUTTONCOLOR);
		XButton.setForeGroundColor(Color.white);
		XButton.setFont(new Font("Verdana", Font.PLAIN, height-20));
		this.childeren.add(XButton);

		_Button = new Button("-", SinGui.WIDTH-2*height+20, 10, height-20, height-20, makeSmallAction);
		_Button.setBackgroundColor(BUTTONCOLOR);
		_Button.setForeGroundColor(Color.white);
		_Button.setFont(new Font("Verdana", Font.PLAIN, height-20));
		this.childeren.add(_Button);
	}

	private Button.ButtonAction closeAction = new Button.ButtonAction() {
		@Override
		public void trigger() {
			VAM.getInstance().closeApplication();
		}
	};

	private Button.ButtonAction makeSmallAction = new Button.ButtonAction() {
		@Override
		public void trigger() {
			VAM.getGui().frame.setState(JFrame.ICONIFIED);
		}
	};

	@Override
	public void draw(Graphics g) {
		g.setColor(BACKGROUND);
		g.fillRect(0, 0, SinGui.WIDTH, height);

		g.setColor(TEXT_COLOR);
		g.setFont(font);

		FontMetrics fm = g.getFontMetrics();
        int txtX = ((SinGui.WIDTH - fm.stringWidth(title)) / 2);
        int txtY = ((height - fm.getHeight()) / 2) + fm.getAscent();

		g.drawString(title, txtX, txtY);
		super.draw(g);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getX() > 0 && e.getX() < SinGui.WIDTH && e.getY() > 0 && e.getY() < height ){
			dragStart.x = e.getX();
			dragStart.y = e.getY();
		}
		super.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		dragStart.x = -1;
		dragStart.y = -1;
		super.mouseReleased(e);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(dragStart.x != -1)
			VAM.getGui().frame.setLocation(e.getXOnScreen()-dragStart.x, e.getYOnScreen()-dragStart.y);
		super.mouseDragged(e);
	}
}
