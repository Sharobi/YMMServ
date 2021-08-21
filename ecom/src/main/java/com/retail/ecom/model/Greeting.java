package com.retail.ecom.model;

public class Greeting {
	private String greet;

	public String getGreet() {
		return greet;
	}

	public void setGreet(String greet) {
		this.greet = greet;
	}

	@Override
	public String toString() {
		return "Greeting [greet=" + greet + "]";
	}

	public Greeting(String greet) {
		super();
		this.greet = greet;
	}
	
}
