package ru.hogwarts.schooll.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.schooll.model.Faculty;
import ru.hogwarts.schooll.model.Student;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerTestRestTemplate {
    @LocalServerPort
    private int port;

    @Autowired
    private FacultyController facultyController;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void contextLoads() throws Exception {
        Assertions.assertThat(facultyController).isNotNull();
    }

    @Test
    public void createFacultyTest() throws Exception {
        Faculty facultyTest = new Faculty();
        HttpEntity<Faculty> httpFaculty = new HttpEntity<>(facultyTest);
        facultyTest.setName("name1");
        facultyTest.setColor("orange");
        ResponseEntity<Faculty> facultyEntity = restTemplate.exchange(
                "http://localhost:" + port + "/faculty",
                HttpMethod.POST,
                httpFaculty,
                Faculty.class
        );
        Assertions.assertThat(facultyEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        String faculty = this.restTemplate.postForObject("http://localhost:" + port + "/faculty", facultyTest, String.class);
        Assertions.assertThat(faculty).isNotNull();
    }
    @Test
    public void getFacultyTest() throws  Exception {
        Faculty facultyTest = new Faculty();
        HttpEntity<Faculty> httpFaculty = new HttpEntity<>(facultyTest);
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty", String.class)).isNotNull();
        facultyTest.setName("name1");
        facultyTest.setColor("orange");
        ResponseEntity<Faculty> studentEntity = restTemplate.exchange(
                "http://localhost:" + port + "/id/",
                HttpMethod.GET,
                httpFaculty,
                Faculty.class
        );
        Assertions.assertThat(studentEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
    @Test
    public void updateFacultyTest() throws Exception {
        Faculty facultyTest = new Faculty();
        HttpEntity<Faculty> httpFaculty = new HttpEntity<>(facultyTest);
        facultyTest.setName("name1");
        facultyTest.setColor("orange");
        ResponseEntity<Faculty> facultyEntity = restTemplate.exchange(
                "http://localhost:" + port + "/faculty",
                HttpMethod.PUT,
                httpFaculty,
                Faculty.class
        );
        Assertions.assertThat(facultyEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
