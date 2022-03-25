package service;

import model.InputData;
import model.Overpayment;
import model.Rate;
import model.Summary;
import service.exception.MortageException;

import java.util.List;
import java.util.Optional;

public class PrintingServiceImpl implements PrintingService {

    @Override
    public void printInputDataInfo(InputData aInputData) {
        StringBuilder msg = new StringBuilder(NEW_LINE);
        msg.append(MORTAGE_AMOUNT).append(aInputData.getAmount()).append(CURRENCY);
        msg.append(NEW_LINE);
        msg.append(MORTAGE_PERIOD).append(aInputData.getMonthsDuration()).append(MONTHS);
        msg.append(NEW_LINE);
        msg.append(INTEREST_PAYMENT).append(aInputData.getInterestDisplay()).append(PERCENT);
        msg.append(NEW_LINE);

        Optional.ofNullable(aInputData.getOverpaymentSchema())
                .filter(schema -> schema.size() > 0)
                .ifPresent(schema -> logOverpayment(msg, aInputData));

        printMessage(msg);
    }

    private void logOverpayment(StringBuilder msg, InputData aInputData) {
        switch (aInputData.getOverpaymentReduceWay()) {
            case Overpayment.REDUCE_PERIOD:
                msg.append(OVERPAYMENT_REDUCE_PERIOD);
                break;
            case Overpayment.REDUCE_RATE:
                msg.append(OVERPAYMENT_REDUCE_RATE);
                break;
            default:
                throw new MortageException();
        }
        msg.append(NEW_LINE);
        msg.append(OVERPAYMENT_FREQUENCY).append(aInputData.getOverpaymentSchema());
        msg.append(NEW_LINE);
    }

    @Override
    public void printInputDataInfo(List<Rate> aRates) {
        String format = " %2s %2s | " +
                "%2s %2s | " +
                "%2s %2s | " +
                "%2s %2s | " +
                "%3s %4s | " +
                "%3s %4s | " +
                "%3s %4s | " +
                "%4s %6s | " +
                "%3s %4s | " +
                "%3s %4s | ";
        for (Rate rate : aRates) {
            String message = String.format(format,
                    RATE_NUMBER, rate.getRateNumber(),
                    DATE, rate.getTimePoint().getDate(),
                    YEAR, rate.getTimePoint().getYear(),
                    MONTH, rate.getTimePoint().getMonth(),
                    RATE, rate.getRateAmounts().getRateAmount(),
                    INTEREST_PAYMENT, rate.getRateAmounts().getInterestAmount(),
                    CAPITAL, rate.getRateAmounts().getCapitalAmount(),
                    OVERPAYMENT,  rate.getRateAmounts().getOverpayment().getAmount(),
                    LEFT_AMOUNT, rate.getMortageResidual().getAmount(),
                    LEFT_MONTHS, rate.getMortageResidual().getDuration()
            );
            System.out.println(message);
            if (rate.getRateNumber().intValue() % 12 == 0) {
                System.out.println();
            }
        }
    }

    private void printMessage(StringBuilder sb) {
        System.out.println(sb.toString());
    }

    @Override
    public void printSummary(Summary aSummary) {
        StringBuilder msg = new StringBuilder(NEW_LINE);
        msg.append(INTEREST_SUM).append(aSummary.getInterestSum()).append(CURRENCY);
        msg.append(NEW_LINE);
        msg.append(OVERPAYMENT_PROVISION).append(aSummary.getOverpaymentProvisions()).append(CURRENCY);
        msg.append(NEW_LINE);
        msg.append(LOSTS_SUM).append(aSummary.getTotalLosts()).append(CURRENCY);
        msg.append(NEW_LINE);

        printMessage(msg);
    }
}
