package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PurchaseTest {
	private Purchase purchase;
	
	@Before
	public void setup(){
		purchase = new Purchase(System.in,System.out);
	}
	
	@Test
	public void set_money(){
		purchase.setMoneyIn(new DollarAmount(0));
		Assert.assertEquals(new DollarAmount(0), purchase.getMoneyIn());
	}
	
	@Test
	public void set_total_sales(){
		purchase.setTotalSales(new DollarAmount(0));
		Assert.assertEquals(new DollarAmount(0), purchase.getTotalSales());
	}
}
