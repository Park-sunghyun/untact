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
	
	private int ArticlesLastId;
	private List<Article> articles;
	
	public UsrArticleController() {
		
		ArticlesLastId = 0;
		
		articles = new ArrayList<>();
		
		articles.add(new Article(++ArticlesLastId, "2021-01-26 12:12:12", "제목1", "내용1"));
		articles.add(new Article(++ArticlesLastId, "2021-01-26 12:12:12", "제목2", "내용2"));
		

		
	}
	
	
	

	@RequestMapping("/usr/article/detail")
	@ResponseBody
	public Article showDetail(int id) {


		return articles.get(id - 1);

	}

	@RequestMapping("/usr/article/list")
	@ResponseBody
	public List<Article> showList() {

		return articles;

	}
	
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public Map<String, Object> doAdd(String regDate, String title, String body) {
		
		articles.add(new Article(++ArticlesLastId, regDate, title, body));
		
		Map<String, Object> rs = new HashMap<>();
		rs.put("resultCode", "S-1");
		rs.put("msg", "성공");
		rs.put("id", ArticlesLastId);

		return rs;

	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public Map<String, Object> doDelete(int id) {
		
		boolean deleteArticleRs = deleteArticle(id);
		
		
		Map<String, Object> rs = new HashMap<>();
		
		if(deleteArticleRs ) {
			rs.put("resultCode", "S-1");
			rs.put("msg", "성공 하였습니다.");	
		}else {
			rs.put("resultCode", "F-1");
			rs.put("msg", "해당 게시물은 존재하지 않습니다.");	
		}
		
		rs.put("id", id);
		
		return rs;
		
		
	}


	private boolean deleteArticle(int id) {
		for(Article article : articles) {
			if(article.getId() == id) {
				articles.remove(article);
				return true;
			}
		}
		return false;
	}
	
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public Map<String, Object> doModify(int id, String title, String body) {
		Article selarticle = null;
		
		
		
		
		for(Article article : articles) {
			if( article.getId() == id) {
				selarticle = article;
				break;
			}
		}
		
		Map<String, Object> rs = new HashMap<>();
		
		if(selarticle == null ) {
			rs.put("resultCode", "F-1");
			rs.put("msg", String.format("%d 번 게시물은 존재하지 않습니다.", id));
			return rs;
		}
		
		
		selarticle.setTitle(title);
		selarticle.setBody(body);
		
		
		rs.put("resultCode", "S-1");
		rs.put("msg", String.format("%d 번 게시물이 수정되었습니다.", id));
		rs.put("id", id);
		
		return rs;
		
	}
	
	
	

}
