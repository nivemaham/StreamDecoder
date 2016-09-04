package com.hyve.streams.processor;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.CharBuffer;

import com.hyve.streams.decoder.HyveDecoder;
import com.hyve.streams.encoder.HyveEncoder;
import com.hyve.streams.exception.InvalidInputPairException;

/**
 * Reads, decodes byte input streams and prints decoded output
 * @author nive
 *
 */
public class HyveProcessor {
	
	private HyveProcessor() {
	}

	public HyveProcessor(HyveDecoder hyveDecoder, HyveEncoder hyveEncoder)
	{
		this.decorder = hyveDecoder;
		this.encoder = hyveEncoder;
	}
	private HyveDecoder decorder = new HyveDecoder();
	private HyveEncoder encoder ;

	/**
	 * Reads byte streams from standard input and decodes the byte using
	 * PairDecoding algorithm and prints the decoded value on standard output
	 *
	 * @throws IOException
	 * @throws InvalidInputPairException
	 */
	public void process() throws IOException, InvalidInputPairException {
		// read input from standard in
		Reader reader = new InputStreamReader(System.in);

		// buffered reader preferred for unbounded input streams
		CharBuffer buffer = CharBuffer.allocate(32);

		int rd;
		while ((rd = reader.read(buffer)) != -1) {
			buffer.rewind();
			char[] input = buffer.array();
			// decode every 32 chars
			String decodedString = decorder.decode(input);
			System.out.println(decodedString.getBytes());
			
			// print re-encoded value to standard error
			String encodedValue = encoder.encode(decodedString);
			System.err.println(encodedValue);
			buffer.clear();
		}

	}

	



}
