package ru.hogwarts.schooll.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.schooll.model.Faculty;
import ru.hogwarts.schooll.repositories.FacultyRepository;

import java.util.Collection;

@Service
public class FacultyService {

    private final static Logger logger = LoggerFactory.getLogger(FacultyService.class);
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        logger.info("Invoked create faculty with argument {}",faculty);
        return facultyRepository.save(faculty);
    }

    public Faculty readFaculty(long idFaculty) {
        logger.info("Invoked read faculty by id method with argument {}",idFaculty);
        return facultyRepository.getById(idFaculty);
    }

    public Faculty updateFaculty(Faculty faculty) {
        logger.info("Invoked update faculty method with argument {}", faculty);
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long idFaculty) {
        logger.info("Invoked delete faculty by id method with argument {}",idFaculty);
        facultyRepository.deleteById(idFaculty);
    }

    public Collection<Faculty> readAllFaculty() {
        logger.info("Invoked readAll faculty method");
        return facultyRepository.findAll();
    }

    public Faculty colorFilterFaculty(String color) {
        logger.info("Invoked colorFilter faculty by color method with argument {}", color);
        return facultyRepository.findByColor(color);
    }

    public Collection<Faculty> findByColorOrName(String color, String name) {
        logger.info("Invoker findByColorOrName faculty method with argument color {} and name {}",color,name);
        return facultyRepository.findAllByColorOrNameIgnoreCase(color, name);
    }
}
