package com.petcalcsalary.CalcSalaryProject.services;

import com.petcalcsalary.CalcSalaryProject.dtos.SalaryInfoDto;
import com.petcalcsalary.CalcSalaryProject.mapper.DayOffImpl;
import com.petcalcsalary.CalcSalaryProject.others.ConstantsClass;
import com.petcalcsalary.CalcSalaryProject.others.exeptions.IncompatibleParametersExсeption;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class SalaryServiceImplTest {
    @InjectMocks
    private SalaryServiceImpl salaryServiceImpl;
    @Mock
    private DayOffImpl dayOffImpl;

    @BeforeEach
    void setUp() {
        salaryServiceImpl = new SalaryServiceImpl();
    }
    @Test
    void verifyThatNumberVacationDaysTest() {
        try {
            Method method = SalaryServiceImpl.class.getDeclaredMethod("verifyThatNumberVacationDaysNotNull", Integer.class, LocalDate.class,
                    LocalDate.class);
            method.setAccessible(true);
            Assertions.assertTrue((Boolean) method.invoke(salaryServiceImpl, 20000, null, null));

            InvocationTargetException invocationTargetExceptionSecond = Assertions.assertThrows(InvocationTargetException.class,
                    () -> method.invoke(salaryServiceImpl, 20000, null, LocalDate.of(2024, 9, 14)));
            Assertions.assertInstanceOf(IncompatibleParametersExсeption.class, invocationTargetExceptionSecond.getCause());

            InvocationTargetException invocationTargetExceptionThird = Assertions.assertThrows(InvocationTargetException.class,
                    () -> method.invoke(salaryServiceImpl, 20000, LocalDate.of(2024, 8, 14), null));
            Assertions.assertInstanceOf(IncompatibleParametersExсeption.class, invocationTargetExceptionThird.getCause());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            log.error(e.getMessage() + ConstantsClass.WHITESPACE + e.getCause());
        }
    }

    @Test
    void verifyThatPeriodNotNullTest(){
        try {
            Method method = SalaryServiceImpl.class.getDeclaredMethod("verifyThatPeriodNotNull", Integer.class, LocalDate.class,
                    LocalDate.class);
            method.setAccessible(true);
            Assertions.assertTrue((Boolean) method.invoke(salaryServiceImpl, null, LocalDate.of(2024, 8, 14),
                    LocalDate.of(2024, 9, 14)));
            InvocationTargetException invocationTargetExceptionFirst = Assertions.assertThrows(InvocationTargetException.class,
                    () -> method.invoke(salaryServiceImpl,null, LocalDate.of(2024, 8, 14), null));
            Assertions.assertInstanceOf(IncompatibleParametersExсeption.class, invocationTargetExceptionFirst.getCause());

            InvocationTargetException invocationTargetExceptionSecond = Assertions.assertThrows(InvocationTargetException.class,
                    () -> method.invoke(salaryServiceImpl,null, null, LocalDate.of(2024, 9, 14)));
            Assertions.assertInstanceOf(IncompatibleParametersExсeption.class, invocationTargetExceptionSecond.getCause());

            InvocationTargetException invocationTargetExceptionThird = Assertions.assertThrows(InvocationTargetException.class,
                    () -> method.invoke(salaryServiceImpl,null, null, null));
            Assertions.assertInstanceOf(IncompatibleParametersExсeption.class, invocationTargetExceptionThird.getCause());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            log.error(e.getMessage() + ConstantsClass.WHITESPACE + e.getCause());
        }
    }

    @Test
    void getCalculateSalaryTest() throws IncompatibleParametersExсeption {
        LocalDate startDate =  LocalDate.of(2024, 8, 14);
        LocalDate endDate = LocalDate.of(2024, 9, 14);
        Mockito.when(dayOffImpl.getDatesBetween(startDate, endDate))
                .thenReturn(List.of(LocalDate.of(2024, 8, 14), LocalDate.of(2024, 8, 15),
                        LocalDate.of(2024, 8, 16), LocalDate.of(2024, 8, 17)));
        Mockito.when(dayOffImpl.getDatesWithoutUsualDayOff())
        Assertions.assertEquals(1656, salaryServiceImpl.getCalculateSalary(25000,
                null,
                startDate,
                endDate));
        Assertions.assertEquals(1656,
                salaryServiceImpl.getCalculateSalary(25000, 24, null, null).getAmountSalary());
    }

}
