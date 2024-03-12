package com.example.jobapplication.Review.Impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.jobapplication.Company.Company;
import com.example.jobapplication.Review.Review;
import com.example.jobapplication.Review.ReviewRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ReviewServiceImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ReviewServiceImplTest {
    @MockBean
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewServiceImpl reviewServiceImpl;

    /**
     * Method under test: {@link ReviewServiceImpl#getAllReviews()}
     */
    @Test
    void testGetAllReviews() {
        // Arrange
        ArrayList<Review> reviewList = new ArrayList<>();
        when(reviewRepository.findAll()).thenReturn(reviewList);

        // Act
        List<Review> actualAllReviews = reviewServiceImpl.getAllReviews();

        // Assert
        verify(reviewRepository).findAll();
        assertTrue(actualAllReviews.isEmpty());
        assertSame(reviewList, actualAllReviews);
    }

    /**
     * Method under test: {@link ReviewServiceImpl#addReview(Review)}
     */
    @Test
    void testAddReview() {
        // Arrange
        Company company = new Company();
        company.setDescription("The characteristics of someone or something");
        company.setId(1);
        company.setJobs(new ArrayList<>());
        company.setName("Name");
        company.setReview(new ArrayList<>());

        Review review = new Review();
        review.setCompany(company);
        review.setDescription("The characteristics of someone or something");
        review.setId(1);
        review.setRating(10.0d);
        review.setTitle("Dr");
        when(reviewRepository.save(Mockito.<Review>any())).thenReturn(review);

        Company company2 = new Company();
        company2.setDescription("The characteristics of someone or something");
        company2.setId(1);
        company2.setJobs(new ArrayList<>());
        company2.setName("Name");
        company2.setReview(new ArrayList<>());

        Review review2 = new Review();
        review2.setCompany(company2);
        review2.setDescription("The characteristics of someone or something");
        review2.setId(1);
        review2.setRating(10.0d);
        review2.setTitle("Dr");

        // Act
        boolean actualAddReviewResult = reviewServiceImpl.addReview(review2);

        // Assert
        verify(reviewRepository).save(Mockito.<Review>any());
        assertTrue(actualAddReviewResult);
    }

    /**
     * Method under test: {@link ReviewServiceImpl#getReviewById(int)}
     */
    @Test
    void testGetReviewById() {
        // Arrange
        Company company = new Company();
        company.setDescription("The characteristics of someone or something");
        company.setId(1);
        company.setJobs(new ArrayList<>());
        company.setName("Name");
        company.setReview(new ArrayList<>());

        Review review = new Review();
        review.setCompany(company);
        review.setDescription("The characteristics of someone or something");
        review.setId(1);
        review.setRating(10.0d);
        review.setTitle("Dr");
        Optional<Review> ofResult = Optional.of(review);
        when(reviewRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        Review actualReviewById = reviewServiceImpl.getReviewById(1);

        // Assert
        verify(reviewRepository).findById(Mockito.<Integer>any());
        assertSame(review, actualReviewById);
    }

    /**
     * Method under test: {@link ReviewServiceImpl#updateReview(int, Review)}
     */
    @Test
    void testUpdateReview() {
        // Arrange
        Company company = new Company();
        company.setDescription("The characteristics of someone or something");
        company.setId(1);
        company.setJobs(new ArrayList<>());
        company.setName("Name");
        company.setReview(new ArrayList<>());

        Review review = new Review();
        review.setCompany(company);
        review.setDescription("The characteristics of someone or something");
        review.setId(1);
        review.setRating(10.0d);
        review.setTitle("Dr");
        Optional<Review> ofResult = Optional.of(review);

        Company company2 = new Company();
        company2.setDescription("The characteristics of someone or something");
        company2.setId(1);
        company2.setJobs(new ArrayList<>());
        company2.setName("Name");
        company2.setReview(new ArrayList<>());

        Review review2 = new Review();
        review2.setCompany(company2);
        review2.setDescription("The characteristics of someone or something");
        review2.setId(1);
        review2.setRating(10.0d);
        review2.setTitle("Dr");
        when(reviewRepository.save(Mockito.<Review>any())).thenReturn(review2);
        when(reviewRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        Company company3 = new Company();
        company3.setDescription("The characteristics of someone or something");
        company3.setId(1);
        company3.setJobs(new ArrayList<>());
        company3.setName("Name");
        company3.setReview(new ArrayList<>());

        Review updateReview = new Review();
        updateReview.setCompany(company3);
        updateReview.setDescription("The characteristics of someone or something");
        updateReview.setId(1);
        updateReview.setRating(10.0d);
        updateReview.setTitle("Dr");

        // Act
        boolean actualUpdateReviewResult = reviewServiceImpl.updateReview(1, updateReview);

        // Assert
        verify(reviewRepository).findById(Mockito.<Integer>any());
        verify(reviewRepository).save(Mockito.<Review>any());
        assertTrue(actualUpdateReviewResult);
    }

    /**
     * Method under test: {@link ReviewServiceImpl#updateReview(int, Review)}
     */
    @Test
    void testUpdateReview2() {
        // Arrange
        Optional<Review> emptyResult = Optional.empty();
        when(reviewRepository.findById(Mockito.<Integer>any())).thenReturn(emptyResult);

        Company company = new Company();
        company.setDescription("The characteristics of someone or something");
        company.setId(1);
        company.setJobs(new ArrayList<>());
        company.setName("Name");
        company.setReview(new ArrayList<>());

        Review updateReview = new Review();
        updateReview.setCompany(company);
        updateReview.setDescription("The characteristics of someone or something");
        updateReview.setId(1);
        updateReview.setRating(10.0d);
        updateReview.setTitle("Dr");

        // Act
        boolean actualUpdateReviewResult = reviewServiceImpl.updateReview(1, updateReview);

        // Assert
        verify(reviewRepository).findById(Mockito.<Integer>any());
        assertFalse(actualUpdateReviewResult);
    }

    /**
     * Method under test: {@link ReviewServiceImpl#deleteReview(int)}
     */
    @Test
    void testDeleteReview() {
        // Arrange
        doNothing().when(reviewRepository).deleteById(Mockito.<Integer>any());

        // Act
        boolean actualDeleteReviewResult = reviewServiceImpl.deleteReview(1);

        // Assert
        verify(reviewRepository).deleteById(Mockito.<Integer>any());
        assertTrue(actualDeleteReviewResult);
    }

    /**
     * Method under test: {@link ReviewServiceImpl#patchReview(int, Review)}
     */
    @Test
    void testPatchReview() {
        // Arrange
        Company company = new Company();
        company.setDescription("The characteristics of someone or something");
        company.setId(1);
        company.setJobs(new ArrayList<>());
        company.setName("Name");
        company.setReview(new ArrayList<>());

        Review review = new Review();
        review.setCompany(company);
        review.setDescription("The characteristics of someone or something");
        review.setId(1);
        review.setRating(10.0d);
        review.setTitle("Dr");
        Optional<Review> ofResult = Optional.of(review);

        Company company2 = new Company();
        company2.setDescription("The characteristics of someone or something");
        company2.setId(1);
        company2.setJobs(new ArrayList<>());
        company2.setName("Name");
        company2.setReview(new ArrayList<>());

        Review review2 = new Review();
        review2.setCompany(company2);
        review2.setDescription("The characteristics of someone or something");
        review2.setId(1);
        review2.setRating(10.0d);
        review2.setTitle("Dr");
        when(reviewRepository.save(Mockito.<Review>any())).thenReturn(review2);
        when(reviewRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        Company company3 = new Company();
        company3.setDescription("The characteristics of someone or something");
        company3.setId(1);
        company3.setJobs(new ArrayList<>());
        company3.setName("Name");
        company3.setReview(new ArrayList<>());

        Review patchReview = new Review();
        patchReview.setCompany(company3);
        patchReview.setDescription("The characteristics of someone or something");
        patchReview.setId(1);
        patchReview.setRating(10.0d);
        patchReview.setTitle("Dr");

        // Act
        boolean actualPatchReviewResult = reviewServiceImpl.patchReview(1, patchReview);

        // Assert
        verify(reviewRepository).findById(Mockito.<Integer>any());
        verify(reviewRepository).save(Mockito.<Review>any());
        assertTrue(actualPatchReviewResult);
    }

    /**
     * Method under test: {@link ReviewServiceImpl#patchReview(int, Review)}
     */
    @Test
    void testPatchReview2() {
        // Arrange
        Optional<Review> emptyResult = Optional.empty();
        when(reviewRepository.findById(Mockito.<Integer>any())).thenReturn(emptyResult);

        Company company = new Company();
        company.setDescription("The characteristics of someone or something");
        company.setId(1);
        company.setJobs(new ArrayList<>());
        company.setName("Name");
        company.setReview(new ArrayList<>());

        Review patchReview = new Review();
        patchReview.setCompany(company);
        patchReview.setDescription("The characteristics of someone or something");
        patchReview.setId(1);
        patchReview.setRating(10.0d);
        patchReview.setTitle("Dr");

        // Act
        boolean actualPatchReviewResult = reviewServiceImpl.patchReview(1, patchReview);

        // Assert
        verify(reviewRepository).findById(Mockito.<Integer>any());
        assertFalse(actualPatchReviewResult);
    }
}
