package com.airlines.ap.api.selfservice.domain.spi;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.airlines.ap.api.selfservice.domain.Airlines;
import com.airlines.ap.api.selfservice.domain.FlightDetails;

public interface IFlightDetailRepository {
	public FlightDetails createFlightDetail(FlightDetails schedule);
	
	public List<FlightDetails> getFlightDetail(String flightName, String flightCode, Integer airlineId);

	@Query(nativeQuery=true,value="Select * from AP.Airlines where airline_name like CONCAT('%', :airlineName, '%')")
	public  List<Airlines> getAirlinesDetailByName(@Param("airlineName")String airlineName);
		
	@Query(nativeQuery=true,value="Select * from AP.Airlines")
	public List<Airlines> getAirlinesDetails();

	public FlightDetails getFlightByFlightcode(String flightCode);
	

}
