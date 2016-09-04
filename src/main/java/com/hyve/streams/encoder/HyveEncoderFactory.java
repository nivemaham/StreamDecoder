package com.hyve.streams.encoder;

/**
 * Created by narmi on 9/3/2016.
 */
public class HyveEncoderFactory {

    private static HyveEncoderFactory instance;


    private HyveEncoderFactory()
    {
    }

    public static HyveEncoderFactory getInstance()
    {
        if(instance==null)
        {
            instance = new HyveEncoderFactory();
        }
        return instance;
    }

    public HyveEncoder getEncoder(int encoderEnvReq)
    {
        if(encoderEnvReq ==1)
        {
            return new TrivialEncoder();
        }
        else {
            return new WindowEncoder();
        }
    }
}
