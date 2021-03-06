package com.sbs.untact.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbs.untact.dto.Article;

@Mapper
public interface ArticleDao {

	public Article getArticle(@Param("id") int id);

	public void addArticle(Map<String, Object> param);

	public void deleteArticle(@Param("id")int id);

	public void modifyArticle(@Param("id")int id, @Param(value = "title")String title, @Param(value = "body")String body);

	public List<Article> getArticles(@Param(value = "searchKeyword")String searchKeyword, @Param(value = "searchKeywordType")String searchKeywordType);

	public Article getForPrintArticle(@Param("id") int id);

	public List<Article> getForPrintArticles(@Param("boardId") int boardId, @Param("searchKeyword")String searchKeyword, @Param(value = "searchKeywordType")String searchKeywordType, @Param("limitStart") int limitStart, @Param("limitTake") int limitTake );
	
	

}
