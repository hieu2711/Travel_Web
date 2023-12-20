/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tdh.service;

import com.tdh.pojo.TourCategory;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface CategoryService {
    List<TourCategory> getCates();
    TourCategory getTourCategoryById(int id);
    boolean addOrUpdateToursCategory(TourCategory c);
    boolean deleteToursCategory(int id);   
}
