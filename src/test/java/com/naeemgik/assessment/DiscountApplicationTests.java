package com.naeemgik.assessment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.naeemgik.assessment.constants.ItemType;
import com.naeemgik.assessment.constants.UserType;
import com.naeemgik.assessment.dto.Bill;
import com.naeemgik.assessment.dto.Item;
import com.naeemgik.assessment.dto.User;
import com.naeemgik.assessment.service.DiscountService;

/**
 * 
 * @author Said Naeem Shah (naeemgik@gmail.com)
 * @version 1.0
 */
@SpringBootTest
public class DiscountApplicationTests {
	
	@Autowired
	private DiscountService discountService;
	
	@Test
	public void testEmployeeDiscount() {
		
		User user = new User("Naeem", UserType.EMPLOYEE, LocalDate.now());
		
		Item item1 = new Item("Laptop", 900, ItemType.NON_GROCERY);
		Item item2 = new Item("Mouse", 90, ItemType.NON_GROCERY);
		Bill bill = new Bill(List.of(item1, item2));
		
		double netAmount = discountService.calculateNetPayableAmount(bill, user);
		System.out.println("netAmount : {}" + netAmount);
		assertEquals(648, netAmount);
	}
	
	@Test
	public void testAffiliateDiscount() {
		
		User user = new User("Khalid", UserType.AFFILIATE, LocalDate.now());
		
		Item item1 = new Item("Laptop", 900, ItemType.NON_GROCERY);
		Item item2 = new Item("Mouse", 90, ItemType.NON_GROCERY);
		Bill bill = new Bill(List.of(item1, item2));
		
		double netAmount = discountService.calculateNetPayableAmount(bill, user);
		System.out.println("netAmount : " + netAmount);
		assertEquals(846, netAmount);
	}
	
	@Test
	public void testLoyalCustomerDiscount() {
		
		User user = new User("Loyal-Customer", UserType.CUSTOMER, LocalDate.now().minusYears(3)); // old customer 2+ years
		Item item1 = new Item("Laptop", 900, ItemType.NON_GROCERY);
		Item item2 = new Item("Mouse", 90, ItemType.NON_GROCERY);
		Bill bill = new Bill(List.of(item1, item2));
		
		double netAmount = discountService.calculateNetPayableAmount(bill, user);
		System.out.println("netAmount : " + netAmount);
		assertEquals(895.5, netAmount);
	}
	
	@Test
	public void testNoPercentageDiscount() {
		User user = new User("Ordinary-Customer", UserType.CUSTOMER, LocalDate.now());
		Item item1 = new Item("Laptop", 900, ItemType.NON_GROCERY);
		Item item2 = new Item("Mouse", 90, ItemType.NON_GROCERY);
		Bill bill = new Bill(List.of(item1, item2));
		
		double netAmount = discountService.calculateNetPayableAmount(bill, user);
		System.out.println("netAmount : " + netAmount);
		assertEquals(945, netAmount);
	}
	
	@Test
	public void testGroceryItemsOnly() {
		User user = new User("naeem", UserType.EMPLOYEE, LocalDate.now());
		
		Item item1 = new Item("Fruit", 100, ItemType.GROCERY);
		Item item2 = new Item("Milk", 50, ItemType.GROCERY);
		Bill bill = new Bill(List.of(item1, item2));
		
		double netAmount = discountService.calculateNetPayableAmount(bill, user);
		System.out.println("netAmount : " + netAmount);
		assertEquals(145, netAmount);
	}
}
