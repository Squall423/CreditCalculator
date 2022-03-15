package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class InputData {

    private LocalDate repaymentStartDate = LocalDate.of(2020, 1, 6);
    private BigDecimal wiborPercent = new BigDecimal("1,73");
    private BigDecimal amount = new BigDecimal("300000");
    private BigDecimal monthsDuration = BigDecimal.valueOf(188);
    private RateType rateType = RateType.CONSTANT;
    private BigDecimal bankMargin = new BigDecimal("1.9");

    LocalDate getRepaymentStartDate() {
        return repaymentStartDate;
    }

    BigDecimal getWiborPercent() {
        return wiborPercent;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getMonthsDuration() {
        return monthsDuration;
    }

    RateType getRateType() {
        return rateType;
    }

    BigDecimal getBankMargin() {
        return bankMargin;
    }

    public InputData withRepaymentStartDate(LocalDate repaymentStartDate) {
        this.repaymentStartDate = repaymentStartDate;
        return this;
    }

    public InputData withWiborPercent(BigDecimal wiborPercent) {
        this.wiborPercent = wiborPercent;
        return this;
    }

    public InputData withAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public InputData withMonthsDuratuon(BigDecimal monthsDuration) {
        this.monthsDuration = monthsDuration;
        return this;
    }

    public InputData withRateType(RateType rateType) {
        this.rateType = rateType;
        return this;
    }

    public InputData withBankMargin(BigDecimal bankMargin) {
        this.bankMargin = bankMargin;
        return this;
    }


}










