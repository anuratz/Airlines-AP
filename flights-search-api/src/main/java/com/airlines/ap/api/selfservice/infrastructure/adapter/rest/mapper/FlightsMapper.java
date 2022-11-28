package com.airlines.ap.api.selfservice.infrastructure.adapter.rest.mapper;

import java.sql.Timestamp;

import org.mapstruct.Mapper;

import com.airlines.ap.api.selfservice.domain.FlightDetails;
import com.airlines.ap.api.selfservice.domain.FlightSchedule;
import com.airlines.ap.api.selfservice.infrastructure.adapter.rest.model.CreateFlightDetailRequest;
import com.airlines.ap.api.selfservice.infrastructure.adapter.rest.model.CreateFlightDetailResponse;
import com.airlines.ap.api.selfservice.infrastructure.adapter.rest.model.CreateFlightScheduleRequest;
import com.airlines.ap.api.selfservice.infrastructure.adapter.rest.model.CreateFlightScheduleResponse;

@Mapper(componentModel = "spring")
public interface FlightsMapper {

	FlightDetails mapFlightRequest(CreateFlightDetailRequest details);

	FlightSchedule mapFlightScheduleRequest(CreateFlightScheduleRequest details);

	CreateFlightDetailResponse mapFlightResponse(FlightDetails details);

	CreateFlightScheduleResponse mapFlightSchedule(FlightSchedule details);
	
	default Timestamp map(String value) {
		return Timestamp.valueOf(value);
	};

}
