package service;

import model.Rate;
import model.RateAmounts;
import model.Summary;

import java.math.BigDecimal;
import java.util.List;

public class SummaryServiceFactory {

    public static SummaryService create() {
        return rates -> {
            BigDecimal interestSum = calculate(rates, rate -> rate.getRateAmounts().getInterestAmount());
            BigDecimal overpaymentProvisionSum = calculate(rates, rate -> rate.getRateAmounts().getOverpayment().getProvisionAmount());
            BigDecimal totalLostSum = interestSum.add(overpaymentProvisionSum);
            BigDecimal totalCapital = calculate(rates, rate -> totalCapital(rate.getRateAmounts()));
            return new Summary(interestSum, overpaymentProvisionSum, totalLostSum, totalCapital);
        };
    }

    private static BigDecimal totalCapital(final RateAmounts aRateAmounts) {
        return aRateAmounts.getCapitalAmount().add(aRateAmounts.getOverpayment().getAmount());
    }

    private static BigDecimal calculate(final List<Rate> aRates, Function function) {
        BigDecimal sum = BigDecimal.ZERO;

        for (Rate rate : aRates) {
            sum = sum.add(function.calculate(rate));
        }
        return sum;
    }
}
