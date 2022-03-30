package model;

import java.math.BigDecimal;

public class Rate {

    private final BigDecimal rateNumber;
    private final TimePoint timePoint;
    private final RateAmounts rateAmounts;
    private final MortageResidual mortageResidual;
    private final MortageReference mortageReference;

    public Rate(final BigDecimal aRateNumber, final TimePoint aTimePoint,final RateAmounts aRateAmounts,
                final MortageResidual aMortageResidual,final MortageReference aMortageReference) {

       rateNumber = aRateNumber;
        timePoint = aTimePoint;
        rateAmounts = aRateAmounts;
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
        return rateAmounts;
    }


    public MortageResidual getMortageResidual() {
        return mortageResidual;
    }

    public MortageReference getMortageReference() {
        return mortageReference;
    }




}
