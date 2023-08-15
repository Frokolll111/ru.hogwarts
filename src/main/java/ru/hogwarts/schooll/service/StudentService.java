package ru.hogwarts.schooll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.schooll.model.Student;
import ru.hogwarts.schooll.repositories.StudentRepository;

import java.util.Collection;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {

        return studentRepository.save(student);
    }

    public Student readStudent(long idStudent) {
        return studentRepository.findById(idStudent).get();
    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(long idStudent) {
        studentRepository.deleteById(idStudent);
    }

    public Collection<Student> readAllStudent() {
        return studentRepository.findAll();
    }

    public Collection<Student> ageFilterStudent(int age) {
        return studentRepository.findByAge(age);
    }

    public Collection<Student> findByAgeBetween(int minAge, int maxAge) {
        return studentRepository.findByAgeBetween(minAge,  maxAge);
    }
    public long getStudentQuantity() {
        return studentRepository.getStudentQuantity();
    }

    public double getAvgAge() {
        return studentRepository.getAverageAge();
    }

    public List<Student> getLastStudent() {
        return studentRepository.getLastStudent();
    }
}
