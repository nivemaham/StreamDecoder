package com.hyve.streams.encoder;

/**
 * Created by narmi on 9/3/2016.
 */
public class InferEncoder implements HyveEncoder {


    @Override
    public String encode(String decodedValue) {
        StringBuffer encodedValue = new StringBuffer("");

        StringBuffer encodedPart = new StringBuffer("");
        encodedValue.append('0');
        encodedValue.append(decodedValue.charAt(0));
        encodedPart.append(decodedValue.charAt(0));
        int i=1;

        while(i<decodedValue.length())
        {
            char currentChar = decodedValue.charAt(i);
            if(encodedPart.charAt(encodedPart.length()-1) == currentChar)
            {
                encodedValue.append('1');
                encodedValue.append('1');

            }
            else
            {
                encodedValue.append('0');
                encodedValue.append(decodedValue.charAt(i));
            }
            encodedPart.append(decodedValue.charAt(i));
            i++;

        }
        return encodedValue.toString();
    }
}
