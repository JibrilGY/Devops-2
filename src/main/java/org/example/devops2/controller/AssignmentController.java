package org.example.devops2.controller;

import org.example.devops2.dto.AssignmentDTO;
import org.example.devops2.service.AssignmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @GetMapping
    public ResponseEntity<List<AssignmentDTO>> getAllAssignments() {
        return ResponseEntity.ok(assignmentService.getAllAssignments());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<AssignmentDTO> getAssignmentById(@PathVariable Long id) {
        return ResponseEntity.ok(assignmentService.getAssignmentById(id));
    }

    @PostMapping("/add/{id}")
    public ResponseEntity<AssignmentDTO> createAssignment(
            @RequestBody AssignmentDTO assignmentDTO) {
        return new ResponseEntity<>(
                assignmentService.createAssignment(assignmentDTO),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AssignmentDTO> updateAssignment(
            @PathVariable Long id,
            @RequestBody AssignmentDTO assignmentDTO) {
        return ResponseEntity.ok(
                assignmentService.updateAssignment(id, assignmentDTO)
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAssignment(@PathVariable Long id) {
        assignmentService.deleteAssignment(id);
        return ResponseEntity.noContent().build();
    }
}