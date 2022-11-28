package com.airlines.ap.api.selfservice.domain.audit;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;

import com.airlines.ap.api.selfservice.domain.FlightDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FLIGHT_AUDIT", schema = "AP")
public class Audit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "REF_USER_ID")
	@CreatedBy
	private String userId ;
	
	@Column(name = "ACTION")
	private String action ;
	
	@Column(name = "DESCRIPTION")
	private String description ;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="FLIGHT_NUMBER")
	private FlightDetails flightschedule ;
	
	@Column(name = "created_ts")
	private Timestamp createdTimestamp;
	
}
