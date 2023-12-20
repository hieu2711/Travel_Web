/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tdh.controllers;

import com.tdh.pojo.TourImages;
import com.tdh.service.ImageToursService;
import com.tdh.service.StatsService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Admin
 */
@Controller
public class ChartController {

    @Autowired
    private StatsService statsService;

    @RequestMapping("/chart")
    public String indexChartSale(Model model,@RequestParam Map<String, String> params) {
        String year = params.get("year");
        int[] totalSoldToursArray = this.statsService.statsSale(params);
        model.addAttribute("chart", totalSoldToursArray);
        return "chart";
    }

    @RequestMapping("/chartRevenue")
    public String indexChartRevenue(Model model, @RequestParam Map<String, String> params) {
        String year = params.get("year");
        int[] totalSoldToursArray = this.statsService.statsRevenue(params);
        model.addAttribute("chartRevenue", totalSoldToursArray);
        return "chartRevenue";
    }
}
