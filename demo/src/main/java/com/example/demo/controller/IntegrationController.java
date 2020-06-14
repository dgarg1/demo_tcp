package com.example.demo.controller;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import com.example.demo.service.IntegrationGatewayNew;
import com.example.demo.service.TCPConfigPOJO;
import com.example.demo.service.XMLParserService;

@RestController
@RequestMapping("/integration")
public class IntegrationController {

	
	@Autowired
	IntegrationGatewayNew ig;
	
	
	private static ExecutorService executor = Executors.newFixedThreadPool(3);
    public  static Integer payload;
	
	@GetMapping(value = "{name}")
	public String get(@PathVariable("name") String name) throws InterruptedException, ExecutionException, ParserConfigurationException, SAXException, IOException {

		UUID no = UUID.randomUUID();
		XMLParserService  s= new XMLParserService();
		/*
		 * TCPConfigPOJO pojo = new TCPConfigPOJO(); pojo.setPort(1901);
		 * pojo.setHost("fooo");
		 */
        Message<String> message = MessageBuilder.withPayload("foo"+no.toString()).setHeader("CORRELATION_ID", no.toString()).build();
		/*
		 * executor.execute(() -> {
		 * 
		 * });
		 */        String msg = ig.sendMessage(message);
        
     //  return (String) s.parse();
       return msg;

	}
}
