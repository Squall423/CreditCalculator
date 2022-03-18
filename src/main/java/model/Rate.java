package model;

import java.math.BigDecimal;

public class Rate {

    private final BigDecimal rateNumber;
    private final TimePoint timePoint;
    private final RateAmounts rateAmount;
    private final MortageResidual mortageResidual;

   public Rate(BigDecimal aRateNumber, TimePoint aTimePoint, RateAmounts aRateAmount,
               MortageResidual aMortageResidual) {
        rateNumber = aRateNumber;
        timePoint = aTimePoint;
        rateAmount = aRateAmount;
        mortageResidual = aMortageResidual;
    }

    BigDecimal getRateNumber() {
        return rateNumber;
    }

    TimePoint getTimePoint() {
        return timePoint;
    }

    RateAmounts getRateAmount() {
        return rateAmount;
    }

    MortageResidual getMortageResidual() {
        return mortageResidual;
    }
}
