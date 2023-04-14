package com.tus.FaceAuthenticationService.controller;

import com.tus.FaceAuthenticationService.service.FaceRecognition;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("v1/compare-faces")
public class FaceRecognitionController
{
    @Autowired
    FaceRecognition faceRecognition;

    @PostMapping("/{id}")
    public ResponseEntity<String> faceRecognition(@PathVariable("id") String voterID)
    {
        String similarity = faceRecognition.CompareFaces(voterID);
        if(similarity == "True")
        {
            log.info("Faces are a match.");
            return new ResponseEntity<>(similarity,HttpStatus.OK);
        }
        else if (similarity == "False")
        {
            log.info("Faces do not match.");
            return new ResponseEntity<>(similarity,HttpStatus.OK);
        }
        log.info("Some error in the process. Check the logs.");
        return new ResponseEntity<>(similarity,HttpStatus.OK);
    }
}
