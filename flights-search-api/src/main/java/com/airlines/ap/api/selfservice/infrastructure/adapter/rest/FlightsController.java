package com.airlines.ap.api.selfservice.infrastructure.adapter.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.airlines.ap.api.selfservice.application.api.IFlightsService;
import com.airlines.ap.api.selfservice.domain.Airlines;
import com.airlines.ap.api.selfservice.infrastructure.adapter.rest.mapper.FlightsMapper;
import com.airlines.ap.api.selfservice.infrastructure.adapter.rest.model.CreateFlightDetailRequest;
import com.airlines.ap.api.selfservice.infrastructure.adapter.rest.model.CreateFlightDetailResponse;
import com.airlines.ap.api.selfservice.infrastructure.adapter.rest.model.CreateFlightScheduleRequest;
import com.airlines.ap.api.selfservice.infrastructure.adapter.rest.model.CreateFlightScheduleResponse;
import com.airlines.ap.api.selfservice.infrastructure.adapter.rest.util.exception.ConflictException;
import com.airlines.ap.api.selfservice.infrastructure.adapter.rest.util.exception.NotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping(value = "/flights", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@Log4j2
public class FlightsController {
	private final IFlightsService flightService;
	private final FlightsMapper mapper;

	public FlightsController(IFlightsService flightService, FlightsMapper mapper) {
		super();
		this.flightService = flightService;
		this.mapper = mapper;
	}

	@Operation(summary = "Get Airline Details")
	@GetMapping("/airlines")
	public ResponseEntity<List<Airlines>> getAirlines(
			@RequestParam(name = "airlineName", required = false) String airlineName,
			@RequestParam(name = "airlineCode", required = false) String airlineCode) {
		var airLinesDetails = flightService.getAirlineDetails(airlineName, airlineCode);
		return ResponseEntity.ok(airLinesDetails);
	}

	@Operation(summary = "create new flight entries")
	@PostMapping
	public ResponseEntity<CreateFlightDetailResponse> createFlightEntry(@RequestBody CreateFlightDetailRequest request) {
		var flightDetails = mapper.mapFlightRequest(request);
		try {
			var existing = flightService.getFlightbyFlightCode(request.getFlightCode());
			if (existing != null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body(mapper.mapFlightResponse(existing));
			}
		} catch (NotFoundException e) {
			log.info("No flight of flight code gicen exists");
		}
		var response = flightService.createFlightDetails(flightDetails);
		return ResponseEntity.status(HttpStatus.CREATED).body(mapper.mapFlightResponse(response));
	}

	@Operation(summary = "Get flight entries. If no parameter provided, all entries fetched")
	@GetMapping("/search")
	public ResponseEntity<List<CreateFlightDetailResponse>> getFlightDetails(
			@RequestParam(name = "flightName", required = false) String flightName,
			@RequestParam(name = "flightCode", required = false) String flightCode,
			@RequestParam(name = "airlineId", required = false) Integer airlineId) {
		var flightDetails = flightService.getFlightDetails(flightName, flightCode, airlineId);
		return ResponseEntity.ok(flightDetails.stream().map(mapper::mapFlightResponse).collect(Collectors.toList()));
	}

	@Operation(summary = "create new flight schedules")
	@PostMapping("/schedule")
	public ResponseEntity<CreateFlightScheduleResponse> createFlightSchedule(@RequestBody CreateFlightScheduleRequest request) {

		var flightDetails = mapper.mapFlightScheduleRequest(request);
		var response = flightService.createScheduleDetails(flightDetails);
		return ResponseEntity.status(HttpStatus.CREATED).body(mapper.mapFlightSchedule(response));
	}

	@Operation(summary = "Get flight schedules. If no parameter provided, all entries fetched")
	@GetMapping("/schedule/search")
	public ResponseEntity<List<CreateFlightScheduleResponse>> getFlightScheduleDetails(
			@RequestParam(name = "flightNumber", required = false) Integer flightNumber,
			@RequestParam(name = "startTime", required = false) String startTime,
			@RequestParam(name = "endTime", required = false) String endTime) {
		if ((StringUtils.isBlank(startTime) && StringUtils.isBlank(endTime))
				|| (!StringUtils.isBlank(startTime) && !StringUtils.isBlank(endTime))) {
			var flightDetails = flightService.getFlightSchedule(flightNumber, startTime, endTime);
			return ResponseEntity
					.ok(flightDetails.stream().map(mapper::mapFlightSchedule).collect(Collectors.toList()));
		} else {
			throw new ConflictException("Provide both start and end time or no time should be provided");
		}

	}

}
