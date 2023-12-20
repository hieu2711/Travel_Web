/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tdh.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tdh.pojo.News;
import com.tdh.repository.NewsRepository;
import com.tdh.service.NewsService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class NewsServiceImpl implements NewsService{
    @Autowired
    private NewsRepository newsRepository;
     @Autowired
    private Cloudinary cloudinary;
    @Override
    public List<News> getNews() {
        return this.newsRepository.getNews();
    }

    @Override
    public News getNewsById(int id) {
       return this.newsRepository.getNewsById(id);
    }

    @Override
    public boolean addOrUpdateNews(News c) {
        c.setIsDelete(Boolean.FALSE);
        if(!c.getFile().isEmpty()){
            try {
                 Map res = this.cloudinary.uploader().upload(c.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                    c.setImage(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(ToursServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return this.newsRepository.addOrUpdateNews(c); 
    }

    @Override
    public boolean deleteNews(int id) {
        
        return this.newsRepository.deleteNews(id);
    }
    
}
