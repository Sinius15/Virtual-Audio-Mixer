package vam.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import vam.Util;

import com.synthbot.jasiohost.AsioChannel;

public class AsioSoundCard{

	private boolean started = false;

	private String name;
	private List<AsioChannel> inputChannels;
	private List<AsioChannel> outputChannels;

	private int bufferSize;
	private float[][] inputBuffer; //[inputChannel][bufferSize]

	private List<AsioSoundCard> sendingTo;

	public AsioSoundCard(String name, int bufferSize) {
		this.name = name;
		this.bufferSize = bufferSize;
		this.inputChannels = new ArrayList<>();
		this.outputChannels = new ArrayList<>();
		this.sendingTo = new ArrayList<>();
	}

	public float getPreFaderVolume(){
		return Util.getMax(inputBuffer[0]);
	}

	public void addChannel(AsioChannel channel){
		if(started)
			throw new IllegalStateException("Can not add channel: Already started");
		if(channel.isInput())
			inputChannels.add(channel);
		else
			outputChannels.add(channel);
	}

	public void start(){
		if(started)
			throw new IllegalStateException("Can not start: Already started");
		started = true;

		inputBuffer = new float[inputChannels.size()][bufferSize];
		Collections.sort(inputChannels, asioChannelComparer);
		Collections.sort(outputChannels, asioChannelComparer);

	}

	Comparator<AsioChannel> asioChannelComparer = new Comparator<AsioChannel>() {
		@Override
		public int compare(AsioChannel o1, AsioChannel o2) {
			return  o1.getChannelName().compareTo(o2.getChannelName());
		}
	};

	public void inputReadEvent(long systemTime, long samplePosition, Set<AsioChannel> channels){
		if(!started)
			throw new IllegalStateException("Can not read: Not yet started");

		for(int i = 0; i < inputChannels.size(); i++){
			inputChannels.get(i).read(inputBuffer[i]);
		}
	}

	public void outputWriteEvent(long systemTime, long samplePosition, Set<AsioChannel> channels) {
		if(!started)
			throw new IllegalStateException("Can not write: Not yet started");
		for(int i = 0; i < sendingTo.size(); i++){
			sendingTo.get(i).writeToOuputChannels(inputBuffer);
		}
	}

	public void writeToOuputChannels(float[][] soundz){
		if(!started)
			throw new IllegalStateException("Can not write: Not yet started");
		for(int i = 0; i < soundz.length && i < outputChannels.size(); i++){
			outputChannels.get(i).write(soundz[i]);
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AsioSoundCard [");
		if (name != null) {
			builder.append("name=");
			builder.append(name);
			builder.append(", ");
		}
		if (inputChannels != null) {
			builder.append("inputChannels=");
			builder.append(inputChannels.size());
			builder.append(", ");
		}
		if (outputChannels != null) {
			builder.append("outputChannels=");
			builder.append(outputChannels.size());
		}
		builder.append("]");
		return builder.toString();
	}

	public String getName() {
		return name;
	}

	public void addFeedbackCard(AsioSoundCard feedback){
		this.sendingTo.add(feedback);
	}

	public void removeFeedbackCard(AsioSoundCard feedback){
		this.sendingTo.remove(feedback);
	}






}
