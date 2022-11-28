package com.airlines.ap.api.selfservice.infrastructure.adapter.jpa;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.airlines.ap.api.selfservice.domain.FlightSchedule;
import com.airlines.ap.api.selfservice.domain.spi.IFlightScheduleRepository;

public interface FlightScheduleRepository extends CrudRepository<FlightSchedule, String>,
		JpaSpecificationExecutor<FlightSchedule>, IFlightScheduleRepository {

	static final Logger LOGGER = LoggerFactory.getLogger(FlightScheduleRepository.class);

	public default FlightSchedule createFlightSchedule(FlightSchedule schedule) {
		return this.save(schedule);

	}

	public default List<FlightSchedule> getFlightSchedule(Integer flightNumber, String startTime,
			String endTime) {
		Specification<FlightSchedule> spec = Specification.where(null);
		var flightNameSpec = FlightScheduleSpecification.flightNumberEquals(flightNumber);
		var arrTimeSpec = FlightScheduleSpecification.arrivalTimeBetween(startTime, endTime);
		var departTimeSpec = FlightScheduleSpecification.departureTimeBetween(startTime, endTime);
		if (flightNumber != null && flightNumber >0) {
			spec = spec.and(flightNameSpec);
		}
		if (!StringUtils.isBlank(startTime) && !StringUtils.isBlank(endTime)) {
			spec = spec.and(arrTimeSpec);
			spec = spec.and(departTimeSpec);
		}
		var flights = this.findAll(spec);
		return flights;
	}
}
