package com.example.uts;

import com.example.uts.model.NewsHeadlines;

import java.util.List;

public interface OnFetchDataListener<NewsApiResponse>{
    void onFetchData(List<NewsHeadlines> list, String message);
    void onError(String message);
}
