package com.example.jobapplication.Company;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

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

@ContextConfiguration(classes = {CompanyController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class CompanyControllerTest {
    @Autowired
    private CompanyController companyController;

    @MockBean
    private CompanyService companyService;

    /**
     * Method under test: {@link CompanyController#getAllCompanies()}
     */
    @Test
    void testGetAllCompanies() throws Exception {
        // Arrange
        when(companyService.getAllCompanies()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/companies");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(companyController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CompanyController#updateCompany(int, Company)}
     */
    @Test
    void testUpdateCompany() throws Exception {
        // Arrange
        when(companyService.updateCompany(anyInt(), Mockito.<Company>any())).thenReturn(true);

        Company company = new Company();
        company.setDescription("The characteristics of someone or something");
        company.setId(1);
        company.setJobs(new ArrayList<>());
        company.setName("Name");
        company.setReview(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(company);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/companies/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(companyController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Company Updated Successfully"));
    }

    /**
     * Method under test: {@link CompanyController#updateCompany(int, Company)}
     */
    @Test
    void testUpdateCompany2() throws Exception {
        // Arrange
        when(companyService.updateCompany(anyInt(), Mockito.<Company>any())).thenReturn(false);

        Company company = new Company();
        company.setDescription("The characteristics of someone or something");
        company.setId(1);
        company.setJobs(new ArrayList<>());
        company.setName("Name");
        company.setReview(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(company);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/companies/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(companyController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Company was not updated Successfully "));
    }

    /**
     * Method under test: {@link CompanyController#addCompany(Company)}
     */
    @Test
    void testAddCompany() throws Exception {
        // Arrange
        when(companyService.addCompany(Mockito.<Company>any())).thenReturn(true);

        Company company = new Company();
        company.setDescription("The characteristics of someone or something");
        company.setId(1);
        company.setJobs(new ArrayList<>());
        company.setName("Name");
        company.setReview(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(company);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(companyController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Company Added Successfully"));
    }

    /**
     * Method under test: {@link CompanyController#addCompany(Company)}
     */
    @Test
    void testAddCompany2() throws Exception {
        // Arrange
        when(companyService.addCompany(Mockito.<Company>any())).thenReturn(false);

        Company company = new Company();
        company.setDescription("The characteristics of someone or something");
        company.setId(1);
        company.setJobs(new ArrayList<>());
        company.setName("Name");
        company.setReview(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(company);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(companyController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Company Was Not Added Sucessfully"));
    }

    /**
     * Method under test: {@link CompanyController#deleteCompany(int)}
     */
    @Test
    void testDeleteCompany() throws Exception {
        // Arrange
        when(companyService.deleteCompany(anyInt())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/companies/{id}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(companyController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Company Deleted Successfully "));
    }

    /**
     * Method under test: {@link CompanyController#deleteCompany(int)}
     */
    @Test
    void testDeleteCompany2() throws Exception {
        // Arrange
        when(companyService.deleteCompany(anyInt())).thenReturn(false);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/companies/{id}", 1);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(companyController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Company Was Not Deleted Successfully "));
    }

    /**
     * Method under test: {@link CompanyController#getAllCompaniesById(int)}
     */
    @Test
    void testGetAllCompaniesById() throws Exception {
        // Arrange
        Company company = new Company();
        company.setDescription("The characteristics of someone or something");
        company.setId(1);
        company.setJobs(new ArrayList<>());
        company.setName("Name");
        company.setReview(new ArrayList<>());
        when(companyService.getAllCompaniesById(anyInt())).thenReturn(company);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/companies/{id}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(companyController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"name\":\"Name\",\"description\":\"The characteristics of someone or something\",\"jobs\":[],"
                                        + "\"review\":[]}"));
    }

    /**
     * Method under test: {@link CompanyController#patchCompany(int, Company)}
     */
    @Test
    void testPatchCompany() throws Exception {
        // Arrange
        when(companyService.patchCompany(anyInt(), Mockito.<Company>any())).thenReturn(true);

        Company company = new Company();
        company.setDescription("The characteristics of someone or something");
        company.setId(1);
        company.setJobs(new ArrayList<>());
        company.setName("Name");
        company.setReview(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(company);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/companies/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(companyController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Company Patched Successfully"));
    }

    /**
     * Method under test: {@link CompanyController#patchCompany(int, Company)}
     */
    @Test
    void testPatchCompany2() throws Exception {
        // Arrange
        when(companyService.patchCompany(anyInt(), Mockito.<Company>any())).thenReturn(false);

        Company company = new Company();
        company.setDescription("The characteristics of someone or something");
        company.setId(1);
        company.setJobs(new ArrayList<>());
        company.setName("Name");
        company.setReview(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(company);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/companies/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(companyController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Company Was Not Patched Successfully"));
    }
}
