package com.airlines.ap.api.selfservice.infrastructure.adapter.rest.model;

import com.airlines.ap.api.selfservice.domain.Airlines;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateFlightDetailResponse {
	private String flightNumber;
	
	private String flightName;
	
	private String flightCode;

	private Airlines airlineDetail;
	
	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getFlightName() {
		return flightName;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	public String getFlightCode() {
		return flightCode;
	}

	public void setFlightCode(String flightCode) {
		this.flightCode = flightCode;
	}

	public Airlines getAirlineDetail() {
		return airlineDetail;
	}

	public void setAirlineDetail(Airlines airlineDetail) {
		this.airlineDetail = airlineDetail;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	private boolean isActive;
}
