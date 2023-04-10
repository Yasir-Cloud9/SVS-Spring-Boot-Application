package com.tus.FaceAuthenticationService.controller;

import com.tus.FaceAuthenticationService.model.FaceRecognitionRequest;
import com.tus.FaceAuthenticationService.service.FaceRecognition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("v1/compare-faces")
public class FaceRecognitionController
{
    @Autowired
    FaceRecognition faceRecognition;

    @PostMapping()
    public ResponseEntity<String> faceRecognition(@RequestBody FaceRecognitionRequest faceRecognitionRequest)
    {
        String similarity = faceRecognition.CompareFaces(faceRecognitionRequest.getPhoto1(),faceRecognitionRequest.getPhoto2());
        //String similarity = faceRecognition.CompareFaces("Shubham_1.jpg", "Shubham_2.jpg", "smart-voting-face-recognition");
        if(similarity == "True")
        {
            return new ResponseEntity<>(similarity,HttpStatus.OK);
        }
        else if (similarity == "False")
        {
            return new ResponseEntity<>(similarity,HttpStatus.OK);
        }
        return new ResponseEntity<>(similarity,HttpStatus.EXPECTATION_FAILED);
    }
}
