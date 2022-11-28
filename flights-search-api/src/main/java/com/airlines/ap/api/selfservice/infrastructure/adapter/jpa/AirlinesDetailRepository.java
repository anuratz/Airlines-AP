package com.airlines.ap.api.selfservice.infrastructure.adapter.jpa;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.airlines.ap.api.selfservice.domain.Airlines;
import com.airlines.ap.api.selfservice.domain.spi.IAirlinesRepository;

public interface AirlinesDetailRepository extends CrudRepository<Airlines, String>,
		JpaSpecificationExecutor<Airlines>, IAirlinesRepository {

	static final Logger LOGGER = LoggerFactory.getLogger(AirlinesDetailRepository.class);


	public default List<Airlines> getairlineDetail(String airlineName, String airlineCode){
		Specification<Airlines> spec = Specification.where(null);
		var airlineNameSpec = AirlineDetailsSpecification.airlineNameLike(airlineName);
		var airlineCodeSpec = AirlineDetailsSpecification.airlineCodeEquals(airlineCode);
		if (!StringUtils.isBlank(airlineName)) {
			spec = spec.and(airlineNameSpec);
		}
		if (!StringUtils.isBlank(airlineCode)) {
			spec = spec.and(airlineCodeSpec);
		}
		var airlines = this.findAll(spec);
		return airlines;
	}
}
