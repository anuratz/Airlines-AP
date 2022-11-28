package com.airlines.ap.api.selfservice.infrastructure.adapter.rest.model;

import com.airlines.ap.api.selfservice.domain.Airlines;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateFlightDetailRequest {
	private String flightName;
	
	private String flightCode;

	private Airlines airlineDetail;
	
	private boolean isActive;
	

}
