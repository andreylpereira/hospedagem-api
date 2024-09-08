package com.senai.api.services.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.senai.api.dto.AmenidadeDto;
import com.senai.api.models.Amenidade;
import com.senai.api.repository.AmenidadeRepository;
import com.senai.api.services.AmenidadeService;

@Service
public class AmenidadeServiceImpl implements AmenidadeService {

	@Autowired
	private AmenidadeRepository amenidadeRepository;

	@Override
	public ResponseEntity<?> cadastrar(AmenidadeDto amenidadeDto) {

		Amenidade amenidade = new Amenidade();
		Boolean isExists = amenidadeRepository.existsByNome(amenidadeDto.getNome());

		if (!isExists) {
			BeanUtils.copyProperties(amenidadeDto, amenidade);
			amenidadeRepository.save(amenidade);
			return ResponseEntity.status(HttpStatus.CREATED).body("Amenidade adicionada ao banco de dados.");
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body("Já existe uma amenidade com este nome.");
	}

	@Override
	public ResponseEntity<?> editar(AmenidadeDto amenidadeDto, Integer amenidadeId) {
		boolean isExists = amenidadeRepository.existsById(amenidadeId);
		if (isExists) {
			Amenidade amenidade = new Amenidade();
			BeanUtils.copyProperties(amenidadeDto, amenidade);
			amenidade.setId(amenidadeId);
			amenidadeRepository.save(amenidade);
			return ResponseEntity.status(HttpStatus.CREATED).body("Amenidade atualizada com sucesso");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body("Amenidade com ID " + amenidadeId + " não encontrada.");
	}

}
