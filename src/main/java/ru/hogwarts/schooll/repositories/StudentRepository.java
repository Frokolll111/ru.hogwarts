package ru.hogwarts.schooll.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.schooll.model.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findByAge(int age);

    Collection<Student> findByAgeBetween(int minAge, int maxAge);
}
