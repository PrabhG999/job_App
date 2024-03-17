package com.example.jobapplication.Company.Impl;

import com.example.jobapplication.Company.Company;
import com.example.jobapplication.Company.CompanyRepository;
import com.example.jobapplication.Job.Job;
import com.example.jobapplication.Job.JobRepository;
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
public class CompanyServiceImplTest {

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private JobRepository jobRepository;

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private CompanyServiceImpl companyService;

    private Company company;
    private Job job;
    private Review review;

    @BeforeEach
    void setUp() {
        company = new Company(1, "Test Company", "A company for testing", null, null);
        job = new Job(1, "Test Job", "A job for testing", 50000, 100000, "Test Location");
        review = new Review(1, "Test Review", "A review for testing", 5.0);
    }

    @Test
    void testGetAllCompaniesWhenCompaniesExistThenReturnListOfCompanies() {
        // Arrange
        when(companyRepository.findAll()).thenReturn(Collections.singletonList(company));

        // Act
        List<Company> companies = companyService.getAllCompanies();

        // Assert
        assertNotNull(companies);
        assertFalse(companies.isEmpty());
        assertEquals(1, companies.size());
        assertEquals(company, companies.get(0));
    }

    @Test
    void testGetAllCompaniesWhenNoCompaniesExistThenReturnEmptyList() {
        // Arrange
        when(companyRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        List<Company> companies = companyService.getAllCompanies();

        // Assert
        assertNotNull(companies);
        assertTrue(companies.isEmpty());
    }

    @Test
    void testGetAllCompaniesByIdWhenCompanyExistsThenReturnCompany() {
        // Arrange
        when(companyRepository.findById(company.getId())).thenReturn(Optional.of(company));

        // Act
        Company foundCompany = companyService.getAllCompaniesById(company.getId());

        // Assert
        assertNotNull(foundCompany);
        assertEquals(company, foundCompany);
    }

    @Test
    void testGetAllCompaniesByIdWhenCompanyDoesNotExistThenReturnNull() {
        // Arrange
        when(companyRepository.findById(anyInt())).thenReturn(Optional.empty());

        // Act
        Company foundCompany = companyService.getAllCompaniesById(company.getId());

        // Assert
        assertNull(foundCompany);
    }

    @Test
    void testDeleteCompanyWhenCompanyExistsThenReturnTrue() {
        // Arrange
        doNothing().when(companyRepository).deleteById(company.getId());

        // Act
        boolean result = companyService.deleteCompany(company.getId());

        // Assert
        assertTrue(result);
    }

    @Test
    void testDeleteCompanyWhenExceptionOccursThenReturnFalse() {
        // Arrange
        doThrow(new RuntimeException()).when(companyRepository).deleteById(anyInt());

        // Act
        boolean result = companyService.deleteCompany(company.getId());

        // Assert
        assertFalse(result);
    }

    @Test
    void testAddCompanyWhenCompanyIsValidThenReturnTrue() {
        // Arrange
        when(companyRepository.save(any(Company.class))).thenReturn(company);

        // Act
        boolean result = companyService.addCompany(company);

        // Assert
        assertTrue(result);
    }

    @Test
    void testAddCompanyWhenCompanyIsNullThenReturnFalse() {
        // Act
        boolean result = companyService.addCompany(null);

        // Assert
        assertFalse(result);
    }

    @Test
    void testPatchCompanyWhenCompanyExistsThenReturnTrue() {
        // Arrange
        when(companyRepository.findById(company.getId())).thenReturn(Optional.of(company));
        when(companyRepository.save(any(Company.class))).thenReturn(company);

        // Act
        boolean result = companyService.patchCompany(company.getId(), company);

        // Assert
        assertTrue(result);
    }

    @Test
    void testPatchCompanyWhenCompanyDoesNotExistThenReturnFalse() {
        // Arrange
        when(companyRepository.findById(anyInt())).thenReturn(Optional.empty());

        // Act
        boolean result = companyService.patchCompany(company.getId(), company);

        // Assert
        assertFalse(result);
    }
}
