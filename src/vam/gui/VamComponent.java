package vam.gui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

public abstract class VamComponent implements MouseListener, MouseMotionListener, MouseWheelListener{

	public ArrayList<VamComponent> childeren = new ArrayList<>();

	public abstract String getStatus();

	public abstract Rectangle getBounds();

	public void draw(Graphics g){
		for(VamComponent c : childeren){
			c.draw(g);
		}
	};

	void addComponent(VamComponent comp){
		childeren.add(comp);
	}

	void removeComponent(VamComponent comp){
		childeren.remove(comp);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for(VamComponent c : childeren){
			c.mouseClicked(e);
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		for(VamComponent c : childeren){
			c.mouseDragged(e);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		for(VamComponent c : childeren){
			c.mouseEntered(e);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		for(VamComponent c : childeren){
			c.mouseExited(e);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		for(VamComponent c : childeren){
			c.mouseMoved(e);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		for(VamComponent c : childeren){
			c.mousePressed(e);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		for(VamComponent c : childeren){
			c.mouseReleased(e);
		}
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		for(VamComponent c : childeren){
			c.mouseWheelMoved(e);
		}
	}
}
