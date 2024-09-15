package com.petcalcsalary.CalcSalaryProject.mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Интерфейс, редоставляющий методы для работы с выходными днями, праздничными днями и расчётами, связанными с календарем.
 * <p>
 * Этот интерфейс предоставляет функциональность для фильтрации выходных и праздничных дней из списка дат;
 * проверки, является ли дата праздничным днём, и расчёта количества недель в году.
 * </p>
 */
public interface DayOff {

    /**
     * Из указанного промежутка дат достаёт остальные даты, помещает в лист
     *
     * @param startDate Дата начала диапазона.
     * @param endDate Дата конца диапазона.
     * @return Список дат от startDate до endDate включительно.
     */
    List<LocalDate> getDatesBetween(LocalDate startDate, LocalDate endDate);

    /**
     * Удаляет из списка даты, которые являются праздничными днями.
     *
     * @param localDateList Список дат для фильтрации.
     * @return Новый список дат без праздничных дней.
     */
    List<LocalDate> deleteFromListLocalDateFestiveDays(List<LocalDate> localDateList);

    /**
     * Проверяет, является ли указанная дата праздничным днём.
     *
     * @param localDate Дата для проверки.
     * @return Optional, содержащий дату, если она не является праздничной, иначе пустой Optional.
     */
    Optional<LocalDate> checkLocalDateIsFestiveDays(LocalDate localDate);

    /**
     * Удаляет из списка дат все дни, которые являются выходными (суббота и воскресенье).
     *
     * @param localDateList Список дат, из которого нужно удалить выходные дни.
     * @return Список дат без выходных дней.
     */
    List<LocalDate> getDatesWithoutUsualDayOff(List<LocalDate> localDateList);

    /**
     * Сравнивает день недели с обычными выходными днями (суббота и воскресенье).
     *
     * @param valueDayOfWeek Числовое значение дня недели (6 = суббота, 7 = воскресенье).
     * @return true, если день недели является выходным (суббота или воскресенье), иначе false.
     */
    boolean compareLocalDateWithUsualDayOff(int valueDayOfWeek);

    /**
     * (в текущей версии не используется)
     * Возвращает количество недель в текущем календарном году.
     *
     * @return Количество недель в текущем году.
     */
    int getAmountWeeksInWeekYear();

    /**
     * (в текущей версии не используется)
     * Определяет, является ли текущий год високосным.
     *
     * @return true, если текущий год високосный, иначе false.
     */
    boolean leapYearOrNot();

}
