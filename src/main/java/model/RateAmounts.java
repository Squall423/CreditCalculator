package model;

import java.math.BigDecimal;

public class RateAmounts {

    private final BigDecimal rateAmount;
    private final BigDecimal interestAmount;
    private final BigDecimal capitalAmount;

    public RateAmounts(BigDecimal aRateAmount, BigDecimal aInterestAmount, BigDecimal aCapitalAmount) {
        rateAmount = aRateAmount;
        interestAmount = aInterestAmount;
        capitalAmount = aCapitalAmount;
    }

    BigDecimal getRateAmount() {
        return rateAmount;
    }

    BigDecimal getInterestAmount() {
        return interestAmount;
    }

    BigDecimal getCapitalAmount() {
        return capitalAmount;
    }
}
