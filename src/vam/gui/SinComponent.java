package vam.gui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public abstract class SinComponent implements MouseListener, MouseMotionListener{

	public ArrayList<SinComponent> childeren = new ArrayList<>();

	public void draw(Graphics g){
		for(SinComponent c : childeren){
			c.draw(g);
		}
	};

	void addComponent(SinComponent comp){
		childeren.add(comp);
	}

	void removeComponent(SinComponent comp){
		childeren.remove(comp);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for(SinComponent c : childeren){
			c.mouseClicked(e);
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		for(SinComponent c : childeren){
			c.mouseDragged(e);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		for(SinComponent c : childeren){
			c.mouseEntered(e);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		for(SinComponent c : childeren){
			c.mouseExited(e);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		for(SinComponent c : childeren){
			c.mouseMoved(e);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		for(SinComponent c : childeren){
			c.mousePressed(e);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		for(SinComponent c : childeren){
			c.mouseReleased(e);
		}
	}

}
