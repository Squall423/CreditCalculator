import model.InputData;
import model.MortageResidual;
import model.RateAmounts;
import service.*;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        InputData inputData = new InputData()
                .withAmount(new BigDecimal("2988008"))
                .withMonthsDuration(BigDecimal.valueOf(168));

        PrintingService printingService = new PrintingServiceImpl();

        RateCalculationService rateCalculationService = new RateCalculationServiceImpl(
                new TimePointsServiceImpl(),
                new AmountsCalculationServiceImpl(),
                new ResidualCalculationServiceImpl());
        MortageCalculationService mortageCalculationService = new MortageCalculationServiceImpl(printingService,
                rateCalculationService);
        mortageCalculationService.calculate(inputData);

    }
}
