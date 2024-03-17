package com.example.jobapplication.Company;

import com.example.jobapplication.Job.Job;
import com.example.jobapplication.Review.Review;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CompanyTest {

    private Company company;

    @BeforeEach
    public void setUp() {
        company = new Company();
    }

    @Test
    public void testGetIdWhenIdIsSetThenReturnId() {
        // Arrange
        int expectedId = 10;
        company.setId(expectedId);

        // Act
        int actualId = company.getId();

        // Assert
        Assertions.assertEquals(expectedId, actualId);
    }

    @Test
    public void testSetIdThenGetIdShouldReturnSameId() {
        // Arrange
        int expectedId = 20;

        // Act
        company.setId(expectedId);
        int actualId = company.getId();

        // Assert
        Assertions.assertEquals(expectedId, actualId);
    }

    @Test
    public void testGetReviewsWhenReviewsAreSetThenReturnReviews() {
        // Arrange
        List<Review> expectedReviews = new ArrayList<>();
        expectedReviews.add(new Review());
        company.setReviews(expectedReviews);

        // Act
        List<Review> actualReviews = company.getReviews();

        // Assert
        Assertions.assertEquals(expectedReviews, actualReviews);
    }

    @Test
    public void testSetReviewsThenGetReviewsShouldReturnSameReviews() {
        // Arrange
        List<Review> expectedReviews = new ArrayList<>();
        expectedReviews.add(new Review());

        // Act
        company.setReviews(expectedReviews);
        List<Review> actualReviews = company.getReviews();

        // Assert
        Assertions.assertEquals(expectedReviews, actualReviews);
    }

    @Test
    public void testGetNameWhenNameIsSetThenReturnName() {
        // Arrange
        String expectedName = "Test Company";
        company.setName(expectedName);

        // Act
        String actualName = company.getName();

        // Assert
        Assertions.assertEquals(expectedName, actualName);
    }

    @Test
    public void testSetNameThenGetNameShouldReturnSameName() {
        // Arrange
        String expectedName = "Another Test Company";

        // Act
        company.setName(expectedName);
        String actualName = company.getName();

        // Assert
        Assertions.assertEquals(expectedName, actualName);
    }

    @Test
    public void testGetDescriptionWhenDescriptionIsSetThenReturnDescription() {
        // Arrange
        String expectedDescription = "A company that does testing";
        company.setDescription(expectedDescription);

        // Act
        String actualDescription = company.getDescription();

        // Assert
        Assertions.assertEquals(expectedDescription, actualDescription);
    }

    @Test
    public void testSetDescriptionThenGetDescriptionShouldReturnSameDescription() {
        // Arrange
        String expectedDescription = "A company specializing in software development";

        // Act
        company.setDescription(expectedDescription);
        String actualDescription = company.getDescription();

        // Assert
        Assertions.assertEquals(expectedDescription, actualDescription);
    }

    @Test
    public void testGetJobsWhenJobsAreSetThenReturnJobs() {
        // Arrange
        List<Job> expectedJobs = new ArrayList<>();
        expectedJobs.add(new Job());
        company.setJobs(expectedJobs);

        // Act
        List<Job> actualJobs = company.getJobs();

        // Assert
        Assertions.assertEquals(expectedJobs, actualJobs);
    }

    @Test
    public void testSetJobsThenGetJobsShouldReturnSameJobs() {
        // Arrange
        List<Job> expectedJobs = new ArrayList<>();
        expectedJobs.add(new Job());

        // Act
        company.setJobs(expectedJobs);
        List<Job> actualJobs = company.getJobs();

        // Assert
        Assertions.assertEquals(expectedJobs, actualJobs);
    }
}
