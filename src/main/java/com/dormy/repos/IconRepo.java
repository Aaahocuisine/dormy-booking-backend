package com.dormy.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dormy.models.Icon;
import com.dormy.models.PropertyImage;

public interface IconRepo extends JpaRepository<Icon, Long> {

	Optional<PropertyImage> findByPropertyNo(long propertyNo);
	

}
