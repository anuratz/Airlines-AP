package com.airlines.ap.api.selfservice.infrastructure.adapter.jpa;

import org.springframework.data.jpa.domain.Specification;

import com.airlines.ap.api.selfservice.domain.FlightDetails;

public class FlightDetailSpecification {
	private FlightDetailSpecification() {
	}

	public static Specification<FlightDetails> flightNameLike(String flightName) {
		return (root, query, builder) -> flightName == null ? builder.conjunction()
				: builder.like(builder.upper(root.get("flightName")),"%"+ flightName.toUpperCase()+"%");
	}

	public static Specification<FlightDetails> flightCodeLike(String flightCode) {
		return (root, query, builder) -> flightCode == null ? builder.conjunction()
				: builder.like(builder.upper(root.get("flightCode")),"%"+flightCode.toUpperCase()+"%");
	}
	
	public static Specification<FlightDetails> flightCodeEquals(String flightCode) {
		return (root, query, builder) -> flightCode == null ? builder.conjunction()
				: builder.like(builder.upper(root.get("flightCode")),"%"+flightCode.toUpperCase()+"%");
	}

	public static Specification<FlightDetails> airlineIdEquals(Integer airlineId) {
		return (root, query, builder) -> airlineId == null ? builder.conjunction()
				: builder.equal(root.get("airlineDetail"), airlineId);
	}

}
