package com.naeemgik.assessment.dto;

import java.time.LocalDate;
import java.time.Period;

import com.naeemgik.assessment.constants.UserType;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 
 * @author Said Naeem Shah (naeemgik@gmail.com)
 * @version 1.0
 */
@Data
@AllArgsConstructor
public class User {
	
	private String name;
	private UserType type;
	private LocalDate since;
	
	public boolean isLoyalCustomer() {
		return Period.between(since, LocalDate.now()).getYears() > 2;
	}
}
