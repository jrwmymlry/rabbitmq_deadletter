package com.example.demo;

import com.example.demo.mq.HelloSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqDeadletterApplicationTests {

	@Autowired
	private HelloSender helloSender;

	private static String exChange = "directExchange";

	private static String routingKey = "bizRoutingKey";
	@Test
	public void test1() throws InterruptedException{
		String message = "currentTime:"+System.currentTimeMillis();
		System.out.println("test1---message:"+message);
		//exchange,queue 都正确,confirm被回调, ack=true
		helloSender.send(exChange,routingKey,message);
		Thread.sleep(1000);
	}

	@Test
	public void test2() throws InterruptedException{
		String message = "currentTime:"+System.currentTimeMillis();
		System.out.println("test2---message:"+message);
		//exchange 错误,queue 正确,confirm被回调, ack=false
		//helloSender.send(exChange+"NO",routingKey,message);
		Thread.sleep(1000);
	}

	@Test
	public void test3() throws InterruptedException{
		String message = "currentTime:"+System.currentTimeMillis();
		System.out.println("test3---message:"+message);
		//exchange 正确,queue 错误 ,confirm被回调, ack=true; return被回调 replyText:NO_ROUTE
		//helloSender.send(exChange,"abc",message);
//        Thread.sleep(1000);
	}

	@Test
	public void test4() throws InterruptedException{
		String message = "currentTime:"+System.currentTimeMillis();
		System.out.println("test4---message:"+message);
		//exchange 错误,queue 错误,confirm被回调, ack=false
		//helloSender.send(exChange+"NO","abc",message);
		Thread.sleep(1000);
	}


}
