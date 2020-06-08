package com.example.demo.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.config.EnableIntegration;

@Configuration
@EnableIntegration
@IntegrationComponentScan
@MessageEndpoint
public class TCPGatewayListener {

    @ServiceActivator(inputChannel = "replyChannel")
    public String replyHandler(byte[] b) {
        return new String(b);
    }
}