package com.petcalcsalary.CalcSalaryProject.mapper;

import com.petcalcsalary.CalcSalaryProject.others.ConstantsClass;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Реализация интерфейса {@link DayOff}, предоставляющая методы для работы с выходными днями
 */
@Component
public class DayOffImpl implements DayOff {



    @Override
    public List<LocalDate> getDatesWithoutUsualDayOff(List<LocalDate> localDateList) {
        List<LocalDate> localDateListWithoutUsualDayOff = new LinkedList<>();
        for (LocalDate ld : localDateList) {
            if(compareLocalDateWithUsualDayOff(ld.getDayOfWeek().getValue())){
                continue;
            }
            localDateListWithoutUsualDayOff.add(ld);
        }
        return localDateListWithoutUsualDayOff;
    }

    @Override
    public boolean compareLocalDateWithUsualDayOff(int valueDayOfWeek){
        int saturday = 6; // because Calendar.SATURDAY == 7
        int sunday = 7; // Calendar.SUNDAY == 1
        if (valueDayOfWeek == saturday) {
            return true;
        } else if (valueDayOfWeek == sunday) {
            return true;
        }
        return false;
    }

    @Override
    public Optional<LocalDate> checkLocalDateIsFestiveDays(LocalDate localDate) {
        for (LocalDate ld : ConstantsClass.DAYOFF_MONTH_DAY) {
            if (localDate.equals(ld)) {
                return Optional.empty();
            }
        }
        return Optional.of(localDate);
    }

    @Override
    public List<LocalDate> deleteFromListLocalDateFestiveDays(List<LocalDate> localDateList) {
        List<LocalDate> localDateListWithoutFestiveDays = new LinkedList<>();
        for (LocalDate i : localDateList) {
            Optional<LocalDate> optionalLocalDate = checkLocalDateIsFestiveDays(i);
            if (optionalLocalDate.isPresent()){
                localDateListWithoutFestiveDays.add(optionalLocalDate.get());
            }
        }
        return localDateListWithoutFestiveDays;
    }

    @Override
    public boolean leapYearOrNot() {
        LocalDate localDateNow = LocalDate.now();
        boolean leapYear = localDateNow.isLeapYear();
        if (leapYear) {
            return true;
        }
        return false;
    }

    @Override
    public int getAmountWeeksInWeekYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Year.now().getValue());
        calendar.set(Calendar.MONTH, Month.DECEMBER.getValue());
        calendar.set(Calendar.DAY_OF_MONTH, ConstantsClass.THIRTY_FIRST_DAY);
        return calendar.getWeeksInWeekYear();
    }

    @Override
    public List<LocalDate> getDatesBetween(LocalDate startDate, LocalDate endDate) {
        List<LocalDate> localDates = new LinkedList<>();
        LocalDate localDate = startDate;
        while (!localDate.isAfter(endDate)) {
            localDates.add(localDate);
            localDate = localDate.plusDays(ConstantsClass.ONE_FLAG);
        }
        return localDates;
    }
}
