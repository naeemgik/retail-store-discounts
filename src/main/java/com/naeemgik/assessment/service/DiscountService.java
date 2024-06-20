package com.naeemgik.assessment.service;

import org.springframework.stereotype.Service;

import com.naeemgik.assessment.constants.UserType;
import com.naeemgik.assessment.dto.Bill;
import com.naeemgik.assessment.dto.Discount;
import com.naeemgik.assessment.dto.PercentageDiscount;
import com.naeemgik.assessment.dto.User;

/**
 * 
 * @author Said Naeem Shah (naeemgik@gmail.com)
 * @version 1.0
 */
@Service
public class DiscountService {
	
	public double calculateNetPayableAmount(Bill bill, User user) {
		
		double totalAmount = bill.getTotalAmount();
		Discount discount = null;
		
		if (user.getType() == UserType.EMPLOYEE) {
			discount = new PercentageDiscount(30);
		}
		else if (user.getType() == UserType.AFFILIATE) {
			discount = new PercentageDiscount(10);
		}
		else if (user.isLoyalCustomer()) {
			discount = new PercentageDiscount(5);
		}
		
		if (discount != null) {
			totalAmount = discount.calculate(bill, user);
		}
		
		int flatDiscount = calculateFlatDiscount(bill);
		double netPayableAmount = totalAmount - flatDiscount;
		return netPayableAmount;
	}
	
	private int calculateFlatDiscount(Bill bill) {
		// ... for 990, you get $ 45 as a discount
		//int flatDiscount1 = ((int) (990 / 100)) * 5;
		int flatDiscount = ((int) (bill.getTotalAmount() / 100)) * 5;
		return flatDiscount;
	}
}
