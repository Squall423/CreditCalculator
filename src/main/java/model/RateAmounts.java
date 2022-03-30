package model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RateAmounts {

    private final BigDecimal rateAmount;
    private final BigDecimal interestAmount;
    private final BigDecimal capitalAmount;
    private final Overpayment overpayment;

    public RateAmounts(final BigDecimal aRateAmount, final BigDecimal aInterestAmount,final BigDecimal aCapitalAmount,
                      final Overpayment aOverpayment) {
        rateAmount = aRateAmount;
        interestAmount = aInterestAmount;
        capitalAmount = aCapitalAmount;
        overpayment = aOverpayment;
    }

    public BigDecimal getRateAmount() {
        return rateAmount;
    }

    public BigDecimal getInterestAmount() {
        return interestAmount;
    }

    public BigDecimal getCapitalAmount() {
        return capitalAmount;
    }

    public Overpayment getOverpayment() {
        return overpayment;
    }


}
