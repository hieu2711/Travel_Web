/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tdh.formatters;


import com.tdh.pojo.Tours;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author Admin
 */
public class ToursFormatter implements  Formatter<Tours>{
    @Override
    public String print(Tours tours, Locale locale) {
        return String.valueOf(tours.getId());
    }

    @Override
    public Tours parse(String toursId, Locale locale) throws ParseException {
        return new Tours(Integer.parseInt(toursId));
    }
}
