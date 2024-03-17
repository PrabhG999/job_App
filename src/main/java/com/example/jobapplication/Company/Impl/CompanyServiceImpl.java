package com.example.jobapplication.Company.Impl;

import com.example.jobapplication.Company.Company;
import com.example.jobapplication.Company.CompanyRepository;
import com.example.jobapplication.Company.CompanyService;
import com.example.jobapplication.Job.Job;
import com.example.jobapplication.Job.JobRepository;
import com.example.jobapplication.Review.Review;
import com.example.jobapplication.Review.ReviewRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    //TODO : Dependency Injection
    private final CompanyRepository companyRepository;

    private final JobRepository jobRepository;

    private final ReviewRepository reviewRepository;

    // it tells Spring to automatically inject the dependencies required by that service when it is instantiated.
    public CompanyServiceImpl(CompanyRepository companyRepository, JobRepository jobRepository, ReviewRepository reviewRepository, JobRepository jobRepository1, ReviewRepository reviewRepository1) {
        this.companyRepository = companyRepository;
        this.jobRepository = jobRepository;
        this.reviewRepository = reviewRepository;
    }


    @Override
    @Transactional
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public boolean updateCompany(int id, Company updateCompany) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isPresent()) {
            Company company = companyOptional.get();
            company.setName(updateCompany.getName());
            company.setDescription(updateCompany.getDescription());
            companyRepository.save(company);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean addCompany(Company company) {
        if (company != null) {
            List<Job> jobsToUpdate = new ArrayList<>();
            if (company.getJobs() != null) {
                for (Job job : company.getJobs()) {
                    // Fetch existing job from the database by ID
                    jobRepository.findById(job.getId()).ifPresent(existingJob -> {
                        existingJob.setCompany(company);
                        jobsToUpdate.add(existingJob);
                    });
                }
                company.setJobs(jobsToUpdate); // Set the updated jobs list to the company
            }

            List<Review> reviewsToUpdate = new ArrayList<>();
            if (company.getReviews() != null) {
                for (Review reviews : company.getReviews()) {
                    // Fetch existing review from the database by ID
                    reviewRepository.findById(reviews.getId()).ifPresent(existingReview -> {
                        existingReview.setCompany(company);
                        reviewsToUpdate.add(existingReview);
                        reviewRepository.save(existingReview);
                    });
                }
                company.setReviews(reviewsToUpdate);
            }

            companyRepository.save(company);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteCompany(int id) {
        try {
            companyRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Company getAllCompaniesById(int id) {
        return companyRepository.findById(id).orElse(null);
    }


    @Override
    public boolean patchCompany(int id, Company company) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isPresent()) {
            Company existingCompany = companyOptional.get();
            if (company.getName() != null) {
                existingCompany.setName(company.getName());
            }
            if (company.getDescription() != null) {
                existingCompany.setDescription(company.getDescription());
            }
            companyRepository.save(existingCompany);
            return true;
        }
        return false;
    }
}
