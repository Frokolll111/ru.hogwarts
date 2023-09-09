package ru.hogwarts.schooll.service;

import liquibase.pro.packaged.S;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.schooll.model.Student;
import ru.hogwarts.schooll.repositories.StudentRepository;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<String> sortStudent() {
        logger.info("Invoked sortStudent student method");
        return studentRepository.findAll().stream()
                .sorted(Comparator.comparing(Student::getName))
                .map(n -> n.getName().toLowerCase())
                .collect(Collectors.toList());
    }

    public List<String> sortStudentChars(Character chars) {
        logger.info("Invoked sortStudentChars by chars method with argument {} ", chars);
        return studentRepository.findAll().stream()
                .filter(n->n.getName().charAt(0) == chars)
                .map(n->n.getName().toLowerCase())
                .collect(Collectors.toList());
    }

    public double averageAgeStudent() {
        logger.info("Invoked averageAgeStudent student method");
        return studentRepository.findAll().stream()
                .mapToInt(Student::getAge)
                .average()
                .orElse(0.0);
    }

    private void  print (Student student) {
        try {
            Thread.sleep(3000);
            System.out.println(student);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void printStudent() {
        List<Student> students = studentRepository.findAll();
        print(students.get(0));
        print(students.get(1));

        new Thread(() -> {
            print(students.get(2));
            print(students.get(3));
        }).start();

        new Thread(() -> {
            print(students.get(4));
            print(students.get(5));
        }).start();
    }

    private synchronized void printSynchronized(Student student) {
        try {
            Thread.sleep(3000);
            System.out.println(student);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void printStudentSynchronized() {
        List<Student> students = studentRepository.findAll();
        printSynchronized(students.get(0));
        printSynchronized(students.get(1));

        new Thread(() -> {
            printSynchronized(students.get(2));
            printSynchronized(students.get(3));
        }).start();

        new Thread(() -> {
            printSynchronized(students.get(4));
            printSynchronized(students.get(5));
        }).start();
    }
}
