package com.example.demo.service;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.ip.tcp.TcpOutboundGateway;
import org.springframework.integration.ip.tcp.TcpReceivingChannelAdapter;
import org.springframework.integration.ip.tcp.TcpSendingMessageHandler;
import org.springframework.integration.ip.tcp.connection.TcpNioClientConnectionFactory;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.PollableChannel;

@EnableIntegration
@IntegrationComponentScan
@ComponentScan
@Configuration
public class TCPConfig145 {

	@Bean(name = "REQUEST_CHANNEL")
	public DirectChannel sender() {
	    return new DirectChannel();
	}

	@Bean(name = "RESPONSE_CHANNEL")
	public PollableChannel receiver() {
	    return new QueueChannel();
	}

	
	/*
	 * @Bean
	 * 
	 * @ServiceActivator(inputChannel = "REQUEST_CHANNEL") public MessageHandler
	 * outboundGateway(TcpNioClientConnectionFactory connectionFactory) {
	 * TcpOutboundGateway gateway = new TcpOutboundGateway();
	 * gateway.setConnectionFactory(connectionFactory);
	 * gateway.setRequestTimeout(TimeUnit.SECONDS.toMillis(9999999));
	 * gateway.setRemoteTimeout(TimeUnit.SECONDS.toMillis(99999999)); return
	 * gateway; }
	 */
	
	
	@Bean
	@ServiceActivator(inputChannel = "REQUEST_CHANNEL",requiresReply = "true", outputChannel = "RESPONSE_CHANNEL", async = "true")
	public TcpSendingMessageHandler outboundClient(TcpNioClientConnectionFactory connectionFactory) {
	    TcpSendingMessageHandler outbound = new TcpSendingMessageHandler();	  
	    outbound.setConnectionFactory(connectionFactory);
	    outbound.setRetryInterval(TimeUnit.SECONDS.toMillis(99999));
	    
	    outbound.setClientMode(true);
	  
	    return outbound;
	}

	@Bean
	public TcpReceivingChannelAdapter inboundClient(TcpNioClientConnectionFactory connectionFactory) {
	    TcpReceivingChannelAdapter inbound = new TcpReceivingChannelAdapter();
	    inbound.setConnectionFactory(connectionFactory);
	    inbound.setRetryInterval(TimeUnit.SECONDS.toMillis(99999));
	    inbound.setOutputChannel(receiver());
	    inbound.setClientMode(true);
	    return inbound;
	}
	
	@Bean
	public TcpNioClientConnectionFactory clientConnectionFactory() {
	    // Get host properties
	    //Host host = getHost(config);
	    // Create socket factory
	    TcpNioClientConnectionFactory factory = new TcpNioClientConnectionFactory("localhost", 1901);
	    factory.setDeserializer(new CommandResultDeserializer());
	    factory.setSingleUse(false); // IMPORTANT FOR SINGLE CHANNEL
	    factory.setSoTimeout((int) TimeUnit.SECONDS.toMillis(9999));
	    return factory;
}
}