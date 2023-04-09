package com.tus.FaceAuthenticationService.service;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.model.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Log4j2
@Component
public class FaceRecognition {

    @Autowired
    AmazonRekognition amazonRekognition;

    public String CompareFaces(String photo1, String photo2, String bucketName) {

//        String photo_1 = "Shubham_1.jpg";
//        String photo_2 = "Shubham_2.jpg";
//        String bucketName_1 = "smart-voting-face-recognition";

        CompareFacesRequest compareFacesRequest = new CompareFacesRequest().withSourceImage(new Image()
                .withS3Object(new S3Object()
                        .withName(photo1).withBucket(bucketName))).withTargetImage(new Image()
                .withS3Object(new S3Object()
                        .withName(photo2).withBucket(bucketName))).withSimilarityThreshold(80F);

        try {

            CompareFacesResult result = amazonRekognition.compareFaces(compareFacesRequest);
            List<CompareFacesMatch> lists = result.getFaceMatches();

            log.info("Detected labels for {} and {}", photo1, photo2);

            if (!lists.isEmpty()) {
                for (CompareFacesMatch label : lists) {
                    log.info(label.getFace() + ": Similarity is " + label.getSimilarity().toString());
                    return "Similarity : " + label.getSimilarity().toString();
                }
            } else {
                log.info("Faces Does not match");
                return "Face does not match";
            }
        } catch (AmazonRekognitionException e) {
            e.printStackTrace();
        }
        log.info("Some Error happened. Check Code.");
        return "Some Error happened. Check Code.";
    }
}
