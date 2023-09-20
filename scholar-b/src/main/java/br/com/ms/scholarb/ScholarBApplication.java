package br.com.ms.scholarb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ScholarBApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScholarBApplication.class, args);
	}

}
