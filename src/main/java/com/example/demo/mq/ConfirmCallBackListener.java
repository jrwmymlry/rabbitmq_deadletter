package com.example.demo.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Service;

/**
 * Created by lixiang38 on 2018/8/1.
 */
@Service("confirmCallBackListener")
public class ConfirmCallBackListener implements RabbitTemplate.ConfirmCallback{

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	//到达broker后调用，如果找到正确的exchange，则ack=true，否则false
	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		LOGGER.info("confirm--:correlationData:"+correlationData+",ack:"+ack+",cause:"+cause);
	}
}
