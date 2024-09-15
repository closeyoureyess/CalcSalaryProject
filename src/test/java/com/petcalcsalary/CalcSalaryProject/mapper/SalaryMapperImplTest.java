package com.petcalcsalary.CalcSalaryProject.mapper;

import com.petcalcsalary.CalcSalaryProject.others.ConstantsClassTest;
import com.petcalcsalary.CalcSalaryProject.common.SalaryInfo;
import com.petcalcsalary.CalcSalaryProject.dtos.SalaryInfoDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SalaryMapperImplTest {

    private SalaryMapper salaryMapper;

    @BeforeEach
    void createObject() {
        salaryMapper = new SalaryMapperImpl();
    }

    @Test
    void convertDtoToSalaryTest() {
        SalaryInfoDto salaryInfoDto = new SalaryInfoDto(ConstantsClassTest.ONE_FLAG);
        Assertions.assertInstanceOf(SalaryInfo.class, salaryMapper.convertDtoToSalary(salaryInfoDto));

        if (salaryMapper.convertDtoToSalary(salaryInfoDto).getAmountVacationPay() != null) {
            Assertions.assertNotNull(salaryMapper.convertDtoToSalary(salaryInfoDto).getAmountVacationPay());
        }
    }

    @Test
    void convertSalaryToDtoTest() {
        SalaryInfo salaryInfo = new SalaryInfo(ConstantsClassTest.ONE_FLAG);
        Assertions.assertInstanceOf(SalaryInfoDto.class, salaryMapper.convertSalaryToDto(salaryInfo));

        if (salaryMapper.convertSalaryToDto(salaryInfo).getAmountVacationPay() != null) {
            Assertions.assertNotNull(salaryMapper.convertSalaryToDto(salaryInfo).getAmountVacationPay());
        }
    }

}
