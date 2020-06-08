package com.example.demo.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.core.serializer.Deserializer;

public class CommandResultDeserializer implements Deserializer {

	@Override
	public String deserialize(InputStream inputStream) throws IOException {
	    BufferedReader br = null;
	    StringBuilder sb = new StringBuilder();

	    String line;

	    br = new BufferedReader(new InputStreamReader(inputStream));
	    
	    while ((line = br.readLine()) != null) {
	    	
	    	if (line.equals("</endofdoc>")) {
	    		   break;
	    		}
	        sb.append(line);
	    }
	    return sb.toString();
}}