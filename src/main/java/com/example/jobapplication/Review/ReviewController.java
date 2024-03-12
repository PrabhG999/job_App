package com.example.jobapplication.Review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {          //created dependency of review service for controller
        this.reviewService = reviewService;
    }
    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(){
        List<Review> reviews = reviewService.getAllReviews();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addReview(@RequestBody Review review){
        boolean isReviewAdded = reviewService.addReview(review);
        if(isReviewAdded){
            return new ResponseEntity<>("Review Added Sucessfully",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Review Was Not Added Successfully",HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable int id){
        Review reviewById = reviewService.getReviewById(id);
        if(reviewById!= null){
            return new ResponseEntity<>(reviewById,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateReview(@PathVariable int id,@RequestBody Review review){
        boolean isUpdated = reviewService.updateReview(id,review);
        if(isUpdated){
            return new ResponseEntity<>("Review Updated SucesfFully",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Review was not updated Successfully",HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable int id){
        boolean isDeleted = reviewService.deleteReview(id);
        if(isDeleted){
            return new ResponseEntity<>("The review is deleted sucessfully",HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Review was not deleted sucessfully",HttpStatus.BAD_REQUEST);
        }
    }
    @PatchMapping("/{id}")
    public ResponseEntity<String> patchReview(@PathVariable int id,@RequestBody Review review){
        boolean isPatched = reviewService.patchReview(id,review);
        if(isPatched){
            return new ResponseEntity<>("Review Patched Sucessfully",HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Review was not patched Sucessfully",HttpStatus.BAD_REQUEST);
        }
    }
}
