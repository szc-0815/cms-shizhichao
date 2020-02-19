package com.shizhichao.cms.dao;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.shizhichao.cms.pojo.Article;
import com.shizhichao.cms.pojo.Shou;

public interface ArticleRepository extends ElasticsearchRepository<Article, Integer>{

	List<Article> findByTitle(String title);

	void saveAll(List<Shou> list);
	
}
