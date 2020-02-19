package com.shizhichao.cms.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.shizhichao.cms.pojo.Shou;

public interface ShouService {

	boolean add(Shou shou);

	PageInfo<Shou> getPageInfoByUid(Shou shou, Integer pageNum);

	PageInfo<Shou> getPageInfo(int pageNum, Integer articleId);

	List<Shou> list();

	boolean delete(int id);

	List<Shou> list1(Integer articleId);

}
