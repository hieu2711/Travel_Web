/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tdh.controllers;

import com.tdh.pojo.Tours;
import com.tdh.service.CategoryService;
import com.tdh.service.PriceService;
import com.tdh.service.ToursService;
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
public class ToursController {

    @Autowired
    private ToursService tourService;
    @Autowired
    private PriceService priceService;

    @GetMapping("/tours")
    public String list(Model model) {
        model.addAttribute("tours", new Tours());
        return "tours";
    }

    @GetMapping("/tours/{id}")
    public String update(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("tours", this.tourService.getToursById(id));
        return "tours";
    }

    @PostMapping("/tours")
    public String add(@ModelAttribute(value = "tours") @Valid Tours p, BindingResult rs) {
        if (!rs.hasErrors()) {
                if (this.tourService.addOrUpdateTours(p) == true) {
                    return "redirect:/";

            }

        }
        return "tours";
    }
}
