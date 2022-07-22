package com.example.demo.Services.Impl;

import com.example.demo.dob.News;
import com.example.demo.repository.NewsRepository;
import com.example.demo.Services.NewsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServicesImpl implements NewsServices {

    @Autowired
    NewsRepository repository;

    @Override
    public News save(News news) {
        return  repository.save(news);

    }

    @Override
    public boolean isExist(String newsTitle) {
        List<News> news = repository.findAll();
        for(News n: news){
            if(n.getTitle().equals(newsTitle)){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<News> getAllNews() {
        return repository.findAll();

    }


}
