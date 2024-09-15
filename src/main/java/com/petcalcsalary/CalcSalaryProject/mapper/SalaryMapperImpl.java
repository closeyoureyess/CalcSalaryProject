package com.petcalcsalary.CalcSalaryProject.mapper;

import com.petcalcsalary.CalcSalaryProject.common.SalaryInfo;
import com.petcalcsalary.CalcSalaryProject.dtos.SalaryInfoDto;
import org.springframework.stereotype.Component;

@Component
public class SalaryMapperImpl implements SalaryMapper {
    @Override
    public SalaryInfo convertDtoToSalary(SalaryInfoDto salaryInfoDto) {
        SalaryInfo salaryInfo = new SalaryInfo();
        salaryInfo.setAmountVacationPay(salaryInfoDto.getAmountVacationPay());
        return salaryInfo;
    }

    @Override
    public SalaryInfoDto convertSalaryToDto(SalaryInfo salaryInfo) {
        SalaryInfoDto salaryInfoDto = new SalaryInfoDto();
        salaryInfoDto.setAmountVacationPay(salaryInfo.getAmountVacationPay());
        return salaryInfoDto;
    }
}
