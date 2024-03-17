package com.example.jobapplication.Job.Impl;

import com.example.jobapplication.Job.Job;
import com.example.jobapplication.Job.JobRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
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

    private Job job;

    @BeforeEach
    void setUp() {
        job = new Job(1, "Software Engineer", "Develop software applications", 60000, 120000, "New York");
    }

    @Test
    void testFindAllWhenJobsExistThenReturnListOfJobs() {
        // Arrange
        when(jobRepository.findAll()).thenReturn(Collections.singletonList(job));

        // Act
        List<Job> jobs = jobService.findAll();

        // Assert
        assertFalse(jobs.isEmpty());
        assertEquals(1, jobs.size());
        assertEquals(job, jobs.get(0));
    }

    @Test
    void testFindAllWhenNoJobsThenReturnEmptyList() {
        // Arrange
        when(jobRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        List<Job> jobs = jobService.findAll();

        // Assert
        assertTrue(jobs.isEmpty());
    }

    @Test
    void testGetJobByIdWhenJobExistsThenReturnJob() {
        // Arrange
        when(jobRepository.findById(job.getId())).thenReturn(Optional.of(job));

        // Act
        Job foundJob = jobService.getJobById(job.getId());

        // Assert
        assertNotNull(foundJob);
        assertEquals(job, foundJob);
    }

    @Test
    void testGetJobByIdWhenJobDoesNotExistThenReturnNull() {
        // Arrange
        when(jobRepository.findById(anyInt())).thenReturn(Optional.empty());

        // Act
        Job foundJob = jobService.getJobById(999);

        // Assert
        assertNull(foundJob);
    }

    @Test
    void testAddJobWhenValidJobThenSaveJob() {
        // Arrange
        when(jobRepository.save(any(Job.class))).thenReturn(job);

        // Act
        boolean result = jobService.addJob(job);

        // Assert
        assertTrue(result);
        verify(jobRepository).save(job);
    }

    @Test
    void testAddJobWhenNullJobThenDoNotSaveJob() {
        // Act
        boolean result = jobService.addJob(null);

        // Assert
        assertFalse(result);
        verify(jobRepository, never()).save(any(Job.class));
    }

    @Test
    void testDeleteJobWhenJobExistsThenDeleteJob() {
        // Arrange
        doNothing().when(jobRepository).deleteById(job.getId());

        // Act
        boolean result = jobService.deleteJob(job.getId());

        // Assert
        assertTrue(result);
        verify(jobRepository).deleteById(job.getId());
    }

    @Test
    void testDeleteJobWhenJobDoesNotExistThenDoNotDeleteJob() {
        // Arrange
        doThrow(new RuntimeException()).when(jobRepository).deleteById(anyInt());

        // Act
        boolean result = jobService.deleteJob(999);

        // Assert
        assertFalse(result);
        verify(jobRepository).deleteById(999);
    }

    @Test
    void testUpdateJobWhenJobExistsThenUpdateJob() {
        // Arrange
        when(jobRepository.findById(job.getId())).thenReturn(Optional.of(job));
        when(jobRepository.save(any(Job.class))).thenReturn(job);

        Job updatedJob = new Job(job.getId(), "Updated Title", "Updated Description", 70000, 130000, "Boston");

        // Act
        boolean result = jobService.updateJob(job.getId(), updatedJob);

        // Assert
        assertTrue(result);
        verify(jobRepository).save(job);
    }

    @Test
    void testUpdateJobWhenJobDoesNotExistThenDoNotUpdateJob() {
        // Arrange
        when(jobRepository.findById(anyInt())).thenReturn(Optional.empty());

        Job updatedJob = new Job(999, "Updated Title", "Updated Description", 70000, 130000, "Boston");

        // Act
        boolean result = jobService.updateJob(999, updatedJob);

        // Assert
        assertFalse(result);
        verify(jobRepository, never()).save(any(Job.class));
    }

    @Test
    void testFetchJobWhenJobExistsThenReturnJob() {
        // Arrange
        when(jobRepository.findByIdAndTitle(job.getId(), job.getTitle())).thenReturn(job);

        // Act
        Job foundJob = jobService.fetchJob(job.getId(), job.getTitle());

        // Assert
        assertNotNull(foundJob);
        assertEquals(job, foundJob);
    }

    @Test
    void testFetchJobWhenJobDoesNotExistThenReturnNull() {
        // Arrange
        when(jobRepository.findByIdAndTitle(anyInt(), anyString())).thenReturn(null);

        // Act
        Job foundJob = jobService.fetchJob(999, "Nonexistent Title");

        // Assert
        assertNull(foundJob);
    }

    @Test
    void testPatchJobWhenJobExistsThenPatchJob() {
        // Arrange
        when(jobRepository.findById(job.getId())).thenReturn(Optional.of(job));
        when(jobRepository.save(any(Job.class))).thenReturn(job);

        Job patchJob = new Job();
        patchJob.setTitle("Patched Title");

        // Act
        boolean result = jobService.patchJob(job.getId(), patchJob);

        // Assert
        assertTrue(result);
        verify(jobRepository).save(job);
    }

    @Test
    void testPatchJobWhenJobDoesNotExistThenDoNotPatchJob() {
        // Arrange
        when(jobRepository.findById(anyInt())).thenReturn(Optional.empty());

        Job patchJob = new Job();
        patchJob.setTitle("Patched Title");

        // Act
        boolean result = jobService.patchJob(999, patchJob);

        // Assert
        assertFalse(result);
        verify(jobRepository, never()).save(any(Job.class));
    }
}
