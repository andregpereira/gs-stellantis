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

import com.stellantis.dataanalytics.apidataanalyticsstellantis.model.Veiculo;
import com.stellantis.dataanalytics.apidataanalyticsstellantis.repository.VeiculoRepository;
import com.stellantis.dataanalytics.apidataanalyticsstellantis.service.VeiculoService;

@RestController
@CrossOrigin(origins = "http://localhost:8082")
@RequestMapping("/veiculo")
public class VeiculoController {

	@Autowired
	private VeiculoRepository veiculoRepository;

	@Autowired
	private VeiculoService veiculoService;

	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> procurarVeiculo(@PathVariable String id) {
		if (!veiculoRepository.existsById(Long.parseLong(id))) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Veículo não encontrado!");
		}

		Optional<Veiculo> veiculo = veiculoRepository.findById(Long.parseLong(id));

		return veiculo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
	}

	@GetMapping("/lista")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<List<Veiculo>> listarVeiculos() {
		List<Veiculo> listaVeiculos = veiculoRepository.findAll();

		return ResponseEntity.status(HttpStatus.OK).body(listaVeiculos);
	}

	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> criarVeiculo(@RequestBody Veiculo veiculo) {
		return ResponseEntity.status(HttpStatus.OK).body(veiculoService.criarVeiculo(veiculo));
	}

	@PutMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> atualizarVeiculo(@RequestBody Veiculo veiculoAtualizado) {
		if (!veiculoRepository.existsById(veiculoAtualizado.getId())) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Veiculo não encontrado!");
		}

		Optional<Veiculo> veiculo = veiculoRepository.findById(veiculoAtualizado.getId());

		veiculoAtualizado.setId(veiculo.get().getId());

		veiculoRepository.save(veiculoAtualizado);

		return ResponseEntity.status(HttpStatus.OK).body(veiculoAtualizado);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<String> deletarVeiculo(@PathVariable String id) {
		if (!veiculoRepository.existsById(Long.parseLong(id))) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Veiculo não encontrado!");
		}

		veiculoRepository.deleteById(Long.parseLong(id));

		return ResponseEntity.status(HttpStatus.OK).body("Veiculo deletado!");
	}

}
