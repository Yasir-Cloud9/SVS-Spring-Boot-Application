package com.tus.VoterService.exception;

import lombok.Data;

@Data
public class VoterServiceCustomExceptionForInvalidData extends RuntimeException
{
    private String errorCode;

    public VoterServiceCustomExceptionForInvalidData(String message, String errorCode)
    {
        super(message);
        this.errorCode = errorCode;
    }
}