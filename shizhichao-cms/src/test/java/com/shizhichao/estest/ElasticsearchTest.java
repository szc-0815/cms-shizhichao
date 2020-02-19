package com.shizhichao.estest;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shizhichao.cms.dao.ArticleDao;
import com.shizhichao.cms.dao.ArticleRepository;
import com.shizhichao.cms.pojo.Article;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-beans.xml")
public class ElasticsearchTest {

	@Autowired
	private ArticleDao articleDao;
	@Autowired
	private ElasticsearchTemplate  elasticsearchTemplate;
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Test
	public void test() {
		//查询最热文章
		List<Article> articleList = articleDao.select(new Article());
		articleRepository.saveAll(articleList);
		System.err.println("最热文章插入完毕");
		
	}
	
	@Test
	public void show() {
		List<Article> select = articleDao.select(new Article());
		articleRepository.saveAll(select);
		System.out.println("插入文章");
		List<Article> selectByHot = articleDao.selectByHot();
		articleRepository.saveAll(selectByHot);
		System.out.println("最热文章");
		
		
		
	}
	
	
	
}
