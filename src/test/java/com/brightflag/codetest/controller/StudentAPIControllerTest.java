package com.brightflag.codetest.controller;

import com.brightflag.domain.Student;
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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentAPIControllerTest {
    @LocalServerPort
    private int port;

    HttpHeaders headers = new HttpHeaders();

    TestRestTemplate restTemplate = new TestRestTemplate();

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    @Test
    public void Scenario_1_getAllStudents() throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        URI uri = new URI(createURLWithPort("/api/getStudents" ));

        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

        //Verify request succeed
        Assert.assertEquals(200, result.getStatusCodeValue());
        JSONArray obj = new JSONArray(result.getBody());
        Assert.assertEquals(2, obj.length());
    }

    @Test
    public void Scenario_1_getStudent() throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        URI uri = new URI(createURLWithPort("/api/getStudent/1" ));

        ResponseEntity<Student> result = restTemplate.getForEntity(uri, Student.class);

        //Verify request succeed
        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertEquals(1, Integer.parseInt(result.getBody().getStudentID().toString()));
        Assert.assertEquals("Mary", result.getBody().getFirstName());
    }

    @Test
    public void Scenario_1_getUnknownStudent() throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        URI uri = new URI(createURLWithPort("/api/getStudent/50" ));

        try {
            ResponseEntity<Student> result = restTemplate.getForEntity(uri, Student.class);
        } catch (final HttpClientErrorException e) {
            Assert.assertEquals(404, e.getRawStatusCode());
        }
    }
}
