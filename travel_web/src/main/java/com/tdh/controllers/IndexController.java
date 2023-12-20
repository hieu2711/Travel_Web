package com.tdh.controllers;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.tdh.service.CategoryService;
import com.tdh.service.PriceService;
import com.tdh.service.ToursService;
import java.util.Map;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
/**
 *
 * @author Admin
 */
@Controller
@ControllerAdvice
@PropertySource("classpath:configs.properties")
public class IndexController {
    @Autowired
    private ToursService toursService;
    @Autowired
    private CategoryService cateService;
    @Autowired
    private Environment env;
    @Autowired
    private PriceService priceService;
    
    @ModelAttribute
    public void commonAttri(Model model) {
        model.addAttribute("categories", this.cateService.getCates());
    }
    
     @ModelAttribute
    public void commonAttriTour(Model model) {
        model.addAttribute("listtours", this.toursService.getListTours());
    }
    
    
    @ModelAttribute
    public void commonAttriPrices(Model model) {
        model.addAttribute("price", this.priceService.getPrice());
    }
    @RequestMapping("/")
    public String index(Model model, @RequestParam Map<String, String> params){
        model.addAttribute("tours",this.toursService.getTours(params));
        
         int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        long count = this.toursService.countProduct();
        model.addAttribute("counter", Math.ceil(count*1.0/pageSize));
        return "index";
    }
}
