package com.stellantis.dataanalytics.apidataanalyticsstellantis.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "regional")
public class Regional {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private long id;

	@Column(name = "data_operacao")
	private LocalDate dataOperacao;

	@OneToMany(cascade = { CascadeType.ALL })
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "lista_veiculos")
	private List<Veiculo> listaVeiculos;

}
