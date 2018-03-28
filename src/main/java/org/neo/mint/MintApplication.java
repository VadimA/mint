package org.neo.mint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

@EnableOAuth2Sso
@SpringBootApplication
public class MintApplication {

	public static void main(String[] args) {
		SpringApplication.run(MintApplication.class, args);
	}
}
