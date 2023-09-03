package com.dormy.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dormy.models.PropertyImage;

public interface ImageRepo extends JpaRepository<PropertyImage, Long>{
	
	Optional<PropertyImage> findByName(String fileName);

}
