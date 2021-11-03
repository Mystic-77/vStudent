package com.oosd.vstudent.controllers;

import com.oosd.vstudent.errors.InvalidEndpointException;
import com.oosd.vstudent.errors.SuccessResponse;
import com.oosd.vstudent.models.*;
import com.oosd.vstudent.services.DatabaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Student endpoints")
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private DatabaseService databaseService;

    @ApiOperation(value = "Return List of all students")
    @GetMapping("/")
    public List<Student> retreiveAllStudents()
    {
        return databaseService.getStudentRepository().findAll();
    }

    @ApiOperation(value = "Return a particular student with the given id")
    @GetMapping("/{id}")
    public Student retrieveStudent(@PathVariable int id)
    {
        if (!databaseService.getStudentRepository().existsById(id))
        {
            throw new InvalidEndpointException("id not found");
        }
        return databaseService.getStudentRepository().getById(id);
    }

    @ApiOperation("Return list of posts made by the user")
    @GetMapping("/{id}/posts")
    public List<Post> retrieveUsersPosts(@PathVariable int id)
    {
        return databaseService.getStudentRepository().getById(id).getPosts();
    }

    @ApiOperation("Return list of liked posts made by the user")
    @GetMapping("/{id}/likedPosts")
    public List<Post> retrieveUsersLikedPosts(@PathVariable int id)
    {
        return databaseService.getStudentRepository().getById(id).getLikedPosts();
    }

    @ApiOperation("Return list of liked comments made by the user")
    @GetMapping("/{id}/likedComments")
    public List<Comment> retrieveLikedComments(@PathVariable int id)
    {
        return databaseService.getStudentRepository().getById(id).getLikedComments();
    }

    @ApiOperation("Return list of documents of the student")
    @GetMapping("/{id}/documents")
    public List<Document> retrieveDocumentsByUser(@PathVariable int id)
    {
        return databaseService.getStudentRepository().getById(id).getDocuments();
    }

    @ApiOperation("Return list of carpools by the user")
    @GetMapping("/{id}/carpools")
    public List<CarPool> retrieveCarpoolsByUser(@PathVariable int id)
    {
        return databaseService.getStudentRepository().getById(id).getCarPools();
    }

    @ApiOperation("Adds a new student to the database")
    @PostMapping("/")
    public Student addStudent(@RequestBody Student student)
    {
        return databaseService.getStudentRepository().save(student);
    }

    @ApiOperation("Edit an existing student with the given id")
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

    @ApiOperation("delete a student entry with given id")
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
