package com.senai.api.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.senai.api.enums.Perfil;
import com.senai.api.models.Usuario;
import com.senai.api.repository.UsuarioRepository;

@Component
public class AdminInitializer implements CommandLineRunner {

	@Value("${admin.cpf}")
	private String cpf;
	@Value("${admin.senha}")
	private String senha;

	private final UsuarioRepository usuarioRepository;
	private final PasswordEncoder passwordEncoder;

	public AdminInitializer(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
		this.usuarioRepository = usuarioRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void run(String... args) throws Exception {
		if (usuarioRepository.findByCpf(cpf).isEmpty()) {
			Usuario admin = new Usuario();
			admin.setCpf(cpf);
			admin.setNome("Admin");
			admin.setSenha(passwordEncoder.encode(senha));
			admin.setPerfil(Perfil.ADMINISTRADOR.getDescricao());
			admin.setAtivo(true);
			usuarioRepository.save(admin);
		}
	}
}
