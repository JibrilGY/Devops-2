package org.example.devops2.service;

import org.example.devops2.dto.AssignmentDTO;

import java.util.List;

public interface AssignmentService {

    List<AssignmentDTO> getAllAssignments();

    AssignmentDTO getAssignmentById(Long id);

    AssignmentDTO createAssignment(AssignmentDTO assignmentDTO);

    AssignmentDTO updateAssignment(Long id, AssignmentDTO assignmentDTO);

    void deleteAssignment(Long id);
}