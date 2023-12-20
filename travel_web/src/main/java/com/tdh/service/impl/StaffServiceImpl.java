/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tdh.service.impl;

import com.tdh.dto.StaffDto;
import com.tdh.pojo.Staffs;
import com.tdh.repository.StaffRepository;
import com.tdh.service.StaffService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class StaffServiceImpl implements StaffService{
    @Autowired
    private StaffRepository staffRepo;
    
    @Override
    public List<StaffDto> getStaff(Map<String, String> params) {
       return this.staffRepo.getStaff(params);
    }

    @Override
    public Long countStaff() {
       return this.staffRepo.countStaff();
    }

    @Override
    public boolean addOrUpdateStaff(Staffs p) {
         return this.staffRepo.addOrUpdateStaff(p); 
    }

    @Override
    public Staffs getStaffById(int id) {
      return this.staffRepo.getStaffById(id);
    }

    @Override
    public boolean deleteStaff(int id) {
        return this.staffRepo.deleteStaff(id);
    }

    
}
