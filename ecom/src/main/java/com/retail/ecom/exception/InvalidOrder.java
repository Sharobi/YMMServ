package com.retail.ecom.exception;

public class InvalidOrder extends RuntimeException {
	public InvalidOrder(String message){
        super(message);
    }

    public InvalidOrder(String message, Throwable cause){
        super(message, cause);
    }
}
