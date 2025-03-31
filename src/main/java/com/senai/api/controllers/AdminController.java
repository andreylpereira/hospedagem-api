package com.senai.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.api.models.Usuario;
import com.senai.api.services.AdminService;



@RestController
@RequestMapping("api/auth")
public class AdminController {

	@Autowired
	private AdminService adminService;	

	@PostMapping("/admin")
	public ResponseEntity<?> admin(@RequestBody Usuario usuario) {
		return adminService.initializerAdmin(usuario);
	}
}
