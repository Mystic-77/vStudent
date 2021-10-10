package com.oosd.vstudent.controllers;

import com.oosd.vstudent.errors.InvalidEndpointException;
import com.oosd.vstudent.errors.SuccessResponse;
import com.oosd.vstudent.models.Student;
import com.oosd.vstudent.services.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private DatabaseService databaseService;

    @GetMapping("/")
    public List<Student> retreiveAllStudents()
    {
        return databaseService.getStudentRepository().findAll();
    }

    @GetMapping("/{id}")
    public Student retrieveStudent(@PathVariable int id)
    {
        if (!databaseService.getStudentRepository().existsById(id))
        {
            throw new InvalidEndpointException("id not found");
        }
        return databaseService.getStudentRepository().getById(id);
    }

    @PostMapping("/")
    public Student addStudent(@RequestBody Student student)
    {
        return databaseService.getStudentRepository().save(student);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SuccessResponse> editStudent(@PathVariable int id, @RequestBody Student student)
    {
        if (!databaseService.getStudentRepository().existsById(id))
        {
            throw new InvalidEndpointException("id not found");
        }
        Student s = databaseService.getStudentRepository().getById(id);
        if (student.getEmail() != null) { s.setEmail(student.getEmail()); }
        if (student.getUsername() != null) { s.setUsername(student.getUsername()); }
        if (student.getPassword() != null) { s.setPassword(student.getPassword()); }

        databaseService.getStudentRepository().save(s);

        SuccessResponse sr = new SuccessResponse();
        sr.setMessage("fields edited");
        sr.setStatus(HttpStatus.OK.value());

        return new ResponseEntity<SuccessResponse>(sr, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse> deleteStudent(@PathVariable int id)
    {
        if (!databaseService.getStudentRepository().existsById(id))
        {
            throw new InvalidEndpointException("id not found");
        }

        SuccessResponse sr = new SuccessResponse();
        sr.setMessage("student deleted");
        sr.setStatus(HttpStatus.OK.value());

        return new ResponseEntity<SuccessResponse>(sr, HttpStatus.OK);
    }
}
