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

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getYear() {
        return year;
    }

    public BigDecimal getMonth() {
        return month;
    }
}
