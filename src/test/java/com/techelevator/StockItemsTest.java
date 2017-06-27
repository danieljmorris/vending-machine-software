package com.techelevator;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class StockItemsTest {
	private StockItems items;
	private List<Products> productList;
	@Before
	public void setup(){
		items = new StockItems();
		productList = items.getProducts();

	}
	
	@Test
	public void test(){
		Assert.assertEquals(16, productList.size());
	}
}
