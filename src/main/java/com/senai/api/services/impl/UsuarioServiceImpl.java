package com.senai.api.services.impl;

import java.util.InputMismatchException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.senai.api.dto.UsuarioDto;
import com.senai.api.enums.Perfil;
import com.senai.api.models.Usuario;
import com.senai.api.repository.UsuarioRepository;
import com.senai.api.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public ResponseEntity<?> cadastrar(UsuarioDto usuarioDto) {

		String cpf = formatCpf(usuarioDto.getCpf());
		Boolean isAvaible = validCpf(cpf) && usuarioRepository.findByCpf(cpf).isEmpty();

		if (isAvaible && usuarioDto != null) {
			Usuario usuario = new Usuario();
			String encryptedPassword = new BCryptPasswordEncoder().encode(usuarioDto.getSenha());
			BeanUtils.copyProperties(usuarioDto, usuario);

			usuario.setCpf(cpf);
			usuario.setSenha(encryptedPassword);

			usuarioRepository.save(usuario);
			return ResponseEntity.ok("Usuário adicionado com sucesso");

		} else {
			return ResponseEntity.ok("Não foi possivel criar");
		}
	}

	public Boolean isCpf(String CPF) {

		if (CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222")
				|| CPF.equals("33333333333") || CPF.equals("44444444444") || CPF.equals("55555555555")
				|| CPF.equals("66666666666") || CPF.equals("77777777777") || CPF.equals("88888888888")
				|| CPF.equals("99999999999") || (CPF.length() != 11))
			return (false);

		char dig10, dig11;
		int sm, i, r, num, peso;

		try {
			sm = 0;
			peso = 10;
			for (i = 0; i < 9; i++) {

				num = (int) (CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig10 = '0';
			else
				dig10 = (char) (r + 48);

			sm = 0;
			peso = 11;
			for (i = 0; i < 10; i++) {
				num = (int) (CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig11 = '0';
			else
				dig11 = (char) (r + 48);

			if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
				return (true);
			else
				return (false);
		} catch (InputMismatchException erro) {
			return (false);
		}
	}

	public String formatCpf(String cpf) {
		if (cpf.contains(".")) {
			cpf = cpf.replace(".", "");
		}
		if (cpf.contains("-")) {
			cpf = cpf.replace("-", "");
		}
		if (cpf.contains("/")) {
			cpf = cpf.replace("/", "");
		}
		return cpf;
	}

	public Boolean validCpf(String cpf) {

		String format = formatCpf(cpf);
		Boolean valid = isCpf(format);

		return valid;
	}

}