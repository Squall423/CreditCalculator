package service;

import model.InputData;
import model.Rate;
import model.Summary;

import java.util.List;

import static java.lang.String.valueOf;


public interface PrintingService {

    String SCHEDULE_TABLE_FORMAT =
            "%-4s %3s |" +
                    "%-4s %3s |" +
                    "%-7s %3s |" +
                    "%-7s %3s |" +
                    "%-4s %10s |" +
                    "%-7s %10s |" +
                    "%-7s %10s |" +
                    "%-7s %10s |" +
                    "%-8s %10s |" +
                    "%-8s %10s |";
    StringBuilder SEPARATOR = createSeparator('-', 180);

    String INTEREST_SUM = " SUMA ODSETEK: ";
    String OVERPAYMENT_PROVISION = " PROWIZJA ZA NADPŁATY ";

    String OVERPAYMENT_REDUCE_PERIOD = " NADPŁATA, SKRÓCENIE OKRESU ";
    String OVERPAYMENT_REDUCE_RATE = " NADPŁATA, ZMNIEJSZENIE RATY ";
    String OVERPAYMENT_FREQUENCY = " SCHEMAT DOKONYWANIA NADPŁAT: ";
    String OVERPAYMENT_START_MONTH = "MIESIAC ROZPOCZECIA NADPLAT: ";

    String LOSTS_SUM = " SUMA STRAT: ";
    String CAPITAL_SUM = "SUMA KAPITALU: ";
    String RATE_NUMBER = " NR: ";
    String YEAR = "ROK: ";
    String MONTH = " MIESIĄC: ";
    String AMOUNT = "KWOTA: ";
    String DATE = " DATA: ";
    String MONTHS = " MIESIĘCY ";
    String RATE = " RATA: ";
    String INTEREST = " ODSETKI: ";
    String OVERPAYMENT = " NADPŁATA: ";
    String CAPITAL = " KAPITAŁ: ";
    String LEFT_AMOUNT = " POZOSTAŁA KWOTA: ";
    String LEFT_MONTHS = " POZOSTAŁO MIESIĘCY: ";
    String MORTAGE_AMOUNT = " KWOTA KREDYTU: ";
    String MORTAGE_PERIOD = " OKRES KREDYTOBRANIA: ";
    String BLANK = " ";

    String CURRENCY = " Zł ";
    String NEW_LINE = "\n";
    String PERCENT = "% ";
    String COMMA = ", ";

    @SuppressWarnings("SameParameterValue")
    private static StringBuilder createSeparator(char sign, int length) {
        StringBuilder sep = new StringBuilder();
        sep.append(String.valueOf(sign).repeat(Math.max(0, length)));
        return sep;
    }

    void printIntroInformation(InputData inputData);

    void printSchedule(List<Rate> rates, final InputData inputData);

    void printSummary(Summary summaryNoOverpayment);
}
