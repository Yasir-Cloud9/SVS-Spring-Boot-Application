package com.tus.VoterService.service;

import com.tus.VoterService.exception.VoterServiceCustomException;
import com.tus.VoterService.exception.VoterServiceCustomExceptionForInvalidData;
import com.tus.VoterService.model.VoterRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
@Log4j2
public class CheckVoterAgeAndGender
{
    public void checkVoterAgeAndGender(VoterRequest voterRequest) throws VoterServiceCustomException
    {
        // Calculate age of voter based on date of birth
        Date birthDate = voterRequest.getVoterDateOfBirth();
        LocalDate localBirthDate = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate today = LocalDate.now();
        int age = Period.between(localBirthDate, today).getYears();

        // Check if age is less than 18 years
        if (age < 18) {
            log.error("Cannot add voter as age is less than 18 years");
            throw new VoterServiceCustomExceptionForInvalidData("Registration failed, voter must be 18 years or older", "VOTER_UNDERAGE");
        }

        List<String> genderList = Arrays.asList("Male", "Female", "Other");
        if (!genderList.contains(voterRequest.getVoterGender().toString()))
        {
            log.error("Cannot add voter as gender field is invalid");
            throw new VoterServiceCustomExceptionForInvalidData("Registration failed, invalid gender", "INVALID_GENDER");
        }


    }
}
