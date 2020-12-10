package com.sumit.knowledgeRepo.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class ExceptionResponse {
	private Date timestamp;
    private String message;
    private String url;
    private HttpStatus status;

    public ExceptionResponse(Date timestamp, String message, String url, HttpStatus status) {
	    super();
	    this.timestamp = timestamp;
	    this.message = message;
	    this.url = url;
	    this.status = status;
    }
    
    public ExceptionResponse(Date timestamp, String message, String url) {
	    super();
	    this.timestamp = timestamp;
	    this.message = message;
	    this.url = url;
    }

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getUrl() {
		return url;
	}

	public HttpStatus getStatus() {
		return status;
	}

}
