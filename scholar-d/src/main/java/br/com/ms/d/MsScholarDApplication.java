package br.com.ms.d;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsScholarDApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsScholarDApplication.class, args);
	}

}
