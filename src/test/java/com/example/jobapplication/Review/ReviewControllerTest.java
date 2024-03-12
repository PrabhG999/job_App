package com.example.jobapplication.Review;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

import com.example.jobapplication.Company.Company;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ReviewController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ReviewControllerTest {
    @Autowired
    private ReviewController reviewController;

    @MockBean
    private ReviewService reviewService;

    /**
     * Method under test: {@link ReviewController#getAllReviews()}
     */
    @Test
    void testGetAllReviews() throws Exception {
        // Arrange
        when(reviewService.getAllReviews()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/reviews");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(reviewController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ReviewController#addReview(Review)}
     */
    @Test
    void testAddReview() throws Exception {
        // Arrange
        when(reviewService.addReview(Mockito.<Review>any())).thenReturn(true);

        Company company = new Company();
        company.setDescription("The characteristics of someone or something");
        company.setId(1);
        company.setJobs(new ArrayList<>());
        company.setName("Name");
        company.setReview(new ArrayList<>());

        Review review = new Review();
        review.setCompany(company);
        review.setDescription("The characteristics of someone or something");
        review.setId(1);
        review.setRating(10.0d);
        review.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(review);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(reviewController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Review Added Sucessfully"));
    }

    /**
     * Method under test: {@link ReviewController#addReview(Review)}
     */
    @Test
    void testAddReview2() throws Exception {
        // Arrange
        when(reviewService.addReview(Mockito.<Review>any())).thenReturn(false);

        Company company = new Company();
        company.setDescription("The characteristics of someone or something");
        company.setId(1);
        company.setJobs(new ArrayList<>());
        company.setName("Name");
        company.setReview(new ArrayList<>());

        Review review = new Review();
        review.setCompany(company);
        review.setDescription("The characteristics of someone or something");
        review.setId(1);
        review.setRating(10.0d);
        review.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(review);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(reviewController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Review Was Not Added Successfully"));
    }

    /**
     * Method under test: {@link ReviewController#getReviewById(int)}
     */
    @Test
    void testGetReviewById() throws Exception {
        // Arrange
        Company company = new Company();
        company.setDescription("The characteristics of someone or something");
        company.setId(1);
        company.setJobs(new ArrayList<>());
        company.setName("Name");
        company.setReview(new ArrayList<>());

        Review review = new Review();
        review.setCompany(company);
        review.setDescription("The characteristics of someone or something");
        review.setId(1);
        review.setRating(10.0d);
        review.setTitle("Dr");
        when(reviewService.getReviewById(anyInt())).thenReturn(review);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/reviews/{id}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(reviewController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"title\":\"Dr\",\"description\":\"The characteristics of someone or something\",\"rating\":10.0,\"company"
                                        + "\":{\"id\":1,\"name\":\"Name\",\"description\":\"The characteristics of someone or something\",\"jobs\":[],\"review"
                                        + "\":[]}}"));
    }

    /**
     * Method under test: {@link ReviewController#updateReview(int, Review)}
     */
    @Test
    void testUpdateReview() throws Exception {
        // Arrange
        when(reviewService.updateReview(anyInt(), Mockito.<Review>any())).thenReturn(true);

        Company company = new Company();
        company.setDescription("The characteristics of someone or something");
        company.setId(1);
        company.setJobs(new ArrayList<>());
        company.setName("Name");
        company.setReview(new ArrayList<>());

        Review review = new Review();
        review.setCompany(company);
        review.setDescription("The characteristics of someone or something");
        review.setId(1);
        review.setRating(10.0d);
        review.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(review);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/reviews/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(reviewController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Review Updated SucesfFully"));
    }

    /**
     * Method under test: {@link ReviewController#updateReview(int, Review)}
     */
    @Test
    void testUpdateReview2() throws Exception {
        // Arrange
        when(reviewService.updateReview(anyInt(), Mockito.<Review>any())).thenReturn(false);

        Company company = new Company();
        company.setDescription("The characteristics of someone or something");
        company.setId(1);
        company.setJobs(new ArrayList<>());
        company.setName("Name");
        company.setReview(new ArrayList<>());

        Review review = new Review();
        review.setCompany(company);
        review.setDescription("The characteristics of someone or something");
        review.setId(1);
        review.setRating(10.0d);
        review.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(review);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/reviews/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(reviewController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Review was not updated Successfully"));
    }

    /**
     * Method under test: {@link ReviewController#deleteReview(int)}
     */
    @Test
    void testDeleteReview() throws Exception {
        // Arrange
        when(reviewService.deleteReview(anyInt())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/reviews/{id}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(reviewController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("The review is deleted sucessfully"));
    }

    /**
     * Method under test: {@link ReviewController#deleteReview(int)}
     */
    @Test
    void testDeleteReview2() throws Exception {
        // Arrange
        when(reviewService.deleteReview(anyInt())).thenReturn(false);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/reviews/{id}", 1);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(reviewController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Review was not deleted sucessfully"));
    }

    /**
     * Method under test: {@link ReviewController#patchReview(int, Review)}
     */
    @Test
    void testPatchReview() throws Exception {
        // Arrange
        when(reviewService.patchReview(anyInt(), Mockito.<Review>any())).thenReturn(true);

        Company company = new Company();
        company.setDescription("The characteristics of someone or something");
        company.setId(1);
        company.setJobs(new ArrayList<>());
        company.setName("Name");
        company.setReview(new ArrayList<>());

        Review review = new Review();
        review.setCompany(company);
        review.setDescription("The characteristics of someone or something");
        review.setId(1);
        review.setRating(10.0d);
        review.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(review);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/reviews/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(reviewController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Review Patched Sucessfully"));
    }

    /**
     * Method under test: {@link ReviewController#patchReview(int, Review)}
     */
    @Test
    void testPatchReview2() throws Exception {
        // Arrange
        when(reviewService.patchReview(anyInt(), Mockito.<Review>any())).thenReturn(false);

        Company company = new Company();
        company.setDescription("The characteristics of someone or something");
        company.setId(1);
        company.setJobs(new ArrayList<>());
        company.setName("Name");
        company.setReview(new ArrayList<>());

        Review review = new Review();
        review.setCompany(company);
        review.setDescription("The characteristics of someone or something");
        review.setId(1);
        review.setRating(10.0d);
        review.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(review);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/reviews/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(reviewController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Review was not patched Sucessfully"));
    }
}
