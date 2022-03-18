package service;

import model.InputData;
import model.Rate;

import java.util.List;

public class MortageCalculationServiceImpl implements MortageCalculationService {


    private final PrintingService printingService;
    private final RateCalculationService rateCalculationService;

    public MortageCalculationServiceImpl(PrintingService aPrintingService,
                                         RateCalculationService aRateCalculationService) {
        printingService = aPrintingService;
        rateCalculationService = aRateCalculationService;
    }

    @Override
    public void calculate(InputData aInputData) {
        printingService.printInputDataInfo(aInputData);
        List<Rate> calculate = rateCalculationService.calculate(aInputData);

    }

}
