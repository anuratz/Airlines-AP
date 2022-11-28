package com.airlines.ap.api.selfservice.infrastructure.adapter.jpa;

import java.util.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;

import com.airlines.ap.api.selfservice.domain.FlightSchedule;
import com.airlines.ap.api.selfservice.infrastructure.adapter.rest.util.exception.BaseException;

public class FlightScheduleSpecification {

	private FlightScheduleSpecification() {
	}


	public static Specification<FlightSchedule> flightNumberEquals(Integer flightNumber) {
		return (root, query, builder) -> flightNumber == null ? builder.conjunction()
				: builder.equal(root.get("flightDetails"), flightNumber);
	}

	// "yyyy-MM-dd'T'HH:mm:ssXXX"
	public static Specification<FlightSchedule> arrivalTimeBetween(String startTime, String endTime) {
		return (root, query, builder) -> startTime == null ? builder.conjunction()
				: builder.between(root.get("arrivalTime"), convert(startTime), convert(endTime));
	}

	public static Specification<FlightSchedule> departureTimeBetween(String startTime, String endTime) {
		return (root, query, builder) -> endTime == null ? builder.conjunction()
				: builder.between(root.get("departureTime"), convert(startTime), convert(endTime));
	}

	public static Timestamp convert(String time) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
	    Date parsedDate;
		try {
			parsedDate = dateFormat.parse(time);
		} catch (ParseException e) {
			throw new BaseException("Incorrect format of Start and End Time provided",HttpStatus.BAD_REQUEST);
		}
	    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
	    return timestamp;
	}

}
