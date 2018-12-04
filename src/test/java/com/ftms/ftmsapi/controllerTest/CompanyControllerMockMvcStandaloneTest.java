package com.ftms.ftmsapi.controllerTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ftms.ftmsapi.controller.CompanyController;
import com.ftms.ftmsapi.model.Company;
import org.json.JSONObject;
import com.ftms.ftmsapi.repository.CompanyRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/*@RunWith(SpringRunner.class)
@SpringBootTest(classes = Company.class , webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) */
public class CompanyControllerMockMvcStandaloneTest {
  /*  @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();
    @Test
    public void testGetAllCompanies(){
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/api/companies"), HttpMethod.GET, entity, String.class);
        String expected = "{id:Course1,name:Spring,description:10 Steps}";
        Assert.assertEquals(true, true);
//        JSONAssert.assertEquals( expected, response.getBody(), false);
    }
    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;

    }

//    @MockBean
//    private CompanyController companyController;
//
//    @MockBean
//    private CompanyRepository companyRepository;
//
//    private JacksonTester<Company> jsonCompany;
//
//    @Before
//    public void setup(){
//        JacksonTester.initFields(this, new ObjectMapper());
//    }
//
//  /* @Test
//    public void canRetrieveByIdWhenExists() throws Exception{
//       //given
//       given(companyController.getAllCompanies()).willReturn(companyRepository.findAll());
//
//       //when
//       MockHttpServletResponse response = mvc.perform(get("/company").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
//
//       //then
//       assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//       assertThat(response.getContentAsString()).isEqualTo(jsonCompany.write(new Company())).getJson();
//   }*/
//    @Test
//    public void canRetrieveByIdWhenDoesNotExist() throws Exception {
//        // given
//        given(companyController.getAllCompanies())
//                .willThrow(new Exception());
//
//        // when
//        MockHttpServletResponse response = mvc.perform(
//                get("/company")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        // then
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
//        assertThat(response.getContentAsString()).isEmpty();}
//
//    @Test
//    public void canCreateANewSuperHero() throws Exception {
//        // when
//        MockHttpServletResponse response = mvc.perform(
//                post("/company/").contentType(MediaType.APPLICATION_JSON).content(
//                        jsonCompany.write(new Company()).getJson()
//                )).andReturn().getResponse();
//
//        // then
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
//    }



}
