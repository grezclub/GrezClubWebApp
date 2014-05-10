package com.paloit.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paloit.dao.NewsService;
import com.paloit.entities.News;

@Service
@Transactional
public class NewsManagerImpl implements NewsManager {
	
	private NewsService newsService;
	private News news;
	private List<News> listeNews;
	@Autowired
	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	public void creerNews(byte[] imageNews, String titre, String contenue) {
			
		news = new News();
			
			news.setImageNews(imageNews);
		
		
		news.setTitreNews(titre);
		news.setTexteNews(contenue);
		
		newsService.creerNews1(news);
		
		
	}

	
	public List<News> recupNews() {
		listeNews = newsService.listeNews();
		return listeNews;
	}

	
	public News recupNewsId(Integer id) {
		return newsService.recupererNewsId(id);
		
	}

	@Override
	public List<News> recup5News() {
		listeNews = newsService.liste5News();
		return listeNews;
	}

	@Override
	public void modifierNews(News news) {
		newsService.modifierNews(news);
		
	}

	@Override
	public void supprimeNews(int idNews) {
		News news = new News();
		news = newsService.recupererNewsId(idNews);
		newsService.deleteNews(news);
		
	}



}
