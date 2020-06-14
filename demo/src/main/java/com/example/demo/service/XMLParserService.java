package com.example.demo.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
@Component
public final class XMLParserService {

	public static List<TCPConfigPOJO> TCPConfig = new ArrayList<TCPConfigPOJO>();

	
	public List<TCPConfigPOJO> getTCPConfig() {
		return TCPConfig;
	}


	public void setTCPConfig(List<TCPConfigPOJO> tCPConfig) {
		TCPConfig = tCPConfig;
	}


	@PostConstruct
	public void parse() throws ParserConfigurationException, SAXException, IOException {
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
	File file = new File("D:\\sample.xml"); 
   // DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    
    StringBuilder xmlStringBuilder = new StringBuilder();
    ByteArrayInputStream input = new ByteArrayInputStream(
    xmlStringBuilder.toString().getBytes("UTF-8"));
    Document doc = builder.parse(file);
    Element root = doc.getDocumentElement();
    doc.getDocumentElement().normalize();
   doc.getDocumentElement().getNodeName();
   doc.getChildNodes();
   NodeList nList = doc.getElementsByTagName("TcpConfiguration");
   TCPConfigurationPOJO config = new TCPConfigurationPOJO();
   for(int i=0; i<nList.getLength();i++) {
	   
	  Node node  = nList.item(i);
	   if(node.getNodeType() == Node.ELEMENT_NODE) {
		   TCPConfigPOJO config1= new TCPConfigPOJO();
           Element elem = (Element) node;
           String host = elem.getElementsByTagName("host").item(0).getTextContent();
           String port = elem.getElementsByTagName("port").item(0).getTextContent();
           config1.setHost(host);
           config1.setPort(Integer.parseInt(port));
           TCPConfig.add(config1);
	   }
	   System.out.println(nList.getLength());
   }
    NamedNodeMap root1 = root.getAttributes();
    root.getChildNodes();


    
    
    
	}
    
}
