package com.hyve.streams.processor;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.CharBuffer;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.Ignore;

import com.hyve.streams.decoder.HyveDecoder;
import com.hyve.streams.encoder.HyveEncoder;
import com.hyve.streams.encoder.TrivialEncoder;
import com.hyve.streams.encoder.WindowEncoder;
import com.hyve.streams.exception.InvalidInputPairException;

public class HyveProcessorTest {
	private HyveDecoder decoder = new HyveDecoder();
	private HyveEncoder windowEncoder = new WindowEncoder();
	private HyveEncoder trivialEncoder = new TrivialEncoder();
	
	/**
	 * Test overall behavior of the application
	 * 
	 * Decodes, re-encodes and validates re-encoded result gets decoded back to originally decoded value
	 * @throws IOException
	 * @throws InvalidInputPairException
	 */
	@Test
	public void testProcess()
		 throws IOException, InvalidInputPairException {
			String str2 = "0a110b32330c";
			byte[] content = str2.getBytes();
			Reader reader = new InputStreamReader(new ByteArrayInputStream(content));

			CharBuffer buffer = CharBuffer.allocate(14);
			int test = reader.read(buffer);
			char[] arr = buffer.array();
			
			//test decoder
			String decodedString = decoder.decode(arr);
			assertEquals("aabaabaac", decodedString);
			
			//test window encoder
			String encoded = windowEncoder.encode(decodedString);
			assertEquals("0a110b330a110c", encoded);
			String redecoded = decoder.decode(encoded.toCharArray());
			assertEquals("aabaabaac", redecoded);
			assertEquals(decodedString, redecoded);
			
			//test trival encoder
			String trivialEncoded = trivialEncoder.encode(decodedString);
			System.out.println(trivialEncoded);
			assertEquals("0a0a0b0a0a0b0a0a0c", trivialEncoded);
			String trivialDecoded = decoder.decode(trivialEncoded.toCharArray());
			assertEquals("aabaabaac", trivialDecoded);
			assertEquals(decodedString, trivialDecoded);
		
		
	}

}
