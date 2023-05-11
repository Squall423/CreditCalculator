package service;

import lombok.extern.slf4j.Slf4j;
import model.InputData;
import model.Overpayment;
import model.Rate;
import model.RateAmounts;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
public class DecreasingAmountsCalculationServiceImpl implements DecreasingAmountsCalculationService {
    @Override
    public RateAmounts calculate(final InputData aInputData, final Overpayment aOverpayment) {
        BigDecimal interestPercent = aInputData.getInterestPercent();
        log.info("InterestPercent: [{}]", interestPercent);

       final BigDecimal residualAmount = aInputData.getAmount();
        log.info("ResidualAmount: [{}]", residualAmount);
       final BigDecimal residualDuration = aInputData.getMonthsDuration();
        log.info("ResidualDuration: [{}]", residualDuration);

        BigDecimal interestAmount = AmountsCalculationService.calculateInterestAmount(residualAmount, interestPercent);
        log.info("InterestAmount: [{}]", interestAmount);
        BigDecimal capitalAmount = AmountsCalculationService.compareCapitalWithResidual(calculateDecreasingCapitalAmount(residualAmount, residualDuration), residualAmount);
        log.info("CapitalAmount: [{}]", capitalAmount);
        BigDecimal rateAmount = capitalAmount.add(interestAmount);
        log.info("RateAmount: [{}]", rateAmount);

        return new RateAmounts(rateAmount, interestAmount, capitalAmount, aOverpayment);
    }

    @Override
    public RateAmounts calculate(final InputData aInputData, final Overpayment aOverpayment, final Rate aPreviousRate) {
        BigDecimal interestPercent = aInputData.getInterestPercent();
        log.info("InterestPercent: [{}]", interestPercent);
        BigDecimal residualAmount = aPreviousRate.getMortageResidual().getResidualAmount();
        log.info("ResidualAmount: [{}]", residualAmount);
        BigDecimal referenceAmount = aPreviousRate.getMortageReference().getReferenceAmount();
        log.info("ReferenceAmount: [{}]", referenceAmount);
        BigDecimal referenceDuration = aPreviousRate.getMortageReference().getReferenceDuration();
        log.info("ReferenceDuration: [{}]", referenceDuration);

        BigDecimal interestAmount = AmountsCalculationService.calculateInterestAmount(residualAmount, interestPercent);
        log.info("InterestAmount: [{}]", interestAmount);
        BigDecimal capitalAmount = AmountsCalculationService.compareCapitalWithResidual(calculateDecreasingCapitalAmount(referenceAmount, referenceDuration), residualAmount);
        log.info("CapitalAmount: [{}]", capitalAmount);
        BigDecimal rateAmount = capitalAmount.add(interestAmount);
        log.info("RateAmount: [{}]", rateAmount);

        return new RateAmounts(rateAmount, interestAmount, capitalAmount, aOverpayment);
    }

    private BigDecimal calculateDecreasingCapitalAmount(final BigDecimal residualAmount, final BigDecimal residualDuration) {
        return residualAmount.divide(residualDuration, 2, RoundingMode.HALF_UP);
    }
}
