package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProductsTest {
	private Products pro;
	
	@Before
	public void setup(){
		
		pro = new Products("chip", new DollarAmount (1) , "A1");
	}
	
	@Test
	public void initialization(){
		Assert.assertEquals("chip",pro.getName());
		Assert.assertEquals(new DollarAmount(1), pro.getPrice());
		Assert.assertEquals("A1", pro.getSlotNumber());
		Assert.assertEquals(5, pro.getQuantity());
		Assert.assertEquals(0, pro.getQtySold());
	}
	
	@Test
	public void set_qty_sold(){
		pro.setQtySold(0);
		Assert.assertEquals(0, pro.getQtySold());
	}
	
	@Test
	public void set_qunatity(){
		pro.setQuantity(5);
		Assert.assertEquals(5, pro.getQuantity());
	}
	
	@Test
	public void to_string(){
		String str = pro.toString();
		Assert.assertTrue(str.contains("A1"));
	}
	
}
