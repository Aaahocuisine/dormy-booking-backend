package com.dormy.services;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dormy.models.Icon;
import com.dormy.models.PropertyImage;
import com.dormy.repos.IconRepo;

@Service
public class IconService {


	
	@Autowired
	private IconRepo iconRepo;
	
	public boolean uploadIcon (MultipartFile file,long propertyNo) throws IOException {

		Icon image = Icon.builder()
				.name(file.getOriginalFilename())
				.type(file.getContentType())
				.imageData(file.getBytes())
				.propertyNo(propertyNo)
				.build();
		
		Icon imageResponse=iconRepo.save(image);
		if(imageResponse==null) {
			return false;
		}
		
	    return true;
	}
	
	
	public byte[] downloadIcon(long propertyNo){
        Optional<PropertyImage> dbImageData = iconRepo.findByPropertyNo(propertyNo);
        byte[] images=dbImageData.get().getImageData();
        return images;
    }

	
}
