/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tdh.controllers;

import com.tdh.pojo.TourCategory;
import com.tdh.pojo.Users;
import com.tdh.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Admin
 */
@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;
    
    @GetMapping("/account")
    public String list(Model model) {
        model.addAttribute("account", new TourCategory());
        model.addAttribute("accounts",this.accountService.getUser());
        return "account";
    }
    
    @GetMapping("/addaccount/{id}")
    public String update(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("addaccount", this.accountService.getUserById(id));
        return "addAccount";
    }
    
 
    @PostMapping("/addaccount")
    public String add(@ModelAttribute(value = "addacount") Users c){
            if (this.accountService.addOrUpdateUser(c) == true)
                return "redirect:/account";
        return "addAccount";
    } 
    
    @GetMapping("/addaccount")
    public String showFormAddCategory(Model model) {
        model.addAttribute("addaccount", new Users());
        return "addAccount";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
