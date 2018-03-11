package com.filelife.myrabbitReceiver;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;

public interface AmqpReceiver {
	public Channel createQueue ();
	public Consumer getConsumer (Channel channel);
}
