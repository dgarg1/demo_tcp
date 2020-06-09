package com.example.demo.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.serializer.DefaultSerializer;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.ip.tcp.TcpOutboundGateway;
import org.springframework.integration.ip.tcp.connection.MessageConvertingTcpMessageMapper;
import org.springframework.integration.ip.tcp.connection.TcpNetClientConnectionFactory;
import org.springframework.integration.support.converter.MapMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;

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
    public PollableChannel replyChannel() {
        return new QueueChannel();
    }

    @Bean(name="sendChannel")
    public MessageChannel sendChannel() {
        return  new DirectChannel();
    }

    @Bean
	public TcpNetClientConnectionFactory connectionFactory() {
		TcpNetClientConnectionFactory connectionFactory = new TcpNetClientConnectionFactory("localhost", 1901);
		
		
		connectionFactory.setDeserializer(new CommandResultDeserializer());
		//connectionFactory.setDeserializer(new DefaultDeserializer());
		connectionFactory.setSerializer(new DefaultSerializer());
	
		connectionFactory.setSingleUse(false);
		connectionFactory.setSoTimeout(10000000);
		
		
		  MapMessageConverter mc = new MapMessageConverter();
		  mc.setHeaderNames("CORRELATION_ID"); 
		  
		  connectionFactory.setMapper(new MessageConvertingTcpMessageMapper(mc));
		 		
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