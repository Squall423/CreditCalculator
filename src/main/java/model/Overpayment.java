package model;

public class Overpayment {

    public static final String REDUCE_RATE = "REDUCE_RATE";
    public static final String REDUCE_PERIOD = "REDUCE_PERIOD";

    private final java.math.BigDecimal amount;
    private final java.math.BigDecimal provisionAmount;

    public Overpayment(java.math.BigDecimal aAmount, java.math.BigDecimal aProvisionAmount) {
        amount = aAmount;
        provisionAmount = aProvisionAmount;
    }

    public java.math.BigDecimal getAmount() {
        return amount;
    }

    public java.math.BigDecimal getProvisionAmount() {
        return provisionAmount;
    }
}
