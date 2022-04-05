package com.example.newspaper.network;

import com.example.newspaper.pojo.NewsResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface NewsApiInterface {
    @GET("/v2/everything?q=tesla&from=2022-02-23&sortBy=publishedAt&apiKey=980bd4ad266a4a498831c06951ee58be")
    Observable<NewsResponse> getNews();
}
