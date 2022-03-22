package model;

import java.math.BigDecimal;

public class Rate {

    private final BigDecimal rateNumber;
    private final TimePoint timePoint;
    private final RateAmounts rateAmount;
    private final MortageResidual mortageResidual;
    private final MortageReference mortageReference;

   public Rate(BigDecimal aRateNumber, TimePoint aTimePoint, RateAmounts aRateAmounts,
               MortageResidual aMortageResidual, MortageReference aMortageReference) {

       rateNumber = aRateNumber;
        timePoint = aTimePoint;
        rateAmount = aRateAmounts;
        mortageResidual = aMortageResidual;
       mortageReference = aMortageReference;
   }

    public BigDecimal getRateNumber() {
        return rateNumber;
    }

    public TimePoint getTimePoint() {
        return timePoint;
    }

    public RateAmounts getRateAmounts() {
        return rateAmount;
    }

    public MortageResidual getMortageResidual() {
        return mortageResidual;
    }




}
