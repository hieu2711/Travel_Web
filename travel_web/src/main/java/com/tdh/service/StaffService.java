/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tdh.service;

import com.tdh.dto.StaffDto;
import com.tdh.pojo.Staffs;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface StaffService {
    List<StaffDto> getStaff(Map<String, String> params);
    Long countStaff();
    boolean addOrUpdateStaff(Staffs p);
    Staffs getStaffById(int id);
    boolean deleteStaff(int id);
}
