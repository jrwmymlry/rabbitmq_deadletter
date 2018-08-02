package com.example.demo.mq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lixiang38 on 2018/7/27.
 */
@Configuration
public class RabbitExchangeConfig {

	/**
	 * 业务队列交换机
	 * @return
	 */
	@Bean("directExchange")
	Exchange directExchange() {
		DirectExchange directExchange = new DirectExchange("directExchange", true, false);
		return directExchange;
	}

	/**
	 * 死信交换机
	 * @return
	 */
	@Bean("deadLetterExchange")
	public Exchange deadLetterExchange() {
		return ExchangeBuilder.directExchange("DL_EXCHANGE").durable(true).build();
	}

	/**
	 * 绑定了死信队列的业务队列
	 *
	 * @return
	 */
	@Bean("biznisQueue")
	Queue biznisQueue() {
		/**
		 * durable="true" 持久化 rabbitmq重启的时候不需要创建新的队列
		 * auto-delete 自动删除，如果该队列没有任何订阅的消费者的话，该队列会被自动删除。这种队列适用于临时队列
		 * exclusive：排他队列，如果一个队列被声明为排他队列，该队列仅对首次申明它的连接可见，并在连接断开时自动删除
		 */
		Map<String, Object> args = new HashMap<>(2);
//       x-dead-letter-exchange    声明  死信交换机
		args.put("x-dead-letter-exchange", "DL_EXCHANGE");
//       x-dead-letter-routing-key    声明 死信路由键
		args.put("x-dead-letter-routing-key", "DL_KEY");
		return QueueBuilder.durable("biznisQueue").withArguments(args).build();
	}

	/**
	 * 死信队列
	 * @return
	 */
	@Bean("deadLetterQueue")
	public Queue deadLetterQueue() {
		return new Queue("DL_QUEUE");
	}

	/**
	 * 业务绑定交换机和routingkey
	 * @return
	 */
	@Bean
	public Binding exchangeBindingBiz() {
		return BindingBuilder.bind(biznisQueue()).to(directExchange()).with("bizRoutingKey").noargs();
	}

	@Bean
	public Binding deadLetterBindding(){
		return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange()).with("DL_KEY").noargs();
	}
}
