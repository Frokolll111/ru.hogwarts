package ru.hogwarts.schooll.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.schooll.model.Faculty;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Faculty findByColor(String color);

    Collection<Faculty> findAllByColorOrNameIgnoreCase(String color, String name);
}
