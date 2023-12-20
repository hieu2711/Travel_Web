/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tdh.service;

import com.tdh.pojo.Rating;

/**
 *
 * @author Admin
 */
public interface RatingService {
    double getRatingAverage(int productId);
    Rating addRating(Rating c);
}
