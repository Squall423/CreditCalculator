package model;

import java.math.RoundingMode;

public class RateAmounts {

    private final java.math.BigDecimal rateAmount;
    private final java.math.BigDecimal interestAmount;
    private final java.math.BigDecimal capitalAmount;
    private final Overpayment overpayment;

    public RateAmounts(java.math.BigDecimal aRateAmount, java.math.BigDecimal aInterestAmount, java.math.BigDecimal aCapitalAmount,
                       Overpayment aOverpayment) {
        rateAmount = aRateAmount;
        interestAmount = aInterestAmount;
        capitalAmount = aCapitalAmount;
        overpayment = aOverpayment;
    }

    public java.math.BigDecimal getRateAmount() {
        return rateAmount.setScale(2, RoundingMode.HALF_UP);
    }

    public java.math.BigDecimal getInterestAmount() {
        return interestAmount.setScale(2, RoundingMode.HALF_UP);
    }

    public java.math.BigDecimal getCapitalAmount() {
        return capitalAmount.setScale(2, RoundingMode.HALF_UP);
    }

    public java.math.BigDecimal getOverpayment() {
        return overpayment.setScale(2,RoundingMode.HALF_UP);
    }


}
