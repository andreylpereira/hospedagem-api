package com.senai.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TesteController {

	@GetMapping("/123")
	public String testResponse() {
		return "123";
	}

}
