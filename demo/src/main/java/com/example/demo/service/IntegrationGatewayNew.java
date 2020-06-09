package com.example.demo.service;
import javax.jws.soap.InitParam;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

@MessagingGateway(defaultRequestChannel = "sendChannel")
//@MessagingGateway(defaultRequestChannel = "REQUEST_CHANNEL")
public interface IntegrationGatewayNew {

	//@Gateway(requestChannel = "sendChannel")
	
	
		public String sendMessage(@Payload String in, @Header("CORRELATION_ID") String transactionId);
	
	
	
}
