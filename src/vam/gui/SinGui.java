package vam.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SinGui extends JPanel implements MouseListener, MouseMotionListener{

	private static final long serialVersionUID = -8834121508019679648L;

	public static final int WIDTH = 800, HEIGHT = 600;
	public static final Color BACKGROUND = new Color(44, 61, 77);

	public JFrame frame;
	public List<VamComponent> comps = new ArrayList<>();

	public SinGui() {
		addMouseListener(this);
		addMouseMotionListener(this);

		frame = new JFrame("Sinius Mixer");
		frame.setUndecorated(true);
		frame.setBounds(0, 0, WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(this);
		frame.setVisible(true);

		frame.setLocationRelativeTo(null);

		this.repaint();
		frame.repaint();

	}

	public void addSinComponent(VamComponent c){
		comps.add(c);
	}

	public void removeSinComponent(VamComponent c){
		comps.remove(c);
	}
	
	public void update(){
		
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(BACKGROUND);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		for(VamComponent c : comps){
			c.draw(g);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for(VamComponent c : comps){
			c.mouseClicked(e);
		}

		frame.repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		for(VamComponent c : comps){
			c.mouseDragged(e);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		for(VamComponent c : comps){
			c.mouseEntered(e);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		for(VamComponent c : comps){
			c.mouseExited(e);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		for(VamComponent c : comps){
			c.mouseMoved(e);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		for(VamComponent c : comps){
			c.mousePressed(e);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		for(VamComponent c : comps){
			c.mouseReleased(e);
		}
	}
}
