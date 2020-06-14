package com.example.demo.service;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.context.IntegrationFlowContext;
import org.springframework.integration.ip.tcp.TcpOutboundGateway;
import org.springframework.integration.ip.tcp.connection.TcpNetClientConnectionFactory;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;
import org.springframework.scheduling.support.PeriodicTrigger;

import com.example.demo.controller.IntegrationController;

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
	/*
	 * @Autowired MainConfigClass configClass;
	 */
   @Autowired
   IntegrationController cont;
   
   @Autowired
	private IntegrationFlowContext flowContext;
   
   
	private final LinkedHashMap<String, MessageChannel> subFlows = new LinkedHashMap<String, MessageChannel>();


	@Bean(name = "replyChannel")
	public PollableChannel replyChannel() {
		return new QueueChannel();
	}

	@Bean(name = "sendChannel")
	public MessageChannel sendChannel() {
		return new DirectChannel();
	}

	/*
	 * @Bean public TcpNetClientConnectionFactory connectionFactory() {
	 * TcpNetClientConnectionFactory connectionFactory = new
	 * TcpNetClientConnectionFactory("localhost", 1901);
	 * 
	 * 
	 * connectionFactory.setDeserializer(new CommandResultDeserializer());
	 * //connectionFactory.setDeserializer(new DefaultDeserializer());
	 * connectionFactory.setSerializer(new DefaultSerializer());
	 * 
	 * connectionFactory.setSingleUse(false);
	 * connectionFactory.setSoTimeout(10000000);
	 * 
	 * MapMessageConverter mc = new MapMessageConverter();
	 * mc.setHeaderNames("CORRELATION_ID");
	 * 
	 * connectionFactory.setMapper(new MessageConvertingTcpMessageMapper(mc));
	 * 
	 * 
	 * return connectionFactory; }
	 */
	@Bean
	@ServiceActivator(inputChannel = "sendChannel")
	public TcpOutboundGateway tcpOutboundGateway() throws InterruptedException {
		TcpOutboundGateway tcpOutboundGateway = new TcpOutboundGateway();
		
		//To-Do need to do this dynamically
		TcpNetClientConnectionFactory conn =  MainConfigClass.ctxMap.get(1901);
		tcpOutboundGateway.setConnectionFactory(conn);
		tcpOutboundGateway.setReplyChannel(this.replyChannel());
		tcpOutboundGateway.setRequiresReply(true);
		return tcpOutboundGateway;
	}

	
	/*
	 * private MessageChannel createNewSubflow(Message<?> message) { String host =
	 * (String) message.getHeaders().get("host"); Integer port = (Integer)
	 * message.getHeaders().get("port"); //Assert.state(host != null && port !=
	 * null, "host and/or port header missing"); String hostPort = host + port;
	 * 
	 * 
	 * TcpNetClientConnectionFactory conn = MainConfigClass.ctxMap.get(1901);
	 * 
	 * TcpOutboundGateway handler = new TcpOutboundGateway();
	 * handler.setConnectionFactory(conn); IntegrationFlow flow = f ->
	 * f.handle(handler); IntegrationFlowContext.IntegrationFlowRegistration
	 * flowRegistration = this.flowContext.registration(flow) .addBean(conn)
	 * .id(hostPort + ".flow") .register(); MessageChannel inputChannel =
	 * flowRegistration.getInputChannel(); this.subFlows.put(hostPort,
	 * inputChannel); return inputChannel; }
	 */
	@Bean(name = PollerMetadata.DEFAULT_POLLER)
	public PollerMetadata defaultPoller() {

		PollerMetadata pollerMetadata = new PollerMetadata(); //
		pollerMetadata.setTrigger(new PeriodicTrigger(10));
		return pollerMetadata;
	}

}