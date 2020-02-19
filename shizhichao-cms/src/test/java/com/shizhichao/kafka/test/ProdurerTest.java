package com.shizhichao.kafka.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.shizhichao.cms.pojo.Article;
import com.shizhichao.cms.pojo.Category;
import com.shizhichao.cms.pojo.Channel;
import com.shizhichao.cms.service.ArticleService;
import com.shizhichao.utils.DateUtil;
import com.shizhichao.utils.FileUtil;
import com.shizhichao.utils.RandomUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-beans.xml")
public class ProdurerTest {

	@Resource
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Resource
	private ArticleService articleService;
	
//	@Test
//	public void sendMsgTest() throws FileNotFoundException {
//		
//		File dir = new File("D:\\1709DJsoup");
//		
//		//循环遍历其中的文件
//		File[] listFiles = dir.listFiles();
//		
//		for (File file : listFiles) {
//			Article article = new Article();
//			
//			//文本内容
//			String concent = FileUtil.readTextFileByLine(file.getPath());
//			article.setContent(concent);
//			
//			//文章名
//			String replace = file.getName().replace(".txt", "");
//			article.setTitle(replace);
//			
//			//截取140个字
//			String fileName=concent;
//			if(fileName.length()>140) {
//				String substring = fileName.substring(0, 140);
//			}
//			
//			//点击量
//			int random = RandomUtil.random(0, 1000);
//			article.setHits(random);
//			
//			//热门
//			int random2 = RandomUtil.random(0, 100);
//			article.setHot(random2);
//			
//			//频道
//			List<Channel> channelList = articleService.getChannelList();
//			int random3 = RandomUtil.random(0, channelList.size()-1);
//			Channel channel = channelList.get(random3);
//			article.setChannelId(channel.getId());
//			
//			//栏目
//			List<Category> channelList1 = articleService.getCateListByChannelId(channel.getId());
//			int random4 = RandomUtil.random(0, channelList1.size()-1);
//			Category categroy = channelList1.get(random4);
//			article.setCategoryId(categroy.getId());
//			
//			//时间
//			Date randomDate = DateUtil.randomDate("2019-01-01", "2020-02-13");
//			article.setCreated(randomDate);
//			
//			article.setUpdated(randomDate);
//			
//			article.setStatus(0);
//			article.setDeleted(0);
//			article.setCommentcnt(0);
//			
//			System.out.println(article);
//			
//			String jsonString = JSON.toJSONString(article);
//			kafkaTemplate.sendDefault("shuju", jsonString);
//			
//			
////			Gson gson = new Gson();
////			kafkaTemplate.sendDefault(gson.toJson(article));
//			
//		}
//		
//	}
	
//	@Test
//	public void show() throws FileNotFoundException {
//		
//		File file = new File("D:\\1709DJsoup");
//		
//		File[] listFiles = file.listFiles();
//		
//		for (File readFile : listFiles) {
//			
//			Article article = new Article();
//			//文章名字
//			String title = readFile.getName().replace(".txt", "");
//			article.setTitle(title);
//			//文本内容
//			String content = FileUtil.readTextFileByLine(readFile.getPath());
//			article.setContent(content);
//			//截取前140个字
//			if(content.length()>140) {
//				CharSequence subSequence = content.subSequence(0, 140);
//				System.out.println(subSequence);
//			}
//			//随机点击，热门，频道
//			int hits = RandomUtil.random(0, 1000);
//			article.setHits(hits);
//			
//			int hot = RandomUtil.random(0, 100);
//			article.setHot(hot);
//			
//			
//			List<Channel> channelList = articleService.getChannelList();
//			int channel = RandomUtil.random(0, channelList.size()-1);
//			Channel channel2 = channelList.get(channel);
//			article.setChannelId(channel2.getId());
//			
//			//栏目
//			List<Category> channelList1 = articleService.getCateListByChannelId(channel2.getId());
//			int random4 = RandomUtil.random(0, channelList1.size()-1);
//			Category categroy = channelList1.get(random4);
//			article.setCategoryId(categroy.getId());
//			
//			//日期
//			Date date = DateUtil.randomDate("2019-01-01", "2020-01-14");
//			article.setCreated(date);
//			
//			article.setUserId(199);
//			article.setStatus(1);
//			article.setDeleted(1);
//			article.setCommentcnt(0);
//			
//			System.out.println(article);
//			
//			
//			
////			String jsonString = JSON.toJSONString(article);
////			kafkaTemplate.sendDefault("shuju",jsonString);
//			
//			Gson gson = new Gson();
//			kafkaTemplate.sendDefault(gson.toJson(article));
			
			
//		}
		
//		
//	}
//	
//	
	
	
	
	
	
	
	
	
	
	
	
}
