package com.naeemgik.assessment.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 
 * @author Said Naeem Shah (naeemgik@gmail.com)
 * @version 1.0
 */
@Data
@AllArgsConstructor
public class Bill {
	
	private List<Item> items;
	
	public double getTotalAmount() {
		return items.stream().mapToDouble(Item::getPrice).sum();
	}
	
}
