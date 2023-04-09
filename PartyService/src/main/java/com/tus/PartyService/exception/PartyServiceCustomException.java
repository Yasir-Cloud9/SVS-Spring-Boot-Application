package com.tus.PartyService.exception;

import lombok.Data;

@Data
public class PartyServiceCustomException extends RuntimeException
{
    private String errorCode;

    public PartyServiceCustomException(String message, String errorCode)
    {
        super(message);
        this.errorCode = errorCode;
    }
}
