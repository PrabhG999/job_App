package com.example.jobapplication.Job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private final JobService jobService; //object of challenge service creating a dependency injection using Spring IOC

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<Job>> findAll() {
        List<Job> jobs = jobService.findAll();
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Job>> findAll(@PathVariable int id) {
        List<Job> getJobById = jobService.getJobById(id);
        if (getJobById != null) {
            return new ResponseEntity<>(getJobById, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping
    public ResponseEntity<String> addJob(@RequestBody Job job) {
        boolean isJobAdded = jobService.addJob(job);
        if (isJobAdded) {
            return new ResponseEntity<>("Job added Successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Job was not added Successfully", HttpStatus.NOT_ACCEPTABLE);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable int id){
        boolean isDeleted = jobService.deleteJob(id);
        if(isDeleted){
            return new ResponseEntity<>("The Job is deleted Suscessfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Job Was Not Deleted , Please check the ID exists or not",HttpStatus.BAD_REQUEST);
    }

}
