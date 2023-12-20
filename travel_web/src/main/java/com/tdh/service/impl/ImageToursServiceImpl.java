/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tdh.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tdh.pojo.TourImages;
import com.tdh.repository.ImageToursRepository;
import com.tdh.service.ImageToursService;
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
public class ImageToursServiceImpl implements ImageToursService {

    @Autowired
    private ImageToursRepository imageToursRepo;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public TourImages getToursImgagesById(int id) {
         return this.imageToursRepo.getToursImgagesById(id);
    }

    @Override
    public boolean addOrUpdateToursImages(TourImages c) {
        if(!c.getFile().isEmpty()){
            try {
                 Map res = this.cloudinary.uploader().upload(c.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                    c.setImageUrl(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(ToursServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return this.imageToursRepo.addOrUpdateToursImages(c); 
    }

    @Override
    public boolean deleteToursImgages(int id) {
        return this.imageToursRepo.deleteToursImgages(id);
    }

    @Override
    public List<TourImages> getImageTours() {
         return this.imageToursRepo.getImageTours();
    }

    @Override
    public List<TourImages> getTourImagesByToursId(int toursId) {
        return this.imageToursRepo.getTourImagesByToursId(toursId);
    }

}
