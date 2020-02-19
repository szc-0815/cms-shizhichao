package com.shizhichao.cms.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.shizhichao.cms.dao.ArticleRepository;
import com.shizhichao.cms.dao.UserDao;
import com.shizhichao.cms.pojo.Article;
import com.shizhichao.cms.pojo.Category;
import com.shizhichao.cms.pojo.Channel;
import com.shizhichao.cms.pojo.Links;
import com.shizhichao.cms.pojo.Slide;
import com.shizhichao.cms.pojo.User;
import com.shizhichao.cms.service.ArticleService;
import com.shizhichao.cms.service.LinksService;
import com.shizhichao.cms.service.RedisArticleService;
import com.shizhichao.cms.service.SlideService;
import com.shizhichao.cms.service.UserService;
import com.shizhichao.cms.util.HLUtils;

import io.netty.handler.codec.http.HttpRequest;


@Controller
public class IndexController {

	@Autowired
	private ArticleService articleService;
	@Autowired
	private UserService userService;
	@Autowired
	private SlideService slideService;
	@Autowired
	private LinksService linksService;
    @Autowired
	private ArticleRepository articleRepository;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private RedisTemplate<String, Article> redisTemplate;
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Autowired
    private RedisArticleService redisArticleService;
    @Autowired
    private UserDao userDao;
    
    
    @RequestMapping(value = "/")
	public String index(Model model,String title) {
		return index(1, model,title);
	}

	/*
	 * @RequestMapping(value="/hot/{pageNum}.html") public String
	 * index(@PathVariable Integer pageNum,Model model) { //频道 List<Channel>
	 * channelList = articleService.getChannelList();
	 * model.addAttribute("channelList", channelList); //轮播图 List<Slide> slideList =
	 * slideService.getAll(); model.addAttribute("slideList", slideList);
	 *//** 最新文章 **//*
					 * List<Article> newArticleList = articleService.getNewList(6);
					 * model.addAttribute("newArticleList", newArticleList); //热点文章
					 * if(pageNum==null) { pageNum=1; } //获取热点文章 PageInfo<Article> pageInfo =
					 * articleService.getHotList(pageNum); model.addAttribute("pageInfo", pageInfo);
					 * //获取收藏链接 List<Links> linksList = linksService.select();
					 * model.addAttribute("linksList", linksList); return "index"; }
					 */

	@RequestMapping(value = "/hot/{pageNum}.html")
	public String index(@PathVariable Integer pageNum, Model model,String title) {
		ValueOperations<String, Article> opsForValue = redisTemplate.opsForValue();
		// 频道
		List<Channel> channelList = articleService.getChannelList();
		model.addAttribute("channelList", channelList);
		// 轮播图
		List<Slide> slideList = slideService.getAll();
		model.addAttribute("slideList", slideList);
		/** 最新文章 **/
		List<Article> newArticleList = articleService.getNewList(6);
		model.addAttribute("newArticleList", newArticleList);
		// 热点文章
		if (pageNum == null) {
			pageNum = 1;
		}

		
		
		
		
		
		// 获取热点文章
		if(title !=null && title !="") {
			long start = System.currentTimeMillis();
			PageInfo<Article> pageInfo = (PageInfo<Article>) HLUtils.findByHighLight(elasticsearchTemplate, Article.class, pageNum, 5, new String[] {"title"}, "id", title);
			long end = System.currentTimeMillis();
			
			
			System.err.println("es索引库高亮查询"+(end-start)+"毫秒");
			pageInfo.setPageNum(pageNum);
			pageInfo.setPageSize(5);
			pageInfo.setPrePage(pageNum-1);
			pageInfo.setNextPage(pageNum+1);
			int[] num= new int[pageInfo.getPages()];
			for (int i = 0; i < pageInfo.getPages(); i++) {
				num[i]=i+1;
				
			}
			pageInfo.setNavigatepageNums(num);
			model.addAttribute("pageInfo", pageInfo);
			model.addAttribute("title", title);
			
			
		}else {
			PageInfo<Article> pageInfo =  articleService.getHotList(pageNum);
			model.addAttribute("pageInfo", pageInfo);
		}
		
		
		
		
		
		// 获取收藏链接
		List<Links> linksList = linksService.select();
		model.addAttribute("linksList", linksList);
		return "index";
	}

	/**
	 * 
	 * @Title: channel @Description: 频道页 分类页 频道下的分类下的分页方法 @param @param
	 * channelId @param @param model @param @param cateId @param @param
	 * pageNo @param @return 设定文件 @return String 返回类型 @throws
	 */
	@RequestMapping("/{channelId}/{cateId}/{pageNo}.html")
	public String channel(@PathVariable Integer channelId, Model model, @PathVariable Integer cateId,
			@PathVariable Integer pageNo) {
		// 频道
		List<Channel> channelList = articleService.getChannelList();
		model.addAttribute("channelList", channelList);
		// 分类
		List<Category> cateList = articleService.getCateListByChannelId(channelId);
		model.addAttribute("cateList", cateList);
		// 根据频道id和分类id查询文章
		PageInfo<Article> pageInfo = articleService.getListByChannelIdAndCateId(channelId, cateId, pageNo);
		model.addAttribute("pageInfo", pageInfo);
		/** 最新文章 **/
		List<Article> newArticleList = articleService.getNewList(6);
		model.addAttribute("newArticleList", newArticleList);

		return "index";
	}

	// 文章详情页查询
	
	@RequestMapping("article/{id}.html")
	public String articleDetail(@PathVariable Integer id, Model model,HttpServletRequest request) {
		
		ValueOperations<String, Article> opsForValue = redisTemplate.opsForValue();
		
		// 查询文章
		Article article = articleService.getById(id);
		model.addAttribute("article", article);
		
		//详情页的浏览量增加
//		articleService.addHot(article.getId());
//		kafkaTemplate.sendDefault("add1", String.valueOf(id));
		
		// 查询用户
		User user = userService.getById(article.getUserId());

		model.addAttribute("user", user);
		// 查询相关文章
		List<Article> articleList = articleService.getListByChannelId(article.getChannelId(), id, 10);
		model.addAttribute("articleList", articleList);
		
		
		
		//redis 和增加浏览量
		
		String remoteAddr = request.getRemoteAddr();
		String key="Hits_"+article.getId()+"_"+remoteAddr;
//		opsForValue.set(key, article);
		
		
		Boolean hasKey = redisTemplate.hasKey(key);
		System.out.println(hasKey);
		if(!hasKey) {
			opsForValue.set(key, null,5,TimeUnit.SECONDS);
			
			//spring线程池
			threadPoolTaskExecutor.execute(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					
					articleService.addHot(article.getId());
					
					
				}
			});
			
			
		}
		
		
		
		
		return "article/detail";
	}

}
