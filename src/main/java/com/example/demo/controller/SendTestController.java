package com.example.demo.controller;

import com.example.demo.mq.HelloSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lixiang38 on 2018/7/27.
 */
@RestController
public class SendTestController {

	@Autowired
	private HelloSender helloSender;
	private static String exChange = "directExchange";
	private static String routingKey = "bizRoutingKey";

	@GetMapping("/t1")
	public void send1(){
		helloSender.send(exChange,routingKey,"我是消息1 ");
	}

	@GetMapping("/t2")
	public void send2(){
		helloSender.send(exChange+"NO",routingKey,"我是消息2 ");
	}

	@GetMapping("/t3")
	public void send3(){
		helloSender.send(exChange,"abc","我是消息3 ");
	}
}
