package com.senai.api.services;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.senai.api.enums.Perfil;
import com.senai.api.models.Usuario;
import com.senai.api.repository.UsuarioRepository;
import com.senai.api.utils.CryptoUtil;

@Service
public class AdminService {
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	PasswordEncoder passwordEncoder;

	@Value("${admin.cpf}")
	private String cpf;
	@Value("${admin.senha}")
	private String senha;

	public ResponseEntity<?> initializerAdmin(Usuario usuario) throws Exception {

		SecretKey key = CryptoUtil.getFixedSecretKey();
		String cpfCriptografado = CryptoUtil.encryptCPF(cpf, key);
		if (!usuarioService.verificarCpfExistente(cpf)) {
			Usuario admin = new Usuario();
			admin.setCpf(cpfCriptografado);
			admin.setNome("Admin");
			admin.setSenha(passwordEncoder.encode(senha));
			admin.setPerfil(Perfil.ADMINISTRADOR.getDescricao());
			admin.setHabilitado(true);
			usuarioRepository.save(admin);
		}
		return null;
	}

}
