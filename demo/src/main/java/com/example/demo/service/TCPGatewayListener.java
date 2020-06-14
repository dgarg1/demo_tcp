package com.example.demo.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.Message;

@Configuration
@EnableIntegration
@IntegrationComponentScan
@MessageEndpoint
public class TCPGatewayListener {

    @ServiceActivator(inputChannel = "replyChannel")
    public String replyHandler(Message<String> b) {
    	
    	System.out.println("&********************************************************************&&&"+b.getHeaders());
    	System.out.println("&********************************************************************&&&"+b.getPayload().toString());

        return new String(b.getPayload());
    }
}