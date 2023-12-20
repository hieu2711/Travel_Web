/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tdh.controllers;

import com.tdh.pojo.News;
import com.tdh.pojo.TourImages;
import com.tdh.service.ImageToursService;
import com.tdh.service.NewsService;
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
public class ImageToursController {
    @Autowired
    private ImageToursService imageToursService;

    @GetMapping("/imageTours")
    public String list(Model model) {
        model.addAttribute("imageTour", new TourImages());
        model.addAttribute("imageTours", this.imageToursService.getImageTours());
        return "imageTours";
    }

    @GetMapping("/addimage/{id}")
    public String update(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("addimage", this.imageToursService.getToursImgagesById(id));
        return "addImage";
    }

    @PostMapping("/addimage")
    public String add(@ModelAttribute(value = "addimage") @Valid TourImages p, BindingResult rs) {
        if (!rs.hasErrors()) {
            if (this.imageToursService.addOrUpdateToursImages(p) == true) {
                return "redirect:/imageTours";
            }
        }
        return "addImage";
    }

    @GetMapping("/addimage")
    public String showFormAddPrices(Model model) {
        model.addAttribute("addimage", new TourImages());
        return "addImage";
    }
}
