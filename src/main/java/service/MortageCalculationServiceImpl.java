package service;

import model.InputData;
import model.Rate;
import model.Summary;

import java.util.List;

public class MortageCalculationServiceImpl implements MortageCalculationService {


    private final PrintingService printingService;
    private final RateCalculationService rateCalculationService;
    private final SummaryService summaryService;

    public MortageCalculationServiceImpl(PrintingService aPrintingService,
                                         RateCalculationService aRateCalculationService, SummaryService aSummaryService) {
        printingService = aPrintingService;
        rateCalculationService = aRateCalculationService;
        summaryService = aSummaryService;
    }

    @Override
    public void calculate(InputData aInputData) {
        printingService.printInputDataInfo(aInputData);
        List<Rate> rates = rateCalculationService.calculate(aInputData);

        Summary summary = summaryService.calculate(rates);
        printingService.printSummary(summary);
        printingService.printInputDataInfo(rates);
    }

}
