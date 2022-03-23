package model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RateAmounts {

    private final BigDecimal rateAmount;
    private final BigDecimal interestAmount;
    private final BigDecimal capitalAmount;
    private final Overpayment overpayment;

    public RateAmounts(BigDecimal aRateAmount, BigDecimal aInterestAmount, BigDecimal aCapitalAmount,
                       Overpayment aOverpayment) {
        rateAmount = aRateAmount;
        interestAmount = aInterestAmount;
        capitalAmount = aCapitalAmount;
        overpayment = aOverpayment;
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

    public Overpayment getOverpayment() {
        return overpayment;
    }


}
