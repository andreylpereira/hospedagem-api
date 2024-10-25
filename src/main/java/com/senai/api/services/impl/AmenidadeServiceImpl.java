package com.senai.api.services.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.senai.api.dto.AmenidadeDto;
import com.senai.api.models.Amenidade;
import com.senai.api.models.Usuario;
import com.senai.api.repository.AmenidadeRepository;
import com.senai.api.repository.UsuarioRepository;
import com.senai.api.services.AmenidadeService;

@Service
public class AmenidadeServiceImpl implements AmenidadeService {

	@Autowired
	private AmenidadeRepository amenidadeRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public ResponseEntity<?> cadastrar(AmenidadeDto amenidadeDto, Integer usuarioId) {

		Amenidade amenidade = new Amenidade();
		Boolean isExists = amenidadeRepository.existsByNome(amenidadeDto.getNome());
		Boolean isUser = usuarioRepository.findById(usuarioId).isPresent();

		if (!isExists && isUser) {
			Usuario funcionario = usuarioRepository.getReferenceById(usuarioId);
			BeanUtils.copyProperties(amenidadeDto, amenidade);
			amenidade.setFuncionario(funcionario);
			amenidadeRepository.save(amenidade);
			return ResponseEntity.status(HttpStatus.CREATED).body("Amenidade adicionada ao banco de dados.");
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body("Já existe uma amenidade com este nome.");
	}

	@Override
	public ResponseEntity<?> editar(AmenidadeDto amenidadeDto, Integer usuarioId, Integer amenidadeId) {
		boolean isExists = amenidadeRepository.existsById(amenidadeId);
		Boolean isUser = usuarioRepository.findById(usuarioId).isPresent();

		if (isExists && isUser) {
			Amenidade amenidade = new Amenidade();
			Usuario funcionario = usuarioRepository.getReferenceById(usuarioId);
			BeanUtils.copyProperties(amenidadeDto, amenidade);
			amenidade.setId(amenidadeId);
			amenidade.setFuncionario(funcionario);
			amenidadeRepository.save(amenidade);
			return ResponseEntity.status(HttpStatus.CREATED).body("Amenidade atualizada com sucesso");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Amenidade com ID " + amenidadeId + " não encontrada.");
	}

}
