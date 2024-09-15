package com.petcalcsalary.CalcSalaryProject.controller;

import com.petcalcsalary.CalcSalaryProject.dtos.SalaryInfoDto;
import com.petcalcsalary.CalcSalaryProject.others.ConstantsClass;
import com.petcalcsalary.CalcSalaryProject.others.exeptions.MainSalaryExсeption;
import com.petcalcsalary.CalcSalaryProject.services.SalaryService;

import javax.validation.constraints.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


/**
 * Контроллер для обработки запросов, связанных с расчётом отпускных.
 * <p>
 * Этот контроллер предоставляет API для вычисления отпускных на основе средней зарплаты за 12 месяцев,
 * количества дней отпуска и опциональных дат начала и окончания отпуска.
 * </p>
 */
@RestController
@RequestMapping("api/v1")
@Validated
@Slf4j
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    /**
     * Обрабатывает запрос на расчёт отпускных.
     * <p>
     * Метод принимает среднюю зарплату за 12 месяцев, количество дней отпуска (опционально) и
     * даты начала и окончания отпуска (опционально). Он вызывает сервис для расчёта отпускных и возвращает
     * результат в виде объекта {@link SalaryInfoDto}. Если переданы некорректные данные, возвращается статус 400 Bad Request.
     * </p>
     *
     * @param salaryTwelveMonth  Средняя зарплата за 12 месяцев. Должна быть положительным числом и не менее 19242(МРОТ в РФ на 2024).
     * @param numberVacationDays Количество дней отпуска. Опционально. Должно быть положительным числом от 1 до 28(согласно ТК РФ).
     * @param startDate          Дата начала отпуска. Опционально. Формат: yyyy-MM-dd.
     * @param endDate            Дата окончания отпуска. Опционально. Формат: yyyy-MM-dd.
     * @return {@link ResponseEntity} содержащий объект {@link SalaryInfoDto} с расчётной суммой отпускных или статус 400 Bad Request при ошибке.
     * @throws MainSalaryExсeption Исключение, которое может быть выброшено при расчёте отпускных.
     */
    @GetMapping("/calculate/{salaryTwelveMonth}")
    public ResponseEntity<SalaryInfoDto> getCalculateSalary(@PathVariable("salaryTwelveMonth") @NotNull @PositiveOrZero @Min(19242) Integer salaryTwelveMonth,
                                                            @RequestParam(value = "numberVacationDays", required = false) @Positive @Min(1) @Max(28) Integer numberVacationDays,
                                                            @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                            @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) throws MainSalaryExсeption {
        log.info(ConstantsClass.GET_METHOD + ConstantsClass.IS_CONTROLLER + SalaryController.class.getName() + salaryTwelveMonth + numberVacationDays + startDate + endDate);
        SalaryInfoDto salaryInfoDto = salaryService.getCalculateSalary(salaryTwelveMonth, numberVacationDays, startDate, endDate);
        if (salaryInfoDto != null) {
            return ResponseEntity.ok(salaryInfoDto);
        }
        return ResponseEntity.badRequest().build();
    }

}
