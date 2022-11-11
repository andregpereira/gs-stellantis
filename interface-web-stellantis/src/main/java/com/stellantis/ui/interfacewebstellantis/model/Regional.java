package com.stellantis.ui.interfacewebstellantis.model;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Regional {

	private long id;
	private LocalDate dataOperacao;
	private List<Veiculo> listaVeiculos;

}
