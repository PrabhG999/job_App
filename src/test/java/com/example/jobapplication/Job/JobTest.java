package com.example.jobapplication.Job;

import com.example.jobapplication.Company.Company;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class JobTest {

    private Job job;
    private Company company;

    @BeforeEach
    public void setUp() {
        company = new Company(); // Assuming Company has a no-arg constructor
        job = new Job();
    }

    @Test
    public void testGetIdWhenIdIsSetThenReturnId() {
        // Arrange
        int expectedId = 10;
        job.setId(expectedId);

        // Act
        int actualId = job.getId();

        // Assert
        assertThat(actualId).isEqualTo(expectedId);
    }

    @Test
    public void testSetIdThenGetIdShouldReturnSetId() {
        // Arrange
        int expectedId = 20;
        job.setId(expectedId);

        // Act
        int actualId = job.getId();

        // Assert
        assertThat(actualId).isEqualTo(expectedId);
    }

    @Test
    public void testGetTitleWhenTitleIsSetThenReturnTitle() {
        // Arrange
        String expectedTitle = "Software Engineer";
        job.setTitle(expectedTitle);

        // Act
        String actualTitle = job.getTitle();

        // Assert
        assertThat(actualTitle).isEqualTo(expectedTitle);
    }

    @Test
    public void testSetTitleThenGetTitleShouldReturnSetTitle() {
        // Arrange
        String expectedTitle = "Project Manager";
        job.setTitle(expectedTitle);

        // Act
        String actualTitle = job.getTitle();

        // Assert
        assertThat(actualTitle).isEqualTo(expectedTitle);
    }

    @Test
    public void testGetDescriptionWhenDescriptionIsSetThenReturnDescription() {
        // Arrange
        String expectedDescription = "Job requires managing projects.";
        job.setDescription(expectedDescription);

        // Act
        String actualDescription = job.getDescription();

        // Assert
        assertThat(actualDescription).isEqualTo(expectedDescription);
    }

    @Test
    public void testSetDescriptionThenGetDescriptionShouldReturnSetDescription() {
        // Arrange
        String expectedDescription = "Job involves coding and testing.";
        job.setDescription(expectedDescription);

        // Act
        String actualDescription = job.getDescription();

        // Assert
        assertThat(actualDescription).isEqualTo(expectedDescription);
    }

    @Test
    public void testGetMinSalaryWhenMinSalaryIsSetThenReturnMinSalary() {
        // Arrange
        double expectedMinSalary = 50000.0;
        job.setMinSalary(expectedMinSalary);

        // Act
        double actualMinSalary = job.getMinSalary();

        // Assert
        assertThat(actualMinSalary).isEqualTo(expectedMinSalary);
    }

    @Test
    public void testSetMinSalaryThenGetMinSalaryShouldReturnSetMinSalary() {
        // Arrange
        double expectedMinSalary = 60000.0;
        job.setMinSalary(expectedMinSalary);

        // Act
        double actualMinSalary = job.getMinSalary();

        // Assert
        assertThat(actualMinSalary).isEqualTo(expectedMinSalary);
    }

    @Test
    public void testGetMaxSalaryWhenMaxSalaryIsSetThenReturnMaxSalary() {
        // Arrange
        double expectedMaxSalary = 100000.0;
        job.setMaxSalary(expectedMaxSalary);

        // Act
        double actualMaxSalary = job.getMaxSalary();

        // Assert
        assertThat(actualMaxSalary).isEqualTo(expectedMaxSalary);
    }

    @Test
    public void testSetMaxSalaryThenGetMaxSalaryShouldReturnSetMaxSalary() {
        // Arrange
        double expectedMaxSalary = 120000.0;
        job.setMaxSalary(expectedMaxSalary);

        // Act
        double actualMaxSalary = job.getMaxSalary();

        // Assert
        assertThat(actualMaxSalary).isEqualTo(expectedMaxSalary);
    }

    @Test
    public void testGetLocationWhenLocationIsSetThenReturnLocation() {
        // Arrange
        String expectedLocation = "New York";
        job.setLocation(expectedLocation);

        // Act
        String actualLocation = job.getLocation();

        // Assert
        assertThat(actualLocation).isEqualTo(expectedLocation);
    }

    @Test
    public void testSetLocationThenGetLocationShouldReturnSetLocation() {
        // Arrange
        String expectedLocation = "San Francisco";
        job.setLocation(expectedLocation);

        // Act
        String actualLocation = job.getLocation();

        // Assert
        assertThat(actualLocation).isEqualTo(expectedLocation);
    }

    @Test
    public void testGetCompanyWhenCompanyIsSetThenReturnCompany() {
        // Arrange
        job.setCompany(company);

        // Act
        Company actualCompany = job.getCompany();

        // Assert
        assertThat(actualCompany).isEqualTo(company);
    }

    @Test
    public void testSetCompanyThenGetCompanyShouldReturnSetCompany() {
        // Arrange
        job.setCompany(company);

        // Act
        Company actualCompany = job.getCompany();

        // Assert
        assertThat(actualCompany).isEqualTo(company);
    }
}
