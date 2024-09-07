package com.petcalcsalary.CalcSalaryProject.services;

import com.petcalcsalary.CalcSalaryProject.mapper.DayOff;
import com.petcalcsalary.CalcSalaryProject.mapper.DayOffImpl;
import com.petcalcsalary.CalcSalaryProject.dtos.SalaryInfoDto;
import com.petcalcsalary.CalcSalaryProject.others.exeptions.DescriptionExceptions;
import com.petcalcsalary.CalcSalaryProject.others.exeptions.IncompatibleParametersExсeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SalaryServiceImpl implements SalaryService {

    @Autowired
    private DayOff dayOff;

    @Override
    public SalaryInfoDto getCalculateSalary(Integer salaryTwelveMonth, Integer numberVacationDays, LocalDate startDate, LocalDate endDate) throws IncompatibleParametersExсeption {
        SalaryInfoDto localSalaryInfoDto = new SalaryInfoDto();
        if (verifyThatNumberVacationDaysNotNull(numberVacationDays, startDate, endDate)) {
            localSalaryInfoDto.setAmountSalary(salaryTwelveMonth * numberVacationDays);
        }
        if (verifyThatPeriodNotNull(numberVacationDays, startDate, endDate)) {
            List<LocalDate> listWithoutFestiveAndDayOffDays = dayOff.deleteFromListLocalDateFestiveDays(
                    dayOff.getDatesWithoutUsualDayOff(
                            dayOff.getDatesBetween(startDate, endDate)
                    )
            );
            localSalaryInfoDto.setAmountSalary(salaryTwelveMonth * listWithoutFestiveAndDayOffDays.size());
        }
        return localSalaryInfoDto;
    }

    private boolean verifyThatNumberVacationDaysNotNull(Integer numberVacationDays, LocalDate startDate, LocalDate endDate) throws IncompatibleParametersExсeption {
        if (numberVacationDays != null && (startDate == null && endDate == null)) {
            return true;
        } else if (numberVacationDays != null) {
            throw new IncompatibleParametersExсeption(DescriptionExceptions.GENERATION_ERROR.getTypeEquipmentEnum(),
                    new IncompatibleParametersExсeption(DescriptionExceptions.STATED_RANGE_AND_NUMBER_VACATION_DAYS.getTypeEquipmentEnum()));
        }
        return false;
    }

    private boolean verifyThatPeriodNotNull(Integer numberVacationDays, LocalDate startDate, LocalDate endDate) throws IncompatibleParametersExсeption {
        if (numberVacationDays == null && (startDate != null && endDate != null)) {
            return true;
        } else if (numberVacationDays == null) {
            throw new IncompatibleParametersExсeption(DescriptionExceptions.GENERATION_ERROR.getTypeEquipmentEnum(),
                    new IncompatibleParametersExсeption(DescriptionExceptions.RANGE_AND_NUMBER_VACATION_DAYS_IS_NOT_NULL.getTypeEquipmentEnum()));
        }
        return false;
    }
}
