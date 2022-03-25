package service;

import model.InputData;
import model.Overpayment;
import model.Rate;
import model.RateAmounts;

public interface ConstantAmountCalculationService {
    RateAmounts calculate(InputData aInputData, Overpayment aOverpayment);

    RateAmounts calculate(InputData aInputData, Overpayment aOverpayment, Rate aPreviousRate);
}
