package com.example.newspaper.di;

import android.app.Application;

import androidx.room.Room;

import com.example.newspaper.db.NewsDao;
import com.example.newspaper.db.NewsDb;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class DatabaseModule {

    @Provides
    @Singleton
    public static NewsDb privideDB(Application application){
        return Room.databaseBuilder(application,NewsDb.class,"NewsDatabase")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    @Singleton
    public static NewsDao provideDao(NewsDb newsDb){
        return newsDb.newsDao();
    }
}
