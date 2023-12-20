/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tdh.service;
import com.tdh.dto.CustomerDto;
import com.tdh.pojo.Users;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
public interface AccountService extends UserDetailsService{
    List<Users> getUser();
    Users getUserById(int id);
    boolean addOrUpdateUser(Users c);
    boolean deleteUser(int id);  
    Users getUserByUsername(String username);
    boolean authUser(String username, String password);
    CustomerDto addUser(Map<String, String> params, MultipartFile avatar);
     Users getUserByUn(String username);
     Boolean existsByUsername(String username);
}
