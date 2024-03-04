package com.example.jobapplication.Job;

import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


public interface JobService {


    public List<Job> findAll();

    public List<Job> getJobById(int id);

    public boolean addJob(@RequestBody Job job);

    public boolean deleteJob(int id);

    public boolean updateJob(int id, @RequestBody Job updatedJob);

    public List<Job> fetchJob(int id, String title);
}

