package com.tus.VoterService.exception;

import lombok.Data;

@Data
public class VoterServiceCustomException extends RuntimeException
{
    private String errorCode;

    public VoterServiceCustomException(String message, String errorCode)
    {
        super(message);
        this.errorCode = errorCode;
    }
}
