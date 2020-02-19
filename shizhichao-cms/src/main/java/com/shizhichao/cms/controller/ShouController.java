package com.shizhichao.cms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.shizhichao.cms.common.CmsConstant;
import com.shizhichao.cms.common.JsonResult;
import com.shizhichao.cms.pojo.Comment;
import com.shizhichao.cms.pojo.Shou;
import com.shizhichao.cms.pojo.User;
import com.shizhichao.cms.service.ShouService;

@Controller
@RequestMapping("/shou/")
public class ShouController {

	@Autowired
	private ShouService shouService;
	
	@RequestMapping(value = "add",method = RequestMethod.POST)
	public @ResponseBody JsonResult add(Shou shou,HttpSession session) {
		
		
		User user = (User)session.getAttribute(CmsConstant.UserSessionKey);
		if(user!=null) {
			return JsonResult.fail(CmsConstant.unLoginErrorCode, "用户未登录");
		}
		shou.setUser_id(user.getId());
		
		boolean jieguo=shouService.add(shou);
		if(jieguo) {
			return JsonResult.sucess();
		}
		
		return JsonResult.fail(1000, "未知错误");
		
	}
	
	@RequestMapping(value = "list",method = RequestMethod.GET)
    public String getPageInfo(@RequestParam(value = "articleId")Integer articleId,
    		@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,Model model) {
		
		PageInfo<Shou> pageInfo = shouService.getPageInfo(pageNum, articleId);
		
		List<Shou> list=shouService.list1(articleId);
		
		model.addAttribute("list", list);
		model.addAttribute("pageInfo", pageInfo);
		return "shou/list";
	}	
	@ResponseBody
	@RequestMapping(value = "del",method = RequestMethod.GET)
	public JsonResult delComment(Integer id) {
		
		boolean flag = shouService.delete(id);
		if(flag) {
			return JsonResult.sucess();
		}
		return JsonResult.fail(500, "未知错误");
	}
	
}
