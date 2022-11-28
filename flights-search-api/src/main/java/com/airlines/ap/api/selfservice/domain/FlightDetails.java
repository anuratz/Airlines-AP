package com.airlines.ap.api.selfservice.domain;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.Builder.Default;

@Getter
@Setter
@ToString
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FLIGHT_DETAILS", schema = "AP")
public class FlightDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FLIGHT_NUMBER")
	private Integer flightNumber;
	
	@Column(name = "flight_name")
	private String flightName;
	
	@Column(name = "flight_code")
	private String flightCode;
	

	@OneToOne(cascade=CascadeType.DETACH, fetch =FetchType.EAGER)
	@JoinColumn(name = "airline_id", referencedColumnName ="airline_id")
	private Airlines airlineDetail;
	
	@Column(name = "IS_ACTIVE")
	@Default
	private boolean isActive = true;
	
	@Column(name = "created_ts")
	@CreationTimestamp
	private Timestamp createdTimestamp;
	
	@Column(name = "updated_ts")
	@UpdateTimestamp
	private Timestamp updatedTimestamp;

}
