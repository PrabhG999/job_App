package com.example.jobapplication.Company.Impl;

import com.example.jobapplication.Company.Company;
import com.example.jobapplication.Company.CompanyRepository;
import com.example.jobapplication.Company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    //TODO : Dependency Injection
    private final CompanyRepository companyRepository;

    @Autowired
    // it tells Spring to automatically inject the dependencies required by that service when it is instantiated.
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;

    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public boolean updateCompany(int id, @RequestBody Company updateCompany) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isPresent()) {
            Company company = companyOptional.get();
            company.setName(updateCompany.getName());
            company.setDescription(updateCompany.getDescription());
        }
        return false;
    }

    @Override
    public boolean addCompany(@RequestBody Company company) {
        if (company != null) {
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
    public boolean patchCompany(int id, @RequestBody Company company) {
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
