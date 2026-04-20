package aaronbearon.finals2;

import org.junit.jupiter.api.Test;

import java.util.List;

public class TestDatabase {

    @Test
    public void getFilteredCarsTest() {
        Database database = new Database();
        List<Car> cars = database.getFilteredCars(
                List.of("Ferrari"),
                List.of(),
                List.of("white"),
                0.0,
                500000.0,
                0.0,
                5.0,
                null
        );

        for (Car car : cars) {
            System.out.println(car);
        }
        assert cars.size() == 2;
    }
}
