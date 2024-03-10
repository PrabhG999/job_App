package com.example.jobapplication.Job.Impl;

import com.example.jobapplication.Job.Job;
import com.example.jobapplication.Job.JobRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {JobServiceImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class JobServiceImplTest {
    @MockBean
    private JobRepository jobRepository;

    @Autowired
    private JobServiceImpl jobServiceImpl;

    /**
     * Method under test: {@link JobServiceImpl#findAll()}
     */
    @Test
    void testFindAll() {
        // Arrange
        ArrayList<Job> jobList = new ArrayList<>();
        when(jobRepository.findAll()).thenReturn(jobList);

        // Act
        List<Job> actualFindAllResult = jobServiceImpl.findAll();

        // Assert
        verify(jobRepository).findAll();
        assertTrue(actualFindAllResult.isEmpty());
        assertSame(jobList, actualFindAllResult);
    }

    /**
     * Method under test: {@link JobServiceImpl#getJobById(int)}
     */
    @Test
    void testGetJobById() {
        // Arrange
        Job job = new Job();
        job.setDescription("The characteristics of someone or something");
        job.setId(1);
        job.setLocation("Location");
        job.setMaxSalary(10.0d);
        job.setMinSalary(10.0d);
        job.setTitle("Dr");
        Optional<Job> ofResult = Optional.of(job);
        when(jobRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        Job actualJobById = jobServiceImpl.getJobById(1);

        // Assert
        verify(jobRepository).findById(Mockito.<Integer>any());
        assertSame(job, actualJobById);
    }

    /**
     * Method under test: {@link JobServiceImpl#addJob(Job)}
     */
    @Test
    void testAddJob() {
        // Arrange
        Job job = new Job();
        job.setDescription("The characteristics of someone or something");
        job.setId(1);
        job.setLocation("Location");
        job.setMaxSalary(10.0d);
        job.setMinSalary(10.0d);
        job.setTitle("Dr");
        when(jobRepository.save(Mockito.any())).thenReturn(job);

        Job job2 = new Job();
        job2.setDescription("The characteristics of someone or something");
        job2.setId(1);
        job2.setLocation("Location");
        job2.setMaxSalary(10.0d);
        job2.setMinSalary(10.0d);
        job2.setTitle("Dr");

        // Act
        boolean actualAddJobResult = jobServiceImpl.addJob(job2);

        // Assert
        verify(jobRepository).save(Mockito.any());
        assertTrue(actualAddJobResult);
    }

    /**
     * Method under test: {@link JobServiceImpl#deleteJob(int)}
     */
    @Test
    void testDeleteJob() {
        // Arrange
        doNothing().when(jobRepository).deleteById(Mockito.<Integer>any());

        // Act
        boolean actualDeleteJobResult = jobServiceImpl.deleteJob(1);

        // Assert
        verify(jobRepository).deleteById(Mockito.<Integer>any());
        assertTrue(actualDeleteJobResult);
    }

    /**
     * Method under test: {@link JobServiceImpl#updateJob(int, Job)}
     */
    @Test
    void testUpdateJob() {
        // Arrange
        Job job = new Job();
        job.setDescription("The characteristics of someone or something");
        job.setId(1);
        job.setLocation("Location");
        job.setMaxSalary(10.0d);
        job.setMinSalary(10.0d);
        job.setTitle("Dr");
        Optional<Job> ofResult = Optional.of(job);

        Job job2 = new Job();
        job2.setDescription("The characteristics of someone or something");
        job2.setId(1);
        job2.setLocation("Location");
        job2.setMaxSalary(10.0d);
        job2.setMinSalary(10.0d);
        job2.setTitle("Dr");
        when(jobRepository.save(Mockito.any())).thenReturn(job2);
        when(jobRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        Job updatedJob = new Job();
        updatedJob.setDescription("The characteristics of someone or something");
        updatedJob.setId(1);
        updatedJob.setLocation("Location");
        updatedJob.setMaxSalary(10.0d);
        updatedJob.setMinSalary(10.0d);
        updatedJob.setTitle("Dr");

        // Act
        boolean actualUpdateJobResult = jobServiceImpl.updateJob(1, updatedJob);

        // Assert
        verify(jobRepository).findById(Mockito.<Integer>any());
        verify(jobRepository).save(Mockito.any());
        assertTrue(actualUpdateJobResult);
    }

    /**
     * Method under test: {@link JobServiceImpl#updateJob(int, Job)}
     */
    @Test
    void testUpdateJob2() {
        // Arrange
        Optional<Job> emptyResult = Optional.empty();
        when(jobRepository.findById(Mockito.<Integer>any())).thenReturn(emptyResult);

        Job updatedJob = new Job();
        updatedJob.setDescription("The characteristics of someone or something");
        updatedJob.setId(1);
        updatedJob.setLocation("Location");
        updatedJob.setMaxSalary(10.0d);
        updatedJob.setMinSalary(10.0d);
        updatedJob.setTitle("Dr");

        // Act
        boolean actualUpdateJobResult = jobServiceImpl.updateJob(1, updatedJob);

        // Assert
        verify(jobRepository).findById(Mockito.<Integer>any());
        assertFalse(actualUpdateJobResult);
    }

    /**
     * Method under test: {@link JobServiceImpl#fetchJob(int, String)}
     */
    @Test
    void testFetchJob() {
        // Arrange
        Job job = new Job();
        job.setDescription("The characteristics of someone or something");
        job.setId(1);
        job.setLocation("Location");
        job.setMaxSalary(10.0d);
        job.setMinSalary(10.0d);
        job.setTitle("Dr");
        when(jobRepository.findByIdAndTitle(anyInt(), Mockito.any())).thenReturn(job);

        // Act
        Job actualFetchJobResult = jobServiceImpl.fetchJob(1, "Dr");

        // Assert
        verify(jobRepository).findByIdAndTitle(eq(1), eq("Dr"));
        assertSame(job, actualFetchJobResult);
    }

    /**
     * Method under test: {@link JobServiceImpl#patchJob(int, Job)}
     */
    @Test
    void testPatchJob() {
        // Arrange
        Job job = new Job();
        job.setDescription("The characteristics of someone or something");
        job.setId(1);
        job.setLocation("Location");
        job.setMaxSalary(10.0d);
        job.setMinSalary(10.0d);
        job.setTitle("Dr");
        Optional<Job> ofResult = Optional.of(job);

        Job job2 = new Job();
        job2.setDescription("The characteristics of someone or something");
        job2.setId(1);
        job2.setLocation("Location");
        job2.setMaxSalary(10.0d);
        job2.setMinSalary(10.0d);
        job2.setTitle("Dr");
        when(jobRepository.save(Mockito.any())).thenReturn(job2);
        when(jobRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        Job patchJob = new Job();
        patchJob.setDescription("The characteristics of someone or something");
        patchJob.setId(1);
        patchJob.setLocation("Location");
        patchJob.setMaxSalary(10.0d);
        patchJob.setMinSalary(10.0d);
        patchJob.setTitle("Dr");

        // Act
        boolean actualPatchJobResult = jobServiceImpl.patchJob(1, patchJob);

        // Assert
        verify(jobRepository).findById(Mockito.<Integer>any());
        verify(jobRepository).save(Mockito.any());
        assertTrue(actualPatchJobResult);
    }

    /**
     * Method under test: {@link JobServiceImpl#patchJob(int, Job)}
     */
    @Test
    void testPatchJob2() {
        // Arrange
        Optional<Job> emptyResult = Optional.empty();
        when(jobRepository.findById(Mockito.<Integer>any())).thenReturn(emptyResult);

        Job patchJob = new Job();
        patchJob.setDescription("The characteristics of someone or something");
        patchJob.setId(1);
        patchJob.setLocation("Location");
        patchJob.setMaxSalary(10.0d);
        patchJob.setMinSalary(10.0d);
        patchJob.setTitle("Dr");

        // Act
        boolean actualPatchJobResult = jobServiceImpl.patchJob(1, patchJob);

        // Assert
        verify(jobRepository).findById(Mockito.<Integer>any());
        assertFalse(actualPatchJobResult);
    }
}
