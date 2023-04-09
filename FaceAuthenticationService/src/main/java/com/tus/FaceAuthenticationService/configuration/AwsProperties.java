package com.tus.FaceAuthenticationService.configuration;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("aws")
public class AwsProperties {

    private String accessKey;

    private String secretKey;
}