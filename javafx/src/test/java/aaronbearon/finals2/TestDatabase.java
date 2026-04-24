package aaronbearon.finals2;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

public class TestDatabase {

    @Test
    public void getFilteredCarsTest() {
        Database database = new Database();
        List<Car> cars = database.getFilteredCars(
                Set.of("Ferrari"),
                Set.of(),
                Set.of("white"),
                0.0,
                500000.0,
                0.0,
                5.0,
                null,
                0,
                12
        );

        for (Car car : cars) {
            System.out.println(car);
        }
        assert cars.size() == 2;
    }
}
