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

    public String CompareFaces(String voterID) {

        String photo1 = voterID + ".jpg";
        String photo2 = voterID + "_" + voterID + ".jpg";
        String bucketName = "smart-voting-face-recognition";

        CompareFacesRequest compareFacesRequest = new CompareFacesRequest().withSourceImage(new Image()
                .withS3Object(new S3Object()
                        .withName("Source/" + photo1).withBucket(bucketName))).withTargetImage(new Image()
                .withS3Object(new S3Object()
                        .withName("Target/" + photo2).withBucket(bucketName))).withSimilarityThreshold(80F);
        try {

            CompareFacesResult result = amazonRekognition.compareFaces(compareFacesRequest);
            List<CompareFacesMatch> lists = result.getFaceMatches();

            log.info("Detected labels for {} and {}", photo1, photo2);

            if (!lists.isEmpty()) {
                for (CompareFacesMatch label : lists) {
                    log.info("Similarity between two faces : " + label.getSimilarity().toString());
                    return "True";
                }
            } else {
                log.info("Faces Does not match");
                return "False";
            }
        } catch (AmazonRekognitionException e) {
            e.printStackTrace();
        }
        log.info("Some Error happened. Check Code.");
        return "Error";
    }
}
