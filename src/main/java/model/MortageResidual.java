package model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MortageResidual {
    private final BigDecimal amount;
    private final BigDecimal duration;

    public MortageResidual(BigDecimal aAmount, BigDecimal aDuration) {
        amount = aAmount;
        duration = aDuration;
    }

    public BigDecimal getDuration() {
        return duration;
    }

    public BigDecimal getAmount() {
        return amount.setScale(2, RoundingMode.HALF_UP);
    }


}
