package com.petcalcsalary.CalcSalaryProject.services;

import com.petcalcsalary.CalcSalaryProject.dtos.SalaryInfoDto;
import com.petcalcsalary.CalcSalaryProject.others.exeptions.DescriptionExceptions;
import com.petcalcsalary.CalcSalaryProject.others.exeptions.IncompatibleParametersExсeption;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SalaryServiceImpl implements SalaryService {


    @Override
    public SalaryInfoDto getCalculateSalary(Integer salaryTwelveMonth, Integer numberVacationDays, LocalDate startDate, LocalDate endDate) throws IncompatibleParametersExсeption {
        if (verifyThatNumberVacationDaysNotNull(numberVacationDays, startDate, endDate)) {

        }
        if (verifyThatPeriodNotNull(numberVacationDays, startDate, endDate)) {

        }

        return null;
    }

    private boolean verifyThatNumberVacationDaysNotNull(Integer numberVacationDays, LocalDate startDate, LocalDate endDate) throws IncompatibleParametersExсeption {
        if (numberVacationDays != null && (startDate == null && endDate == null)) {
            return true;
        } else if (numberVacationDays != null && (startDate != null || endDate != null)) {
            throw new IncompatibleParametersExсeption(DescriptionExceptions.GENERATION_ERROR.getTypeEquipmentEnum(),
                    new IncompatibleParametersExсeption(DescriptionExceptions.STATED_RANGE_AND_NUMBER_VACATION_DAYS.getTypeEquipmentEnum()));
        }
        return false;
    }

    private boolean verifyThatPeriodNotNull(Integer numberVacationDays, LocalDate startDate, LocalDate endDate) throws IncompatibleParametersExсeption {
        if (numberVacationDays == null && (startDate != null && endDate != null)) {
            return true;
        } else if (numberVacationDays == null && (startDate == null || endDate == null)) {
            throw new IncompatibleParametersExсeption(DescriptionExceptions.GENERATION_ERROR.getTypeEquipmentEnum(),
                    new IncompatibleParametersExсeption(DescriptionExceptions.RANGE_AND_NUMBER_VACATION_DAYS_IS_NOT_NULL.getTypeEquipmentEnum()));
        }
        return false;
    }
}
