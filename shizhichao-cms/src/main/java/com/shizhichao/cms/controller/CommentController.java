package com.shizhichao.cms.controller;

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
import com.shizhichao.cms.pojo.User;
import com.shizhichao.cms.service.CommentService;

@Controller
@RequestMapping("/comment/")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@RequestMapping(value = "add",method = RequestMethod.POST)
	public @ResponseBody JsonResult add(Comment comment,HttpSession session) {
		//获取用户的对象
		User userInfo = (User) session.getAttribute(CmsConstant.UserSessionKey);
		if(userInfo==null) {
			return JsonResult.fail(CmsConstant.unLoginErrorCode, "用户未登录");
		}
		comment.setUserid(userInfo.getId());
		
		boolean result = commentService.add(comment);
		if(result) {
			return JsonResult.sucess();
		}
		return JsonResult.fail(10000, "未知错误");
	}
	
	
	@RequestMapping(value = "list",method = RequestMethod.GET)
    public String getPageInfo(@RequestParam(value = "articleId")Integer articleId,
    		@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,Model model) {
		
		PageInfo<Comment> pageInfo = commentService.getPageInfo(pageNum, articleId);
		
		model.addAttribute("pageInfo", pageInfo);
		return "comment/list";
	}	
	@ResponseBody
	@RequestMapping(value = "del",method = RequestMethod.GET)
	public JsonResult delComment(Integer id) {
		
		boolean flag = commentService.delete(id);
		if(flag) {
			return JsonResult.sucess();
		}
		return JsonResult.fail(500, "未知错误");
	}
	
}
