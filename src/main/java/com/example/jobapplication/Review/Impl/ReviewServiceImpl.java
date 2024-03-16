package com.example.jobapplication.Review.Impl;

import com.example.jobapplication.Review.Review;
import com.example.jobapplication.Review.ReviewRepository;
import com.example.jobapplication.Review.ReviewService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    @Transactional
    public boolean addReview(Review review) {
        if (review != null) {
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReviewById(int id) {
        return reviewRepository.findById(id).orElse(null);
    }

    @Override
    public boolean updateReview(int id, @RequestBody Review updateReview) {
        Optional<Review> reviewOptional = reviewRepository.findById(id);
        if (reviewOptional.isPresent()) {
            Review review = reviewOptional.get();
            review.setTitle(updateReview.getTitle());
            review.setDescription(updateReview.getDescription());
            review.setRating(updateReview.getRating());
            review.setCompany(updateReview.getCompany());
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReview(int id) {
        try {
            reviewRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean patchReview(int id, @RequestBody Review patchReview) {
        Optional<Review> reviewOptional = reviewRepository.findById(id);
        if (reviewOptional.isPresent()) {
            Review existingReview = reviewOptional.get();
            if (patchReview.getTitle() != null) {
                existingReview.setTitle(patchReview.getTitle());
            }
            if (patchReview.getDescription() != null) {
                existingReview.setDescription(patchReview.getDescription());
            }
            if (patchReview.getCompany() != null) {
                existingReview.setCompany(patchReview.getCompany());
            }
            reviewRepository.save(existingReview);
            return true;
        }
        return false;
    }
}
