package com.airlines.ap.api.selfservice.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
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
@Table(name = "AIRLINES", schema = "AP")
public class Airlines {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "airline_id")
	private Integer airlineId;
	
	@Column(name = "airline_name")
	private String airlineName;
	
	@Column(name = "airline_code")
	private String airlineCode;
	
	@Column(name = "owned_by")
	private String ownedBy;
	
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
