package service;

import model.InputData;
import model.Overpayment;

import java.math.BigDecimal;

public interface OverpaymentCalculationService {

    Overpayment calculate(final BigDecimal aRateNumber, final InputData aInputData);
}
