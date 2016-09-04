package com.hyve.streams.encoder;

/**
 * Created by nive on 9/3/2016.
 */
public interface HyveEncoder {
    StringBuffer encoded = new StringBuffer("");

    public String encode(String decodedValue);
}
