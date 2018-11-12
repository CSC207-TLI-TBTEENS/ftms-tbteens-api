package com.ftms.ftmsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableOAuth2Sso
public class FtmsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FtmsApiApplication.class, args);
	}
}
