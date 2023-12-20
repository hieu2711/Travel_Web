/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tdh.service;

import com.tdh.pojo.News;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface NewsService {
    List<News> getNews();
    News getNewsById(int id);
    boolean addOrUpdateNews(News c);
    boolean deleteNews(int id);
}
