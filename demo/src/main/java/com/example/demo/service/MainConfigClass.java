package com.example.demo.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.core.serializer.DefaultSerializer;
import org.springframework.integration.ip.tcp.TcpOutboundGateway;
import org.springframework.integration.ip.tcp.connection.TcpNetClientConnectionFactory;
import org.springframework.stereotype.Component;

@Component
public class MainConfigClass implements ApplicationContextAware{

	@Autowired
	private XMLParserService tcpConfiguration;

	public static final Map<Integer,TcpNetClientConnectionFactory> ctxMap = new HashMap<>();

	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		
		System.out.println("*************************************************"+tcpConfiguration.getTCPConfig().size());
		
		for(TCPConfigPOJO config : tcpConfiguration.getTCPConfig()) {
	                  getConnection(config);
		}
	}

	private void getConnection(TCPConfigPOJO config) {
		TcpNetClientConnectionFactory connFactory = connectionFactory(config);
				
	
		ctxMap.put(config.getPort(), connFactory);
	
	}

	
	public TcpNetClientConnectionFactory connectionFactory(TCPConfigPOJO config) {
		TcpNetClientConnectionFactory connectionFactory = new TcpNetClientConnectionFactory(config.getHost(), config.getPort());
		
		
		connectionFactory.setDeserializer(new CommandResultDeserializer());
		//connectionFactory.setDeserializer(new DefaultDeserializer());
		connectionFactory.setSerializer(new DefaultSerializer());
	
		connectionFactory.setSingleUse(false);
		connectionFactory.setSoTimeout(10000000);
		/*
		 * MapMessageConverter mc = new MapMessageConverter();
		 * mc.setHeaderNames("CORRELATION_ID");
		 * 
		 * connectionFactory.setMapper(new MessageConvertingTcpMessageMapper(mc));
		 * 
		 */		 		
		return connectionFactory;
	}
	
	
}
