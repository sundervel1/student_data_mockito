package com.cg.boot.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DateForJson {
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "DateForJson [date=" + date + "]";
	}
	
}
