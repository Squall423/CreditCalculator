package service;

import model.InputData;
import model.Overpayment;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

public class OverpaymentCalculationServiceImpl implements OverpaymentCalculationService {
    @Override
    public Overpayment calculate(final BigDecimal aRateNumber, final InputData aInputData) {

        BigDecimal overpaymentAmount = calculateOverpaymentAmount(aRateNumber, aInputData.getOverpaymentSchema()).orElse(BigDecimal.ZERO);
        BigDecimal overpaymentProvision = calculateOverpaymentProvision(aRateNumber, overpaymentAmount, aInputData);

        return new Overpayment(overpaymentAmount, overpaymentProvision);
    }

    private Optional<BigDecimal> calculateOverpaymentAmount(final BigDecimal aRateNumber,
                                                            Map<Integer, BigDecimal> aOverpaymentSchema) {
        for (Map.Entry<Integer, BigDecimal> entry : aOverpaymentSchema.entrySet()) {
            if (BigDecimal.valueOf(entry.getKey()).equals(aRateNumber)) {
                return Optional.of(entry.getValue());
            }
        }
        return Optional.empty();
    }

    private BigDecimal calculateOverpaymentProvision(final BigDecimal aRateNumber, final BigDecimal aOverpaymentAmount,
                                                     final  InputData aInputData) {
        if (BigDecimal.ZERO.equals(aOverpaymentAmount)) {
            return BigDecimal.ZERO;
        }
        if (aRateNumber.compareTo(aInputData.getOverpaymentProvisionMonths()) > 0) {
            return BigDecimal.ZERO;
        }
        return aOverpaymentAmount.multiply(aInputData.getOverpaymentProvisionPercent());
    }

}
