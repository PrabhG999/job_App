package com.example.jobapplication.Job.Impl;

import com.example.jobapplication.Job.Job;
import com.example.jobapplication.Job.JobRepository;
import com.example.jobapplication.Job.JobService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Service
public class JobServiceImpl implements JobService {
    //private final List<Job> jobs = new ArrayList<>(); //disable array list bcs we making JPA methods
    //define repo obj
    JobRepository jobRepository; //JPA a Bean managed by SPRING it will be autowired at RUNTIME
    public JobServiceImpl(JobRepository jobRepository) { //Dependency Injection
        this.jobRepository = jobRepository;
    }


    public JobServiceImpl() {
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public Job getJobById(int id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean addJob(@RequestBody Job job) {
        if (job != null) {
           jobRepository.save(job);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteJob(int id) {
        /*return jobs.removeIf(job -> job.getId() == id);*/ //Lambda Expression to Delete job by id
        try {
            jobRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updateJob(int id, @RequestBody Job updatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
            if (jobOptional.isPresent()) {
                Job job = jobOptional.get(); //getting the Job object from the optional
                job.setTitle(updatedJob.getTitle());
                job.setDescription(updatedJob.getDescription());
                job.setMinSalary(updatedJob.getMinSalary());
                job.setMaxSalary(updatedJob.getMaxSalary());
                job.setLocation(updatedJob.getLocation());
                return true;

        }
        return false;
    }

    @Override
    public Job fetchJob(int id, String title) {
        return jobRepository.findByIdAndTitle(id,title);
    }
}
