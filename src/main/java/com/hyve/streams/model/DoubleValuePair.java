package com.hyve.streams.model;


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
	public StringBuffer getDecodedString(StringBuffer currentDecoded) {
		int offset = Character.getNumericValue(this.p) ; 
		if (offset> 0) {
			int start = currentDecoded.length()-Character.getNumericValue(this.p);
			String toAppend =currentDecoded.substring(start).substring(0, Character.getNumericValue(this.q));
			return currentDecoded.append(toAppend);
		}
		return currentDecoded;
	}

}
