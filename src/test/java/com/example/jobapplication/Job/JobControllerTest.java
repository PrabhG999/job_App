package com.example.jobapplication.Job;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest(JobController.class)
public class JobControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JobService jobService;

    @Test
    public void testFindAllWhenCalledThenReturnListOfJobs() throws Exception {
        List<Job> jobs = Arrays.asList(
                new Job(1, "Software Engineer", "Develop software", 50000, 100000, "New York"),
                new Job(2, "QA Engineer", "Test software", 40000, 80000, "San Francisco")
        );
        Mockito.when(jobService.findAll()).thenReturn(jobs);

        mockMvc.perform(MockMvcRequestBuilders.get("/jobs"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(jobs.get(0).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(jobs.get(1).getId()));
    }

    @Test
    public void testGetJobByIdWhenCalledThenReturnJob() throws Exception {
        Job job = new Job(1, "Software Engineer", "Develop software", 50000, 100000, "New York");
        Mockito.when(jobService.getJobById(1)).thenReturn(job);

        mockMvc.perform(MockMvcRequestBuilders.get("/jobs/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(job.getId()));
    }

    @Test
    public void testAddJobWhenCalledThenReturnSuccessMessage() throws Exception {
        Job job = new Job(1, "Software Engineer", "Develop software", 50000, 100000, "New York");
        Mockito.when(jobService.addJob(Mockito.any(Job.class))).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/jobs")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Software Engineer\",\"description\":\"Develop software\",\"minSalary\":50000,\"maxSalary\":100000,\"location\":\"New York\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Job added Successfully"));
    }

    @Test
    public void testDeleteJobWhenCalledThenReturnSuccessMessage() throws Exception {
        Mockito.when(jobService.deleteJob(1)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/jobs/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("The Job is deleted Successfully"));
    }

    @Test
    public void testUpdateJobWhenCalledThenReturnSuccessMessage() throws Exception {
        Job updatedJob = new Job(1, "Software Engineer", "Develop software", 60000, 120000, "New York");
        Mockito.when(jobService.updateJob(Mockito.eq(1), Mockito.any(Job.class))).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.put("/jobs/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Software Engineer\",\"description\":\"Develop software\",\"minSalary\":60000,\"maxSalary\":120000,\"location\":\"New York\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Job Updated Successfully"));
    }

    @Test
    public void testFetchJobWhenCalledThenReturnJob() throws Exception {
        Job job = new Job(1, "Software Engineer", "Develop software", 50000, 100000, "New York");
        Mockito.when(jobService.fetchJob(1, "Software Engineer")).thenReturn(job);

        mockMvc.perform(MockMvcRequestBuilders.get("/jobs/{id}/{title}", 1, "Software Engineer"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(job.getId()));
    }

    @Test
    public void testPatchJobWhenCalledThenReturnSuccessMessage() throws Exception {
        Mockito.when(jobService.patchJob(Mockito.eq(1), Mockito.any(Job.class))).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.patch("/jobs/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Software Engineer\",\"description\":\"Develop software\",\"minSalary\":50000,\"maxSalary\":100000,\"location\":\"New York\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Job Patched Successfully"));
    }
}
