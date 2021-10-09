package com.oosd.vstudent.controllers;

import com.oosd.vstudent.models.Student;
import com.oosd.vstudent.services.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
