package service;

import model.InputData;
import model.Overpayment;
import model.Rate;
import model.RateAmounts;
import model.exception.RateCalculateException;

public class AmountsCalculationServiceImpl implements AmountsCalculationService {

    private final ConstantAmountCalculationServiceImpl constantAmountCalculationService;
    private final DecreasingAmountsCalculationService decreasingAmountsCalculationService;

    public AmountsCalculationServiceImpl(ConstantAmountCalculationServiceImpl aConstantAmountCalculationService,
                                         DecreasingAmountsCalculationService aDecreasingAmountsCalculationService) {
        constantAmountCalculationService = aConstantAmountCalculationService;
        decreasingAmountsCalculationService = aDecreasingAmountsCalculationService;
    }

    private final String CASE_NOT_HANDLED = "Case not handled";

    @Override
    public RateAmounts calculate(InputData aInputData, Overpayment aOverpayment) {
        switch (aInputData.getRateType()) {
            case CONSTANT:
                return constantAmountCalculationService.calculate(aInputData, aOverpayment);
            case DECREASING:
                return decreasingAmountsCalculationService.calculate(aInputData, aOverpayment);
            default:
                throw new RateCalculateException(CASE_NOT_HANDLED);
        }
    }

    @Override
    public RateAmounts calculate(InputData aInputData, Overpayment aOverpayment, Rate aPreviousRate) {
        switch (aInputData.getRateType()) {
            case CONSTANT:
                return constantAmountCalculationService.calculate(aInputData, aOverpayment, aPreviousRate);
            case DECREASING:
                return decreasingAmountsCalculationService.calculate(aInputData, aOverpayment, aPreviousRate);
            default:
                throw new RateCalculateException(CASE_NOT_HANDLED);
        }
    }


}
