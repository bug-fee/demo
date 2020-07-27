package com.repairs.message.resp;

import java.util.List;

import com.repairs.message.po.Article;

public class NewsMessage extends BaseMessage{
	private int ArticleCount;
	private List<Article> Articles;
	public int getArticleCount() {
		return ArticleCount;
	}
	public void setArticleCount(int articleCount) {
		this.ArticleCount = articleCount;
	}
	public List<Article> getArticles() {
		return Articles;
	}
	public void setArticles(List<Article> articles) {
		this.Articles = articles;
	}
	
}
