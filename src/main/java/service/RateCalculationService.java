package service;

import model.Rate;
import model.InputData;

import java.util.List;

public interface RateCalculationService {

    List<Rate> calculate(final InputData inputData);
}
