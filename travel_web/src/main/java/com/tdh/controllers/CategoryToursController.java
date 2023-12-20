/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tdh.controllers;

import com.tdh.pojo.TourCategory;
import com.tdh.service.CategoryService;
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
public class CategoryToursController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    public String list(Model model) {
        model.addAttribute("category", new TourCategory());
        return "category";
    }

    @GetMapping("/addcategory/{id}")
    public String update(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("addcategory", this.categoryService.getTourCategoryById(id));
        return "addCategory";
    }

    @PostMapping("/addcategory")
    public String add(@ModelAttribute(value = "addcategory") @Valid TourCategory c, BindingResult rs) {
        if (!rs.hasErrors()) {
            if (this.categoryService.addOrUpdateToursCategory(c) == true) {
                return "redirect:/category";
            }
        }
        return "addCategory";
    }
    @GetMapping("/addcategory")
    public String showFormAddCategory(Model model) {
        model.addAttribute("addcategory", new TourCategory());
        return "addCategory";
    }
}
