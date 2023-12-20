/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tdh.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tdh.dto.ToursDto;
import com.tdh.pojo.TourCategory;
import com.tdh.pojo.Tours;
import com.tdh.repository.ToursRepository;
import com.tdh.service.ToursService;
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
public class ToursServiceImpl implements ToursService{
    @Autowired
    private ToursRepository toursRepo;
    
     @Autowired
    private Cloudinary cloudinary;
     
    @Override
    public List<Tours> getTours(Map<String, String> params) {
        return this.toursRepo.getTours(params);
    }
    @Override
    public Long countProduct() {
        return this.toursRepo.countProduct();
    }

    @Override
    public boolean addOrUpdateTours(Tours p) {
        if(!p.getFile().isEmpty()){
            try {
                 Map res = this.cloudinary.uploader().upload(p.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                    p.setImage(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(ToursServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return this.toursRepo.addOrUpdateTours(p);
    }

    @Override
    public Tours getToursById(int id) {
        return this.toursRepo.getToursById(id);
    }

    @Override
    public boolean deleteTours(int id) {
        return this.toursRepo.deleteTours(id);
    }

    @Override
    public List<Tours> getListTours() {
       return this.toursRepo.getListTours();
    }
}
