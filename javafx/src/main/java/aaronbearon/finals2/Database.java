package aaronbearon.finals2;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.reflect.ConstructorMapper;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Database {
    private static final Car[] CARS = new Car[]{
            new Car("BlkAstonConvertible.jpg", "Aston Martin", "Convertible", "black", 220000.0, 3.6, false, 8),
            new Car("BlkFerrariConvertible.jpg", "Ferrari", "Convertible", "black", 300000.0, 2.7, false, 12),
            new Car("BlkLambo.jpg", "Lamborghini", "Sedan", "black", 400000.0, 2.5, false, 12),
            new Car("BlkLamboConvertible.jpg", "Lamborghini", "Convertible", "black", 400000.0, 2.5, false, 12),
            new Car("BlkLincolnSUV.png", "Lincoln", "SUV", "black", 120000.0, 4.2, false, 8),
            new Car("BlkMaserati.jpg", "Maserati", "Sedan", "black", 200000.0, 3.8, false, 8),
            new Car("BlkMcLaren.jpg", "McLaren", "Sedan", "black", 365000.0, 2.5, false, 8),
            new Car("BluAston.jpg", "Aston Martin", "Sedan", "blue", 220000.0, 3.6, false, 8),
            new Car("BluFerrariConvertible.jpg", "Ferrari", "Convertible", "blue", 300000.0, 2.7, false, 12),
            new Car("BluLamboConvertible.jpg", "Lamborghini", "Convertible", "blue", 400000.0, 2.5, false, 12),
            new Car("BluMaserati.jpg", "Maserati", "Sedan", "blue", 200000.0, 3.8, false, 8),
            new Car("BluMcLaren.jpg", "McLaren", "Sedan", "blue", 365000.0, 2.5, false, 8),
            new Car("BlueCadillacSUVev.png", "Cadillac", "SUV", "blue", 100000.0, 3.5, true, 0),
            new Car("BlueHummerSUVev.png", "Hummer", "SUV", "blue", 180000.0, 2.3, true, 0),
            new Car("BlueLucidEv.png", "Lucid", "Sedan", "blue", 150000.0, 2.3, true, 0),
            new Car("BlueRivianSUVev.png", "Rivian", "SUV", "blue", 80000.0, 2.3, true, 0),
            new Car("GoldBMWev.png", "BMW", "Sedan", "gold", 250000.0, 2.3, true, 0),
            new Car("GoldRollsRoyceEV.png", "Rolls-Royce", "Sedan", "gold", 500000.0, 3.0, true, 0),
            new Car("GrayCyberTruckEV.png", "Tesla", "Pickup", "gray", 50000.0, 2.3, true, 0),
            new Car("GrayHummerPicupEV.png", "Hummer", "Pickup", "gray", 180000.0, 2.3, true, 0),
            new Car("GrayLincolnSUV.png", "Lincoln", "SUV", "gray", 120000.0, 4.2, false, 8),
            new Car("GreenAston.jpg", "Aston Martin", "Sedan", "green", 220000.0, 3.6, false, 8),
            new Car("GreenLambo.jpg", "Lamborghini", "Sedan", "green", 400000.0, 2.5, false, 12),
            new Car("GreenMcLaren.jpg", "McLaren", "Sedan", "green", 365000.0, 2.5, false, 8),
            new Car("OranMcLarConvertible.jpg", "McLaren", "Convertible", "orange", 365000.0, 2.5, false, 8),
            new Car("OrangeHummerPickupEV.png", "Hummer", "Pickup", "orange", 180000.0, 2.3, true, 0),
            new Car("RedAstonConvertible.jpg", "Aston Martin", "Convertible", "red", 220000.0, 3.6, false, 8),
            new Car("RedCadillacSUVev.png", "Cadillac", "SUV", "red", 100000.0, 3.5, true, 0),
            new Car("RedFerrari.jpg", "Ferrari", "Sedan", "red", 300000.0, 2.7, false, 12),
            new Car("RedLucidEV.png", "Lucid", "Sedan", "red", 150000.0, 2.3, true, 0),
            new Car("RedMaserati.jpg", "Maserati", "Sedan", "red", 200000.0, 3.8, false, 8),
            new Car("RedMcLaren.jpg", "McLaren", "Sedan", "red", 365000.0, 2.5, false, 8),
            new Car("RedMercedesMaybachEV.png", "Mercedes-Maybach", "Sedan", "red", 400000.0, 2.3, true, 0),
            new Car("RedRivianSUVev.png", "Rivian", "SUV", "red", 80000.0, 2.3, true, 0),
            new Car("WhiteFerrConvertible.jpg", "Ferrari", "Convertible", "white", 300000.0, 2.7, false, 12),
            new Car("WhiteFerrari.jpg", "Ferrari", "Sedan", "white", 300000.0, 2.7, false, 12),
            new Car("WhiteGenesisConvertibleEV.png", "Genesis", "Convertible", "white", 300000.0, 2.3, true, 0),
            new Car("WhiteHummerPickupEV.png", "Hummer", "Pickup", "white", 180000.0, 2.3, true, 0),
            new Car("WhiteHummerSUVev.png", "Hummer", "SUV", "white", 180000.0, 2.3, true, 0),
            new Car("WhiteLambo.jpg", "Lamborghini", "Sedan", "white", 400000.0, 2.5, false, 12),
            new Car("WhiteLucidEV.png", "Lucid", "Sedan", "white", 150000.0, 2.3, true, 0),
            new Car("WhiteMaserConvertible.jpg", "Maserati", "Convertible", "white", 200000.0, 3.8, false, 8),
            new Car("YellowFerrari.jpg", "Ferrari", "Sedan", "yellow", 300000.0, 2.7, false, 12),
            new Car("YellowLambo.jpg", "Lamborghini", "Sedan", "yellow", 400000.0, 2.5, false, 12),
            new Car("YellowMcLaren.jpg", "McLaren", "Sedan", "yellow", 365000.0, 2.5, false, 8)
    };

    private static final List<Car> allCars = Arrays.asList(CARS);

//    private final Jdbi jdbi;
//
//    public Database() {
//        String path;
//        try {
//            URL resource = getClass().getResource("/aaronbearon/finals2/cars.csv");
//            assert (resource != null);
//            path = Path.of(resource.toURI()).toString().replace('\\', '/');
//        } catch (URISyntaxException e) {
//            throw new AssertionError(e); // should not happen
//        }
//
//        jdbi = Jdbi.create("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1", "sa", "");
//        jdbi.registerRowMapper(Car.class, ConstructorMapper.of(Car.class));
//        jdbi.withHandle(handle -> {
//            handle.execute("CREATE TABLE cars AS SELECT * FROM CSVREAD('" + path + "');");
//            return null;
//        });
//    }

    // Query the database for a list of cars matching the selected filters.
    public List<Car> getFilteredCars(Set<String> selectedBrands,
                                     Set<String> selectedTypes,
                                     Set<String> selectedColors,
                                     double priceMin, double priceMax,
                                     double timeMin, double timeMax,
                                     Boolean isElectric,
                                     int cylinderMin, int cylinderMax) {

        return allCars.stream()
                // Filter by Brand (If set is empty, include all)
                .filter(car -> selectedBrands.isEmpty() || selectedBrands.contains(car.brand()))

                // Filter by Type
                .filter(car -> selectedTypes.isEmpty() || selectedTypes.contains(car.type()))

                // Filter by Color
                .filter(car -> selectedColors.isEmpty() || selectedColors.contains(car.color()))

                // Filter by Price Range
                .filter(car -> car.price() >= priceMin && car.price() <= priceMax)

                // Filter by Performance Range
                .filter(car -> car.zeroToSixty() >= timeMin && car.zeroToSixty() <= timeMax)

                // Filter by Power Train (Handles 'Both' when isElectric is null)
                .filter(car -> isElectric == null || car.isElectric() == isElectric)

                // Filter by Cylinder Range
                .filter(car -> car.cylinders() >= cylinderMin && car.cylinders() <= cylinderMax)

                .collect(Collectors.toList());
    }
}
