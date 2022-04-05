package com.example.newspaper.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.newspaper.pojo.News;

@Database(entities = News.class,version = 1,exportSchema = false)
@TypeConverters(Converters.class)
public abstract class NewsDb extends RoomDatabase {

    public abstract NewsDao newsDao();
}
