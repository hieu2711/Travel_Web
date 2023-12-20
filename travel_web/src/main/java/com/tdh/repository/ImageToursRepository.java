/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tdh.repository;

import com.tdh.pojo.TourImages;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface ImageToursRepository {
    List<TourImages> getImageTours();

    TourImages getToursImgagesById(int id);

    boolean addOrUpdateToursImages(TourImages c);

    boolean deleteToursImgages(int id);
    List<TourImages> getTourImagesByToursId(int toursId);
}
