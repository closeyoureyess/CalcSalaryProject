package com.petcalcsalary.CalcSalaryProject.mapper;

import com.petcalcsalary.CalcSalaryProject.others.ConstantsClass;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Реализация интерфейса {@link DayOff}, предоставляющая методы для работы с выходными днями,
 * праздничными днями и расчётами, связанными с календарем.
 * <p>
 * Этот класс предоставляет функциональность для фильтрации выходных и праздничных дней
 * из списка дат, проверки, является ли дата праздничным днём, и расчёта количества недель в году.
 * </p>
 */
@Component
public class DayOffImpl implements DayOff {


    /**
     * Удаляет из списка даты, которые являются обычными выходными днями (субботами и воскресеньями).
     *
     * @param localDateList Список дат для фильтрации.
     * @return Новый список дат без обычных выходных дней.
     */
    @Override
    public List<LocalDate> getDatesWithoutUsualDayOff(List<LocalDate> localDateList) {
        List<LocalDate> localDateListWithoutUsualDayOff = new LinkedList<>();
        for (LocalDate ld : localDateList) {
            if(compareLocalDateWithUsualDayOff(ld.getDayOfWeek().getValue())){
                continue;
            }
            localDateListWithoutUsualDayOff.add(ld);
        }
        return localDateListWithoutUsualDayOff;
    }

    /**
     * Сравнивает день недели с обычными выходными днями (суббота и воскресенье).
     *
     * @param valueDayOfWeek Числовое значение дня недели (6 = суббота, 7 = воскресенье).
     * @return true, если день недели является выходным (суббота или воскресенье), иначе false.
     */
    @Override
    public boolean compareLocalDateWithUsualDayOff(int valueDayOfWeek){
        int saturday = 6; // because Calendar.SATURDAY == 7
        int sunday = 7; // Calendar.SUNDAY == 1
        if (valueDayOfWeek == saturday) {
            return true;
        } else if (valueDayOfWeek == sunday) {
            return true;
        }
        return false;
    }

    /**
     * Проверяет, является ли указанная дата праздничным днём.
     *
     * @param localDate Дата для проверки.
     * @return Optional, содержащий дату, если она не является праздничной, иначе пустой Optional.
     */
    @Override
    public Optional<LocalDate> checkLocalDateIsFestiveDays(LocalDate localDate) {
        for (LocalDate ld : ConstantsClass.DAYOFF_MONTH_DAY) {
            if (localDate.equals(ld)) {
                return Optional.empty();
            }
        }
        return Optional.of(localDate);
    }

    /**
     * Удаляет из списка даты, которые являются праздничными днями.
     *
     * @param localDateList Список дат для фильтрации.
     * @return Новый список дат без праздничных дней.
     */
    @Override
    public List<LocalDate> deleteFromListLocalDateFestiveDays(List<LocalDate> localDateList) {
        List<LocalDate> localDateListWithoutFestiveDays = new LinkedList<>();
        for (LocalDate i : localDateList) {
            Optional<LocalDate> optionalLocalDate = checkLocalDateIsFestiveDays(i);
            if (optionalLocalDate.isPresent()){
                localDateListWithoutFestiveDays.add(optionalLocalDate.get());
            }
        }
        return localDateListWithoutFestiveDays;
    }

    /**
     * (в текущей версии не используется)
     * Определяет, является ли текущий год високосным.
     *
     * @return true, если текущий год високосный, иначе false.
     */
    @Override
    public boolean leapYearOrNot() {
        LocalDate localDateNow = LocalDate.now();
        boolean leapYear = localDateNow.isLeapYear();
        if (leapYear) {
            return true;
        }
        return false;
    }

    /**
     * (в текущей версии не используется)
     * Возвращает количество недель в текущем календарном году.
     *
     * @return Количество недель в текущем году.
     */
    @Override
    public int getAmountWeeksInWeekYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Year.now().getValue());
        calendar.set(Calendar.MONTH, Month.DECEMBER.getValue());
        calendar.set(Calendar.DAY_OF_MONTH, ConstantsClass.THIRTY_FIRST_DAY);
        return calendar.getWeeksInWeekYear();
    }

    /**
     * Из указанного промежутка дат достаёт остальные даты, помещает в лист
     *
     * @param startDate Дата начала диапазона.
     * @param endDate Дата конца диапазона.
     * @return Список дат от startDate до endDate включительно.
     */
    @Override
    public List<LocalDate> getDatesBetween(LocalDate startDate, LocalDate endDate) {
        List<LocalDate> localDates = new LinkedList<>();
        LocalDate localDate = startDate;
        while (!localDate.isAfter(endDate)) {
            localDates.add(localDate);
            localDate = localDate.plusDays(ConstantsClass.ONE_FLAG);
        }
        return localDates;
    }
}
