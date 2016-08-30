package com.hyve.streams;

import java.io.IOException;

import com.hyve.streams.exception.InvalidInputPairException;
import com.hyve.streams.reader.HyveProcessor;

/**
 * Main method of the application.
 * Initiate the HyveProcessor to decode input streams.
 * @author nive
 *
 */
public class HyveReceiver {

	public static void main(String[] args) {

		HyveProcessor reader = new HyveProcessor();
		try {
			reader.readTest();
		} catch (IOException e) {
			System.err.println("Error occured while processing input streams.");
			e.printStackTrace();
		} catch (InvalidInputPairException e) {
			System.err.println("Invalid input pair received");
			e.printStackTrace();
		}
	}

}
