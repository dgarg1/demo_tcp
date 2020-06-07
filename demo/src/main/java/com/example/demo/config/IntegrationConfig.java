package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.MessageChannel;

@EnableIntegration
@IntegrationComponentScan
@Configuration
public class IntegrationConfig {

	
	@Bean 
	public MessageChannel recveChannel() {
		return new DirectChannel();
	}
	
	@Bean 
	public MessageChannel rplyChannel() {
		return new DirectChannel();
	}
}


