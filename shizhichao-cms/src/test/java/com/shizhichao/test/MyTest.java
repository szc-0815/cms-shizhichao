package com.shizhichao.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageInfo;
import com.shizhichao.cms.dao.ArticleRepository;
import com.shizhichao.cms.dao.ShouDao;
import com.shizhichao.cms.pojo.Article;
import com.shizhichao.cms.pojo.Shou;
import com.shizhichao.cms.service.ShouService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-beans.xml")
public class MyTest {
	
//	public static void main(String[] args) {
//		String str ="^(http|https|ftp)\\://([a-zA-Z0-9\\.\\-]+(\\:[a-zA-Z0-9\\.&amp;%\\$\\-]+)*@)?((25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])|([a-zA-Z0-9\\-]+\\.)*[a-zA-Z0-9\\-]+\\.[a-zA-Z]{2,4})(\\:["
//				+ "0-9]+)?(/[^/][a-zA-Z0-9\\.\\,\\?\\'\\\\/\\+&amp;%\\$#\\=~_\\-@]*)*$";
//	//	String str2="^[a-z]";
//		boolean b = Pattern.matches(str, "https://127.0.0.1:8080/11222");
//		System.out.println(b);
//	}
	
	@Autowired
	private ShouService shouService;
	
	@Autowired
	private ShouDao shouDao;
	
	@Autowired
	private ElasticsearchTemplate  elasticsearchTemplate;
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Test
	public void test() {
		//查询最热文章
		List<Shou> list = shouDao.list();
		articleRepository.saveAll(list);
		System.err.println("文章插入完毕");
		
	}
	
	
	@Test
	public void shou() {
		Shou shou = new Shou(null, "123456", "http://wo.com", 199, null, 1012, null, null);
		
		
		shouService.add(shou);
		
	}
	@Test
	public void list() {
		List<Shou> list= shouService.list();
		for (Shou shou : list) {
			System.out.println(shou);
		}
		
	}
	@Test
	public void delete() {
		int id=9;
		boolean i= shouService.delete(id);
		if(i==true) {
			System.out.println("删除成功");
		}
		
	}
	
	
}
