package com.example.jobapplication.Review;

import com.example.jobapplication.Company.Company;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ReviewTest {

    private Review review;
    private Company mockCompany;

    @BeforeEach
    void setUp() {
        review = new Review();
        mockCompany = new Company(); // Assuming Company has a no-arg constructor
    }

    @Test
    void testSetAndGetId() {
        // Arrange
        int expectedId = 10;
        review.setId(expectedId);

        // Act
        int actualId = review.getId();

        // Assert
        assertThat(actualId).isEqualTo(expectedId);
    }

    @Test
    void testSetAndGetTitle() {
        // Arrange
        String expectedTitle = "Excellent workplace";
        review.setTitle(expectedTitle);

        // Act
        String actualTitle = review.getTitle();

        // Assert
        assertThat(actualTitle).isEqualTo(expectedTitle);
    }

    @Test
    void testSetAndGetDescription() {
        // Arrange
        String expectedDescription = "The work environment is very positive and encouraging.";
        review.setDescription(expectedDescription);

        // Act
        String actualDescription = review.getDescription();

        // Assert
        assertThat(actualDescription).isEqualTo(expectedDescription);
    }

    @Test
    void testSetAndGetRating() {
        // Arrange
        double expectedRating = 4.5;
        review.setRating(expectedRating);

        // Act
        double actualRating = review.getRating();

        // Assert
        assertThat(actualRating).isEqualTo(expectedRating);
    }

    @Test
    void testSetAndGetCompany() {
        // Arrange
        review.setCompany(mockCompany);

        // Act
        Company actualCompany = review.getCompany();

        // Assert
        assertThat(actualCompany).isEqualTo(mockCompany);
    }
}
