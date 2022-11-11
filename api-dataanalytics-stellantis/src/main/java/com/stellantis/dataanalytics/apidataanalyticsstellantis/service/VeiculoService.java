package com.stellantis.dataanalytics.apidataanalyticsstellantis.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.stellantis.dataanalytics.apidataanalyticsstellantis.model.Regional;
import com.stellantis.dataanalytics.apidataanalyticsstellantis.model.Veiculo;
import com.stellantis.dataanalytics.apidataanalyticsstellantis.repository.RegionalRepository;
import com.stellantis.dataanalytics.apidataanalyticsstellantis.repository.VeiculoRepository;

@Service
public class VeiculoService {

	@Autowired
	private VeiculoRepository veiculoRepository;

	@Autowired
	private RegionalRepository regionalRepository;

	public ResponseEntity<?> criarVeiculo(Veiculo veiculo) {
		Regional regional = new Regional();
		regional.setDataOperacao(LocalDate.now());

		List<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
		Veiculo veiculoCriado = veiculoRepository.save(veiculo);
		listaVeiculos.add(veiculoCriado);
		regional.setListaVeiculos(listaVeiculos);

		regionalRepository.save(regional);

		return ResponseEntity.status(HttpStatus.CREATED).body("Veículo registrado!  " + veiculoCriado + " " + regional);
	}

}
