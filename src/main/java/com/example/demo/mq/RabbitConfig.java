package com.example.demo.mq;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.support.RetryTemplate;

/**
 * Created by lixiang38 on 2018/7/27.
 */
@Configuration
public class RabbitConfig {

	@Autowired
	public ConfirmCallBackListener confirmCallback;

	@Autowired
	public ReturnCallBackListener returnCallBack;

	/**
	 * mandatory必须设置true,return callback才生效
	 */
	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMandatory(true);
		rabbitTemplate.setConfirmCallback(confirmCallback);
		rabbitTemplate.setReturnCallback(returnCallBack);

//		RetryTemplate retryTemplate = new RetryTemplate();
//		ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
//		backOffPolicy.setInitialInterval(500);
//		backOffPolicy.setMultiplier(10.0);
//		backOffPolicy.setMaxInterval(10000);
//		retryTemplate.setBackOffPolicy(backOffPolicy);
//		rabbitTemplate.setRetryTemplate(retryTemplate);

		return rabbitTemplate;
	}
}
