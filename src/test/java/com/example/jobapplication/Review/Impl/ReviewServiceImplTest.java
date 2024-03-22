package com.example.jobapplication.Review.Impl;

import com.example.jobapplication.Company.Company;
import com.example.jobapplication.Review.Review;
import com.example.jobapplication.Review.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceImplTest {

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    private Review review;
    private Company company;

    @BeforeEach
    void setUp() {
        company = new Company(1, "Test Company", "Description", null, null);
        review = new Review(1, "Great place to work", "I enjoyed working here", 5.0);
        review.setCompany(company);
    }

    @Test
    void testGetAllReviewsWhenReviewsExistThenReturnReviews() {
        List<Review> reviews = List.of(review);
        when(reviewRepository.findAll()).thenReturn(reviews);

        List<Review> result = reviewService.getAllReviews();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(reviews, result);
    }

    @Test
    void testGetAllReviewsWhenNoReviewsThenReturnEmptyList() {
        when(reviewRepository.findAll()).thenReturn(Collections.emptyList());

        List<Review> result = reviewService.getAllReviews();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testAddReviewWhenValidReviewThenReturnTrue() {
        when(reviewRepository.save(any(Review.class))).thenReturn(review);

        boolean result = reviewService.addReview(review);

        assertTrue(result);
        verify(reviewRepository).save(review);
    }

    @Test
    void testAddReviewWhenNullReviewThenReturnFalse() {
        boolean result = reviewService.addReview(null);

        assertFalse(result);
        verify(reviewRepository, never()).save(any(Review.class));
    }

    @Test
    void testGetReviewByIdWhenReviewExistsThenReturnReview() {
        when(reviewRepository.findById(review.getId())).thenReturn(Optional.of(review));

        Review result = reviewService.getReviewById(review.getId());

        assertNotNull(result);
        assertEquals(review, result);
    }

    @Test
    void testGetReviewByIdWhenReviewDoesNotExistThenReturnNull() {
        when(reviewRepository.findById(anyInt())).thenReturn(Optional.empty());

        Review result = reviewService.getReviewById(review.getId());

        assertNull(result);
    }

    @Test
    void testUpdateReviewWhenReviewExistsAndValidUpdateThenReturnTrue() {
        Review updatedReview = new Review(review.getId(), "Updated title", "Updated description", 4.0);
        when(reviewRepository.findById(review.getId())).thenReturn(Optional.of(review));
        when(reviewRepository.save(any(Review.class))).thenReturn(updatedReview);

        boolean result = reviewService.updateReview(review.getId(), updatedReview);

        assertTrue(result);
        verify(reviewRepository).save(review);
    }

    @Test
    void testUpdateReviewWhenReviewDoesNotExistThenReturnFalse() {
        when(reviewRepository.findById(anyInt())).thenReturn(Optional.empty());

        boolean result = reviewService.updateReview(review.getId(), review);

        assertFalse(result);
        verify(reviewRepository, never()).save(any(Review.class));
    }

    @Test
    void testDeleteReviewWhenReviewExistsThenReturnTrue() {
        when(reviewRepository.findById(review.getId())).thenReturn(Optional.of(review));
        doNothing().when(reviewRepository).delete(review);

        boolean result = reviewService.deleteReview(review.getId());

        assertTrue(result);
        verify(reviewRepository).delete(review);
    }

    @Test
    void testDeleteReviewWhenReviewDoesNotExistThenReturnFalse() {
        when(reviewRepository.findById(anyInt())).thenReturn(Optional.empty());

        boolean result = reviewService.deleteReview(review.getId());

        assertFalse(result);
        verify(reviewRepository, never()).delete(any(Review.class));
    }

    @Test
    void testPatchReviewWhenReviewExistsAndValidPatchThenReturnTrue() {
        Review patchReview = new Review();
        patchReview.setTitle("Patched title");
        when(reviewRepository.findById(review.getId())).thenReturn(Optional.of(review));
        when(reviewRepository.save(any(Review.class))).thenReturn(review);

        boolean result = reviewService.patchReview(review.getId(), patchReview);

        assertTrue(result);
        verify(reviewRepository).save(review);
    }

    @Test
    void testPatchReviewWhenReviewDoesNotExistThenReturnFalse() {
        Review patchReview = new Review();
        patchReview.setTitle("Patched title");
        when(reviewRepository.findById(anyInt())).thenReturn(Optional.empty());

        boolean result = reviewService.patchReview(review.getId(), patchReview);

        assertFalse(result);
        verify(reviewRepository, never()).save(any(Review.class));
    }
}
