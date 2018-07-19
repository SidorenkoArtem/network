package com.social.network;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class NetworkApplication {
	public static void main(String[] args) {
		SpringApplication.run(NetworkApplication.class, args);
	}
}
