/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tdh.repository;

import com.tdh.pojo.Prices;
import com.tdh.pojo.Tours;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface PriceRepository {
    List<Prices> getPrice();
    boolean addOrUpdatePriceTours(Prices p);
    Prices getPriceById(int id);
    boolean deletePrice(int id);
}
