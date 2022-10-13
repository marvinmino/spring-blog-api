package com.api.testapi.controllers;

import com.api.testapi.models.Blog;
import com.api.testapi.repositories.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class BlogController {
    @Autowired
    BlogRepository blogRepository;

    @PostMapping("blogs/store")
    public ResponseEntity<Blog> store(@RequestBody @Valid Blog blog){
        blogRepository.save(blog);
        return new ResponseEntity<Blog>(blog, HttpStatus.OK);
    }

    @GetMapping("blogs/get/{id}")
    public ResponseEntity<Blog> find(@PathVariable(value="id") Long id){
        Optional<Blog> blog =  blogRepository.findById(id);
        return new ResponseEntity<Blog>(blog.get(), HttpStatus.OK);
    }

    @PostMapping("blogs/update/{id}")
    public ResponseEntity<Blog> find(@PathVariable(value="id") Long id,@RequestBody @Valid  Blog blog){
        blog.setId(id);
        return new ResponseEntity<Blog>(blog, HttpStatus.OK);
    }

    @DeleteMapping("blogs/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable(value="id") Long id){
        Optional<Blog> blog = blogRepository.findById(id);
        blogRepository.delete(blog.get());
        return new ResponseEntity<String>("Deleted successfully", HttpStatus.OK);
    }
}
