package com.dormy.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dormy.exception.DormyServiceCustomException;
import com.dormy.models.Property;
import com.dormy.models.PropertyImage;
import com.dormy.repos.ImageRepo;
import com.dormy.repos.PropertyRepository;
import com.dormy.requestDTO.PropertyDTO;

@Service
public class PropertyService {

	@Autowired
	private PropertyRepository propertyRepo;
	
	@Autowired
	private ImageRepo imageRepo;
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private IconService iconService;
	

	public Property save(PropertyDTO propertyDTO,List<MultipartFile> images, MultipartFile icon) throws IOException {
		try {
		Property property = Property.builder()
				.propertyName(propertyDTO.getPropertyName())
				.propertyNo(propertyDTO.getPropertyNo())
				.address1(propertyDTO.getAddress1())
				.address2(propertyDTO.getAddress2())
				.approvalstatus(propertyDTO.getApprovalstatus())
				.city(propertyDTO.getCity())
				.country(propertyDTO.getCountry())
				.customerCareNo(propertyDTO.getCustomerCareNo())
				.dateRegistered(propertyDTO.getDateRegistered())
				.googleMapLink(propertyDTO.getGoogleMapLink())
				.managerMobNo(propertyDTO.getManagerMobNo())
				.managerName(propertyDTO.getManagerName())
				.ownerName(propertyDTO.getOwnerName())
				.pinCode(propertyDTO.getPinCode())
				.state(propertyDTO.getState())
				.build();
		
		 imageService.uploadListOfImage(images, propertyDTO.getPropertyNo());
			
		 iconService.uploadIcon(icon, propertyDTO.getPropertyNo());
		 
		 return propertyRepo.save(property);
		
		} catch (Exception e) {
			throw new DormyServiceCustomException("Email already registered","PROPERTY_SAVE_FAILED");
		}

	}

	public List<Property> finalAll() {
		return propertyRepo.findAll();
	}

	public Property findById(long id) {
		return propertyRepo.getById(id);
	}
	
	public void deleteById(long id) {
		propertyRepo.deleteById(id);
	}
	

}
