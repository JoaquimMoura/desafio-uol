package com.solo.domain.exception;

public class NotfoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotfoundException(String mensagem) {
		super(mensagem);
	}

}