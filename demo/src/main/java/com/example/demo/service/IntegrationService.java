package com.example.demo.service;

import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.ip.tcp.TcpInboundGateway;
import org.springframework.integration.ip.tcp.TcpOutboundGateway;
import org.springframework.integration.ip.tcp.connection.AbstractClientConnectionFactory;
import org.springframework.integration.ip.tcp.connection.AbstractServerConnectionFactory;
import org.springframework.integration.ip.tcp.connection.TcpNetClientConnectionFactory;
import org.springframework.integration.ip.tcp.connection.TcpNetServerConnectionFactory;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.stereotype.Component;

@Component
public class IntegrationService {

	
	/*
	 * @ServiceActivator(inputChannel = "integration.gateway.channel") public void
	 * anotherMessage(Message<String> message) throws MessagingException {
	 * MessageChannel replyChannel = (MessageChannel)
	 * message.getHeaders().getReplyChannel(); MessageBuilder.fromMessage(message);
	 * Message<String> newMessage =
	 * MessageBuilder.withPayload("hello"+message.getPayload()).build();
	 * replyChannel.send(newMessage); }
	 */
	
	@Bean
	@ServiceActivator(inputChannel = "integration.gateway.channel")
	public MessageHandler tcpOutGate(AbstractClientConnectionFactory connectionFactory) {
		TcpOutboundGateway gate = new TcpOutboundGateway();
		gate.setConnectionFactory(connectionFactory);
		gate.setOutputChannelName("integration.gateway.channel");
		return gate;
	}

	@Bean
	public TcpInboundGateway tcpInGate(AbstractServerConnectionFactory connectionFactory) {
		TcpInboundGateway inGate = new TcpInboundGateway();
		inGate.setConnectionFactory(connectionFactory);
		inGate.setRequestChannel(fromTcp());
		return inGate;
	}

	@Bean
	public MessageChannel fromTcp() {
		return new DirectChannel();
	}

	@Bean
	public AbstractClientConnectionFactory clientCF() {
		return new TcpNetClientConnectionFactory("localhost", 1901);
	}

	@Bean
	public AbstractServerConnectionFactory serverCF() {
		return new TcpNetServerConnectionFactory(0);
	}


	
	
}
