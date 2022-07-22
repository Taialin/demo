package com.example.demo.controllers;

import com.example.demo.Com.RssFeedView;
import com.example.demo.Services.NewsServices;
import com.example.demo.dob.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NewsController {

    @Autowired
    NewsServices newsServices;




    @GetMapping("/value")
    public List<News> getAllNews(){
        return newsServices.getAllNews();
    }

    @GetMapping("/savingNews")
    @CrossOrigin(origins = "*")
    public String all() throws Exception{
       List<News> list = new RssFeedView("https://people.onliner.by/feed").getAll();
        for (News item: list) {
            newsServices.save(item);
        }
        return "done";
    }

    @RequestMapping("/getNewsView")
    @ResponseBody

    public List<News>  getFeed () throws Exception {
        return  new RssFeedView("https://people.onliner.by/feed").getAll();
    }
}
