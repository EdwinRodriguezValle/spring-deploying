package com.rodriguez.novi.services;


import com.rodriguez.novi.entities.UserProfileEntity;

import java.time.LocalDate;
import java.time.Period;

public class CalculadoraServices {

    public int ageCalculator(UserProfileEntity userProfileEntity){
        int age;
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(userProfileEntity.getBirthday(), currentDate);
       age = period.getYears();
        return age;
    }

}
