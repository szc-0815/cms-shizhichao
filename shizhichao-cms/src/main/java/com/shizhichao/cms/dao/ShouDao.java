package com.shizhichao.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shizhichao.cms.pojo.Article;
import com.shizhichao.cms.pojo.Shou;

public interface ShouDao {

	int add(@Param("s")Shou shou);

	List<Shou> selectByUserId(@Param("shou")Shou shou);

	List<Shou> select(@Param("shou")Shou shou);

	List<Shou> list();

	boolean delete(@Param("id")int id);

	List<Shou> list1(@Param("id")Integer articleId);

	List<Shou> list2(Article article);

}
