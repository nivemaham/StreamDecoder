package com.hyve.streams.encoder;

/**
 * Trivial Encoder which always encodes char using singlevaluepairs
 * Created by nive on 9/3/2016.
 */
public class TrivialEncoder implements HyveEncoder {

    
    /* Encodes a string using singlevaluepairs
     * (non-Javadoc)
     * @see com.hyve.streams.encoder.HyveEncoder#encode(java.lang.String)
     */
    public String encode(String decodedValue) {
        StringBuffer encodedValue = new StringBuffer("");
        for(char eachChar : decodedValue.toCharArray())
        {
            encodedValue.append('0');
            encodedValue.append(eachChar);
        }
        return encodedValue.toString();
    }
}
