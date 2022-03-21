package model;

import java.math.BigDecimal;

public class Summary {
    private final BigDecimal interestSum;


    public Summary(BigDecimal aInterestSum) {
        interestSum = aInterestSum;
    }

    public BigDecimal getInterestSum() {
        return interestSum;
    }
}
