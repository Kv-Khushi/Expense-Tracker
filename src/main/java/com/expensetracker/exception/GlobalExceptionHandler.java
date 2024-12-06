package com.expensetracker.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    public static class ErrorResponse{
        private String message;
        private int status;

        public ErrorResponse(String message, int status)   {
            this.message=message;
            this.status=status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex){
        ErrorResponse errorResponse=new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleAlreadyExistsException(AlreadyExistsException ex){
        ErrorResponse errorResponse=new ErrorResponse(ex.getMessage(), HttpStatus.CONFLICT.value());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(InvalidCredentialException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCredentialException(InvalidCredentialException ex){
        ErrorResponse errorResponse=new ErrorResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED.value());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse("An unexpected error occurred.", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<List<ErrorResponse>> handleValidationExceptions(final Exception ex) {
        // Log exception details
        List<ErrorResponse> errors = new ArrayList<>();

        if (ex instanceof MethodArgumentNotValidException) {
            ((MethodArgumentNotValidException) ex).getBindingResult().getFieldErrors().forEach(error -> {
                String fieldName = error.getField();
                String errorMessage = error.getDefaultMessage();
                errors.add(new ErrorResponse(String.format("Field '%s': %s", fieldName, errorMessage), HttpStatus.BAD_REQUEST.value()));
            });
        } else if (ex instanceof BindException) {
            ((BindException) ex).getBindingResult().getFieldErrors().forEach(error -> {
                String fieldName = error.getField();
                String errorMessage = error.getDefaultMessage();
                errors.add(new ErrorResponse(String.format("Field '%s': %s", fieldName, errorMessage), HttpStatus.BAD_REQUEST.value()));
            });
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}


