package com.example.demo.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by lixiang38 on 2018/7/27.
 */
@Component
public class HelloSender {

	@Autowired
	@Qualifier("rabbitTemplate")
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private CorrProcessor corrProcessor;

	private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	private DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public void send() {
		String sendMsg = "hello1 " + df.format(LocalDateTime.now());
		LOGGER.info("Sender: " + sendMsg);
		rabbitTemplate.convertAndSend("directExchange","bizRoutingKey", sendMsg);
	}

	public void send(String exchange,String routingKey,String sendMsg){
		sendMsg = sendMsg + df.format(LocalDateTime.now());
		LOGGER.info("Sender: " + sendMsg);
		rabbitTemplate.convertAndSend(exchange,routingKey,sendMsg, new CorrelationData(sendMsg.substring(4)));
	}
}
