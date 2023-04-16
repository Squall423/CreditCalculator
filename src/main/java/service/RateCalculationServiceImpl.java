package service;

import lombok.RequiredArgsConstructor;
import model.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class RateCalculationServiceImpl implements RateCalculationService {

    private final TimePointCalculationService timepointCalculationService;
    private final OverpaymentCalculationService overpaymentCalculationService;
    private final AmountsCalculationService amountsCalculationService;
    private final ResidualCalculationService residualCalculationService;
    private final ReferenceCalculationService referenceCalculationService;

    @Override
    public List<Rate> calculate(final InputData inputData) {
        List<Rate> rateList = new ArrayList<>();

        BigDecimal rateNumber = BigDecimal.ONE;

        Rate zeroRate = calculateZeroRate(rateNumber, inputData);

        Rate previousRate = zeroRate;
        rateList.add(zeroRate);

        for (BigDecimal index = rateNumber.add(BigDecimal.ONE); index.compareTo(inputData.getMonthsDuration()) <= 0; index = index.add(BigDecimal.ONE)) {
            Rate nextRate = calculateNextRate(index, inputData, previousRate);
            previousRate = nextRate;
            rateList.add(nextRate);

            if (BigDecimal.ZERO.equals(nextRate.getMortageResidual().getResidualAmount().setScale(0, RoundingMode.HALF_UP))) {
                break;
            }
        }

        return rateList;
    }

    private Rate calculateZeroRate(final BigDecimal aRateNumber,final InputData aInputData) {
        TimePoint timePoint = timepointCalculationService.calculate(aRateNumber, aInputData);
        Overpayment overpayment = overpaymentCalculationService.calculate(aRateNumber, aInputData);
        RateAmounts rateAmounts = amountsCalculationService.calculate(aInputData, overpayment);
        MortageResidual mortageResidual = residualCalculationService.calculate(rateAmounts, aInputData);
        MortageReference mortageReference = referenceCalculationService.calculate(rateAmounts,aInputData);

        return new Rate(aRateNumber, timePoint, rateAmounts, mortageResidual, mortageReference);
    }

    private Rate calculateNextRate(final BigDecimal aRateNumber, final InputData aInputData, final Rate aPreviousRate) {
        TimePoint timePoint = timepointCalculationService.calculate(aRateNumber, aPreviousRate);
        Overpayment overpayment = overpaymentCalculationService.calculate(aRateNumber, aInputData);
        RateAmounts rateAmounts = amountsCalculationService.calculate(aInputData, overpayment, aPreviousRate);
        MortageResidual mortageResidual = residualCalculationService.calculate(rateAmounts, aInputData, aPreviousRate);
        MortageReference mortageReference = referenceCalculationService.calculate( rateAmounts,aInputData, aPreviousRate);

        return new Rate(aRateNumber, timePoint, rateAmounts, mortageResidual, mortageReference);
    }
}
