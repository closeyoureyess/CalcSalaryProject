package com.petcalcsalary.CalcSalaryProject.others;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.Map;
import java.util.Set;

public class ConstantsClass {

    public static final String GET_METHOD = "Метод GET ";
    public static final String IS_CONTROLLER = "контроллер ";
    public static final Integer THIRTY_FIRST_DAY = Year.now().getValue();
    public static final Integer ONE_FLAG = 1;
    public static final Set<LocalDate> DAYOFF_MONTH_DAY = Set.of(LocalDate.of(2024, 1, 1),
            LocalDate.of(2024, 1, 2),
            LocalDate.of(2024, 1, 3),
            LocalDate.of(2024, 1, 4),
            LocalDate.of(2024, 1, 5),
            LocalDate.of(2024, 1, 6),
            LocalDate.of(2024, 1, 7),
            LocalDate.of(2024, 1, 8),
            // Праздники в феврале (23-25 февраля)
            LocalDate.of(2024, 2, 23),
            LocalDate.of(2024, 2, 24),
            LocalDate.of(2024, 2, 25),
            // Праздники в марте (8-10 марта)
            LocalDate.of(2024, 3, 8),
            LocalDate.of(2024, 3, 9),
            LocalDate.of(2024, 3, 10),
            // Праздники в апреле-мае (28 апреля - 1 мая)
            LocalDate.of(2024, 4, 28),
            LocalDate.of(2024, 4, 29),
            LocalDate.of(2024, 4, 30),
            LocalDate.of(2024, 5, 1),
            // Праздники в мае (9-12 мая)
            LocalDate.of(2024, 5, 9),
            LocalDate.of(2024, 5, 10),
            LocalDate.of(2024, 5, 11),
            LocalDate.of(2024, 5, 12),
            // День России
            LocalDate.of(2024, 6, 12),
            // Праздники в ноябре (3-4 ноября)
            LocalDate.of(2024, 11, 3),
            LocalDate.of(2024, 11, 4),
            // Декабрьские праздники (29-31 декабря)
            LocalDate.of(2024, 12, 29),
            LocalDate.of(2024, 12, 30),
            LocalDate.of(2024, 12, 31) );

}
