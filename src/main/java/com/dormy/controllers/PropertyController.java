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

import com.dormy.dtos.PropertyDTO;
import com.dormy.models.Property;
import com.dormy.services.PropertyService;

@RestController
@RequestMapping("/api/property")
public class PropertyController {

	@Autowired
	public PropertyService propertService;

	@GetMapping(value="/check")
	public String check() {
		return "Hello";
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody PropertyDTO propertyDTO) {
		System.out.println(propertyDTO);
		Property property = propertService.save(propertyDTO);
		return ResponseEntity.ok().body(property);
	}
	
//	@PostMapping
//	public ResponseEntity<?> save(@RequestBody PropertyDTO propertyDTO ,@RequestParam("file") MultipartFile file) throws IOException {
//		System.out.println(propertyDTO);
//		propertService.save(propertyDTO,file);
//		return ResponseEntity.ok().body("Saved the property !!");
//	}

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
