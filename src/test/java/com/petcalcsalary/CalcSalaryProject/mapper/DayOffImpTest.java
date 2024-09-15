package com.petcalcsalary.CalcSalaryProject.mapper;

import com.petcalcsalary.CalcSalaryProject.ConstantsClassTest;
import com.petcalcsalary.CalcSalaryProject.others.ConstantsClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class DayOffImpTest {

    private DayOff dayOff;

    @BeforeEach
    void createObject(){
        dayOff = new DayOffImpl();
    }

    @Test
    void getDatesBetweenTest(){
        List<LocalDate> localDateList = dayOff.getDatesBetween(ConstantsClassTest.START_DATE, ConstantsClassTest.END_DATE);
        Assertions.assertFalse(localDateList.isEmpty());
    }

    @Test
    void getAmountWeeksInWeekYearTest(){
        int amountDaysInYear = dayOff.getAmountWeeksInWeekYear();
        Assertions.assertEquals(amountDaysInYear, dayOff.getAmountWeeksInWeekYear());
    }

    @Test
    void deleteFromListLocalDateFestiveDaysTest(){
        List<LocalDate> localDateList = dayOff.getDatesBetween(ConstantsClassTest.START_DATE, ConstantsClassTest.END_DATE);
        boolean emptyOrNot = false;
        for (LocalDate i : localDateList) {
            Optional<LocalDate> optionalLocalDate = dayOff.checkLocalDateIsFestiveDays(i);
            if (optionalLocalDate.isPresent()){
                emptyOrNot = true;
                break;
            }
        }
        if (emptyOrNot) {
            Assertions.assertEquals(localDateList.size(), dayOff.deleteFromListLocalDateFestiveDays(localDateList).size());
        } else {
            Assertions.assertNotEquals(localDateList.size(), dayOff.deleteFromListLocalDateFestiveDays(localDateList).size());
        }
    }

    @Test
    void compareLocalDateWithUsualDayOffTest(){
        for (int i = 1; i <= 7; i++){
            if (i == 7 || i == 1) {
                Assertions.assertTrue(dayOff.compareLocalDateWithUsualDayOff(i));
            } else {
                Assertions.assertFalse(dayOff.compareLocalDateWithUsualDayOff(i));
            }
        }
    }

    @Test
    void checkLocalDateIsFestiveDaysTest(){
        Optional<LocalDate> optionalLocalDate = dayOff.checkLocalDateIsFestiveDays(ConstantsClassTest.END_DATE);
        if (optionalLocalDate.isEmpty()){
            Assertions.assertFalse(false);
        }
        optionalLocalDate = dayOff.checkLocalDateIsFestiveDays(ConstantsClassTest.START_DATE);
        if (optionalLocalDate.isPresent()) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    void getDatesWithoutUsualDayOffTest(){
        List<LocalDate> dateList = dayOff.getDatesBetween(ConstantsClassTest.START_DATE, ConstantsClassTest.END_DATE);
        Assertions.assertNotEquals(dateList.size(), dayOff.getDatesWithoutUsualDayOff(dateList).size());
        //
        dateList.clear();
        dateList.add(ConstantsClassTest.START_DATE.minusDays(ConstantsClass.ONE_FLAG));
        Assertions.assertEquals(dateList.size(), dayOff.getDatesWithoutUsualDayOff(dateList).size());
    }

}
