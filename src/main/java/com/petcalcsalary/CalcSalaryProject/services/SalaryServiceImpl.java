package com.petcalcsalary.CalcSalaryProject.services;

import com.petcalcsalary.CalcSalaryProject.mapper.DayOff;
import com.petcalcsalary.CalcSalaryProject.mapper.DayOffImpl;
import com.petcalcsalary.CalcSalaryProject.dtos.SalaryInfoDto;
import com.petcalcsalary.CalcSalaryProject.others.exeptions.DescriptionExceptions;
import com.petcalcsalary.CalcSalaryProject.others.exeptions.IncompatibleParametersExсeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SalaryServiceImpl implements SalaryService {

    @Autowired
    private DayOff dayOff;

    /**
     * Рассчитывает отпускные на основе зарплаты за 12 месяцев и кол-ве дней отпуска
     * @param salaryTwelveMonth - общая зарплата за 12 месяцев
     * @param numberVacationDays - кол-во дней отпуска
     * @param startDate - дата начала отпуска
     * @param endDate - дата окончания отпуска
     * @return {@link SalaryInfoDto} - Объект, содержащий информацию о рассчитанной зарплате.
     * @throws IncompatibleParametersExсeption - Если переданы несовместимные параметры
     */
    @Override
    public SalaryInfoDto getCalculateSalary(Integer salaryTwelveMonth, Integer numberVacationDays, LocalDate startDate, LocalDate endDate) throws IncompatibleParametersExсeption {
        SalaryInfoDto localSalaryInfoDto = new SalaryInfoDto();
        if (verifyThatNumberVacationDaysNotNull(numberVacationDays, startDate, endDate)) {
            localSalaryInfoDto.setAmountVacationPay(computeAverageSalaryPerDay(salaryTwelveMonth) * numberVacationDays);
        }
        if (verifyThatPeriodNotNull(numberVacationDays, startDate, endDate)) {
            List<LocalDate> listWithoutFestiveAndDayOffDays = dayOff.deleteFromListLocalDateFestiveDays(
                    dayOff.getDatesWithoutUsualDayOff(
                            dayOff.getDatesBetween(startDate, endDate)
                    )
            );
            localSalaryInfoDto.setAmountVacationPay(
                    computeAverageSalaryPerDay(salaryTwelveMonth) * listWithoutFestiveAndDayOffDays.size()
            );
        }
        return localSalaryInfoDto;
    }

    /**
     * Проверяет корректность переданных параметров: что передано кол-во дней отпуска без промежуток дат
     *
     * @return {@code true} если передано кол-во дней отпуска
     *         {@code false} если передана иная информация
     * @throws IncompatibleParametersExсeption - Если передано кол-во дней и промежуток дат одновременно
     */
    private boolean verifyThatNumberVacationDaysNotNull(Integer numberVacationDays, LocalDate startDate, LocalDate endDate) throws IncompatibleParametersExсeption {
        if (numberVacationDays != null && (startDate == null && endDate == null)) {
            return true;
        } else if (numberVacationDays != null) {
            throw new IncompatibleParametersExсeption(DescriptionExceptions.GENERATION_ERROR.getTypeEquipmentEnum(),
                    new IncompatibleParametersExсeption(DescriptionExceptions.STATED_RANGE_AND_NUMBER_VACATION_DAYS.getTypeEquipmentEnum()));
        }
        return false;
    }

    /**
     * Проверяет корректность переданных параметров: что передан промежуток дат без кол-ва дней
     *
     * @return {@code true} если передан промежуток дат
     *         {@code false} если передана иная информация
     * @throws IncompatibleParametersExсeption - Если передан промежуток дат и кол-во дней одновременно
     */
    private boolean verifyThatPeriodNotNull(Integer numberVacationDays, LocalDate startDate, LocalDate endDate) throws IncompatibleParametersExсeption {
        if (numberVacationDays == null && (startDate != null && endDate != null)) {
            return true;
        } else if (numberVacationDays == null) {
            throw new IncompatibleParametersExсeption(DescriptionExceptions.GENERATION_ERROR.getTypeEquipmentEnum(),
                    new IncompatibleParametersExсeption(DescriptionExceptions.RANGE_AND_NUMBER_VACATION_DAYS_IS_NOT_NULL.getTypeEquipmentEnum()));
        }
        return false;
    }


    /**
     * Метод, считающий зарплату за 1 день рабочего месяца
     *
     * @param salaryTwelveMonth - зарплата за 12 месяцев
     * @return {@code Integer} - зарплата за 1 день рабочего месяца
     */
    private Integer computeAverageSalaryPerDay(Integer salaryTwelveMonth) {
        return (salaryTwelveMonth / 12) / 30;
    }
}
