package com.dormy.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dormy.models.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

}
