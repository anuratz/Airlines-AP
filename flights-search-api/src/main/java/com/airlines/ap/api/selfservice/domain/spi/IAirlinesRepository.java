package com.airlines.ap.api.selfservice.domain.spi;

import java.util.List;

import com.airlines.ap.api.selfservice.domain.Airlines;

public interface IAirlinesRepository {
	
	List<Airlines> getairlineDetail(String airlineName, String airlineCode);

	
}
