package com.petcalcsalary.CalcSalaryProject.others.exeptions;

import lombok.Getter;

@Getter
public enum DescriptionExceptions {

    GENERATION_ERROR("Возникла ошибка в системе: "),
    STATED_RANGE_AND_NUMBER_VACATION_DAYS("нельзя указать диапазон дат для рассчета отпускных и точное количество дней отпуска одновременно. Попробуйте ещё раз"),
    RANGE_AND_NUMBER_VACATION_DAYS_IS_NOT_NULL("поля с количеством дней отпуска и диапазон дат не могут быть нулевыми одновременно. Попробуйте ещё раз");

    private String typeEquipmentEnum;
    DescriptionExceptions(String typeEquipmentEnum) {
        this.typeEquipmentEnum = typeEquipmentEnum;
    }
}
