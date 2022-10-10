package com.api.testapi.controllers;

import com.api.testapi.models.Blog;
import com.api.testapi.repositories.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class BlogController {
    @Autowired
    BlogRepository blogRepository;

    @PostMapping("blogs/store")
    public Blog store(Blog blog){
        return (Blog) blogRepository.save(blog);
    }

    @GetMapping("blogs/get/{id}")
    public Optional<Blog> find(@PathVariable(value="id") Long id){
        return blogRepository.findById(id);
    }

    @PostMapping("blogs/update/{id}")
    public Blog find(@PathVariable(value="id") Long id, Blog blog){
        blog.setId(id);
        return (Blog) blogRepository.save(blog);
    }
}
