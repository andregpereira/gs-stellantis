package com.stellantis.ui.interfacewebstellantis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ambiente {

	private long id;
	private String estado;
	private String cidade;
	private String bairro;
	private String temperaturaAmbiente;
	private String qualidadeDoAr;

}
