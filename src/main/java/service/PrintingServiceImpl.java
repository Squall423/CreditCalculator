package service;

import model.InputData;

public class PrintingServiceImpl implements PrintingService {

    @Override
    public void printInputDataInfo(InputData aInputData) {
        StringBuilder msg = new StringBuilder(NEW_LINE);
        msg.append(MORTAGE_AMOUNT).append(aInputData.getAmount()).append(CURRENCY);
        msg.append(NEW_LINE);
        msg.append(MORTAGE_PERIOD).append(aInputData.getMonthsDuration()).append(MONTHS);
        msg.append(NEW_LINE);
        msg.append(INTEREST).append(aInputData.getInterestDisplay()).append(PERCENT);
        msg.append(NEW_LINE);

        printMessage(msg);
    }

    private void printMessage(StringBuilder sb) {
        System.out.println(sb.toString());
    }
}
