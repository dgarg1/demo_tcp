package com.example.demo.service;

import org.springframework.stereotype.Component;

@Component
public class TCPConfigPOJO {

	
	String host;
	Integer port;
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	
	
}
