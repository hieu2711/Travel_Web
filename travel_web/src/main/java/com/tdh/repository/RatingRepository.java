/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tdh.repository;

import com.tdh.pojo.Rating;

/**
 *
 * @author Admin
 */
public interface RatingRepository {
     double getRatingAverage(int productId);
    Rating addRating(Rating c);
}
