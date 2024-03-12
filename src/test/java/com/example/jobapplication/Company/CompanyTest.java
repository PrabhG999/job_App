package com.example.jobapplication.Company;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import com.example.jobapplication.Job.Job;
import com.example.jobapplication.Review.Review;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class CompanyTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Company#Company()}
     *   <li>{@link Company#setDescription(String)}
     *   <li>{@link Company#setId(int)}
     *   <li>{@link Company#setJobs(List)}
     *   <li>{@link Company#setName(String)}
     *   <li>{@link Company#setReview(List)}
     *   <li>{@link Company#getDescription()}
     *   <li>{@link Company#getId()}
     *   <li>{@link Company#getJobs()}
     *   <li>{@link Company#getName()}
     *   <li>{@link Company#getReview()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        Company actualCompany = new Company();
        actualCompany.setDescription("The characteristics of someone or something");
        actualCompany.setId(1);
        ArrayList<Job> jobs = new ArrayList<>();
        actualCompany.setJobs(jobs);
        actualCompany.setName("Name");
        ArrayList<Review> review = new ArrayList<>();
        actualCompany.setReview(review);
        String actualDescription = actualCompany.getDescription();
        int actualId = actualCompany.getId();
        List<Job> actualJobs = actualCompany.getJobs();
        String actualName = actualCompany.getName();

        // Assert that nothing has changed
        assertEquals("Name", actualName);
        assertEquals("The characteristics of someone or something", actualDescription);
        assertEquals(1, actualId);
        assertSame(jobs, actualJobs);
        assertSame(review, actualCompany.getReview());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Company#Company(int, String, String, List)}
     *   <li>{@link Company#setDescription(String)}
     *   <li>{@link Company#setId(int)}
     *   <li>{@link Company#setJobs(List)}
     *   <li>{@link Company#setName(String)}
     *   <li>{@link Company#setReview(List)}
     *   <li>{@link Company#getDescription()}
     *   <li>{@link Company#getId()}
     *   <li>{@link Company#getJobs()}
     *   <li>{@link Company#getName()}
     *   <li>{@link Company#getReview()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange
        ArrayList<Job> jobs = new ArrayList<>();

        // Act
        Company actualCompany = new Company(1, "Name", "The characteristics of someone or something", jobs);
        actualCompany.setDescription("The characteristics of someone or something");
        actualCompany.setId(1);
        ArrayList<Job> jobs2 = new ArrayList<>();
        actualCompany.setJobs(jobs2);
        actualCompany.setName("Name");
        ArrayList<Review> review = new ArrayList<>();
        actualCompany.setReview(review);
        String actualDescription = actualCompany.getDescription();
        int actualId = actualCompany.getId();
        List<Job> actualJobs = actualCompany.getJobs();
        String actualName = actualCompany.getName();
        List<Review> actualReview = actualCompany.getReview();

        // Assert that nothing has changed
        assertEquals("Name", actualName);
        assertEquals("The characteristics of someone or something", actualDescription);
        assertEquals(1, actualId);
        assertEquals(jobs, actualJobs);
        assertEquals(jobs, actualReview);
        assertSame(jobs2, actualJobs);
        assertSame(review, actualReview);
    }
}
