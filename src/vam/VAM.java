package vam;

import vam.core.AudioManager;
import vam.gui.SinGui;
import vam.gui.components.Header;
import vam.gui.components.InputStrip;
import vam.gui.components.OutputStrip;
import vam.gui.components.Strip;

public class VAM {

	private static VAM instance;
	private static int fps = 30;
	public static final String FONTNAME = "Arial";

	private AudioManager audio;
	private SinGui gui;

	public static final String[] inCardNames = new String[]{"Virtual Cable 1", "Virtual Cable 2", "Virtual Cable 3", "Razer Kraken 7.1", "VoiceMeeter vaio", "VoiceMeeter Aux vaio"};
	public static final String[] outputCards = new String[]{"Razer Kraken 7.1", "VoiceMeeter vaio"};

	private Strip[] strips = new Strip[inCardNames.length + outputCards.length];

	boolean running = true;

	VAM(){
		gui = new SinGui();
		gui.addSinComponent(new Header());

		audio = new AudioManager();

		for(int i = 0; i < inCardNames.length; i++){
			strips[i] = new InputStrip(i*InputStrip.width, Header.height, audio.getCardByName(inCardNames[i]));
		}
		for(int i = inCardNames.length; i < strips.length; i++){
			strips[i] = new OutputStrip();
		}
		for(Strip s : strips){
			gui.addSinComponent(s);
		}

		new Thread(renderUpdater).start();
	}

	Runnable renderUpdater = new Runnable() {

		@Override
		public void run() {
			while (running){
				gui.repaint();
				try {
					Thread.sleep(1000/fps);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	};

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
