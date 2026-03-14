import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Calculate {
    public Calculate() {
    }


    public Double sum(Double firstNumber, Double secondNumber) {
        BigDecimal result = BigDecimal.valueOf(firstNumber)
                .add(BigDecimal.valueOf(secondNumber))
                .setScale(2, RoundingMode.CEILING);

        return result.doubleValue();
    }

    public Double remove(double firstNumber, double secondNumber) {
        BigDecimal result = BigDecimal.valueOf(firstNumber)
                .remainder(BigDecimal.valueOf(secondNumber))
                .setScale(2, RoundingMode.CEILING);

        return result.doubleValue();
    }

    public Double sumNumbers(List<Double> numbers) {
        return numbers.stream()
                .mapToDouble(Double::doubleValue)
                .sum();
    }
}
