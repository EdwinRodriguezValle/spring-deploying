package com.rodriguez.novi.services;

import com.rodriguez.novi.entities.UserProfileEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraServicesTest {

    @Test
    void ageCalculator() {

        // Act: write a code to simulate a context of how going to be applied the code in software.
        UserProfileEntity persona0 = new UserProfileEntity( null, "Edwin", "Rodriguez Valle", "Male", LocalDate.of(1978, 8,  10), "Camera Obscuradreeft 45", "rodriguez.valle.edwin@gmail.com", "Spanish", true, 638497646,  "It professional");
        CalculadoraServices calculadoraServices = new CalculadoraServices();


        // set up the code to be use
        int age = calculadoraServices.ageCalculator(persona0);

        //Assertion or testing the code
        assertTrue( age > 0);
        assertEquals(44, age );

    }
}