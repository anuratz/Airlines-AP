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

@Getter
@Setter
@ToString
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FLIGHT_SCHEDULE", schema = "AP")
public class FlightSchedule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "schedule_id")
	private Integer scheduleId;
	
	@OneToOne(cascade=CascadeType.DETACH, fetch =FetchType.EAGER)
	@JoinColumn(name = "flight_number", referencedColumnName ="flight_number")
	private FlightDetails flightDetails;

	@Column(name = "arrival_Port")
	private String arrivalPort;

	@Column(name = "departure_port")
	private String departurePort;

	@Column(name = "arrival_ts")
	private Timestamp arrivalTime;
	
	@Column(name = "departure_ts")
	private Timestamp departureTime;
	
	@Column(name = "created_ts")
	@CreationTimestamp
	private Timestamp createdTimestamp;
	
	@Column(name = "updated_ts")
	@UpdateTimestamp
	private Timestamp updatedTimestamp;

}
