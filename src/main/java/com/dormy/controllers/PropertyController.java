package com.dormy.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dormy.models.Property;
import com.dormy.requestDTO.PropertyDTO;
import com.dormy.services.PropertyService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/property")
public class PropertyController {

	@Autowired
	private PropertyService propertService;
	
	@Autowired
	private ObjectMapper mapper;

	@GetMapping(value="/check")
	public String check() {
		return "Hello";
	}

	@PostMapping
	public ResponseEntity<?> save(
			@RequestParam("property") String propertyDTO,
			@RequestParam("images") List<MultipartFile> images,
			@RequestParam("icon") MultipartFile icon
			) throws IOException {
		
		PropertyDTO propertyMapped = mapper.readValue(propertyDTO, PropertyDTO.class);
		System.out.println(propertyMapped);
		Property property = propertService.save(propertyMapped,images,icon);
		return ResponseEntity.ok().body(property);
	}
	


	@GetMapping
	public ResponseEntity<?> getAll() {
		List<Property> property = propertService.finalAll();
		return ResponseEntity.ok().body(property);

	}

	@GetMapping("{id}")
	public ResponseEntity<?> findDetails(@PathVariable("id") int id) {
		return ResponseEntity.ok().body(propertService.findById(id));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteProperty(@PathVariable("id") int id) {
		propertService.deleteById(id);
		return ResponseEntity.ok("Property deleted successfully with Id :"+id);
	}
}
