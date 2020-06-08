package com.example.demo.service;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway(defaultRequestChannel = "sendChannel")
public interface IntegrationGatewayNew {

	//@Gateway(requestChannel = "sendChannel")
		public String sendMessage(String message);
	
	
	
}
