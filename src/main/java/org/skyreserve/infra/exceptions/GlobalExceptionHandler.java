package org.skyreserve.infra.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleErrorSaveObjectException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(
                e.getMessage(),
                "Erro ao salvar registro no sistema",
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleObjectNotFoundException(ObjectNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                e.getMessage(),
                "O objeto solicitado não foi encontrado no sistema",
                HttpStatus.NOT_FOUND.value()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AssentoIsReservedException.class)
    public ResponseEntity<ErrorResponse> handleAssentoIsReservedException(AssentoIsReservedException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                e.getMessage(),
                "O assento já está reservado no sistema.",
                HttpStatus.NOT_FOUND.value()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handlePassengerAlreadyExistsdException(DataIntegrityViolationException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                e.getMessage(),
                "O registro já existe no sistema com os dados informados (CPF / Email / Passaporte).",
                HttpStatus.CONFLICT.value()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }




}
