package service;

import model.InputData;
import model.Rate;
import model.TimePoint;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class TimePointCalculationServiceImpl implements TimePointCalculationService {
    @Override
    public TimePoint calculate(final BigDecimal aRateNumber, final InputData aInputData) {
        BigDecimal year = calculateYear(aRateNumber);
        BigDecimal month = calculateMonth(aRateNumber);
        LocalDate date =  aInputData.getRepaymentStartDate();
        return new TimePoint( year, month, date);
    }
    public TimePoint calculate(BigDecimal aRateNumber, Rate aPreviousRate) {
        BigDecimal year = calculateYear(aRateNumber);
        BigDecimal month = calculateMonth(aRateNumber);
        LocalDate date = aPreviousRate.getTimePoint().getDate().plus(1, ChronoUnit.MONTHS);
        return new TimePoint(year, month, date);
    }


    private BigDecimal calculateYear(final BigDecimal aRateNumber) {
        return aRateNumber.divide(AmountsCalculationService.YEAR, RoundingMode.UP).max(BigDecimal.ONE);
    }

    private BigDecimal calculateMonth(final BigDecimal aRateNumber) {
        return BigDecimal.ZERO.equals(aRateNumber.remainder(AmountsCalculationService.YEAR))
                ? AmountsCalculationService.YEAR
                : aRateNumber.remainder(AmountsCalculationService.YEAR);
    }

}
