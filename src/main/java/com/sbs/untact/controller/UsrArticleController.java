package com.sbs.untact.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.untact.dto.Article;

@Controller
public class UsrArticleController {
	
	private List<Article> articles;
	
	private int ArticleLastId;
	
	
	public UsrArticleController() {
		
		ArticleLastId = 0;
		
		articles = new ArrayList<>();
		
		articles.add(new Article(++ArticleLastId, "2021-01-25 12:12:12", "제목1", "내용1"));
		articles.add(new Article(++ArticleLastId, "2021-01-25 12:12:12", "제목2", "내용2"));
			
	}
	

	@RequestMapping("/usr/board/detail")
	@ResponseBody
	public Article showDetail(int id) {
		
		return articles.get(id - 1); 
		
	}
	@RequestMapping("/usr/board/list")
	@ResponseBody
	public List<Article> showList() {
				
		return articles; 
		
	}
	
	
	@RequestMapping("/usr/board/doAdd")
	@ResponseBody
	public Map<String, Object> showdoAdd(String regDate, String title, String body) {
				
		articles.add(new Article(++ArticleLastId, regDate, title, body));
		
		Map<String, Object> rs = new HashMap<>();
		
		rs.put("resultCode", "S-1");
		rs.put("msg", "성공");
		rs.put("id", ArticleLastId);
		
		return rs; 
		
	}
	

}
