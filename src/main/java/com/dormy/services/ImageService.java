package com.dormy.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dormy.exception.DormyServiceCustomException;
import com.dormy.models.PropertyImage;
import com.dormy.repos.ImageRepo;

@Service
public class ImageService {

	@Autowired
	private ImageRepo imageRepo;

	public void uploadIcon(MultipartFile file, long propertyNo) throws IOException {
		try {
			PropertyImage image = PropertyImage.builder()
					.name(file.getOriginalFilename())
					.type(file.getContentType())
					.imageData(file.getBytes())
					.propertyNo(propertyNo)
					.build();

			imageRepo.save(image);

		} catch (Exception e) {
			throw new DormyServiceCustomException("Failed to save Icon");
		}

	}

	public void uploadListOfImage(List<MultipartFile> fileList, long propertyNo) throws IOException {

		try {
		for (MultipartFile file : fileList) {
			PropertyImage image = PropertyImage.builder().name(file.getOriginalFilename()).type(file.getContentType())
					.imageData(file.getBytes()).propertyNo(propertyNo).build();

			imageRepo.save(image);
		}
		} catch (Exception e) {
			throw new DormyServiceCustomException("Failed to save Iamge");
		}
		
	}

	public byte[] downloadImage(long propertyNo) {
		Optional<PropertyImage> dbImageData = imageRepo.findByPropertyNo(propertyNo);
		byte[] images = dbImageData.get().getImageData();
		return images;
	}
}
