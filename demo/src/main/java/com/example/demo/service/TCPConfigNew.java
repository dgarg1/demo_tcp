/*
 * package com.example.demo.service;
 * 
 * import org.springframework.integration.channel.DirectChannel; import
 * org.springframework.integration.channel.QueueChannel; import
 * org.springframework.integration.handler.ServiceActivatingHandler; import
 * org.springframework.messaging.Message; import
 * org.springframework.messaging.MessageHandler; import
 * org.springframework.messaging.MessagingException; import
 * org.springframework.messaging.PollableChannel; import
 * org.springframework.messaging.support.GenericMessage;
 * 
 * public class TCPConfigNew {
 * 
 * void do() { //register an input channel DirectChannel inputChannel = new
 * DirectChannel(); //register a service-activator message handler
 * ServiceActivatingHandler serviceActivator = new ServiceActivatingHandler(new
 * TCPConfig(),"tcpOutboundGateway");
 * 
 * //set service activator as a handler for input channel
 * inputChannel.subscribe(serviceActivator); //register an output channel
 * PollableChannel outputChannel = new QueueChannel(); //set the service
 * activator's output channel to outputChannel
 * serviceActivator.setOutputChannel(outputChannel); //register a message
 * handler for output channel MessageHandler handler = new MessageHandler(){
 * 
 * @Override public void handleMessage(Message<?> message) throws
 * MessagingException{
 * System.out.println("MessageChannel.handleMessage ["+message.getPayload()+"]")
 * ; } }; //subscribe message handler to output channel //this is equivalent to
 * EventDrivenConsumer consumer = new EventDrivenConsumer(outputChannel,
 * handler); //and then doing consumer.start(); then inputChannel.send(); then
 * consumer.stop(); outputChannel.subscribe(handler);
 * 
 * // we are now ready to send the message on input channel
 * inputChannel.send(new GenericMessage<String>("World")); } }
 */