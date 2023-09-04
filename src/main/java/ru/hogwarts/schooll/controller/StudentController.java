package ru.hogwarts.schooll.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.schooll.model.Avatar;
import ru.hogwarts.schooll.model.Student;
import ru.hogwarts.schooll.service.AvatarService;
import ru.hogwarts.schooll.service.StudentService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(consumes = {"application/xml","application/json"})
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping("/id/{idStudent}")
    public ResponseEntity<Student> readStudent(@PathVariable long idStudent) {
        Student student = studentService.readStudent(idStudent);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student foundStudent = studentService.updateStudent(student);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("{idStudent}")
    public ResponseEntity deleteStudent(@PathVariable long idStudent) {
        studentService.deleteStudent(idStudent);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> readAllStudent() {
        return ResponseEntity.ok(studentService.readAllStudent());
    }

    @GetMapping("{age}")
    public ResponseEntity<Collection<Student>> ageFilterStudent(@PathVariable int age) {
        return ResponseEntity.ok(studentService.ageFilterStudent(age));
    }

    @GetMapping("/findByAgeBetween")
    public Collection<Student> findByAgeBetween(@RequestParam int minAge, @RequestParam int maxAge) {
        return studentService.findByAgeBetween(minAge, maxAge);
    }
    @GetMapping("/quantity")
    public long quantity() {
        return studentService.getStudentQuantity();
    }
    @GetMapping("/avg-age")
    public double avgAge() {
        return studentService.getAvgAge();
    }
    @GetMapping("/last")
    public Collection<Student> last() {
        return studentService.getLastStudent();
    }

    @GetMapping("/name/sort")
    public List<String> sortStudent() {
        return studentService.sortStudent();
    }

    @GetMapping("/name/sort/chars {chars}")
    public List<String> sortStudentChars(@PathVariable Character chars) {
        return studentService.sortStudentChars(chars);
    }

    @GetMapping("/average")
    public double averageAgeStudent() {
        return studentService.averageAgeStudent();
    }
}
