package model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MortageResidual {
    private final BigDecimal residualAmount;
    private final BigDecimal residualDuration;

    public MortageResidual(BigDecimal aResidualAmount, BigDecimal aResidualDuration) {
        residualAmount = aResidualAmount;
        residualDuration = aResidualDuration;
    }

    public BigDecimal getResidualAmount() {
        return residualAmount;
    }

    public BigDecimal getResidualDuration() {
        return residualDuration;
    }


}
