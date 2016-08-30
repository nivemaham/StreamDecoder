package com.hyve.streams.decoder;

import java.util.List;

import com.hyve.streams.model.Pair;

/**
 * Decodes each pair using its internal decoding mechanism 
 * @author nive
 *
 */
public class HyveDecoder {

	// a global encoded string
	public static StringBuffer ResultString = new StringBuffer("");

	/**
	 * Returns decoded string of given pairs
	 * @param charPairs
	 * @return
	 */
	public String decode(List<Pair> charPairs) {

		for (Pair pair : charPairs) {
			pair.getDecodedString();
		}
		return ResultString.toString();
	}
}
