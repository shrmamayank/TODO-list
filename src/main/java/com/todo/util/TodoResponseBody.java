package com.todo.util;

public class TodoResponseBody<T> {
	private T body;
	private String message;

	/**
	 * 
	 * @param body
	 * @param message
	 */
	public TodoResponseBody(T body, String message) {
		this.body = body;
		this.message = message;
	}

	/**
	 * @param body
	 */
	public TodoResponseBody(T body) {
		this.body = body;
	}

	/**
	 * @return the body
	 */
	public T getBody() {
		return body;
	}

	/**
	 * @param body
	 *            the body to set
	 */
	public void setBody(T body) {
		this.body = body;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}