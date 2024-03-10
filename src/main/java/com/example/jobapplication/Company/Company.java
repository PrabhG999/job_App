package com.example.jobapplication.Company;

import com.example.jobapplication.Job.Job;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //defining aunique id dor Dbase
    private int id;
    private String name;
    private String description;
    //map every company to list of jobs
    @OneToMany // - means one company have many jbs
    // TODO: Need to Map Each company to Jobs
    private List<Job> jobs;
    // @OneToMany TODO: Need to Map Each Review to Jobs
    /*private List<Job> jobs;*/


    public Company() { //constructor for JPA
    }

    public Company(int id, String name, String description) { //constructor for CompanyIMPL
        this.id = id;
        this.name = name;
        this.description = description;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

}
