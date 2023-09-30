package com.dormy.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dormy.exception.DormyServiceCustomException;
import com.dormy.models.Property;
import com.dormy.requestDTO.ApprovalRequest;
import com.dormy.requestDTO.PropertyDTO;
import com.dormy.responseDTO.SuccessResponse;
import com.dormy.services.PropertyService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/property")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PropertyController {

	@Autowired
	private PropertyService propertService;
	
	@Autowired
	private ObjectMapper mapper;

	@GetMapping(value="/check")
	public String check() {
		return "Hello";
	}

	@PostMapping("/register")
	public ResponseEntity<SuccessResponse> save(
			@RequestParam("property") String propertyDTO,
			@RequestParam("images") MultipartFile images,
			@RequestParam("icon") MultipartFile icon
			) throws IOException {
		
		PropertyDTO propertyMapped = mapper.readValue(propertyDTO, PropertyDTO.class);
		System.out.println(propertyMapped);
		Property property = propertService.save(propertyMapped,images,icon);
		SuccessResponse response = SuccessResponse.builder()
				.message("Property Derails Saved Successfully !!")
				.error(false)
				.success(true)
				.data(property)
				.build();
		return ResponseEntity.ok().body(response);
	}
	
	@PostMapping("/updateApprovalStatus")
	public ResponseEntity<SuccessResponse> updateApprovalStatus(@RequestBody ApprovalRequest request){
		Property property = propertService.updateApprovalSatus(request);
		
		SuccessResponse response = SuccessResponse.builder()
				.message("Status is update")
				.error(false)
				.success(true)
				.data(property)
				.build();
		return ResponseEntity.ok().body(response);
		
	}
	
	@PostMapping("/registerWithout")
	public ResponseEntity<SuccessResponse> save(
			@RequestParam("property") String propertyDTO) throws IOException {
		
		PropertyDTO propertyMapped = mapper.readValue(propertyDTO, PropertyDTO.class);
		System.out.println(propertyMapped);
		Property property = propertService.saveWithout(propertyMapped);
		SuccessResponse response = SuccessResponse.builder()
				.message("Property Derails Saved Successfully !!")
				.error(false)
				.success(true)
				.data(property)
				.build();
		return ResponseEntity.ok().body(response);
	}


	@GetMapping("/fetchAll")
	public ResponseEntity<SuccessResponse> getAll() {
		List<Property> property = propertService.finalAll();
		
		SuccessResponse response = SuccessResponse.builder()
				.message("Get All properties !!")
				.error(false)
				.success(true)
				.data(property)
				.build();
		return ResponseEntity.ok().body(response);

	}

	@GetMapping("/fetch/{id}")
	public ResponseEntity<SuccessResponse> findDetails(@PathVariable("id") int id) {
		
		Property property =propertService.findById(id);
		if(property==null)
			throw new DormyServiceCustomException("Propert with Id Not exist !!");
		
		SuccessResponse response = SuccessResponse.builder()
				.message("Fetch details successfull !!")
				.error(false)
				.success(true)
				.data(property)
				.build();
		return ResponseEntity.ok().body(response);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<SuccessResponse> deleteProperty(@PathVariable("id") int id) {
		try {
		propertService.deleteById(id);
		}catch(Exception e) {
			throw new DormyServiceCustomException(e.getMessage());
		}

		SuccessResponse response = SuccessResponse.builder()
				.message("Property with id : "+id+" is deleted successfull !!")
				.error(false)
				.success(true)
				.build();
		return ResponseEntity.ok().body(response);
	}
}
