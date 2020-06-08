package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.ip.tcp.TcpOutboundGateway;
import org.springframework.integration.ip.tcp.connection.TcpNetClientConnectionFactory;
import org.springframework.messaging.MessageChannel;

@EnableIntegration
@IntegrationComponentScan
@ComponentScan
@Configuration
public class TCPConfig {

	/*
	 * @Value("${legacy.hostname}") private String hostname;
	 * 
	 * @Value("${legacy.port}") private int port;
	 */

    @Bean(name="replyChannel")
    public MessageChannel replyChannel() {
        return new DirectChannel();
    }

    @Bean(name="sendChannel")
    public MessageChannel sendChannel() {
        return  new DirectChannel();
    }

    @Bean
    public TcpNetClientConnectionFactory connectionFactory() {
        TcpNetClientConnectionFactory connectionFactory = new TcpNetClientConnectionFactory("localhost", 1901);
        connectionFactory.setDeserializer(new CommandResultDeserializer());

        connectionFactory.setSingleUse(true);
        connectionFactory.setSoTimeout(10000000);
        return connectionFactory;
    }

    @Bean
    @ServiceActivator(inputChannel = "sendChannel")
    public TcpOutboundGateway tcpOutboundGateway() {
        TcpOutboundGateway tcpOutboundGateway = new TcpOutboundGateway();
        tcpOutboundGateway.setConnectionFactory(connectionFactory());
        tcpOutboundGateway.setReplyChannel(this.replyChannel());
        tcpOutboundGateway.setRequiresReply(true);

        return tcpOutboundGateway;
    }

}