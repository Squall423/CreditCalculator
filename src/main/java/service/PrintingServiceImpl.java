package service;

import model.InputData;

public class PrintingServiceImpl implements PrintingService {

    @Override
    public void printInputDataInfo(InputData inputData) {
        StringBuilder msg = new StringBuilder(NEW_LINE);
        msg.append(MORTAGE_AMOUNT).append(inputData.getAmount()).append(CURRENCY);
        msg.append(NEW_LINE);
        msg.append(MORTAGE_PERIOD).append(inputData.getMonthsDuration()).append(MONTHS);
    }
}
