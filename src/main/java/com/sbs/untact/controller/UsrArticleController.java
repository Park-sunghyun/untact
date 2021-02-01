package com.sbs.untact.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.untact.dto.Article;
import com.sbs.untact.dto.ResultData;
import com.sbs.untact.util.Util;

@Controller
public class UsrArticleController {
	
	private int ArticlesLastId;
	private List<Article> articles;
	
	public UsrArticleController() {
		
		ArticlesLastId = 0;
		
		articles = new ArrayList<>();
		
		articles.add(new Article(++ArticlesLastId, "2021-01-26 12:12:12","2021-01-26 12:12:12", "제목1", "내용1"));
		articles.add(new Article(++ArticlesLastId, "2021-01-26 12:12:12","2021-01-26 12:12:12", "제목2", "내용2"));
		

		
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
	public ResultData doAdd(String title, String body) {
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");		
		Date time = new Date();		
		String regDate = Util.getNowDateStr();
		String updateDate = regDate;
		
		
		articles.add(new Article(++ArticlesLastId, regDate, updateDate, title, body));
		
	
		
		return new ResultData("S-1","성공","id", ArticlesLastId);

	}




	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public ResultData doDelete(int id) {
		
		boolean deleteArticleRs = deleteArticle(id);
		
		
		Map<String, Object> rs = new HashMap<>();
		
		if (deleteArticleRs == false) {
			return new ResultData( "F-1", "해당 게시물은 존재하지 않습니다.");

		} else {
			return new ResultData( "S-1",  "성공 하였습니다.", "id", id);
		}
		
		
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
	public ResultData doModify(int id, String title, String body) {
		Article selarticle = null;
		
		
		
		
		for(Article article : articles) {
			if( article.getId() == id) {
				selarticle = article;
				break;
			}
		}
		
//		Map<String, Object> rs = new HashMap<>();
		
		if(selarticle == null ) {
			return new ResultData("F-1", String.format("%d 번 게시물은 존재하지 않습니다.", id));
		}
		
		selarticle.setUpdateDate(Util.getNowDateStr());
		selarticle.setTitle(title);
		selarticle.setBody(body);
		

		return new ResultData("S-1", String.format("%d 번 게시물이 수정되었습니다.", id),"id", id);
		
	}
	
	
	

}
