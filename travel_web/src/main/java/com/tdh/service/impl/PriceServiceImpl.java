/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tdh.service.impl;

import com.tdh.pojo.Prices;
import com.tdh.repository.PriceRepository;
import com.tdh.service.PriceService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class PriceServiceImpl implements PriceService{
    @Autowired
    private PriceRepository priceRepo;
    
    @Override
    public List<Prices> getPrice() {
        return this.priceRepo.getPrice();
    }

    @Override
    public boolean addOrUpdatePriceTours(Prices p) {
        p.setIsDelete(Boolean.FALSE);
        return this.priceRepo.addOrUpdatePriceTours(p);
    }

    @Override
    public Prices getPriceById(int id) {
        return this.priceRepo.getPriceById(id);
    }

    @Override
    public boolean deletePrice(int id) {
        return this.priceRepo.deletePrice(id);
    }
    
}
