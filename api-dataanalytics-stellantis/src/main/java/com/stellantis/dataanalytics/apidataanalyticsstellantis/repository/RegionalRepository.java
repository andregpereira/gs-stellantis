package com.stellantis.dataanalytics.apidataanalyticsstellantis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stellantis.dataanalytics.apidataanalyticsstellantis.model.Regional;

@Repository
public interface RegionalRepository extends JpaRepository<Regional, Long> {

}
