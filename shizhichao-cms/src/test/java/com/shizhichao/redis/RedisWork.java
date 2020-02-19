package com.shizhichao.redis;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shizhichao.cms.dao.ArticleDao;
import com.shizhichao.cms.pojo.Article;
import com.shizhichao.cms.pojo.Channel;
import com.shizhichao.cms.service.ArticleService;
import com.shizhichao.cms.service.RedisArticleService;
import com.shizhichao.utils.DateUtil;
import com.shizhichao.utils.FileUtil;
import com.shizhichao.utils.RandomUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-beans.xml")
public class RedisWork {


	@Autowired
	private RedisArticleService redisArticleService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private ArticleDao articleDao;

	
	
	@Test
	public void Time() throws FileNotFoundException {
		
		
		
		File file = new File("D:\\1709DJsoup");
		File[] listFiles = file.listFiles();
		for (File file2 : listFiles) {
			
			Article article = new Article();
			
			String name = file2.getName().replace(".txt", "");
			article.setTitle(name);
			
			String readTextFileByLine = FileUtil.readTextFileByLine(file2.getPath());
			article.setContent(readTextFileByLine);
			
			if(readTextFileByLine.length()>140) {
				String substring = readTextFileByLine.substring(0, 140);
				
			}
			
			int hits = RandomUtil.random(0, 1000);
			article.setHits(hits);
			
			int hot = RandomUtil.random(0, 100);
			article.setHot(hot);
			
			
			List<Channel> channelList = articleService.getChannelList();
			int pindao = RandomUtil.random(0, channelList.size()-1);
			Channel channel = channelList.get(pindao);
			article.setChannelId(channel.getId());
			
			Date randomDate = DateUtil.randomDate("2019-01-01", "2020-02-17");
			article.setCreated(randomDate);
			
			article.setUserId(199);
			article.setDeleted(0);
			article.setStatus(1);
			article.setCategoryId(10);
			
			Article a=redisArticleService.save(article);
			
			System.out.println(a);
			
			
			
		}
		
		
		
		
	}
	
	
	
	
}
