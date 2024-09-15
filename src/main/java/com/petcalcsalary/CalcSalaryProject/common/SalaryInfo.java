package com.petcalcsalary.CalcSalaryProject.common;

import lombok.*;

//Здесь должна быть аннотация @Entity, @Table и прочие
/**
 * Класс, представляющий информацию об отпускных
 * <p>
 * Этот класс используется для хранения информации о кол-ве отпускных денежных средств для сотрудника
 * </p>
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SalaryInfo {

    private Integer amountVacationPay;

}
