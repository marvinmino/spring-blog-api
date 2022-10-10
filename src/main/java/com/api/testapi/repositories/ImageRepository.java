package com.api.testapi.repositories;

import java.util.List;
import java.util.Optional;

import com.api.testapi.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
	Optional<Image> findByName(String name);
	Optional<Image> findById(Long id);
	List<Image> findByBlog(Long blog_id);
}
