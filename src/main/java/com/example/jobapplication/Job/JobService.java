package com.example.jobapplication.Job;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service  //always have this annotation so that SB can identify objects
public class JobService {
    private final List<Job> jobs = new ArrayList<>();
    private int jobId =1;
    public JobService() {
        /*jobs.add(new Job(1, "Software Engineer", "Java Backend", 120000.00, 150000.00, "Los Angeles-CA"));*/
    }

    public List<Job> findAll() {
        return jobs;
    }

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

    public boolean addJob(@RequestBody Job job) {
        if(job!= null){
            job.setId(jobId++);
            jobs.add(job);
            jobs.sort(Comparator.comparing(Job::getId));
            return true;
        }else{
            return false;
        }
    }

    public boolean deleteJob(int id) {
        return jobs.removeIf(job -> job.getId()==id);
    }
}

