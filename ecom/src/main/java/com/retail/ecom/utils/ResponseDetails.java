package com.retail.ecom.utils;

import java.util.Date;

public class ResponseDetails {
	  private Date timestamp;
	  private String message;
	  private String details;
	  private Double status;
	  
	  public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Double getStatus() {
		return status;
	}

	public void setStatus(Double status) {
		this.status = status;
	}

	public ResponseDetails(Date timestamp, String message, String details) {
	    super();
	    this.timestamp = timestamp;
	    this.message = message;
	    this.details = details;
	  }

	public ResponseDetails(Date timestamp, String message, String details, Double status) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
		this.status = status;
	}
	
	
	public ResponseDetails(Date timestamp, String message, String details, int status) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
		this.status = (double)status;
	}
	
	@Override
	public String toString() {
		return "ResponseDetails [timestamp=" + timestamp + ", message=" + message + ", details=" + details + ", status="
				+ status + "]";
	}
	
}
