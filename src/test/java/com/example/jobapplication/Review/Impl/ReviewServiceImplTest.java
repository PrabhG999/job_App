package com.example.jobapplication.Review.Impl;

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

    @BeforeEach
    void setUp() {
        review = new Review(1, "Great Company", "I had a good experience working here.", 4.5);
    }

    @Test
    void testGetAllReviewsWhenReviewsExistThenReturnReviews() {
        // Arrange
        when(reviewRepository.findAll()).thenReturn(List.of(review));

        // Act
        List<Review> reviews = reviewService.getAllReviews();

        // Assert
        assertFalse(reviews.isEmpty());
        assertEquals(1, reviews.size());
        assertEquals(review, reviews.get(0));
    }

    @Test
    void testGetAllReviewsWhenNoReviewsThenReturnEmptyList() {
        // Arrange
        when(reviewRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        List<Review> reviews = reviewService.getAllReviews();

        // Assert
        assertTrue(reviews.isEmpty());
    }

    @Test
    void testAddReviewWhenValidReviewThenReturnTrue() {
        // Arrange
        when(reviewRepository.save(any(Review.class))).thenReturn(review);

        // Act
        boolean result = reviewService.addReview(review);

        // Assert
        assertTrue(result);
    }

    @Test
    void testAddReviewWhenNullReviewThenReturnFalse() {
        // Act
        boolean result = reviewService.addReview(null);

        // Assert
        assertFalse(result);
    }

    @Test
    void testGetReviewByIdWhenReviewExistsThenReturnReview() {
        // Arrange
        when(reviewRepository.findById(review.getId())).thenReturn(Optional.of(review));

        // Act
        Review foundReview = reviewService.getReviewById(review.getId());

        // Assert
        assertNotNull(foundReview);
        assertEquals(review.getId(), foundReview.getId());
    }

    @Test
    void testGetReviewByIdWhenReviewDoesNotExistThenReturnNull() {
        // Arrange
        when(reviewRepository.findById(anyInt())).thenReturn(Optional.empty());

        // Act
        Review foundReview = reviewService.getReviewById(1);

        // Assert
        assertNull(foundReview);
    }

    @Test
    void testUpdateReviewWhenReviewExistsThenReturnTrue() {
        // Arrange
        when(reviewRepository.findById(review.getId())).thenReturn(Optional.of(review));
        when(reviewRepository.save(any(Review.class))).thenReturn(review);

        // Act
        boolean result = reviewService.updateReview(review.getId(), review);

        // Assert
        assertTrue(result);
    }

    @Test
    void testUpdateReviewWhenReviewDoesNotExistThenReturnFalse() {
        // Arrange
        when(reviewRepository.findById(anyInt())).thenReturn(Optional.empty());

        // Act
        boolean result = reviewService.updateReview(1, review);

        // Assert
        assertFalse(result);
    }

    @Test
    void testDeleteReviewWhenReviewExistsThenReturnTrue() {
        // Arrange
        doNothing().when(reviewRepository).deleteById(anyInt());

        // Act
        boolean result = reviewService.deleteReview(review.getId());

        // Assert
        assertTrue(result);
    }

    @Test
    void testDeleteReviewWhenReviewDoesNotExistThenReturnFalse() {
        // Arrange
        doThrow(new RuntimeException()).when(reviewRepository).deleteById(anyInt());

        // Act
        boolean result = reviewService.deleteReview(review.getId());

        // Assert
        assertFalse(result);
    }

    @Test
    void testPatchReviewWhenReviewExistsThenReturnTrue() {
        // Arrange
        when(reviewRepository.findById(review.getId())).thenReturn(Optional.of(review));
        when(reviewRepository.save(any(Review.class))).thenReturn(review);

        // Act
        boolean result = reviewService.patchReview(review.getId(), review);

        // Assert
        assertTrue(result);
    }

    @Test
    void testPatchReviewWhenReviewDoesNotExistThenReturnFalse() {
        // Arrange
        when(reviewRepository.findById(anyInt())).thenReturn(Optional.empty());

        // Act
        boolean result = reviewService.patchReview(1, review);

        // Assert
        assertFalse(result);
    }
}
