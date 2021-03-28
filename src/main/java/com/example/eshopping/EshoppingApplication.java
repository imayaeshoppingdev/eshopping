package com.example.eshopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.example.eshopping.util.FileStorageProperties;

@SpringBootApplication
@EntityScan({"com.example.*"})
@EnableConfigurationProperties({
    FileStorageProperties.class
})
//@EnableMongoRepositories({"com.example.*"})
@EnableScheduling
public class EshoppingApplication {

	public static void main(String[] args) {
		SpringApplication.run(EshoppingApplication.class, args);
	}

}
