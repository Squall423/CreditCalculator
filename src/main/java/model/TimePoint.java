package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TimePoint {

    private final BigDecimal year;
    private final BigDecimal month;
    private final LocalDate date;

    public TimePoint(final BigDecimal aYear,final BigDecimal aMonth,final  LocalDate aDate ) {
        year = aYear;
        month = aMonth;
        date = aDate;
    }

    public BigDecimal getYear() {
        return year;
    }

    public BigDecimal getMonth() {
        return month;
    }

    public LocalDate getDate() {
        return date;
    }
}
