package com.petcalcsalary.CalcSalaryProject.controller;

import com.petcalcsalary.CalcSalaryProject.dtos.SalaryInfoDto;
import com.petcalcsalary.CalcSalaryProject.others.ConstantsClass;
import com.petcalcsalary.CalcSalaryProject.others.exeptions.IncompatibleParametersExсeption;
import com.petcalcsalary.CalcSalaryProject.others.exeptions.MainSalaryExсeption;
import com.petcalcsalary.CalcSalaryProject.services.SalaryService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("api/v1")
@Validated
@Slf4j
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    @GetMapping("/calculate/{salaryTwelveMonth}")
    public ResponseEntity<SalaryInfoDto> getCalculateSalary(@PathVariable("salaryTwelveMonth") @NotNull @PositiveOrZero Integer salaryTwelveMonth,
                                                            @RequestParam(value = "numberVacationDays", required = false) @Min(ConstantsClass.ONE_FLAG) @Max(ConstantsClass.MAX_DURATION_VACATION) Integer numberVacationDays,
                                                            @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = ConstantsClass.PATTERN_DATE) LocalDate startDate,
                                                            @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = ConstantsClass.PATTERN_DATE) LocalDate endDate) throws MainSalaryExсeption {
        log.info(ConstantsClass.GET_METHOD + ConstantsClass.IS_CONTROLLER + SalaryController.class.getName() + salaryTwelveMonth + numberVacationDays + startDate + endDate);
        SalaryInfoDto salaryInfoDto = salaryService.getCalculateSalary(salaryTwelveMonth, numberVacationDays, startDate, endDate);
        if (salaryInfoDto != null) {
            return ResponseEntity.ok(salaryInfoDto);
        }
        return ResponseEntity.badRequest().build();
    }

}
