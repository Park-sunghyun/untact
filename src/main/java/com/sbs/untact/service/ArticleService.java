package com.sbs.untact.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.untact.dao.ArticleDao;
import com.sbs.untact.dto.Article;
import com.sbs.untact.dto.ResultData;
import com.sbs.untact.util.Util;

@Service
public class ArticleService {
	@Autowired
	private ArticleDao articleDao;
	@Autowired
	private MemberService memberService; 

	public Article getArticle(int id) {

		return articleDao.getArticle(id);
	}
		

	public ResultData addArticle(Map<String, Object> param) {
		
		articleDao.addArticle(param);
		
		int id = Util.getAsInt(param.get("id"), 0);
		
		
		return new ResultData("S-1", "성공 하였습니다.", "id", id);
	}

	public ResultData deleteArticle(int id) {
		
		articleDao.deleteArticle(id);
		
		
		return new ResultData("S-1", "삭제 되었습니다..", "id", id);
		
	}

	public ResultData modifyArticle(int id, String title, String body) {
		articleDao.modifyArticle(id,title,body);
		return new ResultData("S-1", "게시물 을 수정하였습니다.", "id", id);
	}
	
	
	public List<Article> getArticles(String searchKeyword, String searchKeywordType) {
		
		return articleDao.getArticles(searchKeyword, searchKeywordType);
	}


	public ResultData getActorCanModifyRd(Article article, int actorId) {
		
		// 회원
		if (article.getMemberId() == actorId) {
			return new ResultData("S-1", "가능합니다.");
		}
		
		// 관리자 
		if (memberService.isAdmin(actorId)) {
			return new ResultData("S-2", "가능합니다.");
		}
		
		
		return new ResultData("F-1", "권한이 없습니다..");
	}


	public ResultData getActorCanDeleteRd(Article article, int actorId) {
		
		return getActorCanModifyRd(article,actorId);
	}


	public Article getForPrintArticle(int id) {
		
		return articleDao.getForPrintArticle(id);
	}


	public List<Article> getForPrintArticles(int boardId, String searchKeyword, String searchKeywordType, int page, int itemsInAPage) {
		
		int limitStart = (page - 1) * itemsInAPage;
		int limitTake = itemsInAPage;
		
		return articleDao.getForPrintArticles(boardId, searchKeyword, searchKeywordType,limitStart,limitTake);
	}

}
