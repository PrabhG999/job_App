package com.example.jobapplication.Company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CompanyDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Company#Company()}
     *   <li>{@link Company#setDescription(String)}
     *   <li>{@link Company#setId(int)}
     *   <li>{@link Company#setName(String)}
     *   <li>{@link Company#getDescription()}
     *   <li>{@link Company#getId()}
     *   <li>{@link Company#getName()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        Company actualCompany = new Company();
        actualCompany.setDescription("The characteristics of someone or something");
        actualCompany.setId(1);
        actualCompany.setName("Name");
        String actualDescription = actualCompany.getDescription();
        int actualId = actualCompany.getId();

        // Assert that nothing has changed
        assertEquals("Name", actualCompany.getName());
        assertEquals("The characteristics of someone or something", actualDescription);
        assertEquals(1, actualId);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Company#Company(int, String, String)}
     *   <li>{@link Company#setDescription(String)}
     *   <li>{@link Company#setId(int)}
     *   <li>{@link Company#setName(String)}
     *   <li>{@link Company#getDescription()}
     *   <li>{@link Company#getId()}
     *   <li>{@link Company#getName()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        Company actualCompany = new Company(1, "Name", "The characteristics of someone or something");
        actualCompany.setDescription("The characteristics of someone or something");
        actualCompany.setId(1);
        actualCompany.setName("Name");
        String actualDescription = actualCompany.getDescription();
        int actualId = actualCompany.getId();

        // Assert that nothing has changed
        assertEquals("Name", actualCompany.getName());
        assertEquals("The characteristics of someone or something", actualDescription);
        assertEquals(1, actualId);
    }
}
