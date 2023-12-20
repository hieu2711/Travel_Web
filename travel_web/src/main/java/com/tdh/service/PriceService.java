/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tdh.service;

import com.tdh.pojo.Prices;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface PriceService {
    List<Prices> getPrice();
    boolean addOrUpdatePriceTours(Prices p);
    Prices getPriceById(int id);
    boolean deletePrice(int id);
}
