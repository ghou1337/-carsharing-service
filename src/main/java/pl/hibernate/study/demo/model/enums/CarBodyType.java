package pl.hibernate.study.demo.model.enums;

public enum CarBodyType {
    ALL("All"),
    SEDAN("Sedan"),
    SUV("Suv"),
    COUPE("Coupe"),
    HATCHBACK("Hatchback"),
    COMPACT("Compact");

    private final String carBodyTypeValue;
    CarBodyType(String carBodyTypeValue) {
        this.carBodyTypeValue = carBodyTypeValue;
    }

    public String getCarBodyTypeValue() {
        return carBodyTypeValue;
    }
}
