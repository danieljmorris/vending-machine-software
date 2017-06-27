package com.techelevator;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.Scanner;

public class Purchase {
	private DollarAmount moneyIn;
	private PrintWriter out;
	private Scanner in;
	private DollarAmount totalSales;
	
	public Purchase(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
		moneyIn = new DollarAmount(0);
		totalSales = new DollarAmount(0);
	}
	
	public DollarAmount getMoneyIn(){
		return moneyIn;
	}
	public void setMoneyIn(DollarAmount moneyIn){
		this.moneyIn = moneyIn;
	}
	
	public DollarAmount getTotalSales(){
		return totalSales;
	}
	public void setTotalSales(DollarAmount totalSales){
		this.totalSales = totalSales;
	}
	
	public Object getChoiceFromOptions(Object[] options) {
		Object choice2 = null;
		while(choice2 == null) {
			displayMenuOptions(options);
			choice2 = getChoiceFromUserInput(options);
		}
		return choice2;
	}

	private Object getChoiceFromUserInput(Object[] options) {
		Object choice2 = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if(selectedOption > 0 && selectedOption <= options.length) {
				choice2 = options[selectedOption - 1];
			}
		} catch(NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if(choice2 == null) {
			out.println("\n*** "+userInput+" is not a valid option ***\n");
		}
		return choice2;
	}

	private void displayMenuOptions(Object[] options) {
		out.println();
		for(int i = 0; i < options.length; i++) {
			int optionNum = i+1;
			out.println(optionNum+") "+options[i]);
		}
		out.println("Current Money Provided: " + getMoneyIn());
		out.print("\nPlease choose an option >>> ");
		out.flush();
	}
	
}
