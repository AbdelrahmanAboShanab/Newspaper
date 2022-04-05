package com.example.newspaper.db;

import androidx.room.TypeConverter;

import com.example.newspaper.pojo.News;
import com.example.newspaper.pojo.Source;
import com.google.gson.Gson;

public class Converters {

    @TypeConverter
    public String fromSourceToString(Source source){
        return new Gson().toJson(source);
    }

    @TypeConverter
    public Source fromStringToSource(String stringNews){
        return new Gson().fromJson(stringNews, Source.class);
    }
}
