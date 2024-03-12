package com.example.jobapplication.Job;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.jobapplication.Company.Company;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class JobTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Job#Job()}
     *   <li>{@link Job#setCompany(Company)}
     *   <li>{@link Job#setDescription(String)}
     *   <li>{@link Job#setId(int)}
     *   <li>{@link Job#setLocation(String)}
     *   <li>{@link Job#setMaxSalary(double)}
     *   <li>{@link Job#setMinSalary(double)}
     *   <li>{@link Job#setTitle(String)}
     *   <li>{@link Job#getCompany()}
     *   <li>{@link Job#getDescription()}
     *   <li>{@link Job#getId()}
     *   <li>{@link Job#getLocation()}
     *   <li>{@link Job#getMaxSalary()}
     *   <li>{@link Job#getMinSalary()}
     *   <li>{@link Job#getTitle()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        Job actualJob = new Job();
        Company company = new Company();
        company.setDescription("The characteristics of someone or something");
        company.setId(1);
        company.setJobs(new ArrayList<>());
        company.setName("Name");
        company.setReview(new ArrayList<>());
        actualJob.setCompany(company);
        actualJob.setDescription("The characteristics of someone or something");
        actualJob.setId(1);
        actualJob.setLocation("Location");
        actualJob.setMaxSalary(10.0d);
        actualJob.setMinSalary(10.0d);
        actualJob.setTitle("Dr");
        Company actualCompany = actualJob.getCompany();
        String actualDescription = actualJob.getDescription();
        int actualId = actualJob.getId();
        String actualLocation = actualJob.getLocation();
        double actualMaxSalary = actualJob.getMaxSalary();
        double actualMinSalary = actualJob.getMinSalary();

        // Assert that nothing has changed
        assertEquals("Dr", actualJob.getTitle());
        assertEquals("Location", actualLocation);
        assertEquals("The characteristics of someone or something", actualDescription);
        assertEquals(1, actualId);
        assertEquals(10.0d, actualMaxSalary);
        assertEquals(10.0d, actualMinSalary);
        assertSame(company, actualCompany);
    }

    /**
     * Method under test:
     * {@link Job#Job(int, String, String, double, double, String)}
     */
    @Test
    void testNewJob() {
        // Arrange and Act
        Job actualJob = new Job(1, "Dr", "The characteristics of someone or something", 10.0d, 10.0d, "Location");

        // Assert
        assertEquals("Dr", actualJob.getTitle());
        assertEquals("Location", actualJob.getLocation());
        assertEquals("The characteristics of someone or something", actualJob.getDescription());
        assertEquals(1, actualJob.getId());
        assertEquals(10.0d, actualJob.getMaxSalary());
        assertEquals(10.0d, actualJob.getMinSalary());
    }

    /**
     * Method under test:
     * {@link Job#Job(int, String, String, double, double, String)}
     */
    @Test
    void testNewJob2() {
        // Arrange, Act and Assert
        assertThrows(IllegalArgumentException.class, () -> new Job(1, "foo", "foo", 0.0d, -1.0E-10d, "foo"));

    }

    /**
     * Method under test:
     * {@link Job#Job(int, String, String, double, double, String)}
     */
    @Test
    void testNewJob3() {
        // Arrange, Act and Assert
        assertThrows(IllegalArgumentException.class, () -> new Job(1, "foo", "foo", -1.0E-10d, 0.0d, "foo"));

    }

    /**
     * Method under test:
     * {@link Job#Job(int, String, String, double, double, String)}
     */
    @Test
    void testNewJob4() {
        // Arrange, Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> new Job(1, "Dr", "The characteristics of someone or something", 10.0d, 0.0d, "Location"));

    }
}
