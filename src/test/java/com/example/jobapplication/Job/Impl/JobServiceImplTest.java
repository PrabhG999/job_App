package com.example.jobapplication.Job.Impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.jobapplication.Company.Company;
import com.example.jobapplication.Job.Job;
import com.example.jobapplication.Job.JobRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
        Company company = new Company();
        company.setDescription("The characteristics of someone or something");
        company.setId(1);
        company.setJobs(new ArrayList<>());
        company.setName("Name");
        company.setReview(new ArrayList<>());

        Job job = new Job();
        job.setCompany(company);
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
        Company company = new Company();
        company.setDescription("The characteristics of someone or something");
        company.setId(1);
        company.setJobs(new ArrayList<>());
        company.setName("Name");
        company.setReview(new ArrayList<>());

        Job job = new Job();
        job.setCompany(company);
        job.setDescription("The characteristics of someone or something");
        job.setId(1);
        job.setLocation("Location");
        job.setMaxSalary(10.0d);
        job.setMinSalary(10.0d);
        job.setTitle("Dr");
        when(jobRepository.save(Mockito.<Job>any())).thenReturn(job);

        Company company2 = new Company();
        company2.setDescription("The characteristics of someone or something");
        company2.setId(1);
        company2.setJobs(new ArrayList<>());
        company2.setName("Name");
        company2.setReview(new ArrayList<>());

        Job job2 = new Job();
        job2.setCompany(company2);
        job2.setDescription("The characteristics of someone or something");
        job2.setId(1);
        job2.setLocation("Location");
        job2.setMaxSalary(10.0d);
        job2.setMinSalary(10.0d);
        job2.setTitle("Dr");

        // Act
        boolean actualAddJobResult = jobServiceImpl.addJob(job2);

        // Assert
        verify(jobRepository).save(Mockito.<Job>any());
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
        Company company = new Company();
        company.setDescription("The characteristics of someone or something");
        company.setId(1);
        company.setJobs(new ArrayList<>());
        company.setName("Name");
        company.setReview(new ArrayList<>());

        Job job = new Job();
        job.setCompany(company);
        job.setDescription("The characteristics of someone or something");
        job.setId(1);
        job.setLocation("Location");
        job.setMaxSalary(10.0d);
        job.setMinSalary(10.0d);
        job.setTitle("Dr");
        Optional<Job> ofResult = Optional.of(job);

        Company company2 = new Company();
        company2.setDescription("The characteristics of someone or something");
        company2.setId(1);
        company2.setJobs(new ArrayList<>());
        company2.setName("Name");
        company2.setReview(new ArrayList<>());

        Job job2 = new Job();
        job2.setCompany(company2);
        job2.setDescription("The characteristics of someone or something");
        job2.setId(1);
        job2.setLocation("Location");
        job2.setMaxSalary(10.0d);
        job2.setMinSalary(10.0d);
        job2.setTitle("Dr");
        when(jobRepository.save(Mockito.<Job>any())).thenReturn(job2);
        when(jobRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        Company company3 = new Company();
        company3.setDescription("The characteristics of someone or something");
        company3.setId(1);
        company3.setJobs(new ArrayList<>());
        company3.setName("Name");
        company3.setReview(new ArrayList<>());

        Job updatedJob = new Job();
        updatedJob.setCompany(company3);
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
        verify(jobRepository).save(Mockito.<Job>any());
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

        Company company = new Company();
        company.setDescription("The characteristics of someone or something");
        company.setId(1);
        company.setJobs(new ArrayList<>());
        company.setName("Name");
        company.setReview(new ArrayList<>());

        Job updatedJob = new Job();
        updatedJob.setCompany(company);
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
        Company company = new Company();
        company.setDescription("The characteristics of someone or something");
        company.setId(1);
        company.setJobs(new ArrayList<>());
        company.setName("Name");
        company.setReview(new ArrayList<>());

        Job job = new Job();
        job.setCompany(company);
        job.setDescription("The characteristics of someone or something");
        job.setId(1);
        job.setLocation("Location");
        job.setMaxSalary(10.0d);
        job.setMinSalary(10.0d);
        job.setTitle("Dr");
        when(jobRepository.findByIdAndTitle(anyInt(), Mockito.<String>any())).thenReturn(job);

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
        Company company = new Company();
        company.setDescription("The characteristics of someone or something");
        company.setId(1);
        company.setJobs(new ArrayList<>());
        company.setName("Name");
        company.setReview(new ArrayList<>());

        Job job = new Job();
        job.setCompany(company);
        job.setDescription("The characteristics of someone or something");
        job.setId(1);
        job.setLocation("Location");
        job.setMaxSalary(10.0d);
        job.setMinSalary(10.0d);
        job.setTitle("Dr");
        Optional<Job> ofResult = Optional.of(job);

        Company company2 = new Company();
        company2.setDescription("The characteristics of someone or something");
        company2.setId(1);
        company2.setJobs(new ArrayList<>());
        company2.setName("Name");
        company2.setReview(new ArrayList<>());

        Job job2 = new Job();
        job2.setCompany(company2);
        job2.setDescription("The characteristics of someone or something");
        job2.setId(1);
        job2.setLocation("Location");
        job2.setMaxSalary(10.0d);
        job2.setMinSalary(10.0d);
        job2.setTitle("Dr");
        when(jobRepository.save(Mockito.<Job>any())).thenReturn(job2);
        when(jobRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        Company company3 = new Company();
        company3.setDescription("The characteristics of someone or something");
        company3.setId(1);
        company3.setJobs(new ArrayList<>());
        company3.setName("Name");
        company3.setReview(new ArrayList<>());

        Job patchJob = new Job();
        patchJob.setCompany(company3);
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
        verify(jobRepository).save(Mockito.<Job>any());
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

        Company company = new Company();
        company.setDescription("The characteristics of someone or something");
        company.setId(1);
        company.setJobs(new ArrayList<>());
        company.setName("Name");
        company.setReview(new ArrayList<>());

        Job patchJob = new Job();
        patchJob.setCompany(company);
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
