package service;

import model.*;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class RateCalculationServiceImpl implements RateCalculationService {

    private final TimePointService timepointService;
    private final AmountsCalculationService amountsCalculationService;
    private final ResidualCalculationService residualCalculationService;

    public RateCalculationServiceImpl(TimePointService aTimepointService,
                                      AmountsCalculationService aAmountsCalculationService,
                                      ResidualCalculationService aResidualCalculationService) {

        timepointService = aTimepointService;
        amountsCalculationService = aAmountsCalculationService;
        residualCalculationService = aResidualCalculationService;
    }

    @Override
    public List<Rate> calculate(InputData aInputData) {
        List<Rate> rates = new LinkedList<>();
        BigDecimal rateNumber = BigDecimal.ONE;

        Rate firstRate = calculateRate(rateNumber, aInputData);
        rates.add(firstRate);

        Rate previousRate = firstRate;

        for (BigDecimal index = rateNumber.add(BigDecimal.ONE);
             index.compareTo(aInputData.getMonthsDuration()) <= 0;
             index = index.add(BigDecimal.ONE)) {
            Rate nextRate = calculateRate(index, aInputData, previousRate);
            rates.add(nextRate);
            previousRate = nextRate;
        }
        return rates;
    }

    private Rate calculateRate(BigDecimal aRateNumber, InputData aInputData) {
        TimePoint timePoint = timepointService.calculate(aRateNumber, aInputData);
        RateAmounts rateAmounts = amountsCalculationService.calculate(aInputData);
        MortageResidual mortageResidual = residualCalculationService.calculate();

        return new Rate(aRateNumber, timePoint, rateAmounts, mortageResidual);

    }

    private Rate calculateRate(BigDecimal aRateNumber, InputData aInputData, Rate aPreviousRate) {
        TimePoint timePoint = timepointService.calculate(aRateNumber, aInputData);
        RateAmounts rateAmounts = amountsCalculationService.calculate(aInputData,aPreviousRate);
        MortageResidual mortageResidual = residualCalculationService.calculate();

        return new Rate(aRateNumber, timePoint, rateAmounts, mortageResidual);

    }
}
