package com.empresa.projetoum;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
// injetar as beans criadas!!!
/*
 * @ComponentScan(basePackages = "com.empresa.projetoum.Praticas")
 */
/*
 * @EntityScan(basePackages = "com.empresa.domain.entities")
 * 
 * @ComponentScan(basePackages="com.empresa.domain.repository")
 */
@RestController
public class ProjetoumApplication {





	@GetMapping("/hello")
	public String metodo() {
		return "hello world";
	}



	public static void main(String[] args) {
		SpringApplication.run(ProjetoumApplication.class, args);
	}

}
