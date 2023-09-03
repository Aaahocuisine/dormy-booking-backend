package com.dormy.services;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dormy.models.PropertyImage;
import com.dormy.repos.ImageRepo;

@Service
public class ImageService {
	
	@Autowired
	private ImageRepo imageRepo;
	
	public String uploadImage (MultipartFile file) throws IOException {

		PropertyImage image = new PropertyImage();
		image.setName(file.getOriginalFilename());
		image.setType(file.getContentType());
		image.setImageData(file.getBytes());
		PropertyImage imageResponse=imageRepo.save(image);
		if(imageResponse!=null) {
			return "File is save properly !!!";
		}
		
	    return "Not Saved";
	}
	
	public byte[] downloadImage(String fileName){
        Optional<PropertyImage> dbImageData = imageRepo.findByName(fileName);
        byte[] images=dbImageData.get().getImageData();
        return images;
    }
}
