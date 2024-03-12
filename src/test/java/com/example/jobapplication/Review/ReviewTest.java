package com.example.jobapplication.Review;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import com.example.jobapplication.Company.Company;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class ReviewTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Review#Review()}
     *   <li>{@link Review#setCompany(Company)}
     *   <li>{@link Review#setDescription(String)}
     *   <li>{@link Review#setId(int)}
     *   <li>{@link Review#setRating(double)}
     *   <li>{@link Review#setTitle(String)}
     *   <li>{@link Review#getCompany()}
     *   <li>{@link Review#getDescription()}
     *   <li>{@link Review#getId()}
     *   <li>{@link Review#getRating()}
     *   <li>{@link Review#getTitle()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        Review actualReview = new Review();
        Company company = new Company();
        company.setDescription("The characteristics of someone or something");
        company.setId(1);
        company.setJobs(new ArrayList<>());
        company.setName("Name");
        company.setReview(new ArrayList<>());
        actualReview.setCompany(company);
        actualReview.setDescription("The characteristics of someone or something");
        actualReview.setId(1);
        actualReview.setRating(10.0d);
        actualReview.setTitle("Dr");
        Company actualCompany = actualReview.getCompany();
        String actualDescription = actualReview.getDescription();
        int actualId = actualReview.getId();
        double actualRating = actualReview.getRating();

        // Assert that nothing has changed
        assertEquals("Dr", actualReview.getTitle());
        assertEquals("The characteristics of someone or something", actualDescription);
        assertEquals(1, actualId);
        assertEquals(10.0d, actualRating);
        assertSame(company, actualCompany);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Review#Review(int, String, String, double)}
     *   <li>{@link Review#setCompany(Company)}
     *   <li>{@link Review#setDescription(String)}
     *   <li>{@link Review#setId(int)}
     *   <li>{@link Review#setRating(double)}
     *   <li>{@link Review#setTitle(String)}
     *   <li>{@link Review#getCompany()}
     *   <li>{@link Review#getDescription()}
     *   <li>{@link Review#getId()}
     *   <li>{@link Review#getRating()}
     *   <li>{@link Review#getTitle()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        Review actualReview = new Review(1, "Dr", "The characteristics of someone or something", 10.0d);
        Company company = new Company();
        company.setDescription("The characteristics of someone or something");
        company.setId(1);
        company.setJobs(new ArrayList<>());
        company.setName("Name");
        company.setReview(new ArrayList<>());
        actualReview.setCompany(company);
        actualReview.setDescription("The characteristics of someone or something");
        actualReview.setId(1);
        actualReview.setRating(10.0d);
        actualReview.setTitle("Dr");
        Company actualCompany = actualReview.getCompany();
        String actualDescription = actualReview.getDescription();
        int actualId = actualReview.getId();
        double actualRating = actualReview.getRating();

        // Assert that nothing has changed
        assertEquals("Dr", actualReview.getTitle());
        assertEquals("The characteristics of someone or something", actualDescription);
        assertEquals(1, actualId);
        assertEquals(10.0d, actualRating);
        assertSame(company, actualCompany);
    }
}
