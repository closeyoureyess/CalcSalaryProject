package com.petcalcsalary.CalcSalaryProject.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.petcalcsalary.CalcSalaryProject.others.ConstantsClassTest;
import com.petcalcsalary.CalcSalaryProject.dtos.SalaryInfoDto;
import com.petcalcsalary.CalcSalaryProject.services.SalaryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class SalaryControllerTest {

    @InjectMocks
    private SalaryController salaryController;
    @Mock
    private SalaryService salaryService;
    private MockMvc mockMvc;

    @BeforeEach
    void createObject() {
        mockMvc = MockMvcBuilders.standaloneSetup(salaryController).build();
    }

    @Test
    void getCalculateSalary() throws Exception {
        SalaryInfoDto salaryInfoDto = new SalaryInfoDto(ConstantsClassTest.AMOUNT_VACATION_PAY);
        Mockito.when(salaryService.getCalculateSalary(ConstantsClassTest.AMOUNT_SALARY, null, ConstantsClassTest.START_DATE,
                ConstantsClassTest.END_DATE)).thenReturn(salaryInfoDto);
        mockMvc.perform(get("/api/v1/calculate/{salaryTwelveMonth}", ConstantsClassTest.AMOUNT_SALARY)
                        .param("startDate", "2024-08-14")
                        .param("endDate", "2024-09-14"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amountVacationPay").value(salaryInfoDto.getAmountVacationPay()));
        Mockito.verify(salaryService, Mockito.times(ConstantsClassTest.ONE_FLAG)).getCalculateSalary(ConstantsClassTest.AMOUNT_SALARY,
                null, ConstantsClassTest.START_DATE, ConstantsClassTest.END_DATE);

        Integer numberVacationDays = 24;
        Mockito.when(salaryService.getCalculateSalary(ConstantsClassTest.AMOUNT_SALARY, numberVacationDays,
                null, null)).thenReturn(salaryInfoDto);
        mockMvc.perform(get("/api/v1/calculate/{salaryTwelveMonth}", ConstantsClassTest.AMOUNT_SALARY)
                        .param("numberVacationDays", String.valueOf(numberVacationDays)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amountVacationPay").value(salaryInfoDto.getAmountVacationPay()));
        Mockito.verify(salaryService, Mockito.times(ConstantsClassTest.ONE_FLAG)).getCalculateSalary(ConstantsClassTest.AMOUNT_SALARY, numberVacationDays,
                null, null);

    }
}
