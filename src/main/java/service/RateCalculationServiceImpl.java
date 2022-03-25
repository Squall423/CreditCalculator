package service;

import model.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;

public class RateCalculationServiceImpl implements RateCalculationService {

    private final TimePointService timepointService;
    private final AmountsCalculationService amountsCalculationService;
    private final OverpaymentCalculationService overpaymentCalculationService;
    private final ResidualCalculationService residualCalculationService;
    private final ReferenceCalculationService referenceCalculationService;

    public RateCalculationServiceImpl(TimePointService aTimepointService,
                                      AmountsCalculationService aAmountsCalculationService,
                                      OverpaymentCalculationService aOverpaymentCalculationService,
                                      ResidualCalculationService aResidualCalculationService,
                                      ReferenceCalculationService aReferenceCalculationService) {

        timepointService = aTimepointService;
        amountsCalculationService = aAmountsCalculationService;
        overpaymentCalculationService = aOverpaymentCalculationService;
        residualCalculationService = aResidualCalculationService;
        referenceCalculationService = aReferenceCalculationService;
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

            if (mortageFinished(nextRate)) {
                break;
            }
        }
        return rates;
    }

    private boolean mortageFinished(Rate nextRate) {
        BigDecimal toCompare = nextRate.getMortageResidual().getAmount().setScale(0, RoundingMode.HALF_UP);
        return BigDecimal.ZERO.equals(toCompare);
    }

    private Rate calculateRate(BigDecimal aRateNumber, InputData aInputData) {
        TimePoint timePoint = timepointService.calculate(aRateNumber, aInputData);
        Overpayment overpayment = overpaymentCalculationService.calculate(aRateNumber, aInputData);
        RateAmounts rateAmounts = amountsCalculationService.calculate(aInputData, overpayment);
        MortageResidual mortageResidual = residualCalculationService.calculate(rateAmounts, aInputData);
        MortageReference mortageReference = referenceCalculationService.calculate(aInputData);

        return new Rate(aRateNumber, timePoint, rateAmounts, mortageResidual, mortageReference);

    }

    private Rate calculateRate(BigDecimal aRateNumber, InputData aInputData, Rate aPreviousRate) {
        TimePoint timePoint = timepointService.calculate(aRateNumber, aInputData);
        Overpayment overpayment = overpaymentCalculationService.calculate(aRateNumber, aInputData);
        RateAmounts rateAmounts = amountsCalculationService.calculate(aInputData, overpayment, aPreviousRate);
        MortageResidual mortageResidual = residualCalculationService.calculate(rateAmounts, aPreviousRate);
        MortageReference mortageReference = referenceCalculationService.calculate(aInputData, rateAmounts,
                aPreviousRate);

        return new Rate(aRateNumber, timePoint, rateAmounts, mortageResidual, mortageReference);

    }
}
