package ru.hogwarts.schooll.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.schooll.model.Student;
import ru.hogwarts.schooll.repositories.StudentRepository;

import java.util.Collection;
import java.util.List;

@Service
public class StudentService {

    private final static Logger logger = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        logger.info("Invoked create student with argument {}",student);

        return studentRepository.save(student);
    }

    public Student readStudent(long idStudent) {
        logger.info("Invoked read student by id method with argument {}",idStudent);
        return studentRepository.findById(idStudent).get();
    }

    public Student updateStudent(Student student) {
        logger.info("Invoked update student method with argument {}", student);
        return studentRepository.save(student);
    }

    public void deleteStudent(long idStudent) {
        logger.info("Invoked delete student by id method with argument {}",idStudent);
        studentRepository.deleteById(idStudent);
    }

    public Collection<Student> readAllStudent() {
        logger.info("Invoked readAll student method");
        return studentRepository.findAll();
    }

    public Collection<Student> ageFilterStudent(int age) {
        logger.info("Invoked ageFilter student by age method with argument {}",age);
        return studentRepository.findByAge(age);
    }

    public Collection<Student> findByAgeBetween(int minAge, int maxAge) {
        logger.info("Invoker findByAgeBetween student method with argument minAge {} and maxAge {}",minAge,maxAge);
        return studentRepository.findByAgeBetween(minAge,  maxAge);
    }
    public long getStudentQuantity() {
        logger.info("Invoked getStudentQuantity student method");
        return studentRepository.getStudentQuantity();
    }

    public double getAvgAge() {
        logger.info("Invoked getAvgAge student method");
        return studentRepository.getAverageAge();
    }

    public List<Student> getLastStudent() {
        logger.info("Invoked getLastStudent student method");
        return studentRepository.getLastStudent();
    }
}
