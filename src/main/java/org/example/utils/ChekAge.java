package org.example.utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ChekAge {
    private final long targetYear = 6570;


    public boolean chek(int year1, int month1, int day1) {


        LocalDate today = LocalDate.now();
        int year = year1;
        int month = month1;
        int day = day1;
        LocalDate birthday = LocalDate.of(year, month, day);
        long resultDays = ChronoUnit.DAYS.between(birthday, today);
        if(resultDays<=targetYear)
          return   true ;
        return false;
}


}
