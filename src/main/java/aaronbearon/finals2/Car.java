package aaronbearon.finals2;

public record Car(
        String imageName,
        String brand,
        String type,
        String color,
        double price,
        double zeroToSixty,
        boolean isElectric,
        int cylinders
) {
    public static final String[] BRANDS = {
            "Aston Martin", "BMW", "Cadillac", "Ferrari", "Genesis",
            "Hummer", "Lamborghini", "Lincoln", "Lucid", "Maserati",
            "McLaren", "Mercedes-Maybach", "Rivian", "Rolls-Royce", "Tesla"
    };

    public static final String[] TYPES = {
            "Convertible", "Pickup", "Sedan", "SUV"
    };

    public static final String[] COLORS = {
            "black", "blue", "gold", "gray", "green", "orange", "red", "white", "yellow"
    };
}
