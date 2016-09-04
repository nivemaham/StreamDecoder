package com.hyve.streams.reader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;

import com.hyve.streams.decoder.HyveDecoder;
import com.hyve.streams.encoder.HyveEncoderFactory;
import com.hyve.streams.exception.InvalidInputPairException;
import com.hyve.streams.model.DoubleValuePair;
import com.hyve.streams.model.Pair;
import com.hyve.streams.model.SingleValuePair;

/**
 * Reads, decodes byte input streams and prints decoded output
 * @author nive
 *
 */
public class HyveProcessor {

	private Integer decoderEnv = 1; // default is set to trivial implementation
	private HyveDecoder decorder = new HyveDecoder();

	/**
	 * Reads byte streams from standard input and decodes the byte using
	 * PairDecoding algorithm and prints the decoded value on standard output
	 *
	 * @throws IOException
	 * @throws InvalidInputPairException
	 */
	public void read() throws IOException, InvalidInputPairException {
		// read input from standard in
		Reader reader = new InputStreamReader(System.in);

		// buffered reader preferred for unbounded input streams
		CharBuffer buffer = CharBuffer.allocate(32);

		int rd;
		while ((rd = reader.read(buffer)) != -1) {
			buffer.rewind();
			char[] input = buffer.array();
			// decode every 64 chars

			String decodedString = decorder.decode(input);
			System.out.println(decodedString);
			buffer.clear();
		}

	}

	/**
	 * A test method to test the behavior of the implementation using provided
	 * input
	 *
	 * @throws IOException
	 * @throws InvalidInputPairException
	 */
	public void readTest() throws IOException, InvalidInputPairException {

		String str2 = "0a110b3233";
		byte[] content = str2.getBytes();
//		decoderEnv = Integer.getInteger(System
//				.getenv("USE_TRIVIAL_IMPLEMENTATION"));
		decoderEnv = 2;

		Reader reader = new InputStreamReader(new ByteArrayInputStream(content));

		CharBuffer buffer = CharBuffer.allocate(10);
		int test = reader.read(buffer);
		char[] arr = buffer.array();

		String testStr = decorder.decode(arr);


		System.out.println(testStr);
		String encoded = HyveEncoderFactory.getInstance().getEncoder(decoderEnv).encode(testStr);
		System.out.println(encoded);
		String decoded = decorder.decode(encoded.toCharArray());
		System.out.println(decoded);
	}



}
