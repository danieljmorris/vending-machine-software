package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.techelevator.view.Menu;


public class VendingMachineTest {
	private VendingMachineCLI vending;
	private Purchase purchase;
	private Menu menu;
	private String choice;
	
	@Before
	public void setup(){
		vending = new VendingMachineCLI(menu, purchase); 


		
	}

}

