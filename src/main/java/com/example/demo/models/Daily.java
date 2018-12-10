package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
@Table
public class Daily {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private Date day;

	@Column
	private Double maxTemperature;

	@Column
	private Double minTemperature;

	@Column
	private Double precipitation;

}
