package com.petcalcsalary.CalcSalaryProject.mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DayOff {

    List<LocalDate> getDatesBetween(LocalDate startDate, LocalDate endDate);
    List<LocalDate> deleteFromListLocalDateFestiveDays(List<LocalDate> localDateList);
    Optional<LocalDate> checkLocalDateIsFestiveDays(LocalDate localDate);
    List<LocalDate> getDatesWithoutUsualDayOff(List<LocalDate> localDateList);
    boolean compareLocalDateWithUsualDayOff(int valueDayOfWeek);
    int getAmountWeeksInWeekYear();
    boolean leapYearOrNot();

}
