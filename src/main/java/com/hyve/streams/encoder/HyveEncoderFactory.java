package com.hyve.streams.encoder;

/**
 * Factory class to create HyveEncoders based on request
 * 
 * Created by nive on 9/3/2016.
 */
public class HyveEncoderFactory {

    private static HyveEncoderFactory instance;


    private HyveEncoderFactory()
    {
    }

    /**
     * Returns the singleton object of factory
     * @return
     */
    public static HyveEncoderFactory getInstance()
    {
        if(instance==null)
        {
            instance = new HyveEncoderFactory();
        }
        return instance;
    }

    /**
     * Creates {@link TrivialEncoder} if requested with 1, otherwise {@link WindowEncoder}
     * @param encoderEnvReq
     * @return
     */
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
