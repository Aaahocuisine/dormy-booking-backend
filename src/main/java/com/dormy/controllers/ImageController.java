package com.dormy.controllers;


import java.io.IOException;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dormy.services.ImageService;

@RestController
@RequestMapping("/image")
public class ImageController {

	@Autowired
	private ImageService imageService;
	
	@GetMapping("/check")
	public String check() {
		return "check is Ok !!";
	}
	
//	@PostMapping
//	public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile file) throws IOException {
//		String uploadImage = imageService.uploadImage(file);
//		return ResponseEntity.status(HttpStatus.OK)
//				.body(uploadImage);
//	}
//	
//	@PostMapping("/multiImage")
//	public ResponseEntity<?> uploadListOfImage(@RequestParam("image")List<MultipartFile> file) throws IOException {
//		String uploadImage = imageService.uploadListOfImage(file);
//		return ResponseEntity.status(HttpStatus.OK)
//				.body(uploadImage);
//	}
//
//	@GetMapping("/{fileName}")
//	public ResponseEntity<?> downloadImage(@PathVariable String fileName){
//		byte[] imageData=imageService.downloadImage(fileName);
//		return ResponseEntity.status(HttpStatus.OK)
//				.contentType(MediaType.valueOf("image/png"))
//				.body(imageData);
//
//	}
}
