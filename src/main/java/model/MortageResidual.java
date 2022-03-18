package model;

import java.math.BigDecimal;

public class MortageResidual {
    private final BigDecimal amount;
    private final BigDecimal duration;

    MortageResidual(BigDecimal aAmount, BigDecimal aDuration) {
        amount = aAmount;
        duration = aDuration;
    }

    BigDecimal getDuration() {
        return duration;
    }

    BigDecimal getAmount() {
        return amount;
    }
}
