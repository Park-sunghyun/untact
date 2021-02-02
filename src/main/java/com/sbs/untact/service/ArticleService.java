package com.sbs.untact.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sbs.untact.dto.Article;
import com.sbs.untact.dto.ResultData;
import com.sbs.untact.util.Util;

@Service
public class ArticleService {

	private int ArticlesLastId;
	private List<Article> articles;

	public ArticleService() {
		ArticlesLastId = 0;

		articles = new ArrayList<>();

		articles.add(new Article(++ArticlesLastId, "2021-01-26 12:12:12", "2021-01-26 12:12:12", "제목1", "내용1"));
		articles.add(new Article(++ArticlesLastId, "2021-01-26 12:12:12", "2021-01-26 12:12:12", "제목2", "내용2"));
	}

	public Article getArticle(int id) {

		for (Article article : articles) {

			if (article.getId() == id) {
				return article;
			}
		}

		return null;
	}

	public List<Article> getArticles(String searchKeyword, String searchKeywordType) {

		if (searchKeyword == null) {
			return articles;
		}

		List<Article> filtered = new ArrayList<>();

		for (Article article : articles) {
			boolean contains = false;

			if (searchKeywordType.equals("title")) {

				contains = article.getTitle().contains(searchKeyword);
			} else if (searchKeywordType.equals("body")) {

				contains = article.getBody().contains(searchKeyword);
			
			} else {
				contains = article.getTitle().contains(searchKeyword);
				
				// OR 조건 다른 방법 ( 따로 자른이유 는 길이가 너무 길어져서 )
			//contains = article.getTitle().contains(searchKeyword) || article.getBody().contains(searchKeyword);
				
				if( contains == false ) {
					contains = article.getBody().contains(searchKeyword);	
				}
				
			}

			if (contains) {
				filtered.add(article);
			}
		}
		return filtered;
	}

	public ResultData add(String title, String body) {
		int id = ++ArticlesLastId;
		String regDate = Util.getNowDateStr();
		String updateDate = regDate;

		articles.add(new Article(id, regDate, updateDate, title, body));

		return new ResultData("S-1", "성공 하였습니다.", "id", id);
	}

	public ResultData deleteArticle(int id) {
		for (Article article : articles) {
			if (article.getId() == id) {
				articles.remove(article);
				return new ResultData("S-1", "삭제 하였습니다.", "id", id);
			}
		}
		return new ResultData("S-1", "해당 게시물 은 존재하지 않습니다.", "id", id);
	}

	public ResultData modify(int id, String title, String body) {
		Article article = getArticle(id);

		article.setTitle(title);
		article.setBody(body);
		article.setUpdateDate(Util.getNowDateStr());

		return new ResultData("S-1", "게시물 을 수정하였습니다.", "id", id);
	}

}
