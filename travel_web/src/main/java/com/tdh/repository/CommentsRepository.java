/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tdh.repository;

import com.tdh.pojo.Comments;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface CommentsRepository {
     List<Comments> getComments(int productId);
    Comments addComment(Comments c);
}
