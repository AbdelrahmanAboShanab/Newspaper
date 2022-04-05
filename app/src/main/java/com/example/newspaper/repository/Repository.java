package com.example.newspaper.repository;

import androidx.lifecycle.LiveData;

import com.example.newspaper.db.NewsDao;
import com.example.newspaper.network.NewsApiInterface;
import com.example.newspaper.pojo.News;
import com.example.newspaper.pojo.NewsResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class Repository {
    private NewsApiInterface newsApiInterface;
    private NewsDao newsDao;


    @Inject
    public Repository(NewsApiInterface newsApiInterface,NewsDao newsDao) {
        this.newsApiInterface = newsApiInterface;
        this.newsDao= newsDao;
    }

    public Observable<NewsResponse> getNewsResponse(){
        return newsApiInterface.getNews();
    }

    public void repInsert(News news){
        newsDao.insertNews(news);
    }

    public void repDelete(String name){
        newsDao.deleteNews(name);
    }

    public LiveData<List<News>> getNews(){
        return newsDao.getNews();
    }
}
