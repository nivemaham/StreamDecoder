package com.hyve.streams.encoder;

/**
 * An idea to encode streams by windows. streams are encoded until using naive
 * encoding until the window size is reached. Once window size is reached, if
 * the same window is followed next, it is decoded as a window, otherwise
 * naively encoding for the next character.
 * 
 * Created by nive on 9/3/2016.
 */
public class WindowEncoder implements HyveEncoder {

	private int windowSize;

	private StringBuffer encodedValue;

	private StringBuffer encodedPart;

	public WindowEncoder() {
		this.windowSize = 3; // default size 3
	}

	public WindowEncoder(int windowSize) {
		this.windowSize = windowSize;
	}

	public String encode(String decodedValue) {
		encodedValue = new StringBuffer("");

		encodedPart = new StringBuffer("");

		encodedValue.append('0');
		encodedValue.append(decodedValue.charAt(0));

		encodedPart.append(decodedValue.charAt(0));
		int i = 1;
		while (encodedPart.length() < this.windowSize) {
			naiveEncode(decodedValue, i);
			i++;
		}

		while (i < decodedValue.length()) {
			if (decodedValue.startsWith(
					encodedPart.toString().substring(
							encodedPart.length() - windowSize), i)) {
				encodedValue.append(windowSize);
				encodedValue.append(windowSize);
				i = i + windowSize;
			} else {
				naiveEncode(decodedValue, i);
				i++;
			}
		}
		return encodedValue.toString();
	}

	private void naiveEncode(String decodedValue, int i) {
		char currentChar = decodedValue.charAt(i);
		if (encodedPart.charAt(encodedPart.length() - 1) == currentChar) {
			encodedValue.append('1');
			encodedValue.append('1');
		} else {
			encodedValue.append('0');
			encodedValue.append(decodedValue.charAt(i));
		}
		encodedPart.append(decodedValue.charAt(i));
	}

}
