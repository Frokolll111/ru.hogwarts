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
import ru.hogwarts.schooll.controller.StudentController;
import ru.hogwarts.schooll.model.Student;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void contextLoads() throws Exception {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    public void defaultMessageTest() throws Exception {
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student", String.class)).isNotNull();
    }
    @Test
    public void createStudentTest() throws Exception {
        Student studentTest = new Student();
        studentTest.setName("name1");
        studentTest.setAge(25);
        String student = this.restTemplate.postForObject("http://localhost:" + port + "/student", studentTest, String.class);
        Assertions.assertThat(student).isNotNull();
    }
    @Test
    public void getStudentTest() throws  Exception {
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student", String.class)).isNotNull();
    }
    @Test
    public void updateStudentTest() throws Exception {
        Student studentTest = new Student();
        HttpEntity<Student> httpStudent = new HttpEntity<>(studentTest);
        studentTest.setName("name1");
        studentTest.setAge(25);
        ResponseEntity<Student> studentEntity = restTemplate.exchange(
                "http://localhost:" + port + "/student",
                HttpMethod.PUT,
                httpStudent,
                Student.class
        );
        Assertions.assertThat(studentEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
