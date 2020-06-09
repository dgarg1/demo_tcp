package com.example.demo.service;

import java.util.Collection;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.store.MessageGroup;
import org.springframework.messaging.Message;

@Configuration
@EnableIntegration
@IntegrationComponentScan
@MessageEndpoint
public class TCPGatewayListener {

    @Transformer(inputChannel = "replyChannel")
    public String replyHandler(MessageGroup b) {
    	
    	List<Message<?>> b1 = (List<Message<?>>) b.getMessages();
    	byte[] b2 = (byte[]) b1.get(0).getPayload();
        return new String(b2);
    }
}