package model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RateAmounts {

    private final BigDecimal rateAmount;
    private final BigDecimal interestAmount;
    private final BigDecimal capitalAmount;

    public RateAmounts(BigDecimal aRateAmount, BigDecimal aInterestAmount, BigDecimal aCapitalAmount) {
        rateAmount = aRateAmount;
        interestAmount = aInterestAmount;
        capitalAmount = aCapitalAmount;
    }

    public BigDecimal getRateAmount() {
        return rateAmount.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getInterestAmount() {
        return interestAmount.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getCapitalAmount() {
        return capitalAmount.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
        return "RateAmounts{" +
                "rateAmount=" + rateAmount +
                ", interestAmount=" + interestAmount +
                ", capitalAmount=" + capitalAmount +
                '}';
    }
}
