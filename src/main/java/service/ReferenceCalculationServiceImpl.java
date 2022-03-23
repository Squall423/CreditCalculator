package service;

import model.InputData;
import model.MortageReference;
import model.Rate;

import java.util.List;

public class ReferenceCalculationServiceImpl implements ReferenceCalculationService {


    @Override
    public MortageReference calculate(InputData aInputData) {
        return new MortageReference(aInputData.getAmount(), aInputData.getMonthsDuration());
    }
}
