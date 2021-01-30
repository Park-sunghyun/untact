package com.sbs.untact.dto;

public class Article {
	private int id;
	private String regDate;
	private String UpdateDate;
	private String title;
	private String body;

	public Article(int id, String regDate, String UpdateDate,String title, String body) {
	 this.id = id;
	 this.regDate = regDate;
	 this.UpdateDate = UpdateDate;
	 this.title = title;
	 this.body = body;
	}
	

	@Override
	public String toString() {
		return "Article [id=" + id + ", regDate=" + regDate + ", UpdateDate=" + UpdateDate + ", title=" + title
				+ ", body=" + body + "]";
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	

	public String getUpdateDate() {
		return UpdateDate;
	}

	public void setUpdateDate(String updateDate) {
		UpdateDate = updateDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	

}
