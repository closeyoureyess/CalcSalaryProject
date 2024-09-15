package com.petcalcsalary.CalcSalaryProject.mapper;

import com.petcalcsalary.CalcSalaryProject.common.SalaryInfo;
import com.petcalcsalary.CalcSalaryProject.dtos.SalaryInfoDto;

/**
 * (в текущей версии не используется)
 * Интерфейс для преобразования объектов {@link SalaryInfo} и {@link SalaryInfoDto}.
 * <p>
 * Этот интерфейс предоставляет методы для преобразования объектов между слоями данных и представления
 * </p>
 */
public interface SalaryMapper {

    /**
     * Преобразует объект {@link SalaryInfoDto} в {@link SalaryInfo}.
     *
     * @param salaryInfoDto Объект передачи данных, содержащий информацию о зарплате.
     * @return {@link SalaryInfo} объект, содержащий информацию о зарплате.
     */
    SalaryInfo convertDtoToSalary(SalaryInfoDto salaryInfoDto);

    /**
     * Преобразует объект {@link SalaryInfo} в {@link SalaryInfoDto}.
     *
     * @param salaryInfo Объект, содержащий информацию о зарплате.
     * @return {@link SalaryInfoDto} объект передачи данных, содержащий информацию о зарплате.
     */
    SalaryInfoDto convertSalaryToDto(SalaryInfo salaryInfo);

}
