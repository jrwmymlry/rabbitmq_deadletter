package com.example.demo.mq;

import com.example.demo.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by lixiang38 on 2018/7/27.
 */
@Component
@RabbitListener(queues = "DL_QUEUE")
public class DeadMsgReceiver {
	private static final Logger LOGGER = LoggerFactory.getLogger(DeadMsgReceiver.class);
	@RabbitHandler
	public void process(String workOrderData) {
		LOGGER.info("死信队列消费者接收到："+workOrderData);
	}
}
