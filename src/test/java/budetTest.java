import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class budetTest {

    @Test
    void should_return_2_when_using_adding_between_two_numbers_1_1() {
        //When
        Double execute = new Calculate().sum(1.0, 1.0);

        //then
        Assertions.assertEquals(2.00,execute);
    }

    @Test
    void should_return_0_when_using_romoving_between_two_numbers_1_1() {
        //When
        Double execute = new Calculate().remove(1.0, 1.0);

        //then
        Assertions.assertEquals(0,execute);
    }

    @Test
    void should_calculate_sum_and_remove_of_10_numbers_1_2_3_4_5_6_7_8_9_10() {
        List<Double> integers = List.of(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0);
        //When
        Double execute = new Calculate().sumNumbers(integers);

        //then
        Assertions.assertEquals(55,execute);
    }
}
