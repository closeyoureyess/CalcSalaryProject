package com.petcalcsalary.CalcSalaryProject.common;

import lombok.*;

//Здесь должна быть аннотация @Entity, @Table и прочие
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SalaryInfo {

    private Integer amountVacationPay;

}
