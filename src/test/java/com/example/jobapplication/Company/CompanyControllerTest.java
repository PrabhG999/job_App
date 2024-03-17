package com.example.jobapplication.Company;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CompanyController.class)
public class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyService companyService;

    @Test
    public void testGetAllCompaniesWhenCompaniesExistThenReturnListOfCompanies() throws Exception {
        // Arrange
        List<Company> companies = Arrays.asList(
                new Company(1, "Company A", "Description A", null, null),
                new Company(2, "Company B", "Description B", null, null)
        );
        given(companyService.getAllCompanies()).willReturn(companies);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/companies"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(companies.size()));
    }

    @Test
    public void testGetAllCompaniesWhenNoCompaniesThenReturnEmptyList() throws Exception {
        // Arrange
        given(companyService.getAllCompanies()).willReturn(Collections.emptyList());

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/companies"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(0));
    }

    @Test
    public void testUpdateCompanyWhenCompanyDoesNotExistThenReturnErrorMessage() throws Exception {
        // Arrange
        Company company = new Company(1, "Company A", "Description A", null, null);
        given(companyService.updateCompany(company.getId(), company)).willReturn(false);

        // Act & Assert
        mockMvc.perform(put("/companies/{id}", company.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Company A\",\"description\":\"Description A\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Company was not updated Successfully "));
    }

    @Test
    public void testAddCompanyWhenCompanyNotAddedThenReturnErrorMessage() throws Exception {
        // Arrange
        Company company = new Company(1, "Company A", "Description A", null, null);
        given(companyService.addCompany(company)).willReturn(false);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/companies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Company A\",\"description\":\"Description A\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Company Was Not Added Sucessfully"));
    }

    @Test
    public void testDeleteCompanyWhenCompanyExistsThenReturnSuccessMessage() throws Exception {
        // Arrange
        int companyId = 1;
        given(companyService.deleteCompany(companyId)).willReturn(true);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.delete("/companies/{id}", companyId))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Company Deleted Successfully "));
    }

    @Test
    public void testDeleteCompanyWhenCompanyDoesNotExistThenReturnErrorMessage() throws Exception {
        // Arrange
        int companyId = 1;
        given(companyService.deleteCompany(companyId)).willReturn(false);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.delete("/companies/{id}", companyId))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Company Was Not Deleted Successfully "));
    }

    @Test
    public void testGetAllCompaniesByIdWhenCompanyExistsThenReturnCompany() throws Exception {
        // Arrange
        Company company = new Company(1, "Company A", "Description A", null, null);
        given(companyService.getAllCompaniesById(company.getId())).willReturn(company);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/companies/{id}", company.getId()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(company.getName()));
    }

    @Test
    public void testGetAllCompaniesByIdWhenCompanyDoesNotExistThenReturnNotFound() throws Exception {
        // Arrange
        int companyId = 1;
        given(companyService.getAllCompaniesById(companyId)).willReturn(null);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/companies/{id}", companyId))
                .andExpect(status().isNotFound());
    }


    @Test
    public void testPatchCompanyWhenCompanyDoesNotExistThenReturnErrorMessage() throws Exception {
        // Arrange
        Company company = new Company(1, "Company A", "Description A", null, null);
        given(companyService.patchCompany(company.getId(), company)).willReturn(false);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.patch("/companies/{id}", company.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Company A\",\"description\":\"Description A\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Company Was Not Patched Successfully"));
    }
}
