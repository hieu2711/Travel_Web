/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tdh.controllers;

import com.cloudinary.provisioning.Account;
import com.tdh.pojo.Staffs;
import com.tdh.service.AccountService;
import com.tdh.service.StaffService;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Admin
 */
@Controller
@PropertySource("classpath:configs.properties")
public class StaffController {

    @Autowired
    private StaffService staffService;
    @Autowired
    private AccountService accountService;
    
    @Autowired
    private Environment env;

    @RequestMapping("/staff")
    public String index(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("staffs", this.staffService.getStaff(params));

        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        long count = this.staffService.countStaff();
        model.addAttribute("counter", Math.ceil(count * 1.0 / pageSize));
        return "staff";
    }

    @GetMapping("/addstaff/{id}")
    public String update(Model model, @PathVariable(value = "id") int id) {
        Staffs nv = this.staffService.getStaffById(id);
        model.addAttribute("addstaff", this.staffService.getStaffById(id));
        model.addAttribute("user", this.accountService.getUserById(nv.getUserId().getId()));
        
        return "addStaff";
    }

    @PostMapping("/addstaff")
    public String add(@ModelAttribute(value = "addstaff") @Valid Staffs p, BindingResult rs) {
        if (!rs.hasErrors()) {
            if (this.accountService.addOrUpdateUser(p.getUserId()) == true) {
            if (this.staffService.addOrUpdateStaff(p) == true) {
                return "redirect:/staff";
            }
            }
        }

        return "addStaff";
    }

    @GetMapping("/addstaff")
    public String showFormAddStaff(Model model) {
        model.addAttribute("addstaff", new Staffs());
        return "addStaff";
    }
}
