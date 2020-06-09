/*
 * package com.example.demo.service;
 * 
 * import java.util.Collections; import java.util.Map; import
 * java.util.concurrent.BlockingQueue; import
 * java.util.concurrent.LinkedBlockingQueue;
 * 
 * import javax.annotation.PostConstruct;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.integration.channel.DirectChannel; import
 * org.springframework.messaging.PollableChannel;
 * 
 * public class Demo {
 * 
 * @Autowired private DirectChannel sender;
 * 
 * @Autowired private PollableChannel receiver;
 * 
 * private BlockingQueue<Request> requestPool = new LinkedBlockingQueue<>();
 * 
 * private Map<String, Response> responsePool = Collections.synchronizedMap(new
 * HashMap<>());
 * 
 * @PostConstruct private void init() { new Receiver().start(); new
 * Sender().start(); }
 * 
 * 
 * It can be called as many as necessary without hanging for a response
 * 
 * public void send(Request req) { requestPool.add(req); }
 * 
 * 
 * Check for a response until a socket timout
 * 
 * public Response receive(String key) { Response res = responsePool.get(key);
 * if (res != null) { responsePool.remove(key); } return res; }
 * 
 * private class Receiver extends Thread {
 * 
 * @Override public void run() { while (true) { try { tcpReceive();
 * Thread.sleep(250); } catch (InterruptedException e) { } } } private void
 * tcpReceive() { Response res = (Message<Response>) receiver.receive(); if (res
 * != null) { responsePool.put(res.getUID(), res); } } }
 * 
 * private class Sender extends Thread {
 * 
 * @Override public void run() { while (true) { try { tcpSend();
 * Thread.sleep(250); } catch (InterruptedException e) { } } } private void
 * tcpSend() { Request req = requestPool.poll(125, TimeUnit.MILLISECONDS); if
 * (req != null) { sender.send(MessageBuilder.withPayload(req).build()); } } } }
 * 
 * 
 */