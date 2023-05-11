package service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.InputData;
import model.Rate;
import model.Summary;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class MortageCalculationServiceImpl implements MortageCalculationService {

    private final RateCalculationService rateCalculationService;
    private final PrintingService printingService;
    private final SummaryService summaryService;


    @Override
    public void calculate(InputData aInputData) {
        printingService.printIntroInformation(aInputData);

        List<Rate> rates = rateCalculationService.calculate(aInputData);
        rates.forEach(element -> log.debug("Rate: [{}]", element));
        Summary summary = summaryService.calculateSummary(rates);

        printingService.printSummary(summary);
        printingService.printSchedule(rates, aInputData);
    }

}
