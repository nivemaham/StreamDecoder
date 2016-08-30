package com.hyve.streams.model;

/**
 * General behavior of a pair
 * @author nive
 *
 */
public abstract class Pair {
	Character p;

	Character q;

	Pair(Character first, Character second) {
		this.p = first;
		this.q = second;
	}

	/**
	 * Abstract method to implement specific decoding mechanism
	 */
	public abstract void getDecodedString();

}
