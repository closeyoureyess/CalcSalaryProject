package com.petcalcsalary.CalcSalaryProject.services;

import com.petcalcsalary.CalcSalaryProject.dtos.SalaryInfoDto;
import com.petcalcsalary.CalcSalaryProject.others.exeptions.IncompatibleParametersException;
import com.petcalcsalary.CalcSalaryProject.others.exeptions.MainSalaryException;

import java.time.LocalDate;

/**
 * Интерфейс для предоставления сервисных методов, связанных с расчетом зарплаты.
 * <p>
 * Этот интерфейс определяет методы для выполнения бизнес-логики, связанной с расчетом зарплаты,
 * такими как вычисление отпускных выплат. Реализация этого интерфейса содержит конкретную логику
 * для обработки запросов и предоставления данных.
 * </p>
 */
public interface SalaryService {

    /**
     * Рассчитывает отпускные на основе зарплаты за 12 месяцев и кол-ва дней отпуска
     *
     * @param salaryTwelveMonth - общая зарплата за 12 месяцев
     * @param numberVacationDays - кол-во дней отпуска
     * @param startDate - дата начала отпуска
     * @param endDate - дата окончания отпуска
     * @return {@link SalaryInfoDto} - Объект, содержащий информацию о рассчитанной зарплате.
     * @throws IncompatibleParametersException - Если переданы несовместимные параметры
     */
    SalaryInfoDto getCalculateSalary(Integer salaryTwelveMonth, Integer numberVacationDays, LocalDate startDate, LocalDate endDate) throws MainSalaryException;

}
