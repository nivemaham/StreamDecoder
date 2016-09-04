package com.hyve.streams.decoder;

import java.util.ArrayList;
import java.util.List;

import com.hyve.streams.exception.InvalidInputPairException;
import com.hyve.streams.model.DoubleValuePair;
import com.hyve.streams.model.Pair;
import com.hyve.streams.model.SingleValuePair;

/**
 * Decodes each pair using its internal decoding mechanism 
 * @author nive
 *
 */
public class HyveDecoder {

	// a global encoded string
	private StringBuffer resultString;

	/**
	 * Returns decoded string of given pairs
	 * @param
	 * @return
	 */
	public String decode(char[] input) throws InvalidInputPairException {
		List<Pair> pairs = getPairs(input);
		resultString = new StringBuffer("");
		for (Pair pair : pairs) {
			resultString = pair.getDecodedString(resultString);
		}
		return resultString.toString();
	}

	public List<Pair> getPairs(char[] input) throws InvalidInputPairException {
		if(input.length%2 !=0)
		{
			throw new InvalidInputPairException("The encoded string length should be even");
		}
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
				if(Character.getNumericValue(second)>Character.getNumericValue(first))
				{
					throw new InvalidInputPairException("The tail value of double pair should be less than head value");
				}
				DoubleValuePair doublePair = new DoubleValuePair(first, second);
				charPairs.add(doublePair);
			}

		}
		return charPairs;
	}
}
