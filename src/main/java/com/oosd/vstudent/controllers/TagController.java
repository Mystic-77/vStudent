package com.oosd.vstudent.controllers;

import com.oosd.vstudent.errors.InvalidEndpointException;
import com.oosd.vstudent.errors.SuccessResponse;
import com.oosd.vstudent.models.Post;
import com.oosd.vstudent.models.Student;
import com.oosd.vstudent.models.Tag;
import com.oosd.vstudent.services.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    DatabaseService databaseService;

    //get all tags
    @GetMapping("/")
    public List<Tag> retrieveTags()
    {
        return databaseService.getTagRepository().findAll();
    }

    //get a particular tag
    @GetMapping("/{id}")
    public Tag retrieveTag(@PathVariable int id)
    {
        if (!databaseService.getTagRepository().existsById(id))
        {
            throw new InvalidEndpointException("id not found");
        }
        return databaseService.getTagRepository().getById(id);
    }

    //get all students of this tag
    @GetMapping("/{id}/students")
    public List<Student> retrieveStudentsByTag(@PathVariable int id)
    {
        if (!databaseService.getTagRepository().existsById(id))
        {
            throw new InvalidEndpointException("id not found");
        }
        return databaseService.getTagRepository().getById(id).getStudents();
    }

    //get all posts of this tag
    @GetMapping("/{id}/posts")
    public List<Post> retrievePostsByTag(@PathVariable int id)
    {
        if (!databaseService.getTagRepository().existsById(id))
        {
            throw new InvalidEndpointException("id not found");
        }
        return databaseService.getTagRepository().getById(id).getPosts();
    }

    //get tag by tag name
    @GetMapping("/{tagName}")
    public Tag retrieveTagByName(@PathVariable String tagName)
    {
        return databaseService.getTagRepository().getTagByTagName(tagName);
    }

    //add a tag
    @PostMapping("/")
    public Tag addTag(@RequestBody Tag tag)
    {
        databaseService.getTagRepository().save(tag);
        return tag;
    }

    //edit role
    @PutMapping("/{id}")
    public Tag editTag(@PathVariable int id, @RequestBody Tag tag)
    {
        tag.setId(id);
        if (!databaseService.getTagRepository().existsById(id))
        {
            throw new InvalidEndpointException("id not found");
        }
        databaseService.getTagRepository().save(tag);
        return tag;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse> deleteTag(@PathVariable int id)
    {
        databaseService.getTagRepository().deleteById(id);
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setStatus(HttpStatus.OK.value());
        successResponse.setMessage("tag deleted");
        return new ResponseEntity<SuccessResponse>(successResponse, HttpStatus.OK);
    }
}
