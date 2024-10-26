package org.skyreserve.infra.exceptions;

import org.springframework.dao.DataIntegrityViolationException;

public class PassengerAlreadyExistsdException extends DataIntegrityViolationException {
	private static final long serialVersionUID = 1L;

	public PassengerAlreadyExistsdException(String message, Throwable cause) {
		super(message, cause);
	}

	public PassengerAlreadyExistsdException(String message) {
		super(message);
	}

}
