package service;

import model.InputData;
import model.Rate;

import java.util.List;

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

        printMessage(msg);
    }

    @Override
    public void printInputDataInfo(List<Rate> aRates) {
        String format = " %s %s " +
                "%s %s " +
                "%s %s " +
                "%s %s " +
                "%s %s " +
                "%s %s " +
                "%s %s " +
                "%s %s " +
                "%s %s";
        for (Rate rate : aRates) {
            String message = String.format(format,
                    RATE_NUMBER, rate.getRateNumber(),
                    DATE, rate.getTimePoint().getDate(),
                    YEAR, rate.getTimePoint().getYear(),
                    MONTH, rate.getTimePoint().getMonth(),
                    RATE, rate.getRateAmounts().getRateAmount(),
                    INTEREST_PAYMENT, rate.getRateAmounts().getInterestAmount(),
                    CAPITAL, rate.getRateAmounts().getCapitalAmount(),
                    LEFT, rate.getMortageResidual().getAmount(),
                    MONTHS, rate.getMortageResidual().getDuration()
            );
            System.out.println(message);
        }
    }

    private void printMessage(StringBuilder sb) {
        System.out.println(sb.toString());
    }
}
