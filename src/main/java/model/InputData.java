package model;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Map;

public class InputData {

    private static final BigDecimal PERCENT = new BigDecimal("100");
    private LocalDate repaymentStartDate = LocalDate.of(2020, 12, 10);

    private BigDecimal amount = new BigDecimal("300000");
    private BigDecimal monthsDuration = BigDecimal.valueOf(180);
    private MortageType rateType = MortageType.CONSTANT;
    private BigDecimal wiborPercent = BigDecimal.valueOf(1.70);
    private BigDecimal marginPercent = BigDecimal.valueOf(1.6);
    private BigDecimal overpaymentStartMonth = BigDecimal.valueOf(1);
    private Map<Integer, BigDecimal> overpaymentSchema = Map.of(
            5, BigDecimal.valueOf(10000),
            6, BigDecimal.valueOf(10000),
            7, BigDecimal.valueOf(10000),
            8, BigDecimal.valueOf(10000)
    );

    private String overpaymentReduceWay = Overpayment.REDUCE_PERIOD;
    private BigDecimal overpaymentProvisionPercent = BigDecimal.valueOf(3);
    private BigDecimal overpaymentProvisionMonths = BigDecimal.valueOf(36);

    private boolean mortgagePrintPayoffsSchedule = true;
    private Integer mortgageRateNumberToPrint = 1;


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

    public InputData withMonthsDuration(BigDecimal monthsDuration) {
        this.monthsDuration = monthsDuration;
        return this;
    }

    public InputData withType(MortageType type) {
        this.rateType = type;
        return this;
    }

    public InputData withBankMarginPercent(BigDecimal bankMarginPercent) {
        this.marginPercent = bankMarginPercent;
        return this;
    }

    public InputData withOverpaymentProvisionPercent(BigDecimal overpaymentProvisionPercent) {
        this.overpaymentProvisionPercent = overpaymentProvisionPercent;
        return this;
    }

    public InputData withOverpaymentProvisionMonths(BigDecimal overpaymentProvisionMonths) {
        this.overpaymentProvisionMonths = overpaymentProvisionMonths;
        return this;
    }

    public InputData withOverpaymentStartMonth(BigDecimal overpaymentStartMonth) {
        this.overpaymentStartMonth = overpaymentStartMonth;
        return this;
    }


    public InputData withOverpaymentSchema(Map<Integer, BigDecimal> overpaymentSchema) {
        this.overpaymentSchema = overpaymentSchema;
        return this;
    }

    public InputData withOverpaymentReduceWay(String overpaymentReduceWay) {
        this.overpaymentReduceWay = overpaymentReduceWay;
        return this;
    }

    public InputData withMortgagePrintPayoffsSchedule(boolean mortgagePrintPayoffsSchedule) {
        this.mortgagePrintPayoffsSchedule = mortgagePrintPayoffsSchedule;
        return this;
    }

    public InputData withMortgageRateNumberToPrint(Integer mortgageRateNumberToPrint) {
        this.mortgageRateNumberToPrint = mortgageRateNumberToPrint;
        return this;
    }



    public LocalDate getRepaymentStartDate() {

        return repaymentStartDate;
    }

    public BigDecimal getWiborPercent() {
        return wiborPercent.divide(PERCENT, 4, RoundingMode.HALF_UP);
    }

    public BigDecimal getAmount() {

        return amount;
    }

    public BigDecimal getMonthsDuration() {

        return monthsDuration;
    }

    public MortageType getRateType() {

        return rateType;
    }


    public BigDecimal getOverpaymentStartMonth() {

        return overpaymentStartMonth;
    }


    public BigDecimal getInterestDisplay() {
        return wiborPercent.add(marginPercent);
    }

    public Map<Integer, BigDecimal> getOverpaymentSchema() {

        return overpaymentSchema;
    }

    public BigDecimal getMarginPercent() {

        return marginPercent.divide(PERCENT, 4, RoundingMode.HALF_UP);
    }

    public BigDecimal getOverpaymentProvisionPercent() {
        return overpaymentProvisionPercent.divide(PERCENT, 4, RoundingMode.HALF_UP);
    }

    public BigDecimal getOverpaymentProvisionMonths() {
        return overpaymentProvisionMonths;
    }


    public boolean isMortgagePrintPayoffsSchedule() {

        return mortgagePrintPayoffsSchedule;
    }

    public Integer getMortgageRateNumberToPrint() {

        return mortgageRateNumberToPrint;
    }

    public String getOverpaymentReduceWay() {

        return overpaymentReduceWay;
    }

    public BigDecimal getInterestPercent() {
        return getMarginPercent().add(getWiborPercent());
    }

    public BigDecimal getInterestToDisplay() {
        return wiborPercent.add(marginPercent);
    }
}
