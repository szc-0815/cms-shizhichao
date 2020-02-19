package com.shizhichao.redis;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XiaoFei {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		new ClassPathXmlApplicationContext("classpath:spring-kafka-consumer.xml","classpath:spring-beans.xml");
		
		System.out.println("开始");
	}
	
}
