//package com.example.demo.mq;
//
//import com.rabbitmq.client.Channel;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.core.AcknowledgeMode;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
//import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * Created by lixiang38 on 2018/8/1.
// */
//@Configuration
//public class ConsumerConfig {
//	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
//	@Autowired
//	public Queue biznisQueue;
//
//	@Bean
//	public SimpleMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
//		SimpleMessageListenerContainer messageListenerContainer = new SimpleMessageListenerContainer();
//		messageListenerContainer.setConnectionFactory(connectionFactory);
//		messageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
//		messageListenerContainer.setQueues(biznisQueue);
//		messageListenerContainer.setMessageListener(new ChannelAwareMessageListener() {
//			@Override
//			public void onMessage(Message message,
//			                      Channel channel) throws Exception {
//				boolean flag = false;
//				try {
//					doOnMessage(message);
//					channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//				} catch (Exception e) {
//					LOGGER.error("处理消息:" + message + "失败", e);
//					channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
//				}
//			}
//		});
//		return messageListenerContainer;
//	}
//
//	private void doOnMessage(Message message) {
//		LOGGER.info("消费者处理消息"+message);
//	}
//}
