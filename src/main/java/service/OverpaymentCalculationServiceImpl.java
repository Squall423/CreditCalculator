package service;

import model.InputData;
import model.Overpayment;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

public class OverpaymentCalculationServiceImpl implements OverpaymentCalculationService {
    @Override
    public Overpayment calculate(BigDecimal aRateNumber, InputData aInputData) {

        BigDecimal overpaymentAmount =
                calculateAmount(aRateNumber, aInputData.getOverpaymentSchema()).orElse(BigDecimal.ZERO);
        BigDecimal overpaymentProvision = calculateProvision(aRateNumber, overpaymentAmount, aInputData);

        return new Overpayment(overpaymentAmount, overpaymentProvision);
    }

    private Optional<BigDecimal> calculateAmount(BigDecimal aRateNumber, Map<Integer, BigDecimal> aOverpaymentSchema) {
        for (Map.Entry<Integer, BigDecimal> entry : aOverpaymentSchema.entrySet()) {
            if (aRateNumber.equals(BigDecimal.valueOf(entry.getKey()))) {
                return Optional.of(entry.getValue());
            }
        }
        return Optional.empty();
    }

    private BigDecimal calculateProvision(BigDecimal aRateNumber, BigDecimal aOverpaymentAmount, InputData aInputData) {
        if (BigDecimal.ZERO.equals(aOverpaymentAmount)) {
            return BigDecimal.ZERO;
        }
        if (aRateNumber.compareTo(aInputData.getOverpaymentProvisionMonths()) > 0) {
            return BigDecimal.ZERO;
        }
        return aOverpaymentAmount.multiply(aInputData.getOverpaymentProvisionPercent());
    }

}
