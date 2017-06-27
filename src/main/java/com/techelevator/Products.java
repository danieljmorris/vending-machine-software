package com.techelevator;


public class Products {
	private String name;
	private DollarAmount price;
	private String slotNumber;
	private int quantity = 5;
	private int qtySold = 0;
	
	public Products(String name, DollarAmount price, String slotNumber) {
		this.name = name;
		this.price = price;
		this.slotNumber = slotNumber;
		
	}

	public String getName() {
		return name;
	}

	public DollarAmount getPrice() {
		return price;
	}
	
	public String getSlotNumber(){
		return slotNumber;
	}
	
	public int getQuantity(){
		return quantity;
	}
	
	public int getQtySold(){
		return qtySold;
	}
	
	public void setQtySold(int qtySold) {
		this.qtySold = qtySold;
	}
	
	public void setQuantity(int quantity){
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return slotNumber + "   " +String.format("%-20s" ,name) + " " + String.format("%-12s", price) + " " + quantity;
	}
}
