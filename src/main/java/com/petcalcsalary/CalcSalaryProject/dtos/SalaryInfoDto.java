package com.petcalcsalary.CalcSalaryProject.dtos;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SalaryInfoDto implements Serializable {

    private Integer amountVacationPay;

}
