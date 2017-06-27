package com.techelevator;
 
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.techelevator.view.Menu;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE };

	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY,
			PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION };
	private String inputCheck = "[A-D][1-4]";
	private List<String> bluh = new ArrayList<String>();
	private Menu menu;
	private StockItems items = new StockItems();
	private Purchase purchase;

	public VendingMachineCLI(Menu menu, Purchase purchase) {
		this.menu = menu;
		this.purchase = purchase;

	}

	public void run() {
		while (true) {

			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				System.out.println("     Product              Price    Quantity");
				for (Products product : items.getProducts()) {
					System.out.println(product);
				}
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				while (true) {
					String choice2 = (String) purchase.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

					if (choice2.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
						System.out.println("Choose money to input");
						List<String> money = new ArrayList<String>(Arrays.asList("$1", "$2", "$5", "$10"));
						Scanner feed = new Scanner(System.in);
						System.out.println(money);
						int input = feed.nextInt();
						feed.nextLine();
						if (input == 1) {
							purchase.setMoneyIn(purchase.getMoneyIn().plus(new DollarAmount(100)));
						}
						if (input == 2) {
							purchase.setMoneyIn(purchase.getMoneyIn().plus(new DollarAmount(200)));
						}
						if (input == 5) {
							purchase.setMoneyIn(purchase.getMoneyIn().plus(new DollarAmount(500)));
						}
						if (input == 10) {
							purchase.setMoneyIn(purchase.getMoneyIn().plus(new DollarAmount(1000)));
						}
						System.out.println("Current Money Provided: " + purchase.getMoneyIn());

						try {
							BufferedWriter writer = new BufferedWriter(new FileWriter("Log.txt", true));
							SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
							String dateAsString = simpleDateFormat.format(new Date());
							writer.write(
									String.format("%s %-31s $%-9s %s", dateAsString, "Feed Money:", input, purchase.getMoneyIn()));
							writer.newLine();
							writer.flush();

						} catch (IOException ioe) {
							ioe.printStackTrace();
						}

					}
					if (choice2.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
						System.out.println("Choose item");

						Scanner feed = new Scanner(System.in);
						String input = feed.nextLine();
						if (!Pattern.matches(inputCheck, input)) {
							System.out.print("Item does not exist!");
						} else {

							for (Products product : items.getProducts()) {
								if (product.getSlotNumber().equals(input)) {
									if (product.getQuantity() == 0) {
										System.out.println("Sold out!");
									} else {
										if (purchase.getMoneyIn().isGreaterThan(product.getPrice())) {
											purchase.setMoneyIn(purchase.getMoneyIn().minus(product.getPrice()));
											product.setQuantity(product.getQuantity() - 1);
											product.setQtySold(product.getQtySold() + 1);
											purchase.setTotalSales(purchase.getTotalSales().plus(product.getPrice()));
											bluh.add(input);
											System.out.println("You got " + product.getName() + " and only "
													+ product.getQuantity() + " are left!");
										} else {
											System.out.println("You dont have enough money!");
										}
									}
									purchase.getMoneyIn();

									try {
										BufferedWriter writer = new BufferedWriter(new FileWriter("Log.txt", true));
										SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
										String dateAsString = simpleDateFormat.format(new Date());
										writer.write(String.format("%s %-20s %-10s %-10s %s", dateAsString, product.getName(),
												product.getSlotNumber(), purchase.getMoneyIn().plus(product.getPrice()),
												purchase.getMoneyIn()));
										writer.newLine();
										writer.flush();

									} catch (IOException ioe) {
										ioe.printStackTrace();
									}
								}
							}

						}
					}
					if (choice2.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
						DollarAmount change = purchase.getMoneyIn();
						if (purchase.getMoneyIn().isGreaterThan(DollarAmount.ZERO_DOLLARS)) {
							DollarAmount changeQ = (purchase.getMoneyIn().divide(new DollarAmount(25)));
							purchase.setMoneyIn(purchase.getMoneyIn().minus(changeQ.multiply(new DollarAmount(25))));
							if (purchase.getMoneyIn().equals(DollarAmount.ZERO_DOLLARS)) {
								System.out.println("Change returned is " + changeQ.hashCode() + " quarters");
								System.out.println("Current balance: " + purchase.getMoneyIn());
							}
							if (purchase.getMoneyIn().isGreaterThan(DollarAmount.ZERO_DOLLARS)) {
								DollarAmount changeD = (purchase.getMoneyIn().divide(new DollarAmount(10)));
								purchase.setMoneyIn(
										purchase.getMoneyIn().minus(changeD.multiply(new DollarAmount(10))));
								if (purchase.getMoneyIn().equals(DollarAmount.ZERO_DOLLARS)) {
									System.out.println("Change returned is " + changeQ.hashCode() + " in quarters, "
											+ changeD.hashCode() + " dimes");
									System.out.println("Current balance: " + purchase.getMoneyIn());
								}
								if (purchase.getMoneyIn().isGreaterThan(DollarAmount.ZERO_DOLLARS)) {
									DollarAmount changeN = (purchase.getMoneyIn().divide(new DollarAmount(05)));
									purchase.setMoneyIn(
											purchase.getMoneyIn().minus(changeN.multiply(new DollarAmount(05))));
									System.out.println("Change returned is " + changeQ.hashCode() + " quarters, "
											+ changeD.hashCode() + " dimes and " + changeN.hashCode() + " nickels");
									System.out.println("Current balance: " + purchase.getMoneyIn());
								}
								
							}
						}
						try {
							BufferedWriter writer = new BufferedWriter(new FileWriter("Log.txt", true));
							SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
							String dateAsString = simpleDateFormat.format(new Date());
							writer.write(String.format("%s %-31s %-10s %s", dateAsString, "Give Change: ", change,
									purchase.getMoneyIn()));
							writer.newLine();
							writer.flush();

						} catch (IOException ioe) {
							ioe.printStackTrace();
						}
						
						for (String ahh : bluh) {
							if (ahh.contains("A")) {
								System.out.print("Crunch Crunch, Yum!");
							}
							if (ahh.contains("B")) {
								System.out.print("Munch Munch, Yum!");
							}
							if (ahh.contains("C")) {
								System.out.print("Glug Glug, Yum!");
							}
							if (ahh.contains("D")) {
								System.out.print("Chew Chew, Yum!");
							}
						}
						try {
							BufferedWriter writer = new BufferedWriter(new FileWriter("SalesReport.txt", false));
							for (Products product1 : items.getProducts()) {
								writer.write(String.format("%-20s |  %s", product1.getName(), product1.getQtySold()));
								writer.newLine();
								writer.flush();
							}	
							writer.newLine();
							writer.newLine();
							writer.write(String.format("**TOTAL SALES**  %s", purchase.getTotalSales()));
							writer.newLine();
							writer.flush();
						} catch (IOException ioe) {
							ioe.printStackTrace();
						}
					}
				}
			}
		}
	}
}
