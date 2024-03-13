package com.example.jobapplication.Company;

import com.example.jobapplication.Job.Job;
import com.example.jobapplication.Review.Review;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //defining aunique id dor Dbase
    private int id;
    private String name;
    private String description;


    //map every company to list of jobs
    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("company")
    List<Job> jobs;

    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("company")
    List<Review> review;

    public Company() { //constructor for JPA
    }

    public Company(int id, String name, String description, List<Job> jobs,List<Review> review) { //constructor for CompanyIMPL
        this.id = id;
        this.name = name;
        this.description = description;
        this.jobs=jobs;
        this.review=review;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Review> getReview() {
        return review;
    }

    public void setReview(List<Review> review) {
        this.review = review;
    }
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
}
