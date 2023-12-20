/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tdh.repository;

import com.tdh.pojo.ConmentsNews;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface CommentsNewsRepository {
    List<ConmentsNews> getComments(int productId);
    ConmentsNews addComment(ConmentsNews c);
}
