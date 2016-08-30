package com.hyve.streams.model;

import com.hyve.streams.decoder.HyveDecoder;

/**
 * Handled Double valued pairs
 * @author nive
 *
 */
public class DoubleValuePair extends Pair {

	public DoubleValuePair(Character first, Character second) {
		super(first, second);
		
	}

	/* (non-Javadoc)
	 * Implements DoubeValue specific decoding
	 * @see com.hyve.streams.model.Pair#getDecodedString()
	 */
	@Override
	public void getDecodedString() {
		int offset = Character.getNumericValue(this.p) ; 
		if (offset> 0) {
			int start = HyveDecoder.ResultString.length()-Character.getNumericValue(this.p);
			String toAppend =HyveDecoder.ResultString.substring(start).substring(0, Character.getNumericValue(this.q));
			HyveDecoder.ResultString.append(toAppend);
		}
		
	}

}
