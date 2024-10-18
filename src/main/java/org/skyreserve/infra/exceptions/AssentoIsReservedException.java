package org.skyreserve.infra.exceptions;

public class AssentoIsReservedException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public AssentoIsReservedException(String message, Throwable cause) {
		super(message, cause);
	}

	public AssentoIsReservedException(String message) {
		super(message);
	}

}
