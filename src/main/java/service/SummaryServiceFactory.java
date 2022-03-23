package service;

import model.Rate;
import model.Summary;

import java.math.BigDecimal;
import java.util.List;

public class SummaryServiceFactory {

    public static SummaryService create() {
        return rates -> {
            BigDecimal interestSum = calculate(rates,
                    rate -> rate.getRateAmounts().getInterestAmount());
            BigDecimal provisions = calculate(rates,
                    rate -> rate.getRateAmounts().getOverpayment().getProvisionAmount());
            BigDecimal totalLosts = interestSum.add(provisions);
            return new Summary(interestSum, provisions, totalLosts);
        };
    }

    private static BigDecimal calculate(List<Rate> aRates, Function function) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Rate rate : aRates) {
            sum = sum.add(function.calculate(rate));
        }
        return sum;
    }
}
