package com.naeemgik.assessment.dto;

/**
 * 
 * @author Said Naeem Shah (naeemgik@gmail.com)
 * @version 1.0
 */
public interface Discount {
    
	public double calculate(Bill bill, User user);
}
