package com.stellantis.dataanalytics.apidataanalyticsstellantis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stellantis.dataanalytics.apidataanalyticsstellantis.model.Veiculo;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

}
