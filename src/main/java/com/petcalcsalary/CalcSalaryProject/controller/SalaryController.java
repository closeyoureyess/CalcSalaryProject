package com.petcalcsalary.CalcSalaryProject.controller;

import com.petcalcsalary.CalcSalaryProject.dtos.SalaryInfoDto;
import com.petcalcsalary.CalcSalaryProject.others.ConstantsClass;
import com.petcalcsalary.CalcSalaryProject.others.exeptions.MainSalaryExсeption;
import com.petcalcsalary.CalcSalaryProject.services.SalaryService;
import jakarta.validation.constraints.*;
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
    public ResponseEntity<SalaryInfoDto> getCalculateSalary(@PathVariable("salaryTwelveMonth") @NotNull @PositiveOrZero @Min(19242) Integer salaryTwelveMonth,
                                                            @RequestParam(value = "numberVacationDays", required = false) @Positive @Min(1) @Max(28) Integer numberVacationDays,
                                                            @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate startDate,
                                                            @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate endDate) throws MainSalaryExсeption {
        log.info(ConstantsClass.GET_METHOD + ConstantsClass.IS_CONTROLLER + SalaryController.class.getName() + salaryTwelveMonth + numberVacationDays + startDate + endDate);
        SalaryInfoDto salaryInfoDto = salaryService.getCalculateSalary(salaryTwelveMonth, numberVacationDays, startDate, endDate);
        if (salaryInfoDto != null) {
            return ResponseEntity.ok(salaryInfoDto);
        }
        return ResponseEntity.badRequest().build();
    }

}
