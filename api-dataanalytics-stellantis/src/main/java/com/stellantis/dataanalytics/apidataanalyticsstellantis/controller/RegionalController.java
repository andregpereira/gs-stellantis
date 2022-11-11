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

import com.stellantis.dataanalytics.apidataanalyticsstellantis.model.Regional;
import com.stellantis.dataanalytics.apidataanalyticsstellantis.repository.RegionalRepository;

@RestController
@CrossOrigin(origins = "http://localhost:8082")
@RequestMapping("/regional")
public class RegionalController {

	@Autowired
	private RegionalRepository regionalRepository;

	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> procurarRegional(@PathVariable String id) {
		if (!regionalRepository.existsById(Long.parseLong(id))) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Regional não encontrada!");
		}

		Optional<Regional> regional = regionalRepository.findById(Long.parseLong(id));

		return regional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
	}

	@GetMapping("/lista")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<List<Regional>> listarRegionais() {
		List<Regional> listaRegionais = regionalRepository.findAll();

		return ResponseEntity.status(HttpStatus.OK).body(listaRegionais);
	}

	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Regional> criarRegional(@RequestBody Regional regional) {
		Regional regionalCriada = regionalRepository.save(regional);

		return ResponseEntity.status(HttpStatus.CREATED).body(regionalCriada);
	}

	@PutMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> atualizarRegional(@RequestBody Regional regionalAtualizada) {
		if (!regionalRepository.existsById(regionalAtualizada.getId())) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Regional não encontrada!");
		}

		Optional<Regional> regional = regionalRepository.findById(regionalAtualizada.getId());

		regionalAtualizada.setId(regional.get().getId());

		regionalRepository.save(regionalAtualizada);

		return ResponseEntity.status(HttpStatus.OK).body(regionalAtualizada);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<String> deletarRegional(@PathVariable String id) {
		if (!regionalRepository.existsById(Long.parseLong(id))) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Regional não encontrada!");
		}

		regionalRepository.deleteById(Long.parseLong(id));

		return ResponseEntity.status(HttpStatus.OK).body("Regional deletada!");
	}

}
