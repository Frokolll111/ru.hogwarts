package ru.hogwarts.schooll.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.schooll.model.Faculty;
import ru.hogwarts.schooll.service.FacultyService;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @GetMapping("/id/{idFaculty}")
    public ResponseEntity<Faculty> readFaculty(@PathVariable long idFaculty) {
        Faculty faculty = facultyService.readFaculty(idFaculty);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty) {
        Faculty foundFaculty = facultyService.updateFaculty(faculty);
        if (foundFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundFaculty);
    }

    @DeleteMapping("/{idFaculty}")
    public ResponseEntity deleteFaculty(@PathVariable long idFaculty) {
        facultyService.deleteFaculty(idFaculty);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Collection<Faculty>> readAllFaculty() {
        return ResponseEntity.ok(facultyService.readAllFaculty());
    }

    @GetMapping("/color/{color}")
    public Faculty colorFilterFaculty(@PathVariable String color) {
        return facultyService.colorFilterFaculty(color);
    }

    @GetMapping("findByColorOrName")
    public Collection<Faculty> findByColorOrName(@RequestParam(required = false) String color,
                                                 @RequestParam(required = false) String name) {
        return facultyService.findByColorOrName(color, name);
    }

    @GetMapping("/longNameFaculty")
    public Optional<String> longNameFaculty() {
        return facultyService.longNameFaculty();
    }

    @GetMapping("/number")
    public Integer number() {
        return facultyService.number();
    }
}
