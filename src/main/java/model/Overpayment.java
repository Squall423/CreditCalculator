package model;

import java.math.BigDecimal;

public class Overpayment {

    public static final String REDUCE_RATE = "REDUCE_RATE";
    public static final String REDUCE_PERIOD = "REDUCE_PERIOD";

    private final BigDecimal amount;
    private final BigDecimal provisionAmount;

    public Overpayment(BigDecimal aAmount, BigDecimal aProvisionAmount) {
        amount = aAmount;
        provisionAmount = aProvisionAmount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getProvisionAmount() {
        return provisionAmount;
    }
}
