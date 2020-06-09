package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.IntegrationGatewayNew;

@RestController
@RequestMapping("/integration")
public class IntegrationController {

	
	@Autowired
	IntegrationGatewayNew ig;
	
	@GetMapping(value = "{name}")
	public String get(@PathVariable("name") String name) {
		return ig.sendMessage(name, "123");
	}
	
}
