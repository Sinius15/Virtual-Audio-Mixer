package vam.core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.synthbot.jasiohost.AsioChannel;
import com.synthbot.jasiohost.AsioDriver;
import com.synthbot.jasiohost.AsioDriverListener;

public class AudioManager implements AsioDriverListener{

	private List<AsioSoundCard> soundCards = new ArrayList<AsioSoundCard>();
	private List<AsioChannel> activeChannels = new ArrayList<AsioChannel>();

	private int bufferSize;
	private AsioDriver asioDriver;

	public AudioManager() {
		List<String> driverNameList = AsioDriver.getDriverNames();

		asioDriver = AsioDriver.getDriver(driverNameList.get(0));
		bufferSize = asioDriver.getBufferPreferredSize();

		for(int i = 0; i < asioDriver.getNumChannelsInput(); i++){
			addChannel(asioDriver.getChannelInput(i));
		}
		for(int i = 0; i < asioDriver.getNumChannelsOutput(); i++){
			addChannel(asioDriver.getChannelOutput(i));
		}

		System.out.println("=== Cards ===");
		for(AsioSoundCard card : soundCards){
			System.out.println(card.toString());
			card.start();
		}

		asioDriver.addAsioDriverListener(this);
		asioDriver.createBuffers(new HashSet<>(activeChannels));
		asioDriver.start();
	}

	private void addChannel(AsioChannel channel){
		String name = channel.getChannelName().substring(0, channel.getChannelName().length()-2);
		AsioSoundCard card = getCardByName(name);
		if(card == null){
			AsioSoundCard newCard = new AsioSoundCard(name, bufferSize);
			newCard.addChannel(channel);
			soundCards.add(newCard);
		}else{
			card.addChannel(channel);
		}

		activeChannels.add(channel);
	}

	public AsioSoundCard getCardByName(String name){
		for(AsioSoundCard card : soundCards){
			if(card.getName().equals(name)){
				return card;
			}
		}
		return null;
	}

	public void shutDown(){
		asioDriver.shutdownAndUnloadDriver();
	}

	@Override
	public void bufferSwitch(long systemTime, long samplePosition, Set<AsioChannel> channels) {
		for(AsioSoundCard card : soundCards){
			card.inputReadEvent(systemTime, samplePosition, channels);
		}
		for(AsioSoundCard card : soundCards){
			card.outputWriteEvent(systemTime, samplePosition, channels);
		}
	}

	@Override
	public void sampleRateDidChange(double sampleRate) {}

	@Override
	public void resetRequest() {}

	@Override
	public void resyncRequest() {}

	@Override
	public void bufferSizeChanged(int bufferSize) {}

	@Override
	public void latenciesChanged(int inputLatency, int outputLatency) {}

	public void close() {
		asioDriver.shutdownAndUnloadDriver();
	}
}
