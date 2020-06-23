package com.unmsm.clinica;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.unmsm.clinica.mapper")
@SpringBootApplication
public class ClinicaUnmsmApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClinicaUnmsmApplication.class, args);
		//dfsdfsd
	}

}
