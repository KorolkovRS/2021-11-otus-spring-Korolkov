package ru.korolkovrs.spring23.rest.exception_handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.korolkovrs.spring23.exception.IllegalResponseDataException;
import ru.korolkovrs.spring23.exception.ResourceNotFoundException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        Map<String, String> errors = new HashMap<>();
        errors.put("message", ex.getMessage());
        LibraryError error = new LibraryError(httpStatus.value(), errors, new Date());
        return new ResponseEntity(error, httpStatus);
    }

    @ExceptionHandler(IllegalResponseDataException.class)
    public ResponseEntity<Object> handleIllegalResponseDataException(IllegalResponseDataException ex) {
        HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        Map<String, String> errors = new HashMap<>();
        errors.put("message", ex.getMessage());
        LibraryError error = new LibraryError(httpStatus.value(), errors, new Date());
        return new ResponseEntity(error, httpStatus);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        LibraryError error = new LibraryError(status.value(), errors, new Date());
        return new ResponseEntity(error, status);
    }
}
