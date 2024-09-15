package com.petcalcsalary.CalcSalaryProject.common;

import lombok.*;

//Здесь должна быть аннотация @Entity, @Table и прочие
/**
 * Класс, представляющий информацию об отпускных
 * <p>
 * Этот класс используется для хранения информации об отпускных денежных средствах для сотрудника
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
