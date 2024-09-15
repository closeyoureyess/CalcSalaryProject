package com.petcalcsalary.CalcSalaryProject.services;

import com.petcalcsalary.CalcSalaryProject.others.ConstantsClassTest;
import com.petcalcsalary.CalcSalaryProject.mapper.DayOff;
import com.petcalcsalary.CalcSalaryProject.mapper.DayOffImpl;
import com.petcalcsalary.CalcSalaryProject.others.exeptions.IncompatibleParametersExсeption;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

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
    private DayOff dayOff;

    //Тесты  приватных методов
    @Test
    void verifyThatNumberVacationDaysTest() {
        try {
            Method method = SalaryServiceImpl.class.getDeclaredMethod("verifyThatNumberVacationDaysNotNull", Integer.class, LocalDate.class,
                    LocalDate.class);
            method.setAccessible(true);
            Assertions.assertTrue((Boolean) method.invoke(salaryServiceImpl,ConstantsClassTest.AMOUNT_SALARY, null, null));

            InvocationTargetException invocationTargetExceptionSecond = Assertions.assertThrows(InvocationTargetException.class,
                    () -> method.invoke(salaryServiceImpl, ConstantsClassTest.AMOUNT_SALARY, null, ConstantsClassTest.END_DATE));
            Assertions.assertInstanceOf(IncompatibleParametersExсeption.class, invocationTargetExceptionSecond.getCause());

            InvocationTargetException invocationTargetExceptionThird = Assertions.assertThrows(InvocationTargetException.class,
                    () -> method.invoke(salaryServiceImpl, ConstantsClassTest.AMOUNT_SALARY, ConstantsClassTest.START_DATE, null));
            Assertions.assertInstanceOf(IncompatibleParametersExсeption.class, invocationTargetExceptionThird.getCause());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            log.error(e.getMessage() + ConstantsClassTest.WHITESPACE + e.getCause());
        }
    }

    @Test
    void verifyThatPeriodNotNullTest() {
        try {
            Method method = SalaryServiceImpl.class.getDeclaredMethod("verifyThatPeriodNotNull", Integer.class, LocalDate.class,
                    LocalDate.class);
            method.setAccessible(true);
            Assertions.assertTrue((Boolean) method.invoke(salaryServiceImpl, null, ConstantsClassTest.START_DATE,
                    ConstantsClassTest.END_DATE));
            InvocationTargetException invocationTargetExceptionFirst = Assertions.assertThrows(InvocationTargetException.class,
                    () -> method.invoke(salaryServiceImpl, null, ConstantsClassTest.START_DATE, null));
            Assertions.assertInstanceOf(IncompatibleParametersExсeption.class, invocationTargetExceptionFirst.getCause());

            InvocationTargetException invocationTargetExceptionSecond = Assertions.assertThrows(InvocationTargetException.class,
                    () -> method.invoke(salaryServiceImpl, null, null, ConstantsClassTest.END_DATE));
            Assertions.assertInstanceOf(IncompatibleParametersExсeption.class, invocationTargetExceptionSecond.getCause());

            InvocationTargetException invocationTargetExceptionThird = Assertions.assertThrows(InvocationTargetException.class,
                    () -> method.invoke(salaryServiceImpl, null, null, null));
            Assertions.assertInstanceOf(IncompatibleParametersExсeption.class, invocationTargetExceptionThird.getCause());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            log.error(e.getMessage() + ConstantsClassTest.WHITESPACE + e.getCause());
        }
    }

    @Test
    void getCalculateSalaryTest() throws IncompatibleParametersExсeption {
        DayOffImpl dayOffLocal = new DayOffImpl();
        List<LocalDate> listWithLocalDates = dayOffLocal.getDatesBetween(ConstantsClassTest.START_DATE, ConstantsClassTest.END_DATE);
        Mockito.when(dayOff.getDatesBetween(ConstantsClassTest.START_DATE, ConstantsClassTest.END_DATE)).thenReturn(listWithLocalDates);

        List<LocalDate> listWithoutDayOff = dayOffLocal.getDatesWithoutUsualDayOff(listWithLocalDates);
        Mockito.when(dayOff.getDatesWithoutUsualDayOff(listWithLocalDates)).thenReturn(listWithoutDayOff);

        List<LocalDate> listWithoutFestiveDays = dayOffLocal.deleteFromListLocalDateFestiveDays(listWithoutDayOff);
        Mockito.when(dayOff.deleteFromListLocalDateFestiveDays(listWithoutDayOff)).thenReturn(listWithoutFestiveDays);

        Assertions.assertEquals(ConstantsClassTest.AMOUNT_VACATION_PAY, salaryServiceImpl.getCalculateSalary(ConstantsClassTest.AMOUNT_SALARY, null,
                        ConstantsClassTest.START_DATE, ConstantsClassTest.END_DATE).getAmountVacationPay());
        Assertions.assertEquals(ConstantsClassTest.AMOUNT_VACATION_PAY,
                salaryServiceImpl.getCalculateSalary(ConstantsClassTest.AMOUNT_SALARY, 23, null, null).getAmountVacationPay());
    }

    @Test
    void computeAverageSalaryPerDayTest() {
        try {
            Method method = SalaryServiceImpl.class.getDeclaredMethod("computeAverageSalaryPerDay", Integer.class);
            method.setAccessible(true);
            Assertions.assertEquals(69, method.invoke(salaryServiceImpl,ConstantsClassTest.AMOUNT_SALARY));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            log.error(e.getMessage() + ConstantsClassTest.WHITESPACE + e.getCause());
        }
    }

}
