/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tdh.service.impl;

import com.tdh.pojo.TourCategory;
import com.tdh.repository.CategoryRepository;
import com.tdh.service.CategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository cateRepo;
    @Override
    public List<TourCategory> getCates() {
       return this.cateRepo.getCates();
    }
    
    @Override
    public TourCategory getTourCategoryById(int id) {
        return this.cateRepo.getTourCategoryById(id);
    }
    @Override
    public boolean addOrUpdateToursCategory(TourCategory c) {
        c.setIsDelete(Boolean.FALSE);
        return this.cateRepo.addOrUpdateToursCategory(c); 
    }

    @Override
    public boolean deleteToursCategory(int id) {
         return this.cateRepo.deleteToursCategory(id);
    }
    
}
