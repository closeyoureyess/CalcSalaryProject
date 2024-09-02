package com.petcalcsalary.CalcSalaryProject.mapper;

import com.petcalcsalary.CalcSalaryProject.common.SalaryInfo;
import com.petcalcsalary.CalcSalaryProject.dtos.SalaryInfoDto;

public interface SalaryMapper {

    SalaryInfo convertDtoToSalary(SalaryInfoDto salaryInfoDto);
    SalaryInfoDto convertSalaryToDto(SalaryInfo salaryInfo);

}
