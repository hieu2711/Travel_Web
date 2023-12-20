/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tdh.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tdh.dto.CustomerDto;
import com.tdh.pojo.Customers;
import com.tdh.pojo.Users;
import com.tdh.repository.AccountRepository;
import com.tdh.service.AccountService;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
@Service("userDetailsService")
public class AccountServiceImpl implements AccountService {

     private final Cloudinary cloudinary;

    public AccountServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }
    @Autowired
    private AccountRepository accountRepo;
    @Autowired
    private BCryptPasswordEncoder encoder;
//    @Autowired
//    private Cloudinary cloudinary;
    
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date dateOfBirth;
    
      @Autowired
    private LocalSessionFactoryBean factory;
    @Override
    public List<Users> getUser() {
        return this.accountRepo.getUser();
    }

    @Override
    public Users getUserById(int id) {
        return this.accountRepo.getUserById(id);
    }

    @Override
    public boolean addOrUpdateUser(Users c) {
//        if (!c.getFile().isEmpty()) {
//            try {
//                Map res = this.cloudinary.uploader().upload(c.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
//                c.setAvatar(res.get("secure_url").toString());
//            } catch (IOException ex) {
//                Logger.getLogger(ToursServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        }
        c.setPassword(encoder.encode(c.getPassword()));
        return this.accountRepo.addOrUpdateUser(c);
    }

    @Override
    public boolean deleteUser(int id) {
        return this.accountRepo.deleteUser(id);
    }

    @Override
    public Users getUserByUsername(String username) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Users WHERE username=:un");
        q.setParameter("un", username);

        return (Users) q.getSingleResult();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users u = this.accountRepo.getUserByUsername(username);
        if (u == null) {
            throw new UsernameNotFoundException("Invalid user!");
        }

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(u.getRole()));

        return new org.springframework.security.core.userdetails.User(
                u.getUsername(), u.getPassword(), authorities);
    }

    @Override
    public boolean authUser(String username, String password) {
        return this.accountRepo.authUser(username, password);
    }

    @Override
    public CustomerDto addUser(Map<String, String> params, MultipartFile avatar) {
        Users u = new Users();
        u.setUsername(params.get("username"));
        u.setPassword(this.encoder.encode(params.get("password")));
        u.setRole("ROLE_USER");
        if (!avatar.isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(avatar.getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                u.setAvatar(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(AccountServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Users user = this.accountRepo.addUser(u);
        //Thêm khách hàng
        Customers c = new Customers();
        c.setName(params.get("name"));
        c.setIdentification(Integer.parseInt(params.get("identification")));
        c.setEmail(params.get("email"));
        c.setPhonenumber(Integer.parseInt(params.get("phonenumber")));
        c.setAddress(params.get("address"));
        c.setSex(Integer.parseInt(params.get("sex")));
        c.setUserId(user);
        Customers cus = this.accountRepo.addCustomer(c);
        
        CustomerDto customer = new CustomerDto();
        customer.setUsername(user.getUsername());
        customer.setPassword(user.getPassword());
        customer.setName(cus.getName());
        customer.setIdentification(cus.getIdentification());
        customer.setEmail(cus.getEmail());
        customer.setPhonenumber(cus.getPhonenumber());
        customer.setAddress(cus.getAddress());
        customer.setSex(Integer.toString(cus.getSex()));      
        customer.setRole(user.getRole());
        customer.setAvatar(user.getAvatar());
        
        return customer;
    }

    @Override
    public Users getUserByUn(String username) {
        return this.accountRepo.getUserByUsername(username);
    }
     @Override
    public Boolean existsByUsername(String username) {
        return this.accountRepo.existsByUsername(username);
    }

}
