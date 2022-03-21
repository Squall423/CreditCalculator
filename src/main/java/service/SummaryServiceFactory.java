package service;

import model.Rate;
import model.Summary;

import java.math.BigDecimal;
import java.util.List;

public class SummaryServiceFactory {

    public static SummaryService create() {
        return rates -> {
            BigDecimal interestSum = calculateInterestSum(rates);
            return new Summary(interestSum);
        };
    }

    private static BigDecimal calculateInterestSum(List<Rate> aRates) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Rate rate : aRates) {
            sum = sum.add(rate.getRateAmounts().getInterestAmount());
        }
        return sum;
    }
}
