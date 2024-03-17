package com.example.jobapplication.Review;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReviewController.class)
public class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReviewService reviewService;

    @Test
    public void testGetAllReviewsWhenReviewsExistThenReturnReviews() throws Exception {
        List<Review> reviews = Arrays.asList(
                new Review(1, "Great company", "Had a wonderful experience", 5.0),
                new Review(2, "Good workplace", "Productive and fun", 4.0)
        );
        when(reviewService.getAllReviews()).thenReturn(reviews);

        mockMvc.perform(MockMvcRequestBuilders.get("/companies/{id}/reviews", 1))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(reviews.size()));
    }

    @Test
    public void testGetAllReviewsWhenNoReviewsThenReturnEmptyList() throws Exception {
        when(reviewService.getAllReviews()).thenReturn(List.of());

        mockMvc.perform(MockMvcRequestBuilders.get("/companies/{id}/reviews", 1))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(0));
    }

    @Test
    public void testAddReviewWhenReviewAddedThenReturnSuccessMessage() throws Exception {
        when(reviewService.addReview(any(Review.class))).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/companies/{id}/reviews", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Great company\",\"description\":\"Had a wonderful experience\",\"rating\":5.0}"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Review Added Sucessfully"));
    }

    @Test
    public void testAddReviewWhenReviewNotAddedThenReturnErrorMessage() throws Exception {
        when(reviewService.addReview(any(Review.class))).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.post("/companies/{id}/reviews", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Great company\",\"description\":\"Had a wonderful experience\",\"rating\":5.0}"))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Review Was Not Added Successfully"));
    }

    @Test
    public void testGetReviewByIdWhenReviewExistsThenReturnReview() throws Exception {
        Review review = new Review(1, "Great company", "Had a wonderful experience", 5.0);
        when(reviewService.getReviewById(1)).thenReturn(review);

        mockMvc.perform(MockMvcRequestBuilders.get("/companies/{id}/reviews/{reviewId}", 1, 1))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(review.getTitle()));
    }

    @Test
    public void testGetReviewByIdWhenReviewNotExistsThenReturnNotFound() throws Exception {
        when(reviewService.getReviewById(1)).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get("/companies/{id}/reviews/{reviewId}", 1, 1))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateReviewWhenReviewUpdatedThenReturnSuccessMessage() throws Exception {
        when(reviewService.updateReview(anyInt(), any(Review.class))).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.put("/companies/{id}/reviews/{reviewId}", 1, 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Updated title\",\"description\":\"Updated description\",\"rating\":4.5}"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Review Updated SucesfFully"));
    }

    @Test
    public void testUpdateReviewWhenReviewNotUpdatedThenReturnErrorMessage() throws Exception {
        when(reviewService.updateReview(anyInt(), any(Review.class))).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.put("/companies/{id}/reviews/{reviewId}", 1, 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Updated title\",\"description\":\"Updated description\",\"rating\":4.5}"))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Review was not updated Successfully"));
    }

    @Test
    public void testDeleteReviewWhenReviewDeletedThenReturnSuccessMessage() throws Exception {
        when(reviewService.deleteReview(1)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/companies/{id}/reviews/{reviewId}", 1, 1))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("The review is deleted sucessfully"));
    }

    @Test
    public void testDeleteReviewWhenReviewNotDeletedThenReturnErrorMessage() throws Exception {
        when(reviewService.deleteReview(1)).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.delete("/companies/{id}/reviews/{reviewId}", 1, 1))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Review was not deleted sucessfully"));
    }

    @Test
    public void testPatchReviewWhenReviewPatchedThenReturnSuccessMessage() throws Exception {
        when(reviewService.patchReview(anyInt(), any(Review.class))).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.patch("/companies/{id}/reviews/{reviewId}", 1, 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Great company\",\"description\":\"Had a wonderful experience\",\"rating\":5.0}"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Review Patched Sucessfully"));
    }

    @Test
    public void testPatchReviewWhenReviewNotPatchedThenReturnErrorMessage() throws Exception {
        when(reviewService.patchReview(anyInt(), any(Review.class))).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.patch("/companies/{id}/reviews/{reviewId}", 1, 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Great company\",\"description\":\"Had a wonderful experience\",\"rating\":5.0}"))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Review was not patched Sucessfully"));
    }
}