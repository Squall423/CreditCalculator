package model;

import java.math.BigDecimal;

public class Summary {
    private final BigDecimal interestSum;

    private final BigDecimal overpaymentProvisions;

    private final BigDecimal totalLosts;


    public Summary(BigDecimal aInterestSum, BigDecimal aOverpaymentProvision, BigDecimal aTotalLosts) {
        interestSum = aInterestSum;
        overpaymentProvisions = aOverpaymentProvision;
        totalLosts = aTotalLosts;
    }

    public BigDecimal getInterestSum() {
        return interestSum;
    }

    public BigDecimal getOverpaymentProvisions() {
        return overpaymentProvisions;
    }

    public BigDecimal getTotalLosts() {
        return totalLosts;
    }
}
