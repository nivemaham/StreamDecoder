package com.hyve.streams.model;


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
	public StringBuffer getDecodedString(StringBuffer currentDecoded) {
		int offset = Character.getNumericValue(this.p) ; 
		if (offset== 0) {
			String toAppend =Character.toString(this.q);
			currentDecoded.append(toAppend);
		}
		return currentDecoded;
	}

}
