package com.hyve.streams.encoder;

/**
 * Created by narmi on 9/3/2016.
 */
public class TrivialEncoder implements HyveEncoder {

    @Override
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
