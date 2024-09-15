package com.petcalcsalary.CalcSalaryProject.mapper;

import com.petcalcsalary.CalcSalaryProject.common.SalaryInfo;
import com.petcalcsalary.CalcSalaryProject.dtos.SalaryInfoDto;
import com.petcalcsalary.CalcSalaryProject.others.ConstantsClass;
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
        SalaryInfoDto salaryInfoDto = new SalaryInfoDto(ConstantsClass.ONE_FLAG);
        Assertions.assertInstanceOf(SalaryInfo.class, salaryMapper.convertDtoToSalary(salaryInfoDto));

        if (salaryMapper.convertDtoToSalary(salaryInfoDto).getAmountSalary() != null) {
            Assertions.assertNotNull(salaryMapper.convertDtoToSalary(salaryInfoDto).getAmountSalary());
        }
    }

    @Test
    void convertSalaryToDtoTest() {
        SalaryInfo salaryInfo = new SalaryInfo(ConstantsClass.ONE_FLAG);
        Assertions.assertInstanceOf(SalaryInfoDto.class, salaryMapper.convertSalaryToDto(salaryInfo));

        if (salaryMapper.convertSalaryToDto(salaryInfo).getAmountSalary() != null) {
            Assertions.assertNotNull(salaryMapper.convertSalaryToDto(salaryInfo).getAmountSalary());
        }
    }

}
