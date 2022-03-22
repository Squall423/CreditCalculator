package service;

import model.InputData;
import model.Rate;
import model.Summary;

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
        String format = " %2s %1s | " +
                "%2s %1s | " +
                "%2s %1s | " +
                "%2s %1s | " +
                "%2s %4s | " +
                "%2s %4s | " +
                "%2s %4s | " +
                "%2s %4s | " +
                "%2s %4s | ";
        for (Rate rate : aRates) {
            String message = String.format(format,
                    RATE_NUMBER, rate.getRateNumber(),
                    DATE, rate.getTimePoint().getDate(),
                    YEAR, rate.getTimePoint().getYear(),
                    MONTH, rate.getTimePoint().getMonth(),
                    RATE, rate.getRateAmounts().getRateAmount(),
                    INTEREST_PAYMENT, rate.getRateAmounts().getInterestAmount(),
                    CAPITAL, rate.getRateAmounts().getCapitalAmount(),
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

        printMessage(msg);
    }
}
