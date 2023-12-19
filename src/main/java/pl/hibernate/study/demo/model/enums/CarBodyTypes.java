package pl.hibernate.study.demo.model.enums;

public enum CarBodyTypes {
    ALL("All"),
    SEDAN("Sedan"),
    SUV("Suv"),
    COUPE("Coupe"),
    HATCHBACK("Hatchback"),
    COMPACT("Compact");

    private final String carBodyTypeValue;
    CarBodyTypes(String carBodyTypeValue) {
        this.carBodyTypeValue = carBodyTypeValue;
    }

    public String getCarBodyTypeValue() {
        return carBodyTypeValue;
    }
}
