package com.shizhichao.cms.listener;

import javax.annotation.Resource;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.shizhichao.cms.pojo.Article;
import com.shizhichao.cms.service.ArticleService;

import kafka.utils.Json;
@Component("kafkaConsumerListener")
public class KafkaConsumerListener implements MessageListener<String, String> {

	@Resource
	private ArticleService articleService;
	@Resource
	private RedisTemplate<String, Article> redisTemplate;
	
	@Override
	public void onMessage(ConsumerRecord<String, String> data) {
		// TODO Auto-generated method stub
		
		String value = data.value();
		

		if(data.key().equals("add1")) {
			articleService.addHot(Integer.parseInt(data.value()));
			
			System.out.println("浏览量增加");
			
		}else if(data.key().equals("shuju")){
			Gson gson = new Gson();
			Article fromJson = gson.fromJson(value, Article.class);
			
			articleService.kafkaSave(fromJson);
		}else if(data.key().equals("shizhichao_redis")) {
			ValueOperations<String, Article> opsForValue = redisTemplate.opsForValue();
			
			Article article = opsForValue.get(value);
			
			int i = articleService.kafkaSave(article);
			
			if(i>0) {
				redisTemplate.delete(value);
				System.out.println( data.value() + "已导入完毕");
			}
			
			
			
		}
				

		
		
	}

}
