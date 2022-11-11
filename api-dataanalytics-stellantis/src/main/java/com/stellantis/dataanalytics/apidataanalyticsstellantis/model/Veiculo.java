package com.stellantis.dataanalytics.apidataanalyticsstellantis.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "veiculo")
public class Veiculo {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private long id;

	@Column(name = "marca")
	private String marca;

	@Column(name = "modelo")
	private String modelo;

	@Column(name = "placa_veiculo")
	private String placaVeiculo;

	@Column(name = "quilometragem_percorrida")
	private float quilometragemPercorrida;

	@OneToOne(cascade = { CascadeType.ALL })
	private Ambiente ambiente;

}
