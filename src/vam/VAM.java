package vam;

import java.awt.Rectangle;

import vam.core.AudioManager;
import vam.gui.SinGui;
import vam.gui.components.VUMeter;
import vam.gui.components.Header;

public class VAM {

	private static VAM instance;

	private AudioManager audio;
	private SinGui gui;
	private VUMeter meter;

	boolean running = true;

	VAM(){
		gui = new SinGui();
		gui.addSinComponent(new Header());
		meter = new VUMeter(new Rectangle(100, 100, 40, 250));
		gui.addSinComponent(meter);

		audio = new AudioManager();
//		audio.getCardByName("Virtual Cable 3").addFeedbackCard(audio.getCardByName("Razer Kraken 7.1"));
//		audio.getCardByName("VoiceMeeter vaio").addFeedbackCard(audio.getCardByName("Razer Kraken 7.1"));
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (running){
					meter.setDB(audio.getCardByName("Virtual Cable 2").getPreFaderVolume());
					gui.repaint();

					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	public void closeApplication(){
		gui.frame.dispose();
		audio.close();
		running = false;
	}

	public SinGui getGui_(){
		return gui;
	}

	public static SinGui getGui(){
		return instance.getGui_();
	}

	public static VAM getInstance(){
		return instance;
	}

	public static void main(String[] args) {
		instance = new VAM();
	}

}
