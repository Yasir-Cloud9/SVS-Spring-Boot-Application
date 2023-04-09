package com.tus.VoterService.exception;

import com.tus.VoterService.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler(VoterServiceCustomException.class)
    public ResponseEntity<ErrorResponse> handleVoterServiceException(VoterServiceCustomException exception)
    {
        return new ResponseEntity<>(new ErrorResponse().builder()
                .errorMessage(exception.getMessage())
                .errorCode(exception.getErrorCode())
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(VoterServiceCustomExceptionForInvalidData.class)
    public ResponseEntity<ErrorResponse> handleVoterServiceExceptionForInvalidData(VoterServiceCustomExceptionForInvalidData exception)
    {
        return new ResponseEntity<>(new ErrorResponse().builder()
                .errorMessage(exception.getMessage())
                .errorCode(exception.getErrorCode())
                .build(), HttpStatus.EXPECTATION_FAILED);
    }
}
