package com.senai.api.services.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.senai.api.dto.AcomodacaoDto;
import com.senai.api.models.Acomodacao;
import com.senai.api.models.Amenidade;
import com.senai.api.models.Usuario;
import com.senai.api.repository.AcomodacaoRepository;
import com.senai.api.repository.UsuarioRepository;
import com.senai.api.repository.AmenidadeRepository;
import com.senai.api.services.AcomodacaoService;

@Service
public class AcomodacaoServiceImpl implements AcomodacaoService {

	@Autowired
	private AcomodacaoRepository acomodacaoRepository;
	@Autowired
	private AmenidadeRepository amenidadeRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public ResponseEntity<?> cadastrar(AcomodacaoDto acomodacaoDto, Integer usuarioId) {
		Boolean isUser = usuarioRepository.findById(usuarioId).isPresent();
		if (!isUser || acomodacaoDto == null || acomodacaoDto.getAmenidades() == null
				|| acomodacaoDto.getAmenidades().isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dados da acomodação não fornecidos.");
		}

		Set<Amenidade> amenidades = new HashSet<>();
		for (Amenidade amenidade : acomodacaoDto.getAmenidades()) {
			Amenidade persistedAmenidade = amenidadeRepository.findById(amenidade.getId())
					.orElseThrow(() -> new RuntimeException("Amenidade não encontrada com ID: " + amenidade.getId()));
			amenidades.add(persistedAmenidade);
		}
		Usuario funcionario = usuarioRepository.getReferenceById(usuarioId);
		Acomodacao acomodacao = new Acomodacao();
		BeanUtils.copyProperties(acomodacaoDto, acomodacao);
		acomodacao.setAmenidades(amenidades);
		acomodacao.setFuncionario(funcionario);
		acomodacaoRepository.save(acomodacao);
		return ResponseEntity.status(HttpStatus.CREATED).body("Acomodação adicionada com sucesso.");
	}

	@Override
	public ResponseEntity<?> editar(AcomodacaoDto acomodacaoDto, Integer usuarioId, Integer acomodacaoId) {
		Optional<Acomodacao> existsAcomodacaoOptional = acomodacaoRepository.findById(acomodacaoId);
		Boolean isUser = usuarioRepository.findById(usuarioId).isPresent();

		if (isUser || existsAcomodacaoOptional.isPresent()) {
			Acomodacao acomodacao = existsAcomodacaoOptional.get();
			Usuario funcionario = usuarioRepository.getReferenceById(usuarioId);

			acomodacao.setNome(acomodacaoDto.getNome());
			acomodacao.setDescricao(acomodacaoDto.getDescricao());
			acomodacao.setCapacidade(acomodacaoDto.getCapacidade());
			acomodacao.setPreco(acomodacaoDto.getPreco());
			acomodacao.setHabilitado(acomodacaoDto.isHabilitado());

			Set<Amenidade> amenidades = new HashSet<>();
			for (Amenidade amenidade : acomodacaoDto.getAmenidades()) {
				Optional<Amenidade> amenidadeOptional = amenidadeRepository.findById(amenidade.getId());
				if (amenidadeOptional.isPresent()) {
					amenidades.add(amenidadeOptional.get());
				} else {
					return ResponseEntity.status(HttpStatus.NOT_FOUND)
							.body("Acomodação com ID " + amenidade.getId() + " não encontrada.");
				}
			}
			acomodacao.setAmenidades(amenidades);
			acomodacao.setFuncionario(funcionario);
			acomodacaoRepository.save(acomodacao);
			return ResponseEntity.status(HttpStatus.CREATED).body("Acomodação atualizada com sucesso.");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Amenidade com ID " + acomodacaoId + " não encontrada.");
		}
	}

}
