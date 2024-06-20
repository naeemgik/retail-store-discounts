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
public class PercentageDiscount implements Discount {
	
	private double percentage;
	
	@Override
	public double calculate(Bill bill, User user) {
		double totalAmount = bill.getTotalAmount();
		double nonGroceryTotal = bill.getItems().stream().filter(item -> item.getType() != ItemType.GROCERY)
				.mapToDouble(Item::getPrice).sum();
		
		Double discountAmount = nonGroceryTotal * (percentage / 100);
		return totalAmount - discountAmount;
	}
}
