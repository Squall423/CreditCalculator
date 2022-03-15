package service;

import model.InputData;

public interface PrintingService {

    String INTEREST_SUM = "SUMA ODSETEK: ";
    String RATE_NUMBER = "NR: ";
    String YEAR = "ROK: ";
    String MONTH = "MIESIĄC: ";
    String DATE = "DATA: ";
    String MONTHS = "MIESIĘCY: ";
    String RATE = "RATA: ";
    String INTEREST = "ODSETKI: ";
    String CAPITAL = "KAPITAŁ: ";
    String LEFT = "POZOSTAŁO: ";
    String MORTAGE_AMOUNT = "KWOTA KREDYTU: ";
    String MORTAGE_PERIOD = "OKRES KREDYTOBRANIA: ";

    String CURRENCY = "Zł";
    String NEW_LINE = "\n";
    String PERCENT = "%";

    void printInputDataInfo(final InputData inputData);
}
