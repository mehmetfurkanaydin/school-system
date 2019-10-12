package com.brightflag.codetest.controller;
import com.brightflag.domain.Exam;
import com.brightflag.domain.Grade;
import com.brightflag.domain.Student;
import org.json.JSONArray;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Assert;
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
public class ExamAPIControllerTest {
    @LocalServerPort
    private int port;

    HttpHeaders headers = new HttpHeaders();

    TestRestTemplate restTemplate = new TestRestTemplate();

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    @Test
    public void Scenario_1_createNewExam() throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        URI uri = new URI(createURLWithPort("/api/createExam" ));

        Exam exam = new Exam();
        exam.setExamID(5);
        exam.setExamName("Test-exam");
        exam.setStudentID(1);
        exam.setSubjectID(5);
        ResponseEntity<String> result = restTemplate.postForEntity(uri, exam, String.class);

        //Verify request succeed
        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertEquals("true", result.getBody());

        uri = new URI(createURLWithPort("/api/getStudent/1" ));

        ResponseEntity<Student> resultStudent = restTemplate.getForEntity(uri, Student.class);
        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertEquals(1, Integer.parseInt(resultStudent.getBody().getStudentID().toString()));
        Assert.assertEquals(3, resultStudent.getBody().getExams().size());
    }

    @Test
    public void Scenario_1_createNewGrade() throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        URI uri = new URI(createURLWithPort("/api/createGrade" ));

        Grade grade = new Grade();
        grade.setExamID(3);
        grade.setStudentID(1);
        grade.setGrade("88");
        ResponseEntity<String> result = restTemplate.postForEntity(uri, grade, String.class);

        //Verify request succeed
        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertEquals("true", result.getBody());

        uri = new URI(createURLWithPort("/api/getStudent/1" ));

        ResponseEntity<Student> resultStudent = restTemplate.getForEntity(uri, Student.class);
        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertEquals(1, Integer.parseInt(resultStudent.getBody().getStudentID().toString()));
        Assert.assertEquals(2, resultStudent.getBody().getGrades().size());
    }

}
