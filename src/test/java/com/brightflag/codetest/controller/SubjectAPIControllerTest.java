package com.brightflag.codetest.controller;

import org.json.JSONArray;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SubjectAPIControllerTest {
    @LocalServerPort
    private int port;

    HttpHeaders headers = new HttpHeaders();

    TestRestTemplate restTemplate = new TestRestTemplate();

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    @Test
    public void Scenario_1_getAllSubjects() throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        URI uri = new URI(createURLWithPort("/api/getSubjects" ));

        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

        //Verify request succeed
        Assert.assertEquals(200, result.getStatusCodeValue());
        JSONArray obj = new JSONArray(result.getBody());
        Assert.assertEquals(5, obj.length());
    }

    @Test
    public void Scenario_1_getSubjectOfStudent() throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        URI uri = new URI(createURLWithPort("/api/getSubjectsOfStudent/1" ));

        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

        //Verify request succeed
        Assert.assertEquals(200, result.getStatusCodeValue());
        JSONArray obj = new JSONArray(result.getBody());
        Assert.assertEquals(1, obj.length());
    }

    @Test
    public void Scenario_1_getSubjectOfUnknownStudent() throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        URI uri = new URI(createURLWithPort("/api/getSubjectsOfStudent/50" ));

        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
        JSONArray obj = new JSONArray(result.getBody());
        Assert.assertEquals(0, obj.length());
    }
}
