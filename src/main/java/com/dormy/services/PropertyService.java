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
import com.dormy.requestDTO.ApprovalRequest;
import com.dormy.requestDTO.PropertyDTO;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class PropertyService {

	@Autowired
	private PropertyRepository propertyRepo;
	
	@Autowired
	private ImageRepo imageRepo;
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private IconService iconService;
	

	public Property save(PropertyDTO propertyDTO,MultipartFile images, MultipartFile icon) throws IOException {
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
//		if(!images.isEmpty()) {
//		try {
//		imageService.uploadListOfImage(images, propertyDTO.getPropertyNo());
//		}catch (Exception e) {
//			throw new DormyServiceCustomException(e.getMessage());
//		}
//		}
//		if(!icon.getContentType().isEmpty()) {
//		try {	
//		 iconService.uploadIcon(icon, propertyDTO.getPropertyNo());
//		}catch (Exception e) {
//			throw new DormyServiceCustomException(e.getMessage());
//		}
//		}
		 return propertyRepo.save(property);
		 
	} catch (DataIntegrityViolationException e) {
		throw new DormyServiceCustomException("Property Number already registered");
	}catch (Exception e) {
		throw new DormyServiceCustomException(e.getMessage());
	}
		
		 	

	}
	
	public Property updateApprovalSatus(ApprovalRequest request) {
		Property property= propertyRepo.getById(request.getId());
		if(property==null) {
			throw new DormyServiceCustomException("Property Id not exist");
		}
		property.setApprovalstatus(request.getStatus());
		
		return propertyRepo.save(property);
		
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



	public Property saveWithout(PropertyDTO propertyDTO) {
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
//			if(!images.isEmpty()) {
//			try {
//			imageService.uploadListOfImage(images, propertyDTO.getPropertyNo());
//			}catch (Exception e) {
//				throw new DormyServiceCustomException(e.getMessage());
//			}
//			}
//			if(!icon.getContentType().isEmpty()) {
//			try {	
//			 iconService.uploadIcon(icon, propertyDTO.getPropertyNo());
//			}catch (Exception e) {
//				throw new DormyServiceCustomException(e.getMessage());
//			}
//			}
			 return propertyRepo.save(property);
			 
		} catch (DataIntegrityViolationException e) {
			throw new DormyServiceCustomException("Property Number already registered");
		}catch (Exception e) {
			throw new DormyServiceCustomException(e.getMessage());
		}
			
	}
	

}
