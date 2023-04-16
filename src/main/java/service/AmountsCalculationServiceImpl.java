package service;

import lombok.RequiredArgsConstructor;
import model.InputData;
import model.Overpayment;
import model.Rate;
import model.RateAmounts;
import model.exception.RateCalculateException;

@RequiredArgsConstructor
public class AmountsCalculationServiceImpl implements AmountsCalculationService {

    private final ConstantAmountsCalculationService constantAmountsCalculationService;
    private final DecreasingAmountsCalculationService decreasingAmountsCalculationService;

    private final String CASE_NOT_HANDLED = "Case not handled";

    @Override
    public RateAmounts calculate(final InputData aInputData, final Overpayment aOverpayment) {
        switch (aInputData.getRateType()) {
            case CONSTANT:
                return constantAmountsCalculationService.calculate(aInputData, aOverpayment);
            case DECREASING:
                return decreasingAmountsCalculationService.calculate(aInputData, aOverpayment);
            default:
                throw new RateCalculateException(CASE_NOT_HANDLED);
        }
    }

    @Override
    public RateAmounts calculate(final InputData aInputData, final Overpayment aOverpayment, final Rate aPreviousRate) {
        switch (aInputData.getRateType()) {
            case CONSTANT:
                return constantAmountsCalculationService.calculate(aInputData, aOverpayment, aPreviousRate);
            case DECREASING:
                return decreasingAmountsCalculationService.calculate(aInputData, aOverpayment, aPreviousRate);
            default:
                throw new RateCalculateException(CASE_NOT_HANDLED);
        }
    }


}
