package com.shizhichao.cms.service.impl;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shizhichao.cms.common.JsonResult;
import com.shizhichao.cms.dao.ShouDao;
import com.shizhichao.cms.pojo.Comment;
import com.shizhichao.cms.pojo.Shou;
import com.shizhichao.cms.service.ShouService;
import com.shizhichao.utils.StringUtil;
@Service
public class ShouServiceImpl implements ShouService {

	@Autowired
	private ShouDao shouDao;
	
	
	@Override
	public boolean add(Shou shou) {
		// TODO Auto-generated method stub
		shou.setCreated(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		boolean url = StringUtil.isUrl(shou.getUrl());
		if(url == false) {
			System.out.println("网址错误");
			return false;
		}
		
		
		return shouDao.add(shou)>0;
	}


	@Override
	public PageInfo<Shou> getPageInfoByUid(Shou shou, Integer pageNum) {
		PageHelper.startPage(pageNum, 2);
		List<Shou> commentList = shouDao.selectByUserId(shou);
		return new PageInfo<>(commentList);
	}


	@Override
	public PageInfo<Shou> getPageInfo(int pageNum, Integer articleId) {
		PageHelper.startPage(pageNum, 2);
		Shou shou = new Shou();
		shou.setArticleid(articleId);
		List<Shou> commentList = shouDao.select(shou);
		return new PageInfo<>(commentList);
	}


	@Override
	public List<Shou> list() {
		// TODO Auto-generated method stub
		return shouDao.list();
	}


	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return shouDao.delete(id);
	}


	@Override
	public List<Shou> list1(Integer articleId) {
		// TODO Auto-generated method stub
		return shouDao.list1(articleId);
	}

}
