import model.InputData;
import model.MortageType;
import model.Overpayment;
import service.*;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

public class Main {


    public static void main(String[] args) {

        Map<Integer, BigDecimal> overpaymentSchema = new TreeMap<>();
        overpaymentSchema.put(5, BigDecimal.valueOf(12000));
        overpaymentSchema.put(19, BigDecimal.valueOf(10000));
        overpaymentSchema.put(28, BigDecimal.valueOf(11000));
        overpaymentSchema.put(64, BigDecimal.valueOf(16000));
        overpaymentSchema.put(78, BigDecimal.valueOf(18000));


        InputData defaultInputData = new InputData()
                .withAmount(new BigDecimal("296192.11"))
                .withMonthsDuration(BigDecimal.valueOf(360))
                .withOverpaymentReduceWay(Overpayment.REDUCE_PERIOD)
                .withType(MortageType.DECREASING)
                .withOverpaymentSchema(overpaymentSchema);


        PrintingService printingService = new PrintingServiceImpl();
        RateCalculationService rateCalculationService = new RateCalculationServiceImpl(
                new TimePointCalculationServiceImpl(),
                new OverpaymentCalculationServiceImpl(),
                new AmountsCalculationServiceImpl(
                        new ConstantAmountsCalculationServiceImpl(),
                        new DecreasingAmountsCalculationServiceImpl()
                ),
                new ResidualCalculationServiceImpl(),
                new ReferenceCalculationServiceImpl()
        );

        MortageCalculationService mortageCalculationService = new MortageCalculationServiceImpl(
                rateCalculationService,
                printingService,
                SummaryServiceFactory.create());
        mortageCalculationService.calculate(defaultInputData);


    }
}
