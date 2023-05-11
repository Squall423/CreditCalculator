package service;

import lombok.RequiredArgsConstructor;



import lombok.extern.slf4j.Slf4j;
import model.InputData;
import model.Overpayment;
import model.Rate;
import model.RateAmounts;
import service.exception.MortageException;

@Slf4j
@RequiredArgsConstructor
public class AmountsCalculationServiceImpl implements AmountsCalculationService {

    private final ConstantAmountsCalculationService constantAmountsCalculationService;
    private final DecreasingAmountsCalculationService decreasingAmountsCalculationService;

    @Override
    public RateAmounts calculate(final InputData aInputData, final Overpayment aOverpayment) {
        switch (aInputData.getRateType()) {
            case CONSTANT:
                return constantAmountsCalculationService.calculate(aInputData, aOverpayment);
            case DECREASING:
                return decreasingAmountsCalculationService.calculate(aInputData, aOverpayment);
            default:
                log.error("Case not handled: [{}]", aInputData.getRateType());
                throw new MortageException("Case not handled");
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
                log.error("Case not handled: [{}]", aInputData.getRateType());
                throw new MortageException("Case not handled");
        }
    }


}
