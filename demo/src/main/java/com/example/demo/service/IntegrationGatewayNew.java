package com.example.demo.service;
import java.util.concurrent.Future;

import javax.jws.soap.InitParam;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

@MessagingGateway(defaultRequestChannel = "sendChannel")
//@MessagingGateway(defaultRequestChannel = "toTc.input")

//@MessagingGateway(defaultRequestChannel = "REQUEST_CHANNEL")
public interface IntegrationGatewayNew {

	//@Gateway(requestChannel = "sendChannel")
	
	
		//public String sendMessage(@Payload String in, @Header("PORT") Integer port);
	public String sendMessage(Message<String> message);
	
	
}
