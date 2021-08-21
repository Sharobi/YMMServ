package com.retail.ecom.exception;

public class InvalidStoreCompany extends RuntimeException {
	public InvalidStoreCompany(String message){
        super(message);
    }

    public InvalidStoreCompany(String message, Throwable cause){
        super(message, cause);
    }
}
