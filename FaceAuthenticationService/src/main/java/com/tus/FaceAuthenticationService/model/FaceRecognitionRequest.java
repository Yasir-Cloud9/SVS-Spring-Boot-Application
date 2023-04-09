package com.tus.FaceAuthenticationService.model;


import lombok.Data;

@Data
public class FaceRecognitionRequest
{
        private String photo1;
        private String photo2;
        private String bucketName;
}
