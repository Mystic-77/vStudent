package com.oosd.vstudent.controllers;

import com.oosd.vstudent.errors.InvalidEndpointException;
import com.oosd.vstudent.errors.SuccessResponse;
import com.oosd.vstudent.models.Post;
import com.oosd.vstudent.services.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private DatabaseService databaseService;

    //get all blog posts
    @GetMapping("/blog/")
    public List<Post> getAllBlogPosts()
    {
        return databaseService.getPostRepository().getPostsByType(Post.PostType.BLOG);
    }

    @GetMapping("/forum/")
    public List<Post> getAllForumPosts()
    {
        return databaseService.getPostRepository().getPostsByType(Post.PostType.FORUM);
    }

    @GetMapping("/{id}")
    public Post retrievePost(@PathVariable int id)
    {
        if (!databaseService.getPostRepository().existsById(id))
        {
            throw new InvalidEndpointException("id not found");
        }
        return databaseService.getPostRepository().getById(id);
    }

    @PostMapping("/")
    public ResponseEntity<SuccessResponse> addPost(@RequestBody Post post)
    {
        databaseService.getPostRepository().save(post);
        SuccessResponse sr = new SuccessResponse();
        sr.setMessage("post added");
        sr.setStatus(HttpStatus.OK.value());

        return new ResponseEntity<SuccessResponse>(sr, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SuccessResponse> editPost(@PathVariable int id, @RequestBody Post post)
    {
        if (!databaseService.getPostRepository().existsById(id))
        {
            throw new InvalidEndpointException("id not found");
        }

        Post p = databaseService.getPostRepository().getById(id);
        if (post.getTitle() != null) { p.setTitle(post.getTitle()); }
        if (post.getContent() != null) { p.setContent(post.getContent()); }
        if (post.getTags() != null) { p.setTags(post.getTags()); }

        databaseService.getPostRepository().save(p);
        SuccessResponse sr = new SuccessResponse();
        sr.setStatus(HttpStatus.OK.value());
        sr.setMessage("post edited");

        return new ResponseEntity<SuccessResponse>(sr, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse> deletePost(@PathVariable int id)
    {
        if (!databaseService.getPostRepository().existsById(id))
        {
            throw new InvalidEndpointException("id not found");
        }

        SuccessResponse sr = new SuccessResponse();
        sr.setStatus(HttpStatus.OK.value());
        sr.setMessage("post deleted");

        return new ResponseEntity<SuccessResponse>(sr, HttpStatus.OK);
    }
}