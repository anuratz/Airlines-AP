package com.airlines.ap.api.selfservice.infrastructure.adapter.jpa;

import org.springframework.data.jpa.domain.Specification;

import com.airlines.ap.api.selfservice.domain.Airlines;

public class AirlineDetailsSpecification {
	private AirlineDetailsSpecification() {
	}

	public static Specification<Airlines> airlineNameLike(String airlineName) {
		return (root, query, builder) -> airlineName == null ? builder.conjunction()
				: builder.equal(builder.upper(root.get("airlineName")), "%" + airlineName.toUpperCase() + "%");
	}

	public static Specification<Airlines> airlineCodeEquals(String airlineCode) {
		return (root, query, builder) -> airlineCode == null ? builder.conjunction()
				: builder.equal(builder.upper(root.get("airlineCode")), "%" + airlineCode.toUpperCase() + "%");
	}

}
