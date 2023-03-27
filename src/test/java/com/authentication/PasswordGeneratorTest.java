package com.authentication;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGeneratorTest {
	@Test
	public static void main(String[] args) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String plainPassword = "nam2020";
		String encodedPassword = passwordEncoder.encode(plainPassword);
		
		System.out.println(encodedPassword);
	}

}