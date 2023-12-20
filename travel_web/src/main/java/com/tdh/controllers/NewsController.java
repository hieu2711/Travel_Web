/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tdh.controllers;

import com.tdh.pojo.News;
import com.tdh.pojo.Prices;
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
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping("/news")
    public String list(Model model) {
        model.addAttribute("new", new News());
        model.addAttribute("news", this.newsService.getNews());
        return "new";
    }

    @GetMapping("/addnew/{id}")
    public String update(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("addnew", this.newsService.getNewsById(id));
        return "addNews";
    }

    @PostMapping("/addnew")
    public String add(@ModelAttribute(value = "addnew") @Valid News p, BindingResult rs) {
        if (!rs.hasErrors()) {
            if (this.newsService.addOrUpdateNews(p) == true) {
                return "redirect:/news";
            }
        }
        return "addNews";
    }

    @GetMapping("/addnew")
    public String showFormAddPrices(Model model) {
        model.addAttribute("addnew", new News());
        return "addNews";
    }
}
