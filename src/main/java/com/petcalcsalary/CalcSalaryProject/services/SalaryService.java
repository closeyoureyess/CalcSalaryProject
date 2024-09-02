package com.petcalcsalary.CalcSalaryProject.services;

import com.petcalcsalary.CalcSalaryProject.dtos.SalaryInfoDto;
import com.petcalcsalary.CalcSalaryProject.others.exeptions.IncompatibleParametersExсeption;
import com.petcalcsalary.CalcSalaryProject.others.exeptions.MainSalaryExсeption;

import java.time.LocalDate;

public interface SalaryService {

    SalaryInfoDto getCalculateSalary(Integer salaryTwelveMonth, Integer numberVacationDays, LocalDate startDate, LocalDate endDate) throws MainSalaryExсeption;

}
