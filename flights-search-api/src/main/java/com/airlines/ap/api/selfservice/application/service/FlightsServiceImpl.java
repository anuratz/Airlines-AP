package com.airlines.ap.api.selfservice.application.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airlines.ap.api.selfservice.application.api.IFlightsService;
import com.airlines.ap.api.selfservice.domain.Airlines;
import com.airlines.ap.api.selfservice.domain.FlightDetails;
import com.airlines.ap.api.selfservice.domain.FlightSchedule;
import com.airlines.ap.api.selfservice.domain.spi.IAirlinesRepository;
import com.airlines.ap.api.selfservice.domain.spi.IAuditRepository;
import com.airlines.ap.api.selfservice.domain.spi.IFlightDetailRepository;
import com.airlines.ap.api.selfservice.domain.spi.IFlightScheduleRepository;

@Service
public class FlightsServiceImpl implements IFlightsService {

	@Autowired
	private IFlightScheduleRepository scheduleRepository;

	@Autowired
	private IFlightDetailRepository detailRepository;
	
	@Autowired
	private IAirlinesRepository airlineRepository;

	@Autowired
	private IAuditRepository auditRepository;

	@Override
	public List<FlightDetails> getFlightDetails(String flightName, String flightCode, Integer airlineId) {
		return detailRepository.getFlightDetail(flightName, flightCode, airlineId);
	}

	@Override
	public List<FlightSchedule> getFlightSchedule(Integer flightNumber, String startTime,
			String endTime) {
		return scheduleRepository.getFlightSchedule(flightNumber,startTime,endTime);
	}

	@Override
	public List<Airlines> getAirlineDetails(String airlineName, String airlineCode) {
			return airlineRepository.getairlineDetail(airlineName, airlineCode);
	}

	@Override
	public FlightDetails createFlightDetails(FlightDetails flightDetails) {
		return detailRepository.createFlightDetail(flightDetails);
	}

	@Override
	public FlightSchedule createScheduleDetails(FlightSchedule schedule) {
		return scheduleRepository.createFlightSchedule(schedule);
	}

	@Override
	public FlightDetails getFlightbyFlightCode(String flightCode) {
		return detailRepository.getFlightByFlightcode(flightCode);
	}


}
