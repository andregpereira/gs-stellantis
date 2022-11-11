package com.stellantis.ui.interfacewebstellantis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Veiculo {

	private long id;
	private String marca;
	private String modelo;
	private String placaVeiculo;
	private float quilometragemPercorrida;
	private Ambiente ambiente;

}
