package com.airlines.ap.api.selfservice.infrastructure.adapter.rest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.airlines.ap.api.selfservice.application.service.FlightsServiceImpl;
import com.airlines.ap.api.selfservice.domain.Airlines;
import com.airlines.ap.api.selfservice.domain.FlightDetails;
import com.airlines.ap.api.selfservice.domain.FlightSchedule;
import com.airlines.ap.api.selfservice.infrastructure.adapter.rest.mapper.FlightsMapper;
import com.airlines.ap.api.selfservice.infrastructure.adapter.rest.mapper.FlightsMapperImpl;
import com.airlines.ap.api.selfservice.infrastructure.adapter.rest.model.CreateFlightDetailRequest;
import com.airlines.ap.api.selfservice.infrastructure.adapter.rest.model.CreateFlightScheduleRequest;
import com.airlines.ap.api.selfservice.infrastructure.adapter.rest.util.exception.CustomExceptionHandler;
import com.google.gson.Gson;

@ExtendWith(MockitoExtension.class)
@DisplayName("Controller endPoint Test")
class FlightsControllerTest {
	private MockMvc mockMvc;
	private HttpHeaders headers;

	@InjectMocks
	private FlightsController subject;

	@Mock
	private FlightsServiceImpl service;
	
	@Mock
	private FlightsMapper mapper;

	@BeforeEach
	public void setUp() {
		mapper = new FlightsMapperImpl();
		mockMvc = MockMvcBuilders.standaloneSetup(subject).setControllerAdvice(CustomExceptionHandler.class).build();
		headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		ReflectionTestUtils.setField(this, "headers", headers);
		ReflectionTestUtils.setField(this, "mapper", mapper);
	}

	@Test
	@DisplayName("Create a flight entry")
	void testCreateFlightEntry() throws Exception {
		Airlines airlines = new Airlines();
		airlines.setAirlineId(201);
		CreateFlightDetailRequest req = new CreateFlightDetailRequest();
		req.setFlightCode("A01");
		req.setAirlineDetail(airlines);
		req.setFlightName("AirAsia");
		Mockito.when(service.createFlightDetails(Mockito.any())).thenReturn(getFlightDetails());
		mockMvc.perform(MockMvcRequestBuilders.post("/flights").content(new Gson().toJson(req)).headers(headers))
				.andExpect(status().isCreated());
	}

	@Test
	@DisplayName("Create a flight entry for existing flight Code")
	void testCreateFlightEntry2() throws Exception {
		Airlines airlines = new Airlines();
		airlines.setAirlineId(201);
		CreateFlightDetailRequest req = new CreateFlightDetailRequest();
		req.setFlightCode("A01");
		req.setAirlineDetail(airlines);
		req.setFlightName("AirAsia");
		Mockito.when(service.getFlightbyFlightCode("A01")).thenReturn(getFlightDetails());
		mockMvc.perform(MockMvcRequestBuilders.post("/flights").content(new Gson().toJson(req)).headers(headers))
				.andExpect(status().isConflict());
	}

	@Test
	@DisplayName("Get flight details")
	void testGetFlightDetails() throws Exception {
		FlightDetails det = getFlightDetails();
		List<FlightDetails> list = new ArrayList<>();
		list.add(det);
		Mockito.when(service.getFlightDetails("AirAsia", "A020", 201)).thenReturn(list);
		mockMvc.perform(MockMvcRequestBuilders.get("/flights/search").param("flightName", "AirAsia")
				.param("flightCode", "A020").param("airlineId", "201").headers(headers)).andExpect(status().isOk());
	}

	@Test
	@DisplayName("Create flight Schedule")
	void testCreateFlightSchedule() throws Exception {
		CreateFlightScheduleRequest req = new CreateFlightScheduleRequest();
		req.setFlightDetails(getFlightDetails());
		req.setArrivalTime("2022-09-07 00:00:07");
		req.setDepartureTime("2022-10-07 00:00:07");
		mockMvc.perform(
				MockMvcRequestBuilders.post("/flights/schedule").content(new Gson().toJson(req)).headers(headers))
				.andExpect(status().isCreated());
	}
	
	@Test
	@DisplayName("get flight Schedule")
	void testGetFlightScheduleDetails() throws Exception {
		FlightSchedule det = getFlightSchedule();
		List<FlightSchedule> list = new ArrayList<>();
		list.add(det);
		Mockito.when(service.getFlightSchedule(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(list);
		mockMvc.perform(MockMvcRequestBuilders.get("/flights/schedule/search").param("flightNumber", "200")
				.param("startTime", "2022-09-07 00:00:07").param("endTime", "2022-09-07 00:00:07").headers(headers)).andExpect(status().isOk());
	}

	@Test
	@DisplayName("get flight Schedule-exception")
	void testGetFlightScheduleDetails2() throws Exception {
		FlightSchedule det = getFlightSchedule();
		List<FlightSchedule> list = new ArrayList<>();
		list.add(det);
		mockMvc.perform(MockMvcRequestBuilders.get("/flights/schedule/search").param("flightNumber", "200")
				.param("startTime", "2022-09-07 00:00:07").headers(headers)).andExpect(status().isConflict());
	}

	private Airlines getAirlineDetails() {
		Airlines airlines = new Airlines();
		airlines.setAirlineId(201);
		return airlines;
	}

	private FlightDetails getFlightDetails() {
		FlightDetails det = FlightDetails.builder().flightCode("A020").flightName("AirAsia").flightNumber(200)
				.airlineDetail(getAirlineDetails()).build();
		return det;
	}
	
	private FlightSchedule getFlightSchedule() {
		FlightSchedule det = FlightSchedule.builder().scheduleId(301).flightDetails(getFlightDetails()).build();
		return det;
	}

}
