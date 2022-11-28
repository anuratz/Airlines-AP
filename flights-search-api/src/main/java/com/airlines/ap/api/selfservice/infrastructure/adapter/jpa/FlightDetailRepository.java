package com.airlines.ap.api.selfservice.infrastructure.adapter.jpa;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.airlines.ap.api.selfservice.domain.FlightDetails;
import com.airlines.ap.api.selfservice.domain.spi.IFlightDetailRepository;

public interface FlightDetailRepository extends CrudRepository<FlightDetails, String>,
		JpaSpecificationExecutor<FlightDetails>, IFlightDetailRepository {

	static final Logger LOGGER = LoggerFactory.getLogger(FlightDetailRepository.class);

	public default FlightDetails createFlightDetail(FlightDetails details) {
		return this.save(details);
	}

	public default List<FlightDetails> getFlightDetail(String flightName, String flightCode, Integer airlineId) {
		Specification<FlightDetails> spec = Specification.where(null);
		var flightNameSpec = FlightDetailSpecification.flightNameLike(flightName);
		var flightCodeSpec = FlightDetailSpecification.flightCodeEquals(flightCode);
		var airlineIdSpec = FlightDetailSpecification.airlineIdEquals(airlineId);
		if (!StringUtils.isBlank(flightName)) {
			System.out.println("inside:::");
			spec = spec.and(flightNameSpec);
		}
		if (!StringUtils.isBlank(flightCode)) {
			spec = spec.and(flightCodeSpec);
		}
		if (airlineId != null && airlineId > 0) {
			spec = spec.and(airlineIdSpec);
		}
		var flights = this.findAll(spec);
		return flights;
	}
}
