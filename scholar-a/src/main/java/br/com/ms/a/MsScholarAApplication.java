package br.com.ms.a;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsScholarAApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsScholarAApplication.class, args);
    }
}
