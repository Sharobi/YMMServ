package com.retail.ecom.exception;

public class UnAuthorizedRequest extends RuntimeException {
	public UnAuthorizedRequest(String message){
        super(message);
    }

    public UnAuthorizedRequest(String message, Throwable cause){
        super(message, cause);
    }
}
