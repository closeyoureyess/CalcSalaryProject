package com.petcalcsalary.CalcSalaryProject.dtos;

import lombok.*;

import java.io.Serializable;

/**
 * DTO с информацией об отпускных
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SalaryInfoDto implements Serializable {

    private Integer amountVacationPay;

}
