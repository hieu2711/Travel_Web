/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tdh.formatters;

import com.tdh.pojo.Prices;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author Admin
 */
public class PriceFormatter implements  Formatter<Prices>{
    @Override
    public String print(Prices price, Locale locale) {
        return String.valueOf(price.getId());
    }

    @Override
    public Prices parse(String priceId, Locale locale) throws ParseException {
        return new Prices(Integer.parseInt(priceId));
    }
}
