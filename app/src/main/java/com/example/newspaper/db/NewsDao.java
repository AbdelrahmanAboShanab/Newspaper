package com.example.newspaper.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.newspaper.pojo.News;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface NewsDao {
    @Insert
    public void insertNews(News news);

    @Query("delete from favouriteNews where title =:newsName ")
    public void deleteNews(String newsName);

    @Query("select * from favouriteNews")
    public LiveData<List<News>> getNews();
}
