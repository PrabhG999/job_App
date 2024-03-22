package com.example.jobapplication.Job.Impl;

import com.example.jobapplication.Company.Company;
import com.example.jobapplication.Job.Job;
import com.example.jobapplication.Job.JobRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JobServiceImplTest {

    @Mock
    private JobRepository jobRepository;

    @InjectMocks
    private JobServiceImpl jobService;

    private Job job1;
    private Job job2;

    @BeforeEach
    void setUp() {
        job1 = new Job(1, "Developer", "Develops stuff", 50000, 100000, "Remote");
        job2 = new Job(2, "Tester", "Tests stuff", 40000, 80000, "On-site");
    }

    @Test
    void testFindAllWhenJobsExistThenReturnListOfJobs() {
        List<Job> expectedJobs = Arrays.asList(job1, job2);
        when(jobRepository.findAll()).thenReturn(expectedJobs);

        List<Job> actualJobs = jobService.findAll();

        assertNotNull(actualJobs);
        assertEquals(expectedJobs, actualJobs);
        verify(jobRepository).findAll();
    }

    @Test
    void testGetJobByIdWhenJobExistsThenReturnJob() {
        when(jobRepository.findById(1)).thenReturn(Optional.of(job1));

        Job actualJob = jobService.getJobById(1);

        assertNotNull(actualJob);
        assertEquals(job1, actualJob);
        verify(jobRepository).findById(1);
    }

    @Test
    void testAddJobWhenJobIsValidThenSaveJob() {
        when(jobRepository.save(any(Job.class))).thenReturn(job1);

        boolean result = jobService.addJob(job1);

        assertTrue(result);
        verify(jobRepository).save(job1);
    }

    @Test
    void testDeleteJobWhenJobDoesNotExistThenReturnFalse() {
        when(jobRepository.findById(anyInt())).thenReturn(Optional.empty());

        boolean result = jobService.deleteJob(1);

        assertFalse(result);
        verify(jobRepository, never()).delete(any(Job.class));
    }

    @Test
    void testAddJobWhenJobIsNullThenReturnFalse() {
        boolean result = jobService.addJob(null);

        assertFalse(result);
        verify(jobRepository, never()).save(any(Job.class));
    }

    @Test
    void testUpdateJobWhenJobDoesNotExistThenReturnFalse() {
        when(jobRepository.findById(anyInt())).thenReturn(Optional.empty());

        Job updatedJob = new Job(1, "Non-existent", "Does not exist", 0, 0, "Nowhere");
        boolean result = jobService.updateJob(1, updatedJob);

        assertFalse(result);
        verify(jobRepository, never()).save(any(Job.class));
    }
    @Test
    void testFindAllWhenNoJobsExistThenReturnEmptyList() {
        when(jobRepository.findAll()).thenReturn(Arrays.asList());

        List<Job> actualJobs = jobService.findAll();

        assertNotNull(actualJobs);
        assertTrue(actualJobs.isEmpty());
        verify(jobRepository).findAll();
    }
    @Test
    void testUpdateJobWhenJobExistsThenUpdateAndReturnTrue() {
        when(jobRepository.findById(1)).thenReturn(Optional.of(job1));
        Job updatedJob = new Job(1, "Senior Developer", "Develops complex stuff", 70000, 140000, "Remote");

        boolean result = jobService.updateJob(1, updatedJob);

        assertTrue(result);
        verify(jobRepository).save(job1);
        assertEquals("Senior Developer", job1.getTitle());
        assertEquals("Develops complex stuff", job1.getDescription());
        assertEquals(70000, job1.getMinSalary());
        assertEquals(140000, job1.getMaxSalary());
        assertEquals("Remote", job1.getLocation());
    }

    @Test
    void testGetJobByIdWhenJobDoesNotExistThenReturnNull() {
        when(jobRepository.findById(anyInt())).thenReturn(Optional.empty());

        Job actualJob = jobService.getJobById(1);

        assertNull(actualJob);
        verify(jobRepository).findById(1);
    }

    @Test
    void testPatchJobWhenJobDoesNotExistThenReturnFalse() {
        when(jobRepository.findById(anyInt())).thenReturn(Optional.empty());

        Job patchJob = new Job();
        patchJob.setTitle("Non-existent");

        boolean result = jobService.patchJob(1, patchJob);

        assertFalse(result);
        verify(jobRepository, never()).save(any(Job.class));
    }


    @Test
    void testUpdateJobWhenJobExistsThenUpdateJob() {
        when(jobRepository.findById(1)).thenReturn(Optional.of(job1));
        Job updatedJob = new Job(1, "Updated Developer", "Develops more stuff", 60000, 120000, "Remote");

        boolean result = jobService.updateJob(1, updatedJob);

        assertTrue(result);
        verify(jobRepository).save(job1);
        assertEquals("Updated Developer", job1.getTitle());
        assertEquals("Develops more stuff", job1.getDescription());
        assertEquals(60000, job1.getMinSalary());
        assertEquals(120000, job1.getMaxSalary());
        assertEquals("Remote", job1.getLocation());
    }

    @Test
    void testFetchJobWhenJobExistsThenReturnJob() {
        when(jobRepository.findByIdAndTitle(1, "Developer")).thenReturn(job1);

        Job actualJob = jobService.fetchJob(1, "Developer");

        assertNotNull(actualJob);
        assertEquals(job1, actualJob);
        verify(jobRepository).findByIdAndTitle(1, "Developer");
    }

    @Test
    void testPatchJobWhenJobExistsThenPatchJob() {
        when(jobRepository.findById(1)).thenReturn(Optional.of(job1));
        Job patchJob = new Job();
        patchJob.setTitle("Patched Developer");

        boolean result = jobService.patchJob(1, patchJob);

        assertTrue(result);
        verify(jobRepository).save(job1);
        assertEquals("Patched Developer", job1.getTitle());
    }
}
