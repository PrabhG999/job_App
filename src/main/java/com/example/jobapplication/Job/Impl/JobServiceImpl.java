package com.example.jobapplication.Job.Impl;

import com.example.jobapplication.Job.Job;
import com.example.jobapplication.Job.JobService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
@Service
public class JobServiceImpl implements JobService {
    private final List<Job> jobs = new ArrayList<>();
    private int jobId = 1;

    public JobServiceImpl() {
        /*jobs.add(new Job(1, "Software Engineer", "Java Backend", 120000.00, 150000.00, "Los Angeles-CA"));*/
    }

    @Override
    public List<Job> findAll() {
        return jobs;
    }
    @Override
    public List<Job> getJobById(int id) {
        List<Job> fetchJob = new ArrayList<>();
        for (Job job : jobs) {
            if (job.getId() == id) {
                fetchJob.add(job);
                return fetchJob;
            }
        }
        return null;
    }
    @Override
    public boolean addJob(@RequestBody Job job) {
        if (job != null) {
            job.setId(jobId++);
            jobs.add(job);
            jobs.sort(Comparator.comparing(Job::getId));
            return true;
        } else {
            return false;
        }
    }
    @Override
    public boolean deleteJob(int id) {
        return jobs.removeIf(job -> job.getId() == id);
    }
    @Override
    public boolean updateJob(int id, @RequestBody Job updatedJob) {
        for (Job job : jobs) {
            if (job.getId() == id) {
                job.setTitle(updatedJob.getTitle());
                job.setDescription(updatedJob.getDescription());
                job.setMinSalary(updatedJob.getMinSalary());
                job.setMaxSalary(updatedJob.getMaxSalary());
                job.setLocation(updatedJob.getLocation());
                return true;
            }
        }
        return false;
    }
    @Override
    public List<Job> fetchJob(int id,String title){
        List<Job> fetchJob = new ArrayList<>();
            for(Job job:jobs){
                if(job!=null && job.getId()==id && Objects.equals(job.getTitle(), title)){
                    fetchJob.add(job);
                    return fetchJob;
                }
            }
            return null;
    }
}
