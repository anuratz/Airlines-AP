package com.airlines.ap.api.selfservice.domain.spi;

import java.util.List;

import com.airlines.ap.api.selfservice.domain.FlightSchedule;

public interface IFlightScheduleRepository {
	public FlightSchedule createFlightSchedule(FlightSchedule schedule);
	
	public List<FlightSchedule> getFlightSchedule(Integer flightNumber, String startTime, String endTime);
	
	

}
