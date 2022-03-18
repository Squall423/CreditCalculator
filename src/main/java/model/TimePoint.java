package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TimePoint {

    private final LocalDate date;
    private final BigDecimal year;
    private final BigDecimal month;

    public TimePoint(LocalDate aDate, BigDecimal aYear, BigDecimal aMonth) {
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
