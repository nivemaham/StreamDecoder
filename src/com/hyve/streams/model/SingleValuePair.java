package com.hyve.streams.model;

import com.hyve.streams.decoder.HyveDecoder;

/**
 * Handles single valued pair
 * @author nive
 *
 */
public class SingleValuePair extends Pair {

	public SingleValuePair(Character first, Character second) {
		super(new Character('0'), second);
		
	}

	/* (non-Javadoc)
	 * Implement singlevalue specific decoding
	 * @see com.hyve.streams.model.Pair#getDecodedString()
	 */
	@Override
	public void getDecodedString() {
		int offset = Character.getNumericValue(this.p) ; 
		if (offset== 0) {
			String toAppend =Character.toString(this.q);
			
			HyveDecoder.ResultString.append(toAppend);
		}
	}

}
