package com.tus.ElectionService.external.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "FACE-RECOG-SERVICE/v1/compare-faces")
public interface FaceAuthenticationServiceClient
{
    @PostMapping("/{id}")
    String faceRecognition(@PathVariable String id);
}
