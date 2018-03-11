package com.filelife.myrabbitReceiver;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class RabbitmqReceiver implements AmqpReceiver {
	private final static String QUEUE_NAME = "helloQueue";

	public Channel createQueue() {
		ConnectionFactory factory = new ConnectionFactory();
		Channel channel = null;
		factory.setHost("localhost");
		Connection connection;
		try {
			connection = factory.newConnection();

			channel = connection.createChannel();

			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
			return channel;
		} catch (IOException e) {
			System.err.println("Cannot create channel and queue");
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
		return channel;
	}

	public Consumer getConsumer(Channel channel) {
		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println(" [x] Received '" + message + "'");
				
			}
		};
		return consumer;
	}

}
