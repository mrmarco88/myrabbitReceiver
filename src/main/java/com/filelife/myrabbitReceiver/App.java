package com.filelife.myrabbitReceiver;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        AmqpReceiver rmqr = new RabbitmqReceiver();
        Channel myChannel = rmqr.createQueue();
        Consumer myConsumer =rmqr.getConsumer(myChannel);
        
        try {
			myChannel.basicConsume("helloQueue", true, myConsumer);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
