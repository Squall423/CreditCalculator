package service;

import model.InputData;
import model.Overpayment;
import model.Rate;
import model.Summary;
import service.exception.MortageException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PrintingServiceImpl implements PrintingService {

    @Override
    public void printIntroInformation(InputData aInputData) {
        StringBuilder msg = new StringBuilder(NEW_LINE);
        msg.append(MORTAGE_AMOUNT).append(aInputData.getAmount()).append(CURRENCY);
        msg.append(NEW_LINE);
        msg.append(MORTAGE_PERIOD).append(aInputData.getMonthsDuration()).append(MONTHS);
        msg.append(NEW_LINE);
        msg.append(INTEREST).append(aInputData.getInterestToDisplay()).append(PERCENT);
        msg.append(NEW_LINE);
        msg.append(OVERPAYMENT_START_MONTH).append(aInputData.getOverpaymentStartMonth()).append(BLANK).append(MONTH);


        Optional.of(aInputData.getOverpaymentSchema())
                .filter(schema -> schema.size() > 0)
                .ifPresent(schema -> logOverpayment(msg, aInputData.getOverpaymentSchema(), aInputData.getOverpaymentReduceWay()));

        logMessage(msg);
    }

    private void logOverpayment(final StringBuilder msg, final Map<Integer, BigDecimal> schema, final String reduceWay) {
        switch (reduceWay) {
            case Overpayment.REDUCE_PERIOD:
                msg.append(OVERPAYMENT_REDUCE_PERIOD);
                break;
            case Overpayment.REDUCE_RATE:
                msg.append(OVERPAYMENT_REDUCE_RATE);
                break;
            default:
                throw new MortageException("Case not handled");
        }
        msg.append(NEW_LINE);
        msg.append(OVERPAYMENT_FREQUENCY).append(NEW_LINE).append(prettyPrintOverpaymentSchema(schema));
    }
    private String prettyPrintOverpaymentSchema(Map<Integer, BigDecimal> schema) {
        StringBuilder schemaMsg = new StringBuilder();
        for (Map.Entry<Integer, BigDecimal> entry : schema.entrySet()) {
            schemaMsg.append(MONTH)
                    .append(entry.getKey())
                    .append(COMMA)
                    .append(AMOUNT)
                    .append(entry.getValue())
                    .append(CURRENCY)
                    .append(NEW_LINE);
        }
        return schemaMsg.toString();
    }

    @Override
    public void printSchedule(final List<Rate> aRates, final InputData inputData) {
        if (!inputData.isMortgagePrintPayoffsSchedule()) {
            return;
        }

        int index = 1;
        for (Rate rate : aRates) {
            if (rate.getRateNumber().remainder(BigDecimal.valueOf(inputData.getMortgageRateNumberToPrint())).equals(BigDecimal.ZERO)) {
                String logMessage = String.format(SCHEDULE_TABLE_FORMAT,
                        RATE_NUMBER, rate.getRateNumber(),
                        YEAR, rate.getTimePoint().getYear(),
                        MONTH, rate.getTimePoint().getMonth(),
                        DATE, rate.getTimePoint().getDate(),
                        RATE, rate.getRateAmounts().getRateAmount(),
                        INTEREST, rate.getRateAmounts().getInterestAmount(),
                        CAPITAL, rate.getRateAmounts().getCapitalAmount(),
                        OVERPAYMENT, rate.getRateAmounts().getOverpayment().getAmount(),
                        LEFT_AMOUNT, rate.getMortageResidual().getResidualAmount(),
                        LEFT_MONTHS, rate.getMortageResidual().getResidualDuration()
                );
                logMessage(logMessage);
                if (index % AmountsCalculationService.YEAR.intValue() == 0) {
                    logSeparator(SEPARATOR);
                }
                index++;
            }
        }
        logMessage(NEW_LINE);
    }

    @Override
    public void printSummary(final Summary aSummary) {
        StringBuilder msg = new StringBuilder();
        msg.append(INTEREST_SUM).append(aSummary.getInterestSum()).append(CURRENCY);
        msg.append(NEW_LINE);
        msg.append(OVERPAYMENT_PROVISION).append(aSummary.getOverpaymentProvisionsSum().setScale(2, RoundingMode.HALF_UP)).append(CURRENCY);
        msg.append(NEW_LINE);
        msg.append(LOSTS_SUM).append(aSummary.getTotalLostSum().setScale(2, RoundingMode.HALF_UP)).append(CURRENCY);
        msg.append(NEW_LINE);
        msg.append(CAPITAL_SUM).append(aSummary.getTotalCapital().setScale(2, RoundingMode.HALF_UP)).append(CURRENCY);
        msg.append(NEW_LINE);

        logMessage(msg);
    }

    @SuppressWarnings("SameParameterValue")
    private void logSeparator(StringBuilder sep) {
        logMessage(sep);
    }

    private void logMessage(StringBuilder message) {
        logMessage(message.toString());
    }

    private void logMessage(String message) {
        System.out.println(message);
    }
}
