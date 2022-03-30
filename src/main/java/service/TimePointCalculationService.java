package service;

import model.InputData;
import model.Rate;
import model.TimePoint;

import java.math.BigDecimal;

public interface TimePointCalculationService {

    TimePoint calculate(final BigDecimal aRateNumber, final InputData aInputData);

    TimePoint calculate(BigDecimal aRateNumber, Rate aPreviousRate);
}
