package com.example.OWASP10.ClientTests;

import Client.OWASP_Component;
import Client.User_Config;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import java.security.acl.Owner;

import static org.junit.Assert.*;

public class OWASP_ComponentTests {

    private static User_Config user_config = new User_Config("admin","admin", "http://localhost:9000","demo");
    private static OWASP_Component owasp_component = new OWASP_Component();

    @Test
    public void TestRestTemplateIsNotNull(){
        //User_Config user_config = new User_Config("","","","");
        OWASP_Component owasp_component = new OWASP_Component();
        RestTemplate rt = owasp_component.createAuthenticationTemplate(user_config);
        assertNotNull(rt);
    }

   /* @Test
    public void TestRestTemplateCanAccessLocalSonarqube(){
        RestTemplate rt = owasp_component.createAuthenticationTemplate(user_config);
        RequestEntity<String> response = rt.getForObject("http://localhost:9000", RequestEntity.class);
        assertEquals(HttpStatus.OK, response.g);
    } */

    @Test
    public void TestGetIssuesReturnedJsonIsNotNull() throws JSONException {
        RestTemplate rt = owasp_component.createAuthenticationTemplate(user_config);
        JSONObject json = owasp_component.getIssues(rt);
        assertNotEquals(null, json);
    }

    @Test
    public void TestGetIssuesReturnedJson() throws JSONException {
        RestTemplate rt = owasp_component.createAuthenticationTemplate(user_config);
        JSONObject json = owasp_component.getIssues(rt);
        assertEquals("{\"p\":1,\"total\":0,\"components\":[],\"ps\":100,\"effortTotal\":0,\"paging\":{\"total\":0,\"pageIndex\":1,\"pageSize\":100},\"debtTotal\":0,\"issues\":[],\"facets\":[]}", json.toString());
    }







}
