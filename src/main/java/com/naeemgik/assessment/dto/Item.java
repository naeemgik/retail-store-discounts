package com.naeemgik.assessment.dto;

import com.naeemgik.assessment.constants.ItemType;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 
 * @author Said Naeem Shah (naeemgik@gmail.com)
 * @version 1.0
 */
@Data
@AllArgsConstructor
public class Item {
	
	private String name;
	private double price;
	private ItemType type;
	
}
