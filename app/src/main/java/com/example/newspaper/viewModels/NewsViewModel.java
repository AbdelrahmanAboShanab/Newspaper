package com.example.newspaper.viewModels;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.newspaper.pojo.News;
import com.example.newspaper.repository.Repository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class NewsViewModel extends ViewModel {

    private Repository repository;
    MutableLiveData<ArrayList<News>> list = new MutableLiveData<>();
    private LiveData<List<News>> favouriteNews = null;

    @ViewModelInject
    public NewsViewModel(Repository repository) {
        this.repository = repository;
    }

    public MutableLiveData<ArrayList<News>> getList() {
        return list;
    }

    public void getNews() {
        repository.getNewsResponse().subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> list.setValue(result.getArticles()));
    }

    public void viewModeInsert(News news) {
        repository.repInsert(news);
    }

    public void viewModelDelete(String name) {
        repository.repDelete(name);
    }

    public void viewModelGetNews() {
        favouriteNews = repository.getNews();
    }

    public LiveData<List<News>> viewModelGetNewsList(){
            return favouriteNews;
    }
}
