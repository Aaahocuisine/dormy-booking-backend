package com.dormy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.dormy.utils.FileUploadProperties;

@SpringBootApplication
@EnableConfigurationProperties({
    FileUploadProperties.class
})
public class DormyBookingBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(DormyBookingBackendApplication.class, args);
	}

}
