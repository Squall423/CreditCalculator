package service;

import lombok.RequiredArgsConstructor;
import model.InputData;
import model.Rate;
import model.Summary;

import java.util.List;

@RequiredArgsConstructor
public class MortageCalculationServiceImpl implements MortageCalculationService {

    private final RateCalculationService rateCalculationService;
    private final PrintingService printingService;
    private final SummaryService summaryService;


    @Override
    public void calculate(InputData aInputData) {
        printingService.printIntroInformation(aInputData);

        List<Rate> rates = rateCalculationService.calculate(aInputData);
        Summary summary = summaryService.calculateSummary(rates);

        printingService.printSummary(summary);
        printingService.printSchedule(rates, aInputData);
    }

}
