package com.techelevator;

import com.techelevator.view.Menu;

public class Main {
	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		Purchase purchase = new Purchase(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu, purchase);
		cli.run();
	}
}
