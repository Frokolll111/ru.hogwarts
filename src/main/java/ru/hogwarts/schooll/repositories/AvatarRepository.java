package ru.hogwarts.schooll.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.schooll.model.Avatar;

import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar,Long> {
    Optional<Avatar> findByStudentId(Long idStudent);
}
