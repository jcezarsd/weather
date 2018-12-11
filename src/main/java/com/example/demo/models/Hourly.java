package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
@Table
public class Hourly {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private Date hour;

	@Column
	private Double maxTemperature;

	@Column
	private Double minTemperature;

	@Column
	private Double precipitation;

	@Column
	private String icon;

	@Transient
	private String weatherName;

	@Transient
	private Double currentTemperature;

}
