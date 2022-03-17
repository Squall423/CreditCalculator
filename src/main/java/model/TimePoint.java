package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TimePoint {

    private LocalDate date;
    private BigDecimal year;
    private BigDecimal month;

    TimePoint(LocalDate aDate, BigDecimal aYear, BigDecimal aMonth) {
        date = aDate;
        year = aYear;
        month = aMonth;
    }

    LocalDate getDate() {
        return date;
    }

    BigDecimal getYear() {
        return year;
    }

    BigDecimal getMonth() {
        return month;
    }
}
