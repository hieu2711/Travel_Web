/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tdh.repository;
import com.tdh.pojo.Tours;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface ToursRepository {
    List<Tours> getListTours();
    List<Tours> getTours(Map<String, String> params);
    Long countProduct();
    boolean addOrUpdateTours(Tours p);
    Tours getToursById(int id);
    boolean deleteTours(int id);
}
