package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StockItems {
	private List<Products> productList = new ArrayList<Products>();
	
	public StockItems() {
		
		try (Scanner stockItems = new Scanner(new File("vendingmachine.csv"))) {
			while (stockItems.hasNextLine()) {
				String word = stockItems.nextLine();
				String[] parts = word.split("\\|");
				String slotNumber = parts[0];
				String name = parts [1];
				DollarAmount price = DollarAmount.parseDollarAmount(parts[2]);

				Products product = new Products(name, price, slotNumber);
				productList.add(product);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public List<Products> getProducts() {
		return productList;
	}	
}
