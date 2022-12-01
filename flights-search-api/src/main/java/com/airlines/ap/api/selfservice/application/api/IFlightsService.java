package com.airlines.ap.api.selfservice.application.api;

import java.util.List;

import com.airlines.ap.api.selfservice.domain.Airlines;
import com.airlines.ap.api.selfservice.domain.FlightDetails;
import com.airlines.ap.api.selfservice.domain.FlightSchedule;

public interface IFlightsService {

	List<FlightDetails> getFlightDetails(String flightName, String flightCode, Integer airlineId);

	List<FlightSchedule> getFlightSchedule(Integer flightNumber, String startTime, String endTime);

	List<Airlines> getAirlineDetails(String airlineName, String airlineCode);

	FlightDetails createFlightDetails(FlightDetails flightDetails);

	FlightSchedule createScheduleDetails(FlightSchedule flightDetails);

	FlightDetails getFlightbyFlightCode(String flightCode);
	
	

}
