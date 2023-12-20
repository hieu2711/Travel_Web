/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tdh.formatters;

import com.tdh.pojo.TourCategory;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author admin
// */
public class CategoryFormatter implements Formatter<TourCategory> {

    @Override
    public String print(TourCategory cate, Locale locale) {
        return String.valueOf(cate.getId());
    }

    @Override
    public TourCategory parse(String cateId, Locale locale) throws ParseException {
        return new TourCategory(Integer.parseInt(cateId));
    }

    
    
}
