package vam;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Util {

	public static float getAvg(float[] in) {
		float total = 0;
		for (float f : in) {
			total += f;
		}
		return total / in.length;
	}

	public static String[] splitAtLastDot(String in) {
		int p = in.lastIndexOf(".");
		if (p == -1) {
			return null;
		} else {
			return new String[] { in.substring(0, p), in.substring(p + 1) };
		}
	}

	public static float getMax(float[] in) {
		float max = 0;
		for (float f : in) {
			max = f > max ? f : max;
		}
		return max;
	}

	public static void appendToFile(float[] in, String filename) throws IOException {
		BufferedWriter outputWriter = null;
		outputWriter = new BufferedWriter(new FileWriter(filename));
		for (int i = 0; i < in.length; i++) {
			outputWriter.write(Float.toString(in[i]));
			outputWriter.newLine();
		}
		outputWriter.flush();
		outputWriter.close();
	}

	public static float map(float x, float in_min, float in_max, float out_min, float out_max) {
		return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
	}

	public static float constraint(float x, int min, int max) {
		x = (x < min ? min : x);
		return x > max ? max : x;
	}

}
