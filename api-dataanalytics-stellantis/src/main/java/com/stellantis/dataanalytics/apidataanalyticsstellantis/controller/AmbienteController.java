package com.stellantis.dataanalytics.apidataanalyticsstellantis.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stellantis.dataanalytics.apidataanalyticsstellantis.model.Ambiente;
import com.stellantis.dataanalytics.apidataanalyticsstellantis.repository.AmbienteRepository;

@RestController
@CrossOrigin(origins = "http://localhost:8082")
@RequestMapping("/ambiente")
public class AmbienteController {

	@Autowired
	private AmbienteRepository ambienteRepository;

	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> procurarAmbiente(@PathVariable String id) {
		if (!ambienteRepository.existsById(Long.parseLong(id))) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ambiente não encontrado!");
		}

		Optional<Ambiente> ambiente = ambienteRepository.findById(Long.parseLong(id));

		return ambiente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
	}

	@GetMapping("/lista")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<List<Ambiente>> listarAmbientes() {
		List<Ambiente> listaVeiculos = ambienteRepository.findAll();

		return ResponseEntity.status(HttpStatus.OK).body(listaVeiculos);
	}

	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Ambiente> criarAmbiente(@RequestBody Ambiente ambiente) {
		Ambiente ambienteCriado = ambienteRepository.save(ambiente);

		return ResponseEntity.status(HttpStatus.CREATED).body(ambienteCriado);
	}

	@PutMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> atualizarAmbiente(@RequestBody Ambiente ambienteAtualizado) {
		if (!ambienteRepository.existsById(ambienteAtualizado.getId())) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ambiente não encontrado!");
		}

		Optional<Ambiente> regional = ambienteRepository.findById(ambienteAtualizado.getId());

		ambienteAtualizado.setId(regional.get().getId());

		ambienteRepository.save(ambienteAtualizado);

		return ResponseEntity.status(HttpStatus.OK).body(ambienteAtualizado);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<String> deletarAmbiente(@PathVariable String id) {
		if (!ambienteRepository.existsById(Long.parseLong(id))) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ambiente não encontrado!");
		}

		ambienteRepository.deleteById(Long.parseLong(id));

		return ResponseEntity.status(HttpStatus.OK).body("Ambiente deletado!");
	}

}
