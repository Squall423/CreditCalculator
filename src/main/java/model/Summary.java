package model;

import java.math.BigDecimal;

public class Summary {
    private final BigDecimal interestSum;

    private final BigDecimal overpaymentProvisionsSum;

    private final BigDecimal totalLostSum;
    private final BigDecimal totalCapital;


    public Summary(final BigDecimal aInterestSum,final BigDecimal aOverpaymentProvisionSum,final BigDecimal aTotalLostSum,
                   final BigDecimal aTotalCapital) {
        interestSum = aInterestSum;
        overpaymentProvisionsSum = aOverpaymentProvisionSum;
        totalLostSum = aTotalLostSum;
        totalCapital = aTotalCapital;
    }

    public BigDecimal getInterestSum() {
        return interestSum;
    }

    public BigDecimal getOverpaymentProvisionsSum() {
        return overpaymentProvisionsSum;
    }

    public BigDecimal getTotalLostSum() {
        return totalLostSum;
    }

    public BigDecimal getTotalCapital() {
        return totalCapital;
    }
}

