package com.shizhichao.cms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.shizhichao.cms.pojo.Article;
@Service
public class RedisArticleService {

	
	@Autowired
	private RedisTemplate<String, Article> redisTemplate;
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	
	public Article save(Article article) {
		// TODO Auto-generated method stub
		ValueOperations<String, Article> opsForValue = redisTemplate.opsForValue();
		
		opsForValue.set(article.getTitle(), article);
		

		kafkaTemplate.sendDefault("shizhichao_redis", article.getTitle());
		
		
		return article;
		
		
	}
	
	
	

}
