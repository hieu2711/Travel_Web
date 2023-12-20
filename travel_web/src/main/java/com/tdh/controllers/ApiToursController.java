/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tdh.controllers;

import com.tdh.pojo.Comments;
import com.tdh.pojo.ConmentsNews;
import com.tdh.pojo.Rating;
import com.tdh.pojo.TourImages;
import com.tdh.pojo.Tours;
import com.tdh.service.AccountService;
import com.tdh.service.CategoryService;
import com.tdh.service.CommentsNewsService;
import com.tdh.service.CommentsService;
import com.tdh.service.ImageToursService;
import com.tdh.service.NewsService;
import com.tdh.service.PriceService;
import com.tdh.service.RatingService;
import com.tdh.service.StaffService;
import com.tdh.service.ToursService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api")
public class ApiToursController {
     @Autowired
    private ToursService toursService;
     @Autowired
    private CategoryService CategoryTourService;
     @Autowired
     private NewsService NewsService;
     @Autowired
     private StaffService staffService;
     @Autowired
     private AccountService accountService;
    @Autowired
    private PriceService priceService;
    @Autowired
    private ImageToursService imageToursService;
    @Autowired
    private CommentsService commentService;
    @Autowired
    private RatingService ratingService;
    @Autowired 
    private CommentsNewsService commentsNewService;
    
    @DeleteMapping("/tours/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") int id) {
        this.toursService.deleteTours(id);
    }
    
    @PutMapping("/addcategory/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategoryTours(@PathVariable(value = "id") int id) {
        this.CategoryTourService.deleteToursCategory(id);
    }
    
    @PutMapping("/addprice/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePrice(@PathVariable(value = "id") int id) {
        this.priceService.deletePrice(id);
    }
    
    @PutMapping("/addnew/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNewsTours(@PathVariable(value = "id") int id) {
        this.NewsService.deleteNews(id);
    }
    
    @DeleteMapping("/addstaff/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStaff(@PathVariable(value = "id") int id) {
        this.staffService.deleteStaff(id);
    }
    
    @DeleteMapping("/addimage/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteImage(@PathVariable(value = "id") int id) {
        this.imageToursService.deleteToursImgages(id);
    }
    
    @RequestMapping("/products/")
    @CrossOrigin
    public ResponseEntity<List<Tours>> list(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.toursService.getTours(params), HttpStatus.OK);
    }
    
    @RequestMapping(path = "/products/{productId}/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Tours> details(@PathVariable(value = "productId") int id) {
        return new ResponseEntity<>(this.toursService.getToursById(id), HttpStatus.OK);
    }
    
    @RequestMapping(path = "/products/image/{productId}/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<List<TourImages>> ImageTours(@PathVariable(value = "productId") int id) {
        return new ResponseEntity<>(this.imageToursService.getTourImagesByToursId(id), HttpStatus.OK);
    }
    
    @GetMapping("/products/{productId}/comments/")
    @CrossOrigin
    public ResponseEntity<List<Comments>> listComments(@PathVariable(value = "productId") int id) {
        return new ResponseEntity<>(this.commentService.getComments(id), HttpStatus.OK);
    }
    
    @PostMapping(path="/comments/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Comments> addComment(@RequestBody Comments comment) {
        Comments c = this.commentService.addComment(comment);
        
        return new ResponseEntity<>(c, HttpStatus.CREATED);
    }
    
    @GetMapping("/products/{productId}/rating/")
    @CrossOrigin
      public ResponseEntity<Double> getTourRatingAverage(@PathVariable(value = "productId") int id) {
        double averageRating = ratingService.getRatingAverage(id);
        return new ResponseEntity<>(averageRating, HttpStatus.OK);
    }
      
    @PostMapping(path="/rating/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Rating> addRating(@RequestBody Rating rating) {
        Rating c = this.ratingService.addRating(rating);
        return new ResponseEntity<>(c, HttpStatus.CREATED);
    }
    
    @GetMapping("/news/{productId}/comments/")
    @CrossOrigin
     public ResponseEntity<List<ConmentsNews>> listCommentsNews(@PathVariable(value = "productId") int id) {
        return new ResponseEntity<>(this.commentsNewService.getComments(id), HttpStatus.OK);
    }
      
    @PostMapping(path="/commentsNews/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<ConmentsNews> addCommentsNews(@RequestBody ConmentsNews conmentsNews) {
        ConmentsNews c = this.commentsNewService.addComment(conmentsNews);
        return new ResponseEntity<>(c, HttpStatus.CREATED);
    }
}
