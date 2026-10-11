package com.festoi.festoi;

import com.festoi.festoi.models.Art;
import com.festoi.festoi.models.Customer;
import com.festoi.festoi.models.Role;
import com.festoi.festoi.repositories.ArtistRepo;
import com.festoi.festoi.repositories.CustomerRepo;
import com.festoi.festoi.repositories.RoleRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.util.HashSet;


@SpringBootApplication
public class FestoiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FestoiApplication.class, args);
	}

	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setDefaultEncoding("UTF-8");
		return multipartResolver;
	}


}
