package com.oosd.vstudent.controllers;

import com.oosd.vstudent.errors.ErrorResponse;
import com.oosd.vstudent.errors.InvalidEndpointException;
import com.oosd.vstudent.errors.SuccessResponse;
import com.oosd.vstudent.models.Role;
import com.oosd.vstudent.models.Student;
import com.oosd.vstudent.services.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private DatabaseService databaseService;

    @GetMapping("/{id}")
    public Role retrieveRole(@PathVariable int id)
    {
        if (!databaseService.getRoleRepository().existsById(id))
        {
            throw new InvalidEndpointException("id not found");
        }
        return databaseService.getRoleRepository().getById(id);

    }

    @GetMapping("/")
    public List<Role> retrieveRoles()
    {
        return databaseService.getRoleRepository().findAll();
    }

    @PostMapping("/")
    public Role addRole(@RequestBody Role role)
    {
        databaseService.getRoleRepository().save(role);
        return role;
    }

    @GetMapping("/{id}/students")
    public List<Student> retrieveStudentsByRole(@PathVariable int id)
    {
        if (!databaseService.getRoleRepository().existsById(id))
        {
            throw new InvalidEndpointException("id not found");
        }
        return databaseService.getRoleRepository().getById(id).getStudents();
    }

    @PutMapping("/{id}")
    public Role updateRole(@PathVariable("id") int id, @RequestBody Role role)
    {
        role.setId(id);
        if (!databaseService.getRoleRepository().existsById(id))
        {
            throw new InvalidEndpointException("id not found");
        }
        databaseService.getRoleRepository().save(role);
        return role;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse> deleteRole(@PathVariable("id") int id)
    {
        databaseService.getRoleRepository().deleteById(id);
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setMessage("role deleted");
        successResponse.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<SuccessResponse>(successResponse, HttpStatus.OK);
    }
}
