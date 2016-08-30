package com.hyve.streams.reader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;

import com.hyve.streams.decoder.HyveDecoder;
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

	private Integer myEnv = Integer.getInteger(System
			.getenv("USE_TRIVIAL_IMPLEMENTATION"));
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
			List<Pair> pairs = getPairs(input);
			String decodedString = decorder.decode(pairs);
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

		Reader reader = new InputStreamReader(new ByteArrayInputStream(content));

		CharBuffer buffer = CharBuffer.allocate(10);
		int test = reader.read(buffer);
		char[] arr = buffer.array();
		List<Pair> pairs = getPairs(arr);
		String testStr = decorder.decode(pairs);

		System.out.println(testStr);

	}

	public List<Pair> getPairs(char[] input) throws InvalidInputPairException {
		List<Pair> charPairs = new ArrayList<Pair>();

		for (int i = 0; i < input.length; i = i + 2) {
			char first = input[i];
			// throw exception if the first char of a pair is not numeric
			if (!Character.isDigit(first)) {
				throw new InvalidInputPairException(
						"The head charactor of pair should be a digit");
			}
			// create singlevaluepair if the first chat is 0
			if (Character.getNumericValue(first) == 0) {
				SingleValuePair single = new SingleValuePair(first,
						input[i + 1]);
				charPairs.add(single);
			}
			// create doublevaluepair is the first char is greater than 0
			else if (Character.getNumericValue(first) > 0) {
				char second = input[i + 1];
				if (!Character.isDigit(second))
					throw new InvalidInputPairException(
							"The tail charactor of a double pair should be a numberic value");
				DoubleValuePair doublePair = new DoubleValuePair(first, second);
				charPairs.add(doublePair);
			}
		}
		return charPairs;
	}

}
