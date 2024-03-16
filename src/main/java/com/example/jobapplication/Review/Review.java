package com.example.jobapplication.Review;

import com.example.jobapplication.Company.Company;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
public class Review {

    @ManyToOne
    @JoinColumn(name = "id")
    @JsonIgnoreProperties("reviews")
    Company company;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewId;
    private String title;
    private String description;
    private double rating;

    public Review() { //JPA Constructor
    }

    public Review(int reviewId, String title, String description, double rating) {
        this.reviewId = reviewId;
        this.title = title;
        this.description = description;
        this.rating = rating;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int id) {
        this.reviewId = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
