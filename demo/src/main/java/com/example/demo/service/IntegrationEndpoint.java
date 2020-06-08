package com.example.demo.service;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
@MessageEndpoint
public class IntegrationEndpoint {
	

		@Transformer(inputChannel = "fromTcp", outputChannel = "toEcho")
		public String convert(byte[] bytes) {
			return new String(bytes);
		}

		@ServiceActivator(inputChannel = "toEcho")
		public String upCase(String in) {
			return in.toUpperCase();
		}

		@Transformer(inputChannel = "resultToString")
		public String convertResult(byte[] bytes) {
			return new String(bytes);
		}

	
}
