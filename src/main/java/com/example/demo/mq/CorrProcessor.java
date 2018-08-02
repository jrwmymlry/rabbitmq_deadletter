package com.example.demo.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.support.Correlation;
import org.springframework.stereotype.Component;

/**
 * Created by lixiang38 on 2018/8/1.
 */
@Component
public class CorrProcessor implements MessagePostProcessor {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Override
	public Message postProcessMessage(Message message) throws AmqpException {
		message.getMessageProperties().setCorrelationId(message.toString().substring(2));
		return message;
	}

	@Override
	public Message postProcessMessage(Message message, Correlation correlation) {

		LOGGER.info("message="+message+",correlation="+correlation);
		return postProcessMessage(message);
	}
}
