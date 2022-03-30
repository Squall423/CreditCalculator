package service;

import model.InputData;
import model.Rate;
import model.Summary;

import java.util.List;

public class MortageCalculationServiceImpl implements MortageCalculationService {


    private final RateCalculationService rateCalculationService;
    private final PrintingService printingService;
    private final SummaryService summaryService;

    public MortageCalculationServiceImpl(
        final RateCalculationService rateCalculationService,
        final PrintingService printingService,
        final SummaryService summaryService
    ) {
        this.rateCalculationService = rateCalculationService;
        this.printingService = printingService;
        this.summaryService = summaryService;
    }

    @Override
    public void calculate(InputData aInputData) {
        printingService.printIntroInformation(aInputData);

        List<Rate> rates = rateCalculationService.calculate(aInputData);
        Summary summary = summaryService.calculateSummary(rates);

        printingService.printSummary(summary);
        printingService.printSchedule(rates, aInputData);
    }

}
