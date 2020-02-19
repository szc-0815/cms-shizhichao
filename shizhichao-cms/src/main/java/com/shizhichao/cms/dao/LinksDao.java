package com.shizhichao.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shizhichao.cms.pojo.Links;

public interface LinksDao {

	List<Links> select(@Param("links")Links links);

	Links selectById(@Param("id")Integer id);
	
	int update(@Param("links")Links links);

	int insert(@Param("links")Links links);

	boolean deleteByIds(@Param("ids")String ids);

}
