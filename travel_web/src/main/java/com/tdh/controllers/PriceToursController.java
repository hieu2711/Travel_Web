/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tdh.controllers;

import com.tdh.pojo.Prices;
import com.tdh.service.PriceService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Admin
 */
@Controller
public class PriceToursController {
     @Autowired
    private PriceService priceService;
     
    @GetMapping("/price")
    public String list(Model model) {
        model.addAttribute("price", new Prices());
        model.addAttribute("prices",this.priceService.getPrice());
        return "priceTours";
    }
    
    @GetMapping("/addprice/{id}")
    public String update(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("addprice", this.priceService.getPriceById(id));
        return "addPriceTours";
    }
    
    @PostMapping("/addprice")
    public String add(@ModelAttribute(value = "addprice") Prices p){
            if (this.priceService.addOrUpdatePriceTours(p) == true)
                return "redirect:/price";
        return "addPriceTours";
    } 
    @GetMapping("/addprice")
    public String showFormAddPrices(Model model) {
        model.addAttribute("addprice", new Prices());
        return "addPriceTours";
    }
}
