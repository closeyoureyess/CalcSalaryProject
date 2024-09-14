package com.petcalcsalary.CalcSalaryProject.services;

import com.petcalcsalary.CalcSalaryProject.mapper.DayOff;
import com.petcalcsalary.CalcSalaryProject.mapper.DayOffImpl;
import com.petcalcsalary.CalcSalaryProject.others.ConstantsClass;
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
    private final LocalDate startDate = LocalDate.of(2024, 8, 14);
    private final LocalDate endDate = LocalDate.of(2024, 9, 14);

    //Тесты  приватных методов
    @Test
    void verifyThatNumberVacationDaysTest() {
        try {
            Method method = SalaryServiceImpl.class.getDeclaredMethod("verifyThatNumberVacationDaysNotNull", Integer.class, LocalDate.class,
                    LocalDate.class);
            method.setAccessible(true);
            Assertions.assertTrue((Boolean) method.invoke(salaryServiceImpl, 20000, null, null));

            InvocationTargetException invocationTargetExceptionSecond = Assertions.assertThrows(InvocationTargetException.class,
                    () -> method.invoke(salaryServiceImpl, 20000, null, endDate));
            Assertions.assertInstanceOf(IncompatibleParametersExсeption.class, invocationTargetExceptionSecond.getCause());

            InvocationTargetException invocationTargetExceptionThird = Assertions.assertThrows(InvocationTargetException.class,
                    () -> method.invoke(salaryServiceImpl, 20000, startDate, null));
            Assertions.assertInstanceOf(IncompatibleParametersExсeption.class, invocationTargetExceptionThird.getCause());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            log.error(e.getMessage() + ConstantsClass.WHITESPACE + e.getCause());
        }
    }

    @Test
    void verifyThatPeriodNotNullTest() {
        try {
            Method method = SalaryServiceImpl.class.getDeclaredMethod("verifyThatPeriodNotNull", Integer.class, LocalDate.class,
                    LocalDate.class);
            method.setAccessible(true);
            Assertions.assertTrue((Boolean) method.invoke(salaryServiceImpl, null, LocalDate.of(2024, 8, 14),
                    LocalDate.of(2024, 9, 14)));
            InvocationTargetException invocationTargetExceptionFirst = Assertions.assertThrows(InvocationTargetException.class,
                    () -> method.invoke(salaryServiceImpl, null, startDate, null));
            Assertions.assertInstanceOf(IncompatibleParametersExсeption.class, invocationTargetExceptionFirst.getCause());

            InvocationTargetException invocationTargetExceptionSecond = Assertions.assertThrows(InvocationTargetException.class,
                    () -> method.invoke(salaryServiceImpl, null, null, endDate));
            Assertions.assertInstanceOf(IncompatibleParametersExсeption.class, invocationTargetExceptionSecond.getCause());

            InvocationTargetException invocationTargetExceptionThird = Assertions.assertThrows(InvocationTargetException.class,
                    () -> method.invoke(salaryServiceImpl, null, null, null));
            Assertions.assertInstanceOf(IncompatibleParametersExсeption.class, invocationTargetExceptionThird.getCause());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            log.error(e.getMessage() + ConstantsClass.WHITESPACE + e.getCause());
        }
    }

    @Test
    void getCalculateSalaryTest() throws IncompatibleParametersExсeption {
        DayOffImpl dayOffLocal = new DayOffImpl();
        List<LocalDate> listWithLocalDates = dayOffLocal.getDatesBetween(startDate, endDate);
        Mockito.when(dayOff.getDatesBetween(startDate, endDate)).thenReturn(listWithLocalDates);

        List<LocalDate> listWithoutDayOff = dayOffLocal.getDatesWithoutUsualDayOff(listWithLocalDates);
        Mockito.when(dayOff.getDatesWithoutUsualDayOff(listWithLocalDates)).thenReturn(listWithoutDayOff);

        List<LocalDate> listWithoutFestiveDays = dayOffLocal.deleteFromListLocalDateFestiveDays(listWithoutDayOff);
        Mockito.when(dayOff.deleteFromListLocalDateFestiveDays(listWithoutDayOff)).thenReturn(listWithoutFestiveDays);

        Assertions.assertEquals(1656, salaryServiceImpl.getCalculateSalary(25000, null, startDate, endDate)
                .getAmountSalary());
        Assertions.assertEquals(1656,
                salaryServiceImpl.getCalculateSalary(25000, 24, null, null).getAmountSalary());
    }

}
