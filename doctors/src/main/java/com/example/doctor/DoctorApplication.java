package com.example.doctor;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class DoctorApplication {

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		SpringApplicationBuilder app = (SpringApplicationBuilder) new SpringApplicationBuilder(DoctorApplication.class)
			    .web(WebApplicationType.REACTIVE)
			    .run(args);
	}

}
