package com.hyve.streams;

import java.io.IOException;

import com.hyve.streams.decoder.HyveDecoder;
import com.hyve.streams.encoder.HyveEncoder;
import com.hyve.streams.encoder.HyveEncoderFactory;
import com.hyve.streams.exception.InvalidInputPairException;
import com.hyve.streams.processor.HyveProcessor;

/**
 * Main method of the application. Initiate the HyveProcessor to decode input
 * streams.
 * 
 * @author nive
 *
 */
public class HyveReceiver {

	public static void main(String[] args) {

		//initiate the application with preferred encoder
		HyveDecoder decoder = new HyveDecoder();
		HyveEncoder encoder;

		try {
			Integer encoderEnv = Integer.getInteger(System
					.getenv("USE_TRIVIAL_IMPLEMENTATION"));
			//default to WindowEncoder
			if (encoderEnv == null) {
				encoderEnv = 2; // or 1 for TrivialEncoder
			}
			encoder = HyveEncoderFactory.getInstance().getEncoder(encoderEnv);
			
			HyveProcessor processor = new HyveProcessor(decoder, encoder);

			processor.process();
		} catch (IOException e) {
			System.err.println("Error occured while processing input streams.");
			e.printStackTrace();
		} catch (InvalidInputPairException e) {
			System.err.println("Invalid input pair received");
			e.printStackTrace();
		}
	}

}
