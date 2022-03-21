package service;

import model.InputData;
import model.TimePoint;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class TimePointServiceImpl implements TimePointService {

    public static final BigDecimal YEAR = BigDecimal.valueOf(12);


    @Override
    public TimePoint calculate(BigDecimal aRateNumber, InputData aInputData) {

        LocalDate date = calculateDate(aRateNumber, aInputData);
        BigDecimal year = calculateYear(aRateNumber);
        BigDecimal month = calculateMonth(aRateNumber);
        return new TimePoint(date, year, month);

    }

    private LocalDate calculateDate(BigDecimal aRateNumber, InputData aInputData) {
        return aInputData.getRepaymentStartDate().plus(aRateNumber.subtract(BigDecimal.ONE).intValue(),
                ChronoUnit.MONTHS);
    }

    private BigDecimal calculateYear(final BigDecimal aRateNumber) {
        return aRateNumber.divide(YEAR, RoundingMode.UP).max(BigDecimal.ONE);
    }

    private BigDecimal calculateMonth(final BigDecimal aRateNumber) {
        return BigDecimal.ZERO.equals(aRateNumber.remainder(YEAR)) ? YEAR : aRateNumber.remainder(YEAR);
    }

}
