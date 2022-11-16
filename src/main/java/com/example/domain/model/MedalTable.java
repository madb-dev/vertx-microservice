package com.example.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "medal_table")
public class MedalTable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "state")
	private String state;
	@Column(name = "total")
	private int totalMedals;

	public MedalTable() {
	}

	public MedalTable(String state, int totalMedals) {
		this.state = state;
		this.totalMedals = totalMedals;
	}

	public Integer getId() {
		return id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getTotalMedals() {
		return totalMedals;
	}

	public void setTotalMedals(int totalMedals) {
		this.totalMedals = totalMedals;
	}
}
