package com.example.jobapplication.Company.Impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.jobapplication.Company.Company;
import com.example.jobapplication.Company.CompanyRepository;
import com.example.jobapplication.Job.Job;
import com.example.jobapplication.Review.Review;

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

@ContextConfiguration(classes = {CompanyServiceImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class CompanyServiceImplDiffblueTest {
    @MockBean
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyServiceImpl companyServiceImpl;

    /**
     * Method under test: {@link CompanyServiceImpl#getAllCompanies()}
     */
    @Test
    void testGetAllCompanies() {
        // Arrange
        ArrayList<Company> companyList = new ArrayList<>();
        when(companyRepository.findAll()).thenReturn(companyList);

        // Act
        List<Company> actualAllCompanies = companyServiceImpl.getAllCompanies();

        // Assert
        verify(companyRepository).findAll();
        assertTrue(actualAllCompanies.isEmpty());
        assertSame(companyList, actualAllCompanies);
    }

    /**
     * Method under test: {@link CompanyServiceImpl#updateCompany(int, Company)}
     */
    @Test
    void testUpdateCompany() {
        // Arrange
        Company company = new Company();
        company.setDescription("The characteristics of someone or something");
        company.setId(1);
        company.setJobs(new ArrayList<>());
        company.setName("Name");
        company.setReview(new ArrayList<>());
        Optional<Company> ofResult = Optional.of(company);
        when(companyRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        Company updateCompany = new Company();
        updateCompany.setDescription("The characteristics of someone or something");
        updateCompany.setId(1);
        updateCompany.setJobs(new ArrayList<>());
        updateCompany.setName("Name");
        updateCompany.setReview(new ArrayList<>());

        // Act
        boolean actualUpdateCompanyResult = companyServiceImpl.updateCompany(1, updateCompany);

        // Assert
        verify(companyRepository).findById(Mockito.<Integer>any());
        assertFalse(actualUpdateCompanyResult);
    }

    /**
     * Method under test: {@link CompanyServiceImpl#updateCompany(int, Company)}
     */
    @Test
    void testUpdateCompany2() {
        // Arrange
        Optional<Company> emptyResult = Optional.empty();
        when(companyRepository.findById(Mockito.<Integer>any())).thenReturn(emptyResult);

        Company updateCompany = new Company();
        updateCompany.setDescription("The characteristics of someone or something");
        updateCompany.setId(1);
        updateCompany.setJobs(new ArrayList<>());
        updateCompany.setName("Name");
        updateCompany.setReview(new ArrayList<>());

        // Act
        boolean actualUpdateCompanyResult = companyServiceImpl.updateCompany(1, updateCompany);

        // Assert
        verify(companyRepository).findById(Mockito.<Integer>any());
        assertFalse(actualUpdateCompanyResult);
    }

    /**
     * Method under test: {@link CompanyServiceImpl#addCompany(Company)}
     */
    @Test
    void testAddCompany() {
        // Arrange
        Company company = new Company();
        company.setDescription("The characteristics of someone or something");
        company.setId(1);
        company.setJobs(new ArrayList<>());
        company.setName("Name");
        company.setReview(new ArrayList<>());
        when(companyRepository.save(Mockito.<Company>any())).thenReturn(company);

        Company company2 = new Company();
        company2.setDescription("The characteristics of someone or something");
        company2.setId(1);
        company2.setJobs(new ArrayList<>());
        company2.setName("Name");
        company2.setReview(new ArrayList<>());

        // Act
        boolean actualAddCompanyResult = companyServiceImpl.addCompany(company2);

        // Assert
        verify(companyRepository).save(Mockito.<Company>any());
        assertTrue(actualAddCompanyResult);
    }

    /**
     * Method under test: {@link CompanyServiceImpl#addCompany(Company)}
     */
    @Test
    void testAddCompany2() {
        // Arrange
        Company company = new Company();
        company.setDescription("The characteristics of someone or something");
        company.setId(1);
        company.setJobs(new ArrayList<>());
        company.setName("Name");
        company.setReview(new ArrayList<>());
        when(companyRepository.save(Mockito.<Company>any())).thenReturn(company);

        Company company2 = new Company();
        company2.setDescription("The characteristics of someone or something");
        company2.setId(1);
        company2.setJobs(new ArrayList<>());
        company2.setName("Name");
        company2.setReview(new ArrayList<>());

        Job job = new Job();
        job.setCompany(company2);
        job.setDescription("The characteristics of someone or something");
        job.setId(1);
        job.setLocation("Location");
        job.setMaxSalary(10.0d);
        job.setMinSalary(10.0d);
        job.setTitle("Dr");

        ArrayList<Job> jobs = new ArrayList<>();
        jobs.add(job);

        Company company3 = new Company();
        company3.setDescription("The characteristics of someone or something");
        company3.setId(1);
        company3.setJobs(jobs);
        company3.setName("Name");
        company3.setReview(new ArrayList<>());

        // Act
        boolean actualAddCompanyResult = companyServiceImpl.addCompany(company3);

        // Assert
        verify(companyRepository).save(Mockito.<Company>any());
        assertTrue(actualAddCompanyResult);
    }

    /**
     * Method under test: {@link CompanyServiceImpl#addCompany(Company)}
     */
    @Test
    void testAddCompany3() {
        // Arrange
        Company company = new Company();
        company.setDescription("The characteristics of someone or something");
        company.setId(1);
        company.setJobs(new ArrayList<>());
        company.setName("Name");
        company.setReview(new ArrayList<>());
        when(companyRepository.save(Mockito.<Company>any())).thenReturn(company);

        Company company2 = new Company();
        company2.setDescription("The characteristics of someone or something");
        company2.setId(1);
        company2.setJobs(new ArrayList<>());
        company2.setName("Name");
        company2.setReview(new ArrayList<>());

        Review review = new Review();
        review.setCompany(company2);
        review.setDescription("The characteristics of someone or something");
        review.setRating(10.0d);
        review.setReviewId(1);
        review.setTitle("Dr");

        ArrayList<Review> review2 = new ArrayList<>();
        review2.add(review);

        Company company3 = new Company();
        company3.setDescription("The characteristics of someone or something");
        company3.setId(1);
        company3.setJobs(new ArrayList<>());
        company3.setName("Name");
        company3.setReview(review2);

        // Act
        boolean actualAddCompanyResult = companyServiceImpl.addCompany(company3);

        // Assert
        verify(companyRepository).save(Mockito.<Company>any());
        assertTrue(actualAddCompanyResult);
        assertSame(company3, company3.getReview().get(0).getCompany());
    }

    /**
     * Method under test: {@link CompanyServiceImpl#deleteCompany(int)}
     */
    @Test
    void testDeleteCompany() {
        // Arrange
        doNothing().when(companyRepository).deleteById(Mockito.<Integer>any());

        // Act
        boolean actualDeleteCompanyResult = companyServiceImpl.deleteCompany(1);

        // Assert
        verify(companyRepository).deleteById(Mockito.<Integer>any());
        assertTrue(actualDeleteCompanyResult);
    }

    /**
     * Method under test: {@link CompanyServiceImpl#getAllCompaniesById(int)}
     */
    @Test
    void testGetAllCompaniesById() {
        // Arrange
        Company company = new Company();
        company.setDescription("The characteristics of someone or something");
        company.setId(1);
        company.setJobs(new ArrayList<>());
        company.setName("Name");
        company.setReview(new ArrayList<>());
        Optional<Company> ofResult = Optional.of(company);
        when(companyRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        Company actualAllCompaniesById = companyServiceImpl.getAllCompaniesById(1);

        // Assert
        verify(companyRepository).findById(Mockito.<Integer>any());
        assertSame(company, actualAllCompaniesById);
    }

    /**
     * Method under test: {@link CompanyServiceImpl#patchCompany(int, Company)}
     */
    @Test
    void testPatchCompany() {
        // Arrange
        Company company = new Company();
        company.setDescription("The characteristics of someone or something");
        company.setId(1);
        company.setJobs(new ArrayList<>());
        company.setName("Name");
        company.setReview(new ArrayList<>());
        Optional<Company> ofResult = Optional.of(company);

        Company company2 = new Company();
        company2.setDescription("The characteristics of someone or something");
        company2.setId(1);
        company2.setJobs(new ArrayList<>());
        company2.setName("Name");
        company2.setReview(new ArrayList<>());
        when(companyRepository.save(Mockito.<Company>any())).thenReturn(company2);
        when(companyRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        Company company3 = new Company();
        company3.setDescription("The characteristics of someone or something");
        company3.setId(1);
        company3.setJobs(new ArrayList<>());
        company3.setName("Name");
        company3.setReview(new ArrayList<>());

        // Act
        boolean actualPatchCompanyResult = companyServiceImpl.patchCompany(1, company3);

        // Assert
        verify(companyRepository).findById(Mockito.<Integer>any());
        verify(companyRepository).save(Mockito.<Company>any());
        assertTrue(actualPatchCompanyResult);
    }

    /**
     * Method under test: {@link CompanyServiceImpl#patchCompany(int, Company)}
     */
    @Test
    void testPatchCompany2() {
        // Arrange
        Optional<Company> emptyResult = Optional.empty();
        when(companyRepository.findById(Mockito.<Integer>any())).thenReturn(emptyResult);

        Company company = new Company();
        company.setDescription("The characteristics of someone or something");
        company.setId(1);
        company.setJobs(new ArrayList<>());
        company.setName("Name");
        company.setReview(new ArrayList<>());

        // Act
        boolean actualPatchCompanyResult = companyServiceImpl.patchCompany(1, company);

        // Assert
        verify(companyRepository).findById(Mockito.<Integer>any());
        assertFalse(actualPatchCompanyResult);
    }
}
