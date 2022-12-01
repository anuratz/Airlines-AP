package com.airlines.ap.api.selfservice.infrastructure.adapter.rest.model;

import com.airlines.ap.api.selfservice.domain.FlightDetails;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateFlightScheduleResponse {
	private Integer scheduleId;
	private FlightDetails flightDetails;
	private String arrivalPort;
	private String departurePort;
	private String arrivalTime;
	private String departureTime;
}
