package com.example.jobapplication.Job;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.ArrayList;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {JobController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class JobControllerDiffblueTest {
    @Autowired
    private JobController jobController;

    @MockBean
    private JobService jobService;

    /**
     * Method under test: {@link JobController#findAll()}
     */
    @Test
    void testFindAll() throws Exception {
        // Arrange
        when(jobService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/jobs");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(jobController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link JobController#addJob(Job)}
     */
    @Test
    void testAddJob() throws Exception {
        // Arrange
        when(jobService.addJob(Mockito.any())).thenReturn(true);

        Job job = new Job();
        job.setDescription("The characteristics of someone or something");
        job.setId(1);
        job.setLocation("Location");
        job.setMaxSalary(10.0d);
        job.setMinSalary(10.0d);
        job.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(job);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/jobs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(jobController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Job added Successfully"));
    }

    /**
     * Method under test: {@link JobController#addJob(Job)}
     */
    @Test
    void testAddJob2() throws Exception {
        // Arrange
        when(jobService.addJob(Mockito.any())).thenReturn(false);

        Job job = new Job();
        job.setDescription("The characteristics of someone or something");
        job.setId(1);
        job.setLocation("Location");
        job.setMaxSalary(10.0d);
        job.setMinSalary(10.0d);
        job.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(job);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/jobs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(jobController).build().perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(406))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Job was not added Successfully"));
    }

    /**
     * Method under test: {@link JobController#deleteJob(int)}
     */
    @Test
    void testDeleteJob() throws Exception {
        // Arrange
        when(jobService.deleteJob(anyInt())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/jobs/{id}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(jobController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("The Job is deleted Successfully"));
    }

    /**
     * Method under test: {@link JobController#deleteJob(int)}
     */
    @Test
    void testDeleteJob2() throws Exception {
        // Arrange
        when(jobService.deleteJob(anyInt())).thenReturn(false);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/jobs/{id}", 1);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(jobController).build().perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Job Was Not Deleted , Please check the ID exists or not"));
    }

    /**
     * Method under test: {@link JobController#updateJob(int, Job)}
     */
    @Test
    void testUpdateJob() throws Exception {
        // Arrange
        when(jobService.updateJob(anyInt(), Mockito.any())).thenReturn(true);

        Job job = new Job();
        job.setDescription("The characteristics of someone or something");
        job.setId(1);
        job.setLocation("Location");
        job.setMaxSalary(10.0d);
        job.setMinSalary(10.0d);
        job.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(job);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/jobs/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(jobController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Job Updated Successfully"));
    }

    /**
     * Method under test: {@link JobController#updateJob(int, Job)}
     */
    @Test
    void testUpdateJob2() throws Exception {
        // Arrange
        when(jobService.updateJob(anyInt(), Mockito.any())).thenReturn(false);

        Job job = new Job();
        job.setDescription("The characteristics of someone or something");
        job.setId(1);
        job.setLocation("Location");
        job.setMaxSalary(10.0d);
        job.setMinSalary(10.0d);
        job.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(job);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/jobs/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(jobController).build().perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Job was not updated successfully"));
    }

    /**
     * Method under test: {@link JobController#fetchJob(int, String)}
     */
    @Test
    void testFetchJob() throws Exception {
        // Arrange
        Job job = new Job();
        job.setDescription("The characteristics of someone or something");
        job.setId(1);
        job.setLocation("Location");
        job.setMaxSalary(10.0d);
        job.setMinSalary(10.0d);
        job.setTitle("Dr");
        when(jobService.fetchJob(anyInt(), Mockito.any())).thenReturn(job);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/jobs/{id}/{title}", 1, "Dr");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(jobController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"title\":\"Dr\",\"description\":\"The characteristics of someone or something\",\"minSalary\":10.0,"
                                        + "\"maxSalary\":10.0,\"location\":\"Location\"}"));
    }

    /**
     * Method under test: {@link JobController#patchJob(int, Job)}
     */
    @Test
    void testPatchJob() throws Exception {
        // Arrange
        when(jobService.patchJob(anyInt(), Mockito.any())).thenReturn(true);

        Job job = new Job();
        job.setDescription("The characteristics of someone or something");
        job.setId(1);
        job.setLocation("Location");
        job.setMaxSalary(10.0d);
        job.setMinSalary(10.0d);
        job.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(job);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/jobs/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(jobController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Job Patched Successfully"));
    }

    /**
     * Method under test: {@link JobController#patchJob(int, Job)}
     */
    @Test
    void testPatchJob2() throws Exception {
        // Arrange
        when(jobService.patchJob(anyInt(), Mockito.any())).thenReturn(false);

        Job job = new Job();
        job.setDescription("The characteristics of someone or something");
        job.setId(1);
        job.setLocation("Location");
        job.setMaxSalary(10.0d);
        job.setMinSalary(10.0d);
        job.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(job);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/jobs/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(jobController).build().perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Job Was Not Updated"));
    }

    /**
     * Method under test: {@link JobController#findAll(int)}
     */
    @Test
    void testFindAll2() throws Exception {
        // Arrange
        Job job = new Job();
        job.setDescription("The characteristics of someone or something");
        job.setId(1);
        job.setLocation("Location");
        job.setMaxSalary(10.0d);
        job.setMinSalary(10.0d);
        job.setTitle("Dr");
        when(jobService.getJobById(anyInt())).thenReturn(job);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/jobs/{id}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(jobController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"title\":\"Dr\",\"description\":\"The characteristics of someone or something\",\"minSalary\":10.0,"
                                        + "\"maxSalary\":10.0,\"location\":\"Location\"}"));
    }
}
